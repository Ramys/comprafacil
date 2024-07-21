package br.com.leaf.apigateway.config;

import br.com.leaf.apigateway.exceptions.UnauthorizedException;
import br.com.leaf.apigateway.filter.RouteValidator;
import br.com.leaf.apigateway.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    private final RouteValidator routeValidator;

    private final JwtUtil jwtUtil;

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            ServerHttpRequest request = null;
            if (routeValidator.isSecured.test(exchange.getRequest())) {
                if (!exchange.getRequest().getHeaders().containsKey("Authorization")) {
                    exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                    return exchange.getResponse().setComplete();
                }

                String token = exchange.getRequest().getHeaders().get("Authorization").get(0);

                if (token != null && token.startsWith("Bearer ")) {
                    token = token.substring(7);
                }

                try {
                    jwtUtil.validateToken(token);

                    var role = jwtUtil.extractRole(token);

                    if (role.equals("CUSTOMER")) {
                        var cartId = jwtUtil.extractCustId(token);
                        var userId = jwtUtil.extractUserId(token);
                        request = exchange.getRequest()
                                .mutate()
                                .header("cartId", cartId)
                                .header("userId", userId.toString())
                                .build();
                    }

                    String requiredRole = routeValidator.getRequiredRole(exchange.getRequest());

                    if (requiredRole != null && !requiredRole.equals(role)) {
                        throw new UnauthorizedException(HttpStatus.UNAUTHORIZED.value(), "Unauthorized");
                    }

                } catch (Exception e) {
                    System.out.println("Error in AuthenticationFilter: " + e.getMessage());
                    throw new UnauthorizedException(HttpStatus.UNAUTHORIZED.value(), "Unauthorized");
                }
            }
            return chain.filter(exchange.mutate().request(request).build());
        });
    }

    public static class Config {

    }
}

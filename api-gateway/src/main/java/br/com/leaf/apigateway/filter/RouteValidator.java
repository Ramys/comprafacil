package br.com.leaf.apigateway.filter;

import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
public class RouteValidator {

    public static final List<String> openApiEndpoints = List.of("/cadastro-usuario/registrar-usuarios",
                                                                "/cadastro-usuario/logar",
                                                                "/eureka");

    public Predicate<ServerHttpRequest> isSecured = request -> openApiEndpoints
                                                                            .stream()
                                                                            .noneMatch(uri -> request.getURI().getPath().contains(uri));

    public String getRequiredRole(ServerHttpRequest request) {
        String path = request.getPath().toString();

        if (request.getMethod() == HttpMethod.POST && path.startsWith("/gestao")) {
            return "ADMIN";
        } else if (request.getMethod() == HttpMethod.PUT && path.matches("/gestao/\\d+")) {
            return "ADMIN";
        } else if (request.getMethod() == HttpMethod.DELETE && path.matches("/gestao/\\d+")) {
            return "ADMIN";
        }
        return null;
    }

}

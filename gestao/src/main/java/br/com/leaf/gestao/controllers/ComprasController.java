package br.com.leaf.gestao.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ComprasController {

    @GetMapping("/oi")
    public String teste() {
        return "oi";
    }

}

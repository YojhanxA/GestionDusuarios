package com.gestion.usuarios.gestion_de_usuarios_alcaldia.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingController {

    @GetMapping("/")
    public String ping() {
        return "Backend activo";
    }
}

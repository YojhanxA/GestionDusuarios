package com.gestion.usuarios.gestion_de_usuarios_alcaldia.Controller;

import org.springframework.web.bind.annotation.*;

import com.gestion.usuarios.gestion_de_usuarios_alcaldia.entities.Solicitud;
import com.gestion.usuarios.gestion_de_usuarios_alcaldia.repository.SolicitudRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/solicitudes")
@CrossOrigin(origins = "*")
public class SolicitudController {

    @Autowired
    private SolicitudRepository repo;

    @GetMapping
    public List<Solicitud> getAll(@RequestParam(required = false) Boolean estado) {
        return (estado != null)
                ? repo.findByCuentaCreada(estado)
                : repo.findAll();
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Solicitud solicitud) {
        // Verifica si ya existe una solicitud con la misma cédula
        Optional<Solicitud> existente = repo.findByCedula(solicitud.getCedula());
        if (existente.isPresent()) {
            return ResponseEntity.badRequest().body("Ya existe una solicitud con esa cédula");
        }

        if (Boolean.TRUE.equals(solicitud.isVinculado())) {
            solicitud.setFechaInicioContrato(null);
            solicitud.setFechaFinContrato(null);
        }

        Solicitud nuevaSolicitud = repo.save(solicitud);
        return ResponseEntity.ok(nuevaSolicitud);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Solicitud> marcarCreada(@PathVariable Long id) {
        Optional<Solicitud> opt = repo.findById(id);
        if (opt.isPresent()) {
            Solicitud s = opt.get();
            s.setCuentaCreada(true);
            return ResponseEntity.ok(repo.save(s));
        }
        return ResponseEntity.notFound().build();
    }
}

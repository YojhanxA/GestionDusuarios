package com.gestion.usuarios.gestion_de_usuarios_alcaldia.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestion.usuarios.gestion_de_usuarios_alcaldia.entities.Solicitud;

public interface SolicitudRepository extends JpaRepository<Solicitud, Long> {
    List<Solicitud> findByCuentaCreada(boolean estado);
    Optional<Solicitud> findByCedula(String cedula);

}

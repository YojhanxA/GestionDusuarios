package com.gestion.usuarios.gestion_de_usuarios_alcaldia.entities;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "solicitudes")
public class Solicitud {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String apellido;
    private String cedula;
    private String correo;
    private String secretaria;
    private String subsecretaria;

    // Fechas de contrato, pueden ser nulas si es vinculado sin fechas
    private LocalDate fechaInicioContrato;
    private LocalDate fechaFinContrato;

    // Indica si el usuario está vinculado sin fechas
    private boolean vinculado;

    // Estado de creación de cuenta
    private boolean cuentaCreada = false;

    public Solicitud() {
        
    }

    public Solicitud(String nombre, String apellido, String cedula, String correo, String secretaria,
            String subsecretaria,
            LocalDate fechaInicioContrato, LocalDate fechaFinContrato, boolean vinculado) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.correo = correo;
        this.secretaria = secretaria;
        this.subsecretaria = subsecretaria;
        this.fechaInicioContrato = fechaInicioContrato;
        this.fechaFinContrato = fechaFinContrato;
        this.vinculado = vinculado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getSecretaria() {
        return secretaria;
    }

    public void setSecretaria(String secretaria) {
        this.secretaria = secretaria;
    }

    public String getSubsecretaria() {
        return subsecretaria;
    }

    public void setSubsecretaria(String subsecretaria) {
        this.subsecretaria = subsecretaria;
    }

    public LocalDate getFechaInicioContrato() {
        return fechaInicioContrato;
    }

    public void setFechaInicioContrato(LocalDate fechaInicioContrato) {
        this.fechaInicioContrato = fechaInicioContrato;
    }

    public LocalDate getFechaFinContrato() {
        return fechaFinContrato;
    }

    public void setFechaFinContrato(LocalDate fechaFinContrato) {
        this.fechaFinContrato = fechaFinContrato;
    }

    public boolean isVinculado() {
        return vinculado;
    }

    public void setVinculado(boolean vinculado) {
        this.vinculado = vinculado;
    }

    public boolean isCuentaCreada() {
        return cuentaCreada;
    }

    public void setCuentaCreada(boolean cuentaCreada) {
        this.cuentaCreada = cuentaCreada;
    }

    @Override
    public String toString() {
        return "Solicitud{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", cedula='" + cedula + '\'' +
                ", correo='" + correo + '\'' +
                ", secretaria='" + secretaria + '\'' +
                ", subsecretaria='" + subsecretaria + '\'' +
                ", fechaInicioContrato=" + fechaInicioContrato +
                ", fechaFinContrato=" + fechaFinContrato +
                ", vinculado=" + vinculado +
                ", cuentaCreada=" + cuentaCreada +
                '}';
    }

}

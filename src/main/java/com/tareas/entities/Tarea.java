package com.tareas.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;
@Entity
@Table(name="Tarea")
public class Tarea {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name ="nombre")
    private String name;
    @Column(name ="descripcion")
    private String descripcion;
    @Column(name ="fecha")
    private LocalDate fecha;
    @Column(name ="estado")
    private Boolean estado;

    public Tarea(){}

    public Tarea( String name, String descripcion, LocalDate fecha, Boolean estado) {
        this.name = name;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Tarea{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", fecha=" + fecha +
                ", estado=" + estado +
                '}';
    }
}

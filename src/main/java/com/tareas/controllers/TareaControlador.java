package com.tareas.controllers;

import com.tareas.entities.Tarea;
import com.tareas.services.TareaServicios;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tareas")
public class TareaControlador {

    private final TareaServicios tareaServicios;

    public TareaControlador(TareaServicios tareaServicios){
        this.tareaServicios = tareaServicios;
    }

    @GetMapping // Obtener todas las tareas
    public List<Tarea> getAllTareas(){
        return this.tareaServicios.getAllTarea();
    }

    @PostMapping // Crear una nueva tarea
    public Tarea createTarea(@RequestBody Tarea tarea) {
        return this.tareaServicios.saveTarea(tarea);
    }

    @PutMapping("/{id}") // Actualizar una tarea existente
    public Tarea updateTarea(@PathVariable Long id, @RequestBody Tarea tarea) {
        return this.tareaServicios.updateTarea(id, tarea);
    }

    @DeleteMapping("/{id}") // Eliminar una tarea
    public void deleteTarea(@PathVariable Long id) {
        this.tareaServicios.deleteTarea(id);
    }
}
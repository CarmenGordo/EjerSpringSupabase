package com.tareas.services;

import com.tareas.entities.Tarea;
import com.tareas.repositories.TareaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TareaServicios {

    private final TareaRepository tareaRepository;

    public TareaServicios(TareaRepository tareaRepository) {
        this.tareaRepository = tareaRepository;
    }

    public List<Tarea> getAllTarea() {
        return tareaRepository.findAll();
    }

    public Tarea saveTarea(Tarea tarea) {
        return tareaRepository.save(tarea);
    }

    public Tarea updateTarea(Long id, Tarea tarea) {
        Optional<Tarea> optionalTarea = tareaRepository.findById(id);
        if (optionalTarea.isPresent()) {
            Tarea existingTarea = optionalTarea.get();
            existingTarea.setName(tarea.getName());

            // Validar y establecer descripcion
            if (!(tarea.getDescripcion() instanceof String)) {
                throw new IllegalArgumentException("La descripción debe ser un String");
            }
            existingTarea.setDescripcion(tarea.getDescripcion());

            // Establecer fecha
            existingTarea.setFecha(tarea.getFecha());

            // Establecer estado
            existingTarea.setEstado(tarea.getEstado());

            return tareaRepository.save(existingTarea);
        } else {
            throw new RuntimeException("Tarea no encontrada con id " + id);
        }
    }

    public void deleteTarea(Long id) {
        tareaRepository.deleteById(id);
    }
}
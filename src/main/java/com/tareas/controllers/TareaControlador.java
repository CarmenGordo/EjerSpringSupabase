package com.tareas.controllers;

import com.tareas.entities.Tarea;
import com.tareas.services.TareaServicios;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<Tarea> createTarea(@Valid @RequestBody TareaDTO tareaDTO) {
        Tarea nuevaTarea = new Tarea(tareaDTO.getName(), tareaDTO.getDescripcion(), tareaDTO.getFecha(), tareaDTO.getEstado());
        nuevaTarea = this.tareaServicios.saveTarea(nuevaTarea);
        return new ResponseEntity<>(nuevaTarea, HttpStatus.CREATED);
    }

    @PutMapping("/{id}") // Actualizar una tarea existente
    public ResponseEntity<Tarea> updateTarea(@PathVariable Long id, @Valid @RequestBody TareaDTO tareaDTO) {
        Tarea tareaActualizada = new Tarea(tareaDTO.getName(), tareaDTO.getDescripcion(), tareaDTO.getFecha(), tareaDTO.getEstado());
        tareaActualizada.setId(id);
        tareaActualizada = this.tareaServicios.updateTarea(id, tareaActualizada);
        return new ResponseEntity<>(tareaActualizada, HttpStatus.OK);
    }

    @DeleteMapping("/{id}") // Eliminar una tarea
    public ResponseEntity<String> deleteTarea(@PathVariable Long id) {
        try {
            this.tareaServicios.deleteTarea(id);
            return new ResponseEntity<>("Tarea eliminada con éxito", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("Error al eliminar la tarea: " + e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar la tarea: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("message", "Campos incompletos o tipo de dato incorrecto");
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("message", "Tipo de dato incorrecto para uno o más campos");
        return errors;
    }

    // DTO de Tarea para validaciones
    public static class TareaDTO {
        @NotBlank(message = "El nombre es obligatorio")
        @Size(min = 1, max = 100, message = "El nombre debe tener entre 1 y 100 caracteres")
        @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$", message = "El nombre solo puede contener letras y espacios")
        private String name;

        @NotBlank(message = "La descripción es obligatoria")
        @Size(min = 1, max = 255, message = "La descripción debe tener entre 1 y 255 caracteres")
        private String descripcion;

        @NotNull(message = "La fecha es obligatoria")
        private LocalDate fecha;

        @NotNull(message = "El estado es obligatorio")
        private Boolean estado;

        public TareaDTO() {}

        public TareaDTO(String name, String descripcion, LocalDate fecha, Boolean estado) {
            this.name = name;
            this.descripcion = descripcion;
            this.fecha = fecha;
            this.estado = estado;
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
    }
}

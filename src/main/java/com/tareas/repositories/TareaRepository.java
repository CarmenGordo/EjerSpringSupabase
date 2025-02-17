package com.tareas.repositories;

import com.tareas.entities.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TareaRepository extends JpaRepository<Tarea,Long> {

    //Metodos heredados de JpaRepository

}
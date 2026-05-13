package es.ediae.master.programacion.gestionusuario.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.ediae.master.programacion.gestionusuario.entity.PuestoDeTrabajoEntity;

@Repository
public interface PuestoDeTrabajoRepository extends JpaRepository<PuestoDeTrabajoEntity, Integer> {

    // Método para buscar un puesto de trabajo por su nombre (ignorando mayúsculas/minúsculas)
    Optional<PuestoDeTrabajoEntity> findByNombreIgnoreCase(String nombre);
}

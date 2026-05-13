package es.ediae.master.programacion.gestionusuario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.ediae.master.programacion.gestionusuario.entity.ImagenUsuarioEntity;

@Repository
public interface ImagenUsuarioRepository extends JpaRepository<ImagenUsuarioEntity, Integer> {

}

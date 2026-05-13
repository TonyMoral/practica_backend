package es.ediae.master.programacion.gestionusuario.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.ediae.master.programacion.gestionusuario.entity.UsuarioEntity;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {

    // Método para buscar un usuario por su nick y contraseña, necesario para iniciar sesión
    Optional<UsuarioEntity> findByNickUsuarioAndContrasena (String nickUsuario, String contrasena);

    // Método para buscar un usuario por su nick, 
    // necesario para validar que no se repitan nicks al crear o actualizar usuarios
    Optional<UsuarioEntity> findByNickUsuario(String nickUsuario);

}

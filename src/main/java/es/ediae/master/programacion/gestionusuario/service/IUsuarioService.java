package es.ediae.master.programacion.gestionusuario.service;

import java.util.List;


import es.ediae.master.programacion.gestionusuario.dto.UsuarioRequestDTO;
import es.ediae.master.programacion.gestionusuario.dto.UsuarioResponseDTO;


public interface IUsuarioService {

    Boolean iniciarSesion(String nickUsuario, String contrasena);

    List<UsuarioResponseDTO> obtenerUsuarios();

    UsuarioResponseDTO obtenerUsuario(Integer id);

    UsuarioResponseDTO crearUsuario(UsuarioRequestDTO usuarioDTO);

    UsuarioResponseDTO actualizarUsuario(Integer id, UsuarioRequestDTO usuarioDTO);

    void eliminarUsuario(Integer id);
}

package es.ediae.master.programacion.gestionusuario.service;

import java.util.List;

import es.ediae.master.programacion.gestionusuario.dto.DireccionRequestDTO;
import es.ediae.master.programacion.gestionusuario.dto.DireccionResponseDTO;


public interface IDireccionService {

public List<DireccionResponseDTO> obtenerDirecciones(Integer usuarioId);
public DireccionResponseDTO obtenerDireccion(Integer id);
public DireccionResponseDTO crearDireccion(DireccionRequestDTO direccionDto);
public DireccionResponseDTO actualizarDireccion(Integer id, DireccionRequestDTO direccionDto);
public void eliminarDireccion(Integer id); 

}

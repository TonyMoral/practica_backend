package es.ediae.master.programacion.gestionusuario.service;

import java.util.List;

import es.ediae.master.programacion.gestionusuario.dto.GeneroRequestDTO;
import es.ediae.master.programacion.gestionusuario.dto.GeneroResponseDTO;

public interface IGeneroService {

    List<GeneroResponseDTO> obtenerGeneros();
    GeneroResponseDTO obtenerGenero(Integer id);
    GeneroResponseDTO crearGenero(GeneroRequestDTO generoRequestDTO);
    GeneroResponseDTO actualizarGenero(Integer id, GeneroRequestDTO generoRequestDTO);
    void eliminarGenero(Integer id);

}

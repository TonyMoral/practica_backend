package es.ediae.master.programacion.gestionusuario.service;

import java.util.List;

import es.ediae.master.programacion.gestionusuario.dto.PuestoDeTrabajoRequestDTO;
import es.ediae.master.programacion.gestionusuario.dto.PuestoDeTrabajoResponseDTO;

public interface IPuestoDeTrabajoService {

    List<PuestoDeTrabajoResponseDTO> obtenerPuestosDeTrabajo();
    PuestoDeTrabajoResponseDTO obtenerPuestoDeTrabajo(Integer id);
    PuestoDeTrabajoResponseDTO crearPuestoDeTrabajo(PuestoDeTrabajoRequestDTO puestoDeTrabajoRequestDTO);
    PuestoDeTrabajoResponseDTO actualizarPuestoDeTrabajo(Integer id, PuestoDeTrabajoRequestDTO puestoDeTrabajoRequestDTO);
    void eliminarPuestoDeTrabajo(Integer id);

}

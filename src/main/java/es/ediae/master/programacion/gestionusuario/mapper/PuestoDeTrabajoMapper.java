package es.ediae.master.programacion.gestionusuario.mapper;

import org.springframework.stereotype.Component;

import es.ediae.master.programacion.gestionusuario.dto.PuestoDeTrabajoRequestDTO;
import es.ediae.master.programacion.gestionusuario.dto.PuestoDeTrabajoResponseDTO;
import es.ediae.master.programacion.gestionusuario.entity.PuestoDeTrabajoEntity;

@Component
public class PuestoDeTrabajoMapper {

    public PuestoDeTrabajoResponseDTO convertirADTO(PuestoDeTrabajoEntity entity) {

        PuestoDeTrabajoResponseDTO dto = new PuestoDeTrabajoResponseDTO();
        dto.setId(entity.getId());
        dto.setNombre(entity.getNombre());
        return dto;
    }

    public PuestoDeTrabajoEntity convertirAEntity(PuestoDeTrabajoRequestDTO dto) {

        PuestoDeTrabajoEntity entity = new PuestoDeTrabajoEntity();
        entity.setNombre(dto.getNombre().trim());
        return entity;
    }

}

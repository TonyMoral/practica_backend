package es.ediae.master.programacion.gestionusuario.mapper;

import org.springframework.stereotype.Component;

import es.ediae.master.programacion.gestionusuario.dto.DireccionRequestDTO;
import es.ediae.master.programacion.gestionusuario.dto.DireccionResponseDTO;
import es.ediae.master.programacion.gestionusuario.entity.DireccionEntity;

@Component
public class DireccionMapper {

    public DireccionResponseDTO convertirADTO(DireccionEntity entity) {

        DireccionResponseDTO dto = new DireccionResponseDTO();
        dto.setId(entity.getId());
        dto.setNombreCalle(entity.getNombreCalle());
        dto.setNumeroCalle(entity.getNumeroCalle());
        dto.setDireccionPrincipal(entity.getDireccionPrincipal());

        if (entity.getUsuario() != null) {
            dto.setUsuarioId(entity.getUsuario().getId());
        }
        return dto;
    }

    public DireccionEntity convertirAEntity(DireccionRequestDTO dto) {
        DireccionEntity entity = new DireccionEntity();
        entity.setNombreCalle(dto.getNombreCalle());
        entity.setNumeroCalle(dto.getNumeroCalle());
        // Si direccionPrincipal es null, se establece como false por defecto
        entity.setDireccionPrincipal(dto.getDireccionPrincipal() != null ? dto.getDireccionPrincipal() : false);
        return entity;
    }

}

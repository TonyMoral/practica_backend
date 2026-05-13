package es.ediae.master.programacion.gestionusuario.mapper;

import org.springframework.stereotype.Component;

import es.ediae.master.programacion.gestionusuario.dto.GeneroRequestDTO;
import es.ediae.master.programacion.gestionusuario.dto.GeneroResponseDTO;
import es.ediae.master.programacion.gestionusuario.entity.GeneroEntity;

@Component  
public class GeneroMapper {

    public GeneroResponseDTO convertirADTO(GeneroEntity generoEntity) {

        GeneroResponseDTO dto = new GeneroResponseDTO();
        dto.setId(generoEntity.getId());
        dto.setNombre(generoEntity.getNombre());
        return dto;
    }

    public GeneroEntity convertirAEntity(GeneroRequestDTO dto) {

        GeneroEntity entity = new GeneroEntity();
        entity.setNombre(dto.getNombre().trim());
        return entity;
    }

}

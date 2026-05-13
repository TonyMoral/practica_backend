package es.ediae.master.programacion.gestionusuario.mapper;

import org.springframework.stereotype.Component;

import es.ediae.master.programacion.gestionusuario.dto.ImagenUsuarioRequestDTO;
import es.ediae.master.programacion.gestionusuario.dto.ImagenUsuarioResponseDTO;
import es.ediae.master.programacion.gestionusuario.entity.ImagenUsuarioEntity;

@Component
public class ImagenUsuarioMapper {

    public ImagenUsuarioResponseDTO convertirADTO(ImagenUsuarioEntity entity) {

        ImagenUsuarioResponseDTO dto = new ImagenUsuarioResponseDTO();
        dto.setId(entity.getId());
        dto.setImagen(entity.getImagen());

        if (entity.getUsuario() != null) {
            dto.setUsuarioId(entity.getUsuario().getId());
        }

        return dto;
    }

    public ImagenUsuarioEntity convertirAEntity(ImagenUsuarioRequestDTO dto) {

        ImagenUsuarioEntity entity = new ImagenUsuarioEntity();
        entity.setImagen(dto.getImagen());
        return entity;
    }

}

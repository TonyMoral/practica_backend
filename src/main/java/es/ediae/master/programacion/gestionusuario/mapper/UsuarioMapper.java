package es.ediae.master.programacion.gestionusuario.mapper;

import org.springframework.stereotype.Component;

import es.ediae.master.programacion.gestionusuario.dto.UsuarioRequestDTO;
import es.ediae.master.programacion.gestionusuario.dto.UsuarioResponseDTO;
import es.ediae.master.programacion.gestionusuario.entity.UsuarioEntity;

@Component
public class UsuarioMapper {

        // Método para convertir de Entity a DTO, necesario para devolver datos
    public UsuarioResponseDTO convertirADTO(UsuarioEntity entity) {

        UsuarioResponseDTO dto = new UsuarioResponseDTO();

        dto.setId(entity.getId());
        dto.setNickUsuario(entity.getNickUsuario());
        dto.setNombre(entity.getNombre());
        dto.setPrimerApellido(entity.getPrimerApellido());
        dto.setSegundoApellido(entity.getSegundoApellido());
        dto.setFechaNacimiento(entity.getFechaNacimiento());

        if (entity.getHoraDesayuno() != null) {
            dto.setHoraDesayuno(entity.getHoraDesayuno().toString());
        }
        if (entity.getGenero() != null) {
            dto.setGeneroId(entity.getGenero().getId());
            dto.setGeneroNombre(entity.getGenero().getNombre());
        }

        if (entity.getPuestoDeTrabajo() != null) {
            dto.setPuestoDeTrabajoId(entity.getPuestoDeTrabajo().getId());
            dto.setPuestoDeTrabajoNombre(entity.getPuestoDeTrabajo().getNombre());
        }

        return dto;
    }

    // Método para convertir de DTO a Entity, necesario para crear o actualizar usuarios
    public UsuarioEntity convertirAEntity(UsuarioRequestDTO dto) {

        UsuarioEntity entity = new UsuarioEntity();

        entity.setNickUsuario(dto.getNickUsuario());
        entity.setNombre(dto.getNombre());
        entity.setPrimerApellido(dto.getPrimerApellido());
        entity.setSegundoApellido(dto.getSegundoApellido());
        entity.setFechaHoraCreacion(new java.sql.Date(System.currentTimeMillis()));
        entity.setContrasena(dto.getContrasena());
        entity.setFechaNacimiento(dto.getFechaNacimiento());

        if (dto.getHoraDesayuno() != null) {
            entity.setHoraDesayuno(java.sql.Time.valueOf(dto.getHoraDesayuno()));
        }

        return entity;
    }
}

package es.ediae.master.programacion.gestionusuario.service.impl;

import es.ediae.master.programacion.gestionusuario.repository.PuestoDeTrabajoRepository;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.ediae.master.programacion.gestionusuario.dto.UsuarioRequestDTO;
import es.ediae.master.programacion.gestionusuario.dto.UsuarioResponseDTO;
import es.ediae.master.programacion.gestionusuario.entity.GeneroEntity;
import es.ediae.master.programacion.gestionusuario.entity.PuestoDeTrabajoEntity;
import es.ediae.master.programacion.gestionusuario.entity.UsuarioEntity;
import es.ediae.master.programacion.gestionusuario.repository.GeneroRepository;
import es.ediae.master.programacion.gestionusuario.repository.UsuarioRepository;
import es.ediae.master.programacion.gestionusuario.service.IUsuarioService;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

    @Autowired
    private PuestoDeTrabajoRepository puestoDeTrabajoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private GeneroRepository generoRepository;

    // Método para convertir de Entity a DTO, necesario para devolver datos
    private UsuarioResponseDTO convertirADTO(UsuarioEntity entity) {

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
    private UsuarioEntity convertirAEntity(UsuarioRequestDTO dto) {

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


        if (dto.getGeneroId() != null) {
            GeneroEntity genero = generoRepository.findById(dto.getGeneroId())
                    .orElseThrow(() -> new RuntimeException("Género no encontrado"));

            entity.setGenero(genero);
        }

        if (dto.getPuestoDeTrabajoId() != null) {
            PuestoDeTrabajoEntity puesto = puestoDeTrabajoRepository.findById(dto.getPuestoDeTrabajoId())
                    .orElseThrow(() -> new RuntimeException("Puesto de trabajo no encontrado"));

            entity.setPuestoDeTrabajo(puesto);
        }

        return entity;
    }

    // Método para iniciar sesión, 
    // verifica si existe un usuario con el nick y la contraseña 
    @Override
    public Boolean iniciarSesion(String nickUsuario, String contrasena) {
        return usuarioRepository.findByNickUsuarioAndContrasena(nickUsuario, contrasena).isPresent();

    }

    // Metodo para obtener todos los usuarios, 
    // convierte cada entidad a DTO para devolver solo los datos necesarios
    @Override
    public List<UsuarioResponseDTO> obtenerUsuarios() {
        return usuarioRepository.findAll()
                .stream()
                .map(this::convertirADTO)
                .toList();

    }

    // Método para obtener un usuario por su ID, 
    // convierte la entidad a DTO para devolver solo los datos necesarios
    @Override
    public UsuarioResponseDTO obtenerUsuario(Integer id) {
        return usuarioRepository.findById(id)
                .map(this::convertirADTO)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

    }

    // Método para crear un nuevo usuario, 
    // convierte el DTO a entidad para guardarlo en la base de datos y 
    // luego convierte la entidad guardada a DTO para devolverla
    @Override
    public UsuarioResponseDTO crearUsuario(UsuarioRequestDTO usuarioDTO) {

        UsuarioEntity usuarioEntity = convertirAEntity(usuarioDTO);
        UsuarioEntity usuarioGuardado = usuarioRepository.save(usuarioEntity);
        return convertirADTO(usuarioGuardado);
    }

    // Método para actualizar un usuario existente, primero busca la entidad por su ID, 
    // luego actualiza los campos con los datos del DTO, 
    // guarda la entidad actualizada en la base de datos y 
    // finalmente convierte la entidad actualizada a DTO para devolverla
    @Override
    public UsuarioResponseDTO actualizarUsuario(Integer id, UsuarioRequestDTO usuarioDTO) {

        UsuarioEntity usuarioExistente = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        usuarioExistente.setNickUsuario(usuarioDTO.getNickUsuario());
        usuarioExistente.setNombre(usuarioDTO.getNombre());
        usuarioExistente.setPrimerApellido(usuarioDTO.getPrimerApellido());
        usuarioExistente.setSegundoApellido(usuarioDTO.getSegundoApellido());
        if (usuarioDTO.getHoraDesayuno() != null) {
            usuarioExistente.setHoraDesayuno(java.sql.Time.valueOf(usuarioDTO.getHoraDesayuno()));
        }
        GeneroEntity genero;
        genero = generoRepository.findById(usuarioDTO.getGeneroId())
                .orElseThrow(() -> new RuntimeException("Género no encontrado"));
        usuarioExistente.setGenero(genero);
        PuestoDeTrabajoEntity puestoDeTrabajo = puestoDeTrabajoRepository.findById(usuarioDTO.getPuestoDeTrabajoId())
                .orElseThrow(() -> new RuntimeException("Puesto de trabajo no encontrado"));
        usuarioExistente.setPuestoDeTrabajo(puestoDeTrabajo);

        usuarioExistente.setContrasena(usuarioDTO.getContrasena());

        UsuarioEntity usuarioActualizado = usuarioRepository.save(usuarioExistente);
        return convertirADTO(usuarioActualizado);

    }

    // Método para eliminar un usuario por su ID, 
    // llama al método deleteById del repositorio
    @Override
    public void eliminarUsuario(Integer id) {
        usuarioRepository.deleteById(id);
    }
}

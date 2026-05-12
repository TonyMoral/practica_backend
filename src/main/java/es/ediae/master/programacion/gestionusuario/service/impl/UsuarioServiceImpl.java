package es.ediae.master.programacion.gestionusuario.service.impl;

import es.ediae.master.programacion.gestionusuario.repository.PuestoDeTrabajoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.ediae.master.programacion.gestionusuario.dto.UsuarioRequestDTO;
import es.ediae.master.programacion.gestionusuario.dto.UsuarioResponseDTO;
import es.ediae.master.programacion.gestionusuario.entity.GeneroEntity;
import es.ediae.master.programacion.gestionusuario.entity.PuestoDeTrabajoEntity;
import es.ediae.master.programacion.gestionusuario.entity.UsuarioEntity;
import es.ediae.master.programacion.gestionusuario.mapper.UsuarioMapper;
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

    @Autowired
    private UsuarioMapper usuarioMapper;

    // Método para iniciar sesión,
    // verifica si existe un usuario con el nick y la contraseña
    @Override
    public Boolean iniciarSesion(String nickUsuario, String contrasena) {

        Optional<UsuarioEntity> usuarioOptional = usuarioRepository.findByNickUsuarioAndContrasena(nickUsuario, contrasena);
        if (usuarioOptional.isEmpty()) {
            return null;
            
        } 
        return usuarioOptional.isPresent();

    }

    // Metodo para obtener todos los usuarios,
    // convierte cada entidad a DTO para devolver solo los datos necesarios
    @Override
    public List<UsuarioResponseDTO> obtenerUsuarios() {
        List<UsuarioEntity> entidades = usuarioRepository.findAll();

        List<UsuarioResponseDTO> resultado = new ArrayList<>();

        for (UsuarioEntity entity : entidades) {
            resultado.add(usuarioMapper.convertirADTO(entity));
        }

        return resultado;

    }

    // Método para obtener un usuario por su ID,
    // convierte la entidad a DTO para devolver solo los datos necesarios
    @Override
    public UsuarioResponseDTO obtenerUsuario(Integer id) {

    UsuarioEntity usuario = usuarioRepository.findById(id)
            .orElse(null);

    if (usuario == null) {
        throw new RuntimeException("Usuario no encontrado");
    }

    return usuarioMapper.convertirADTO(usuario);

    }

    // Método para crear un nuevo usuario,
    // convierte el DTO a entidad para guardarlo en la base de datos y
    // luego convierte la entidad guardada a DTO para devolverla
    // Antes de crear el usuario, verifica que no exista otro usuario con el mismo nick para evitar duplicados
    @Override
    public UsuarioResponseDTO crearUsuario(UsuarioRequestDTO dto) {

        if (usuarioRepository.findByNickUsuario(dto.getNickUsuario()).isPresent()) {
            throw new RuntimeException("El nick de usuario ya existe " + dto.getNickUsuario());
        }

        UsuarioEntity entity = usuarioMapper.convertirAEntity(dto);

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

        UsuarioEntity usuarioGuardado = usuarioRepository.save(entity);

        return usuarioMapper.convertirADTO(usuarioGuardado);
    }

    // Método para actualizar un usuario existente, primero busca la entidad por su
    // ID,
    // luego actualiza los campos con los datos del DTO,
    // guarda la entidad actualizada en la base de datos y
    // finalmente convierte la entidad actualizada a DTO para devolverla
    // Antes de actualizar el usuario, verifica que no exista otro usuario con el mismo nick para evitar duplicados
    @Override
    public UsuarioResponseDTO actualizarUsuario(Integer id, UsuarioRequestDTO usuarioDTO) {

        UsuarioEntity usuarioExistente = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Optional<UsuarioEntity> usuarioConMismoNick = usuarioRepository.findByNickUsuario(usuarioDTO.getNickUsuario());
        if (usuarioConMismoNick.isPresent() && !usuarioConMismoNick.get().getId().equals(id)) {
            throw new RuntimeException("El nick de usuario ya existe: " + usuarioDTO.getNickUsuario());
        }

        usuarioExistente.setNickUsuario(usuarioDTO.getNickUsuario());
        usuarioExistente.setNombre(usuarioDTO.getNombre());
        usuarioExistente.setPrimerApellido(usuarioDTO.getPrimerApellido());
        usuarioExistente.setSegundoApellido(usuarioDTO.getSegundoApellido());
        usuarioExistente.setEsAdmin(usuarioDTO.getEsAdmin() != null ? usuarioDTO.getEsAdmin() : false);
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
        return usuarioMapper.convertirADTO(usuarioActualizado);

    }

    // Método para eliminar un usuario por su ID,
    // llama al método deleteById del repositorio
    @Override
    public void eliminarUsuario(Integer id) {
        usuarioRepository.deleteById(id);
    }
}

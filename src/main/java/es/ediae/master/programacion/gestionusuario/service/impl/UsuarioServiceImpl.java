package es.ediae.master.programacion.gestionusuario.service.impl;

import es.ediae.master.programacion.gestionusuario.repository.PuestoDeTrabajoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.ediae.master.programacion.gestionusuario.dto.ImagenUsuarioRequestDTO;
import es.ediae.master.programacion.gestionusuario.dto.ImagenUsuarioResponseDTO;
import es.ediae.master.programacion.gestionusuario.dto.UsuarioRequestDTO;
import es.ediae.master.programacion.gestionusuario.dto.UsuarioResponseDTO;
import es.ediae.master.programacion.gestionusuario.entity.DireccionEntity;
import es.ediae.master.programacion.gestionusuario.entity.GeneroEntity;
import es.ediae.master.programacion.gestionusuario.entity.ImagenUsuarioEntity;
import es.ediae.master.programacion.gestionusuario.entity.PuestoDeTrabajoEntity;
import es.ediae.master.programacion.gestionusuario.entity.UsuarioEntity;
import es.ediae.master.programacion.gestionusuario.mapper.ImagenUsuarioMapper;
import es.ediae.master.programacion.gestionusuario.mapper.UsuarioMapper;
import es.ediae.master.programacion.gestionusuario.repository.DireccionRepository;
import es.ediae.master.programacion.gestionusuario.repository.GeneroRepository;
import es.ediae.master.programacion.gestionusuario.repository.ImagenUsuarioRepository;
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

    @Autowired
    public DireccionRepository direccionRepository;

    @Autowired
    public ImagenUsuarioRepository imagenUsuarioRepository;
    
    @Autowired
    public ImagenUsuarioMapper imagenUsuarioMapper;

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
    // primero busca las direcciones asociadas al usuario y las elimina, luego elimina el usuario
    @Override
    // @Transactional sirve asegurar que eliminar direcciones y eliminar usuario 
    // se realicen en una sola transacción, garantizando la integridad de los datos
    @Transactional
    public void eliminarUsuario(Integer id) {
        List<DireccionEntity> direcciones = direccionRepository.buscarPorUsuarioId(id);

        if (direcciones != null && !direcciones.isEmpty()) {
            direccionRepository.deleteAll(direcciones);
        }
        usuarioRepository.deleteById(id);
    }

    // Método para crear imagen de usuario
    @Override
    public ImagenUsuarioResponseDTO crearImagenUsuario(ImagenUsuarioRequestDTO imagenDTO) {
        UsuarioEntity usuario = usuarioRepository.findById(imagenDTO.getUsuarioId())
        .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        ImagenUsuarioEntity entity = imagenUsuarioMapper.convertirAEntity(imagenDTO);
        entity.setUsuario(usuario);

        ImagenUsuarioEntity imagenGuardada = imagenUsuarioRepository.save(entity);
        return imagenUsuarioMapper.convertirADTO(imagenGuardada);
    }

    // Método para actualizar la imagen de usuario
    @Override
    public ImagenUsuarioResponseDTO actualizarImagenUsuario(Integer id, ImagenUsuarioRequestDTO imagenDTO) {
        ImagenUsuarioEntity existente = imagenUsuarioRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Imagen no encontrada"));

        existente.setImagen(imagenDTO.getImagen());
        return imagenUsuarioMapper.convertirADTO(imagenUsuarioRepository.save(existente));
    }

    // Método para obtener las imagenes de usuario por ID
    @Override
    public ImagenUsuarioResponseDTO obtenerImagenUsuario(Integer id) {
        ImagenUsuarioEntity entity = imagenUsuarioRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Imagen no encontrada"));
        return imagenUsuarioMapper.convertirADTO(entity);
    }

    // Método para eliminar una imagen de usuario por id
    @Override
    public void eliminarImagenUsuario(Integer id) {
        imagenUsuarioRepository.deleteById(id);
    }
}

package es.ediae.master.programacion.gestionusuario.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.ediae.master.programacion.gestionusuario.dto.DireccionRequestDTO;
import es.ediae.master.programacion.gestionusuario.dto.DireccionResponseDTO;
import es.ediae.master.programacion.gestionusuario.entity.DireccionEntity;
import es.ediae.master.programacion.gestionusuario.entity.UsuarioEntity;
import es.ediae.master.programacion.gestionusuario.mapper.DireccionMapper;
import es.ediae.master.programacion.gestionusuario.repository.DireccionRepository;
import es.ediae.master.programacion.gestionusuario.repository.UsuarioRepository;
import es.ediae.master.programacion.gestionusuario.service.IDireccionService;

@Service
public class DireccionServiceImpl implements IDireccionService {


    @Autowired
    private DireccionRepository direccionRepository;
    
    @Autowired
    private DireccionMapper direccionMapper;

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    // Metodo para obtener todas las direcciones de un usuario
    @Override
    public List<DireccionResponseDTO> obtenerDirecciones(Integer usuarioId) {
        List<DireccionEntity> entidades = direccionRepository.buscarPorUsuarioId(usuarioId);
        List<DireccionResponseDTO> resultado = new ArrayList<>();
        for (DireccionEntity entidad : entidades) {
            resultado.add(direccionMapper.convertirADTO(entidad));
        }
        return resultado;
    }

    // Metodo para obtener una direccion por su id
    @Override
    public DireccionResponseDTO obtenerDireccion(Integer id) {
        DireccionEntity entity = direccionRepository.findById(id).orElse(null);
        return entity != null ? direccionMapper.convertirADTO(entity) : null;
    }

    // Metodo para crear una nueva direccion
    @Override
    public DireccionResponseDTO crearDireccion(DireccionRequestDTO direccionDto) {
        DireccionEntity entity = direccionMapper.convertirAEntity(direccionDto);

        UsuarioEntity usuario = usuarioRepository.findById(direccionDto.getUsuarioId()).orElse(null);
        if (usuario != null) {
            entity.setUsuario(usuario);
        }

        return direccionMapper.convertirADTO(direccionRepository.save(entity));
    }

    // Metodo para actualizar una direccion existente
    @Override
    public DireccionResponseDTO actualizarDireccion(Integer id, DireccionRequestDTO direccionDto) {
        DireccionEntity direccionExistente = direccionRepository.findById(id).orElse(null);
        if (direccionExistente != null) {
            direccionExistente.setNombreCalle(direccionDto.getNombreCalle());
            direccionExistente.setNumeroCalle(direccionDto.getNumeroCalle());

            if (direccionDto.getDireccionPrincipal() != null) {
                if (direccionDto.getDireccionPrincipal()) {
                    // Marcar como dirección principaly desmarcar otras direcciones principales del mismo usuario
                    List<DireccionEntity> direccionesUsuario = direccionRepository.buscarPorUsuarioId(direccionExistente.getUsuario().getId());
                    for (DireccionEntity dir : direccionesUsuario) {
                        if (dir.getDireccionPrincipal() && !dir.getId().equals(id)) {
                            dir.setDireccionPrincipal(false);
                            direccionRepository.save(dir);
                        }
                    }
                }
                direccionExistente.setDireccionPrincipal(direccionDto.getDireccionPrincipal());
            }
            return direccionMapper.convertirADTO(direccionRepository.save(direccionExistente));
        }
        return null;
    }

    // Metodo para eliminar una direccion por su id
    @Override
    public void eliminarDireccion(Integer id) {
        direccionRepository.deleteById(id);
    }


    }


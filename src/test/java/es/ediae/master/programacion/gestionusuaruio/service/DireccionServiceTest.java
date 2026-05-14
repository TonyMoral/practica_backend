package es.ediae.master.programacion.gestionusuaruio.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import es.ediae.master.programacion.gestionusuario.dto.DireccionRequestDTO;
import es.ediae.master.programacion.gestionusuario.dto.DireccionResponseDTO;
import es.ediae.master.programacion.gestionusuario.entity.DireccionEntity;
import es.ediae.master.programacion.gestionusuario.entity.UsuarioEntity;
import es.ediae.master.programacion.gestionusuario.exception.DireccionNoEncontradaException;
import es.ediae.master.programacion.gestionusuario.exception.UsuarioNoEncontradoException;
import es.ediae.master.programacion.gestionusuario.mapper.DireccionMapper;
import es.ediae.master.programacion.gestionusuario.repository.DireccionRepository;
import es.ediae.master.programacion.gestionusuario.repository.UsuarioRepository;
import es.ediae.master.programacion.gestionusuario.service.impl.DireccionServiceImpl;

@ExtendWith(MockitoExtension.class)
public class DireccionServiceTest {

    @Mock
    private DireccionRepository direccionRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private DireccionMapper direccionMapper;

    @InjectMocks
    private DireccionServiceImpl direccionService;

    private DireccionEntity direccionEntity;
    private DireccionRequestDTO requestDTO;
    private UsuarioEntity usuarioEntity;

    @BeforeEach
    void setUp() {
        usuarioEntity = new UsuarioEntity();
        usuarioEntity.setId(1);

        direccionEntity = new DireccionEntity();
        direccionEntity.setId(10);
        direccionEntity.setNombreCalle("Gran Vía");

        requestDTO = new DireccionRequestDTO();
        requestDTO.setUsuarioId(1);
        requestDTO.setNombreCalle("Gran Vía");
    }

    @Test
    void testObtenerDireccion_NoEncontrada_LanzaExcepcion() {
        when(direccionRepository.findById(99)).thenReturn(Optional.empty());

        assertThrows(DireccionNoEncontradaException.class, () -> direccionService.obtenerDireccion(99));
    }

    @Test
    void testCrearDireccion_UsuarioNoExiste_LanzaExcepcion() {
        when(direccionMapper.convertirAEntity(requestDTO)).thenReturn(direccionEntity);
        when(usuarioRepository.findById(requestDTO.getUsuarioId())).thenReturn(Optional.empty());

        assertThrows(UsuarioNoEncontradoException.class, () -> direccionService.crearDireccion(requestDTO));
        verify(direccionRepository, never()).save(any());
    }

}

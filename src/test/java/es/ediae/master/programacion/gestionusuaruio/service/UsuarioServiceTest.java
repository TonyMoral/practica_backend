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

import es.ediae.master.programacion.gestionusuario.dto.UsuarioRequestDTO;
import es.ediae.master.programacion.gestionusuario.dto.UsuarioResponseDTO;
import es.ediae.master.programacion.gestionusuario.entity.UsuarioEntity;
import es.ediae.master.programacion.gestionusuario.exception.UsuarioNickYaExisteException;
import es.ediae.master.programacion.gestionusuario.exception.UsuarioNoEncontradoException;
import es.ediae.master.programacion.gestionusuario.exception.UsuarioNoValidoException;
import es.ediae.master.programacion.gestionusuario.mapper.UsuarioMapper;
import es.ediae.master.programacion.gestionusuario.repository.UsuarioRepository;
import es.ediae.master.programacion.gestionusuario.service.impl.UsuarioServiceImpl;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private UsuarioMapper usuarioMapper;

    @InjectMocks
    private UsuarioServiceImpl usuarioService;

    private UsuarioEntity usuarioEntity;
    private UsuarioRequestDTO requestDTO;
    private UsuarioResponseDTO responseDTO;

    @BeforeEach
    void setUp() {
        usuarioEntity = new UsuarioEntity();
        usuarioEntity.setId(1);
        usuarioEntity.setNickUsuario("admin");
        usuarioEntity.setContrasena("clave_encriptada");

        requestDTO = new UsuarioRequestDTO();
        requestDTO.setNickUsuario("admin");
        requestDTO.setContrasena("1234");

        responseDTO = new UsuarioResponseDTO();
        responseDTO.setId(1);
        responseDTO.setNickUsuario("admin");
    }


    @Test
    void testObtenerUsuario_Exito() {
        when(usuarioRepository.findById(1)).thenReturn(Optional.of(usuarioEntity));
        when(usuarioMapper.convertirADTO(usuarioEntity)).thenReturn(responseDTO);

        UsuarioResponseDTO resultado = usuarioService.obtenerUsuario(1);

        assertNotNull(resultado);
        assertEquals("admin", resultado.getNickUsuario());
    }

    @Test
    void testObtenerUsuario_NoEncontrado_LanzaExcepcion() {
        when(usuarioRepository.findById(99)).thenReturn(Optional.empty());

        assertThrows(UsuarioNoEncontradoException.class, () -> usuarioService.obtenerUsuario(99));
    }


    @Test
    void testCrearUsuario_NickYaExiste_LanzaExcepcion() {
when(usuarioRepository.findByNickUsuario("admin")).thenReturn(Optional.of(usuarioEntity));
        assertThrows(UsuarioNickYaExisteException.class, () -> usuarioService.crearUsuario(requestDTO));
        verify(usuarioRepository, never()).save(any());
    }


    @Test
    void testIniciarSesion_Exito() {
        when(usuarioRepository.findByNickUsuarioAndContrasena("admin", "1234")).thenReturn(Optional.of(usuarioEntity));
        
        Boolean resultado = usuarioService.iniciarSesion("admin", "1234");
        
        assertTrue(resultado);
    }

    @Test
    void testIniciarSesion_CredencialesIncorrectas_LanzaExcepcion() {
        when(usuarioRepository.findByNickUsuarioAndContrasena("admin", "clave_falsa")).thenReturn(Optional.empty());

        assertThrows(UsuarioNoValidoException.class, () -> usuarioService.iniciarSesion("admin", "clave_falsa"));
    }
}

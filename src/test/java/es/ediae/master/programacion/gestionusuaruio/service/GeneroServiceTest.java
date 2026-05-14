package es.ediae.master.programacion.gestionusuaruio.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.hibernate.validator.constraints.ModCheck;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import es.ediae.master.programacion.gestionusuario.dto.GeneroRequestDTO;
import es.ediae.master.programacion.gestionusuario.dto.GeneroResponseDTO;
import es.ediae.master.programacion.gestionusuario.entity.GeneroEntity;
import es.ediae.master.programacion.gestionusuario.exception.GeneroNoEncontradoException;
import es.ediae.master.programacion.gestionusuario.exception.GeneroYaExisteException;
import es.ediae.master.programacion.gestionusuario.mapper.GeneroMapper;
import es.ediae.master.programacion.gestionusuario.repository.GeneroRepository;
import es.ediae.master.programacion.gestionusuario.service.impl.GeneroServiceImpl;

@ExtendWith(MockitoExtension.class)
public class GeneroServiceTest {

    @Mock
    private GeneroRepository generoRepository;

    @Mock
    private GeneroMapper generoMapper;

    @InjectMocks
    private GeneroServiceImpl generoService;

    private GeneroEntity generoEntity;
    private GeneroRequestDTO requestDTO;
    private GeneroResponseDTO responseDTO;

    @BeforeEach
    void setUp() {
        generoEntity = new GeneroEntity();
        generoEntity.setId(1);
        generoEntity.setNombre("Femenino");

        requestDTO = new GeneroRequestDTO();
        requestDTO.setNombre("Femenino");

        responseDTO = new GeneroResponseDTO();
        responseDTO.setId(1);
        responseDTO.setNombre("Femenino");
    }

    @Test
    void testObtenerGenero_Exito() {
        when(generoRepository.findById(1)).thenReturn(Optional.of(generoEntity));
        when(generoMapper.convertirADTO(generoEntity)).thenReturn(responseDTO);

        GeneroResponseDTO resultado = generoService.obtenerGenero(1);

        assertNotNull(resultado);
        assertEquals("Femenino", resultado.getNombre());
    }

    @Test
    void testObtenerGenero_NoEncontrado_LanzaExcepcion() {
        when(generoRepository.findById(99)).thenReturn(Optional.empty());

        assertThrows(GeneroNoEncontradoException.class, () -> generoService.obtenerGenero(99));
    }

    @Test
    void testCrearGenero_Exito() {
        when(generoRepository.findByNombreIgnoreCase("Femenino")).thenReturn(Optional.empty());
        when(generoMapper.convertirAEntity(requestDTO)).thenReturn(generoEntity);
        when(generoRepository.save(generoEntity)).thenReturn(generoEntity);
        when(generoMapper.convertirADTO(generoEntity)).thenReturn(responseDTO);

        GeneroResponseDTO resultado = generoService.crearGenero(requestDTO);

        assertNotNull(resultado);
        verify(generoRepository).save(any(GeneroEntity.class));
    }

    @Test
    void testCrearGenero_YaExiste_LanzaExcepcion() {
        when(generoRepository.findByNombreIgnoreCase("Femenino")).thenReturn(Optional.of(generoEntity));

        assertThrows(GeneroYaExisteException.class, () -> generoService.crearGenero(requestDTO));
        verify(generoRepository, never()).save(any());
    }


}

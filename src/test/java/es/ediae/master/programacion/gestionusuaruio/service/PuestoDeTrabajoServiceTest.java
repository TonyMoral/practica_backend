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

import es.ediae.master.programacion.gestionusuario.dto.PuestoDeTrabajoRequestDTO;
import es.ediae.master.programacion.gestionusuario.dto.PuestoDeTrabajoResponseDTO;
import es.ediae.master.programacion.gestionusuario.entity.PuestoDeTrabajoEntity;
import es.ediae.master.programacion.gestionusuario.exception.PuestoDeTrabajoNoEncontradoException;
import es.ediae.master.programacion.gestionusuario.exception.PuestoDeTrabajoYaExisteException;
import es.ediae.master.programacion.gestionusuario.mapper.PuestoDeTrabajoMapper;
import es.ediae.master.programacion.gestionusuario.repository.PuestoDeTrabajoRepository;
import es.ediae.master.programacion.gestionusuario.service.impl.PuestoDeTrabajoServiceImpl;

@ExtendWith(MockitoExtension.class)
class PuestoDeTrabajoServiceTest {

    @Mock
    private PuestoDeTrabajoRepository puestoRepository;

    @Mock
    private PuestoDeTrabajoMapper puestoMapper;

    @InjectMocks
    private PuestoDeTrabajoServiceImpl puestoService;

    private PuestoDeTrabajoEntity puestoEntity;
    private PuestoDeTrabajoRequestDTO requestDTO;
    private PuestoDeTrabajoResponseDTO responseDTO;

    @BeforeEach
    void setUp() {
        puestoEntity = new PuestoDeTrabajoEntity();
        puestoEntity.setId(1);
        puestoEntity.setNombre("Programador");

        requestDTO = new PuestoDeTrabajoRequestDTO();
        requestDTO.setNombre("Programador");

        responseDTO = new PuestoDeTrabajoResponseDTO();
        responseDTO.setId(1);
        responseDTO.setNombre("Programador");
    }

    @Test
    void testObtenerPuesto_Exito() {
        when(puestoRepository.findById(1)).thenReturn(Optional.of(puestoEntity));
        when(puestoMapper.convertirADTO(puestoEntity)).thenReturn(responseDTO);

        PuestoDeTrabajoResponseDTO resultado = puestoService.obtenerPuestoDeTrabajo(1);

        assertNotNull(resultado);
        assertEquals("Programador", resultado.getNombre());
    }

    @Test
    void testObtenerPuesto_NoEncontrado_LanzaExcepcion() {
        when(puestoRepository.findById(99)).thenReturn(Optional.empty());

        assertThrows(PuestoDeTrabajoNoEncontradoException.class, () -> puestoService.obtenerPuestoDeTrabajo(99));
    }

    @Test
    void testCrearPuesto_YaExiste_LanzaExcepcion() {
        when(puestoRepository.findByNombreIgnoreCase("Programador")).thenReturn(Optional.of(puestoEntity));

        assertThrows(PuestoDeTrabajoYaExisteException.class, () -> puestoService.crearPuestoDeTrabajo(requestDTO));
        verify(puestoRepository, never()).save(any());
    }
}

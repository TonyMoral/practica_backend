package es.ediae.master.programacion.gestionusuario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.ediae.master.programacion.gestionusuario.entity.DireccionEntity;
import es.ediae.master.programacion.gestionusuario.repository.DireccionRepository;

@RestController
@RequestMapping("/api/v1")
public class DireccionController {

    @Autowired
    private DireccionRepository direccionRepository;

    @GetMapping("/usuario/{usuarioId}/direcciones")
    public List<DireccionEntity> obtenerDireccionesPorUsuario(@PathVariable Integer usuarioId) {
        return direccionRepository.buscarPorUsuarioId(usuarioId);
    }
    
    @GetMapping("/direcciones")
    public List<DireccionEntity> obtenerTodasLasDirecciones() {
        return direccionRepository.findAll();
    }

    @GetMapping("/direccion/{id}")
    public DireccionEntity obtenerDireccionPorId(@PathVariable Integer id) {
        return direccionRepository.findById(id).orElse(null);
    }
}

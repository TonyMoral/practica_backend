package es.ediae.master.programacion.gestionusuario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;

import es.ediae.master.programacion.gestionusuario.dto.DireccionRequestDTO;
import es.ediae.master.programacion.gestionusuario.dto.DireccionResponseDTO;
import es.ediae.master.programacion.gestionusuario.service.IDireccionService;

@RestController
@RequestMapping("/direcciones")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE, RequestMethod.OPTIONS })
public class DireccionController {

    @Autowired
    private IDireccionService direccionService;

    @GetMapping("/usuario/{usuarioId}")
    public List<DireccionResponseDTO> obtenerDirecciones(@PathVariable Integer usuarioId) {
        return direccionService.obtenerDirecciones(usuarioId);
    }

    @GetMapping("{id}")
    public DireccionResponseDTO obtenerDireccion(@PathVariable Integer id) {
        return direccionService.obtenerDireccion(id);
    }

    @PostMapping
    public DireccionResponseDTO crearDireccion(@RequestBody DireccionRequestDTO direccionDto) {
        return direccionService.crearDireccion(direccionDto);
    }

    @PutMapping("/{id}")
    public DireccionResponseDTO actualizarDireccion(@PathVariable Integer id, @RequestBody DireccionRequestDTO direccionDto) {
        return direccionService.actualizarDireccion(id, direccionDto);
    }

    @DeleteMapping("/{id}")
    public void eliminarDireccion(@PathVariable Integer id) {
        direccionService.eliminarDireccion(id);
    }
}

package es.ediae.master.programacion.gestionusuario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import es.ediae.master.programacion.gestionusuario.dto.GeneroRequestDTO;
import es.ediae.master.programacion.gestionusuario.dto.GeneroResponseDTO;
import es.ediae.master.programacion.gestionusuario.service.IGeneroService;

@RestController
@RequestMapping("/generos")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET,
        RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE, RequestMethod.OPTIONS})   
public class GeneroController {

    @Autowired
    private IGeneroService generoService;

    @GetMapping
    public List<GeneroResponseDTO> obtenerGeneros() {
        return generoService.obtenerGeneros();
    }

    @GetMapping("/{id}")
    public GeneroResponseDTO obtenerGenero(@PathVariable Integer id) {
        return generoService.obtenerGenero(id);
    }

    @PostMapping
    public GeneroResponseDTO crearGenero(@RequestBody GeneroRequestDTO generoRequestDTO) {
        return generoService.crearGenero(generoRequestDTO);
    }

    @PutMapping("/{id}")
    public GeneroResponseDTO actualizarGenero(@PathVariable Integer id, @RequestBody GeneroRequestDTO generoRequestDTO) {
        return generoService.actualizarGenero(id, generoRequestDTO);
    }

    @DeleteMapping("/{id}")
    public void eliminarGenero(@PathVariable Integer id) {
        generoService.eliminarGenero(id);
    }

}

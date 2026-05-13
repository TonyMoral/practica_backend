package es.ediae.master.programacion.gestionusuario.controller;

import es.ediae.master.programacion.gestionusuario.dto.PuestoDeTrabajoRequestDTO;
import es.ediae.master.programacion.gestionusuario.dto.PuestoDeTrabajoResponseDTO;
import es.ediae.master.programacion.gestionusuario.service.IPuestoDeTrabajoService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/puestos-de-trabajo")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET,
        RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE, RequestMethod.OPTIONS})
public class PuestoDeTrabajoController {

    @Autowired
    private IPuestoDeTrabajoService puestoDeTrabajoService;

        @GetMapping
    public List<PuestoDeTrabajoResponseDTO> obtenerPuestosDeTrabajo() {
        return puestoDeTrabajoService.obtenerPuestosDeTrabajo();
    }

    @GetMapping("/{id}")
    public PuestoDeTrabajoResponseDTO obtenerPuestoDeTrabajo(@PathVariable Integer id) {
        return puestoDeTrabajoService.obtenerPuestoDeTrabajo(id);
    }

    @PostMapping
    public PuestoDeTrabajoResponseDTO crearPuestoDeTrabajo(@RequestBody PuestoDeTrabajoRequestDTO puestoDeTrabajoRequestDTO) {
        return puestoDeTrabajoService.crearPuestoDeTrabajo(puestoDeTrabajoRequestDTO);
    }

    @PutMapping("/{id}")
    public PuestoDeTrabajoResponseDTO actualizarPuestoDeTrabajo(@PathVariable Integer id,
            @RequestBody PuestoDeTrabajoRequestDTO puestoDeTrabajoRequestDTO) {
        return puestoDeTrabajoService.actualizarPuestoDeTrabajo(id, puestoDeTrabajoRequestDTO);
    }

    @DeleteMapping("/{id}")
    public void eliminarPuestoDeTrabajo(@PathVariable Integer id) {
        puestoDeTrabajoService.eliminarPuestoDeTrabajo(id);
    }

}

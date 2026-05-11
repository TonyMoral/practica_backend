package es.ediae.master.programacion.gestionusuario.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

//Modificar el DTO para incluir validaciones

@RestController
@RequestMapping("api/v1")
@Validated
public class ValidationController {

   /*  @PostMapping("/producto")
    public ResponseEntity<ProductoDTO> crearProducto(@RequestBody @Valid ProductoDTO productoDTO) {
        // Aquí podrías agregar lógica para guardar el producto en la base de datos
        return ResponseEntity.ok(productoDTO);
    } */

}

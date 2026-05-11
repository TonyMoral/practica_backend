package es.ediae.master.programacion.gestionusuario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.ediae.master.programacion.gestionusuario.dto.UsuarioRequestDTO;
import es.ediae.master.programacion.gestionusuario.dto.UsuarioResponseDTO;
import es.ediae.master.programacion.gestionusuario.service.IUsuarioService;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE, RequestMethod.OPTIONS })
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;

    @GetMapping
    public List<UsuarioResponseDTO> obtenerUsuarios() {
        return usuarioService.obtenerUsuarios();
    }

    @GetMapping("/{id}")
    public UsuarioResponseDTO obtenerUsuario(@PathVariable Integer id) {
        return usuarioService.obtenerUsuario(id);
    }

    @PostMapping
    public UsuarioResponseDTO crearUsuario(@RequestBody UsuarioRequestDTO usuarioDTO) {
        return usuarioService.crearUsuario(usuarioDTO);
    }

    @PutMapping("/{id}")
    public UsuarioResponseDTO actualizarUsuario(@PathVariable Integer id, @RequestBody UsuarioRequestDTO usuarioDTO) {
        return usuarioService.actualizarUsuario(id, usuarioDTO);
    }

    @DeleteMapping("/{id}")
    public void eliminarUsuario(@PathVariable Integer id) {
        usuarioService.eliminarUsuario(id);
    }


    @PostMapping("/login")
    public Boolean iniciarSesion(
            @RequestParam String nickUsuario,
            @RequestParam String contrasena) {

        // Pasamos las credenciales de validación al servicio
        return usuarioService.iniciarSesion(nickUsuario, contrasena);
    }
}

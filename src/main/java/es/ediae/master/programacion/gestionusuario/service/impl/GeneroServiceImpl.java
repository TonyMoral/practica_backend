package es.ediae.master.programacion.gestionusuario.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.ediae.master.programacion.gestionusuario.dto.GeneroRequestDTO;
import es.ediae.master.programacion.gestionusuario.dto.GeneroResponseDTO;
import es.ediae.master.programacion.gestionusuario.entity.GeneroEntity;
import es.ediae.master.programacion.gestionusuario.exception.GeneroNoEncontradoException;
import es.ediae.master.programacion.gestionusuario.exception.GeneroYaExisteException;
import es.ediae.master.programacion.gestionusuario.mapper.GeneroMapper;
import es.ediae.master.programacion.gestionusuario.repository.GeneroRepository;
import es.ediae.master.programacion.gestionusuario.service.IGeneroService;

@Service
public class GeneroServiceImpl implements IGeneroService {

    @Autowired
    private GeneroRepository generoRepository;

    @Autowired
    private GeneroMapper generoMapper;
    
    // Metodo para obtener todos los generos de la base de datos, 
    // convertirlos a DTOs y devolverlos en una lista
    @Override
    public List<GeneroResponseDTO> obtenerGeneros() {
        List<GeneroEntity> entidades = generoRepository.findAll();
        List<GeneroResponseDTO> resultado = new ArrayList<>();
        for (GeneroEntity entidad : entidades) {
            resultado.add(generoMapper.convertirADTO(entidad));
        }
        return resultado;
    }

    // Metodo para obtener un genero por su ID, convertirlo a DTO y devolverlo al controlador
    @Override
    public GeneroResponseDTO obtenerGenero(Integer id) {
        GeneroEntity entidad = generoRepository.findById(id).orElseThrow(() -> new GeneroNoEncontradoException());
        return generoMapper.convertirADTO(entidad);
    }

    // Metodo para crear un nuevo genero, convertir el DTO a entidad, 
    // guardarlo en la base de datos y devolverlo como DTO
    @Override
    public GeneroResponseDTO crearGenero(GeneroRequestDTO generoRequestDTO) {
        String nombre = generoRequestDTO.getNombre().trim();

        // Comprobar si el genero ya existe en la base de datos (ignorando mayusculas/minusculas)
        if (generoRepository.findByNombreIgnoreCase(nombre).isPresent()) {
            throw new GeneroYaExisteException();
        }

        
        GeneroEntity entidad = generoMapper.convertirAEntity(generoRequestDTO);
        return generoMapper.convertirADTO(generoRepository.save(entidad));
    }

    // Metodo para actualizar un genero existente, comprobar si el nuevo nombre ya existe,
    // actualizar la entidad, guardarla en la base de datos y devolverla como DTO
    @Override
    public GeneroResponseDTO actualizarGenero(Integer id, GeneroRequestDTO generoRequestDTO) {
        GeneroEntity entidadExistente = generoRepository.findById(id).orElseThrow(() -> new GeneroNoEncontradoException());
        
        String nuevoNombre = generoRequestDTO.getNombre().trim();

        // Comprobar si el genero ya existe en la base de datos (ignorando mayusculas/minusculas)
        Optional<GeneroEntity> generoExistente = generoRepository.findByNombreIgnoreCase(nuevoNombre);
        if (generoExistente.isPresent() && !generoExistente.get().getId().equals(id)) {
            throw new GeneroYaExisteException();
        }

        entidadExistente.setNombre(nuevoNombre);
        return generoMapper.convertirADTO(generoRepository.save(entidadExistente));
    }

    // Metodo para eliminar un genero por su ID
    @Override
    public void eliminarGenero(Integer id) {
        generoRepository.deleteById(id);
    }

}

package es.ediae.master.programacion.gestionusuario.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.ediae.master.programacion.gestionusuario.dto.PuestoDeTrabajoRequestDTO;
import es.ediae.master.programacion.gestionusuario.dto.PuestoDeTrabajoResponseDTO;
import es.ediae.master.programacion.gestionusuario.entity.PuestoDeTrabajoEntity;
import es.ediae.master.programacion.gestionusuario.mapper.PuestoDeTrabajoMapper;
import es.ediae.master.programacion.gestionusuario.repository.PuestoDeTrabajoRepository;
import es.ediae.master.programacion.gestionusuario.service.IPuestoDeTrabajoService;

@Service
public class PuestoDeTrabajoServiceImpl implements IPuestoDeTrabajoService {

    @Autowired
    private PuestoDeTrabajoRepository puestoDeTrabajoRepository;

    @Autowired
    private PuestoDeTrabajoMapper puestoDeTrabajoMapper;

    // Metodo para obtener todos los puestos de trabajo de la base de datos,
    // convertirlos a DTOs y devolverlos en una lista
    @Override
    public List<PuestoDeTrabajoResponseDTO> obtenerPuestosDeTrabajo() {
        List<PuestoDeTrabajoEntity> entidades = puestoDeTrabajoRepository.findAll();
        List<PuestoDeTrabajoResponseDTO> resultado = new java.util.ArrayList<>();
        for (PuestoDeTrabajoEntity entidad : entidades) {
            resultado.add(puestoDeTrabajoMapper.convertirADTO(entidad));
        }
        return resultado;
    }

    // Metodo para obtener un puesto de trabajo por su ID,
    // convertirlo a DTO y devolverlo al controlador
    @Override
    public PuestoDeTrabajoResponseDTO obtenerPuestoDeTrabajo(Integer id) {
        PuestoDeTrabajoEntity entidad = puestoDeTrabajoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Puesto de trabajo no encontrado"));
        return puestoDeTrabajoMapper.convertirADTO(entidad);
    }

    // Metodo para crear un nuevo puesto de trabajo, convertir el DTO a entidad,
    // guardarlo en la base de datos y devolverlo como DTO
    @Override
    public PuestoDeTrabajoResponseDTO crearPuestoDeTrabajo(PuestoDeTrabajoRequestDTO puestoDeTrabajoRequestDTO) {
        String nombre = puestoDeTrabajoRequestDTO.getNombre().trim();

        // Comprobar si el puesto de trabajo ya existe en la base de datos (ignorando
        // mayusculas/minusculas)
        if (puestoDeTrabajoRepository.findByNombreIgnoreCase(nombre).isPresent()) {
            throw new RuntimeException("El puesto de trabajo '" + nombre + "' ya existe");
        }
        PuestoDeTrabajoEntity entidad = puestoDeTrabajoMapper.convertirAEntity(puestoDeTrabajoRequestDTO);
        return puestoDeTrabajoMapper.convertirADTO(puestoDeTrabajoRepository.save(entidad));
    }

    // Metodo para actualizar un puesto de trabajo existente, comprobar si el nuevo nombre ya existe,
    // actualizar la entidad, guardarla en la base de datos y devolverla como DTO
    @Override
    public PuestoDeTrabajoResponseDTO actualizarPuestoDeTrabajo(Integer id,
            PuestoDeTrabajoRequestDTO puestoDeTrabajoRequestDTO) {

        PuestoDeTrabajoEntity entidadExistente = puestoDeTrabajoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Puesto de trabajo no encontrado"));
        String nuevoNombre = puestoDeTrabajoRequestDTO.getNombre().trim();
        // Comprobar si el puesto de trabajo ya existe en la base de datos (ignorando
        // mayusculas/minusculas)
        Optional<PuestoDeTrabajoEntity> puestoDeTrabajoExistente = puestoDeTrabajoRepository.findByNombreIgnoreCase(nuevoNombre);
        if (puestoDeTrabajoExistente.isPresent() && !puestoDeTrabajoExistente.get().getId().equals(id)) {
            throw new RuntimeException("El puesto de trabajo '" + nuevoNombre + "' ya existe");
        }
        entidadExistente.setNombre(nuevoNombre);
        return puestoDeTrabajoMapper.convertirADTO(puestoDeTrabajoRepository.save(entidadExistente));
    }

    // Metodo para eliminar un puesto de trabajo por su ID
    @Override
    public void eliminarPuestoDeTrabajo(Integer id) {
        puestoDeTrabajoRepository.deleteById(id);
    }

}

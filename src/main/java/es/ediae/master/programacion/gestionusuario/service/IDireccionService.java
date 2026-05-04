package es.ediae.master.programacion.gestionusuario.service;

import java.util.List;

import org.springframework.stereotype.Service;

import es.ediae.master.programacion.gestionusuario.entity.DireccionEntity;


@Service
public interface IDireccionService {
public List<DireccionEntity> obtenerTodasLasDirecciones();
}

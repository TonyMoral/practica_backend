package es.ediae.master.programacion.gestionusuario.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.ediae.master.programacion.gestionusuario.entity.DireccionEntity;
import es.ediae.master.programacion.gestionusuario.service.IDireccionService;

@Service
public class DireccionServiceImpl implements IDireccionService {

    @Autowired
    private IDireccionService direccionService;
    
    @Override
    public List<DireccionEntity> obtenerTodasLasDirecciones() {
        return direccionService.obtenerTodasLasDirecciones();
    }

}

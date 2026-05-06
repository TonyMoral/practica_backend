 package es.ediae.master.programacion.gestionusuario.controller.handlers;

 import org.springframework.http.HttpStatus;
 import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
 import org.springframework.web.bind.annotation.RestControllerAdvice;

import es.ediae.master.programacion.gestionusuario.exception.GeneralException;
import es.ediae.master.programacion.gestionusuario.exception.UnsuportedOperationException;

 @RestControllerAdvice
 public class GlobalControllerExceptionHandler {

     @ExceptionHandler(UnsuportedOperationException.class)
     public ResponseEntity<String> handleUnsuportedOperationException(UnsuportedOperationException ex) {
         return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(ex.getMessage());
    }

    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<ModelMap> handleGeneralException(GeneralException ex) {
        ModelMap model = new ModelMap();
        model.addAttribute("error", ex.getClass().getSimpleName());
        model.addAttribute("codigoDeError", ex.getCodigoDeError());
        model.addAttribute("mensajeDeError", ex.getMensajeDeError());
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(model);
    }

 }

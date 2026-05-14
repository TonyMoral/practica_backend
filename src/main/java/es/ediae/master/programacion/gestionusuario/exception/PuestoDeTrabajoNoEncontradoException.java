package es.ediae.master.programacion.gestionusuario.exception;

import es.ediae.master.programacion.gestionusuario.constant.GeneralConstant;

public class PuestoDeTrabajoNoEncontradoException extends GeneralException {

     public PuestoDeTrabajoNoEncontradoException() {
        super(GeneralConstant.NO_ENCONTRADO_ERROR_CODE, GeneralConstant.PUESTO_DE_TRABAJO_NO_ENCONTRADO_ERROR_MESSAGE);
    }

}

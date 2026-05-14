package es.ediae.master.programacion.gestionusuario.exception;

import es.ediae.master.programacion.gestionusuario.constant.GeneralConstant;

public class UnsuportedOperationException extends GeneralException {

    public UnsuportedOperationException() {
        super(GeneralConstant.OPERACION_NO_SOPORTADA_ERROR_CODE, GeneralConstant.OPERACION_NO_SOPORTADA_ERROR_MESSAGE);
    }

}

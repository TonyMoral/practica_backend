package es.ediae.master.programacion.gestionusuario.exception;

import es.ediae.master.programacion.gestionusuario.constant.GeneralConstant;

public class PuestoDeTrabajoYaExisteException extends GeneralException {

         public PuestoDeTrabajoYaExisteException() {
        super(GeneralConstant.PUESTO_DE_TRABAJO_YA_EXISTE_ERROR_CODE, GeneralConstant.PUESTO_DE_TRABAJO_YA_EXISTE_ERROR_MESSAGE);
    }

}

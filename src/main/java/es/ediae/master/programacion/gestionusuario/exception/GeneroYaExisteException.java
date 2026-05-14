package es.ediae.master.programacion.gestionusuario.exception;

import es.ediae.master.programacion.gestionusuario.constant.GeneralConstant;

public class GeneroYaExisteException extends GeneralException{

        public GeneroYaExisteException() {
        super(GeneralConstant.GENERO_YA_EXISTE_ERROR_CODE, GeneralConstant.GENERO_YA_EXISTE_ERROR_MESSAGE);
    }

}

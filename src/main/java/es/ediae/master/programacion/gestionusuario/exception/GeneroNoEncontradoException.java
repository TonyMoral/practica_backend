package es.ediae.master.programacion.gestionusuario.exception;

import es.ediae.master.programacion.gestionusuario.constant.GeneralConstant;

public class GeneroNoEncontradoException extends GeneralException{

    public GeneroNoEncontradoException() {
        super(GeneralConstant.NO_ENCONTRADO_ERROR_CODE, GeneralConstant.GENERO_NO_ENCONTRADO_ERROR_MESSAGE);
    }

}

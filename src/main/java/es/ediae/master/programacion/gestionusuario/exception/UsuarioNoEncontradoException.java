package es.ediae.master.programacion.gestionusuario.exception;

import es.ediae.master.programacion.gestionusuario.constant.GeneralConstant;

public class UsuarioNoEncontradoException extends GeneralException {

    public UsuarioNoEncontradoException() {
        super(GeneralConstant.NO_ENCONTRADO_ERROR_CODE, GeneralConstant.USUARIO_NO_ENCONTRADO_ERROR_MESSAGE);
    }

}

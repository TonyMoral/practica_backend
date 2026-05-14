package es.ediae.master.programacion.gestionusuario.exception;

import es.ediae.master.programacion.gestionusuario.constant.GeneralConstant;

public class UsuarioNickYaExisteException extends GeneralException {

        public UsuarioNickYaExisteException() {
        super(GeneralConstant.USUARIO_NICK_YA_EXISTE_ERROR_CODE, GeneralConstant.USUARIO_NICK_YA_EXISTE_ERROR_MESSAGE);
    }
}

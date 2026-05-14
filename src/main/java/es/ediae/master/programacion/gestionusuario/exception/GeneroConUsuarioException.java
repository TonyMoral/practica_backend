package es.ediae.master.programacion.gestionusuario.exception;

import es.ediae.master.programacion.gestionusuario.constant.GeneralConstant;

public class GeneroConUsuarioException extends GeneralException {

        public GeneroConUsuarioException() {
        super(GeneralConstant.GENERO_CON_USUARIO_ERROR_CODE, GeneralConstant.GENERO_CON_USUARIO_ERROR_MESSAGE);
    }

}

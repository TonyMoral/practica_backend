package es.ediae.master.programacion.gestionusuario.exception;

import es.ediae.master.programacion.gestionusuario.constant.GeneralConstant;

public class ImagenNoEncontradaException extends GeneralException{

    public ImagenNoEncontradaException() {
        super(GeneralConstant.NO_ENCONTRADO_ERROR_CODE, GeneralConstant.IMAGEN_DE_USUARIO_NO_ENCONTRADO_ERROR_MESSAGE);
    }

}

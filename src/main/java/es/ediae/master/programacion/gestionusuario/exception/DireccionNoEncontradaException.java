package es.ediae.master.programacion.gestionusuario.exception;

import es.ediae.master.programacion.gestionusuario.constant.GeneralConstant;

public class DireccionNoEncontradaException extends GeneralException {

    public DireccionNoEncontradaException() {
        super(GeneralConstant.NO_ENCONTRADO_ERROR_CODE, GeneralConstant.DIRECCION_NO_ENCONTRADA_ERROR_MESSAGE);
    }



}

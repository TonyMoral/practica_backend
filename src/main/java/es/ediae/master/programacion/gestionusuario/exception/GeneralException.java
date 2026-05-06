package es.ediae.master.programacion.gestionusuario.exception;

public class GeneralException extends Exception {

    public GeneralException(int codigoDeError, String mensajeDeError) {
        super(mensajeDeError);
        this.codigoDeError = codigoDeError;
        this.mensajeDeError = mensajeDeError;
    }

    public GeneralException(String operacionNoSoportadaErrorCode, String operacionNoSoportadaErrorCode2) {
        //TODO Auto-generated constructor stub
    }

    private int codigoDeError;

    private String mensajeDeError;

    public int getCodigoDeError() {
        return codigoDeError;
    }

    public void setCodigoDeError(int codigoDeError) {
        this.codigoDeError = codigoDeError;
    }

    public String getMensajeDeError() {
        return mensajeDeError;
    }

    public void setMensajeDeError(String mensajeDeError) {
        this.mensajeDeError = mensajeDeError;
    }
}

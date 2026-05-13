package es.ediae.master.programacion.gestionusuario.dto;

public class ImagenUsuarioRequestDTO {

    private Integer usuarioId;
    private String imagen;

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

}

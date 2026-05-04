package es.ediae.master.programacion.gestionusuario.dto;

import jakarta.validation.constraints.NotEmpty;

public class ProductoDTO {

    //Borrar en algun momento, solo para pruebas con la validacioncontroller
    private Integer id;

    @NotEmpty(message = "El nombre del producto no puede estar vacío")
    private String nombre;
    @NotEmpty(message = "La descripción del producto no puede estar vacía")
    private String descripcion;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}

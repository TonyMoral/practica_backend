package es.ediae.master.programacion.gestionusuario.dto;

public class UsuarioResponseDTO {

    private Integer id;

    private String nickUsuario;
    private String nombre;
    private String primerApellido;
    private String segundoApellido;
    private String horaDesayuno;
    private String fechaNacimiento;
    
    private Integer generoId;
    private String generoNombre;

    private Integer puestoDeTrabajoId;
    private String puestoDeTrabajoNombre;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNickUsuario() {
        return nickUsuario;
    }

    public void setNickUsuario(String nickUsuario) {
        this.nickUsuario = nickUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getHoraDesayuno() {
        return horaDesayuno;
    }

    public void setHoraDesayuno(String horaDesayuno) {
        this.horaDesayuno = horaDesayuno;
    }

    public Integer getGeneroId() {
        return generoId;
    }

    public void setGeneroId(Integer generoId) {
        this.generoId = generoId;
    }

    public String getGeneroNombre() {
        return generoNombre;
    }

    public void setGeneroNombre(String generoNombre) {
        this.generoNombre = generoNombre;
    }

    public Integer getPuestoDeTrabajoId() {
        return puestoDeTrabajoId;
    }

    public void setPuestoDeTrabajoId(Integer puestoDeTrabajoId) {
        this.puestoDeTrabajoId = puestoDeTrabajoId;
    }

    public String getPuestoDeTrabajoNombre() {
        return puestoDeTrabajoNombre;
    }

    public void setPuestoDeTrabajoNombre(String puestoDeTrabajoNombre) {
        this.puestoDeTrabajoNombre = puestoDeTrabajoNombre;

    }


    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

}

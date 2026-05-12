package es.ediae.master.programacion.gestionusuario.entity;
import java.sql.Date;
import java.sql.Time;

import jakarta.persistence.*;

@Entity
@Table(name = "usuario")
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nick_usuario", nullable = false)
    private String nickUsuario;

    @Column(nullable = false)
    private String contrasena;

    @Column(name = "fecha_hora_creacion")
    private Date fechaHoraCreacion;

    @Column(nullable = false)
    private String nombre;

    @Column(name = "primer_apellido", nullable = false)
    private String primerApellido;

    @Column(name = "segundo_apellido", nullable = true)
    private String segundoApellido;

    @Column(name = "fecha_nacimiento", nullable = false)
    private String fechaNacimiento;

    @Column(name="hora_desayuno", nullable = true)
    private Time horaDesayuno;

    @ManyToOne
    @JoinColumn(name = "genero_id", nullable = false)
    private GeneroEntity genero;

    @ManyToOne
    @JoinColumn(name = "puesto_de_trabajo_id", nullable = true)
    private PuestoDeTrabajoEntity puestoDeTrabajo;

    @Column(name = "es_admin", nullable = false)
    private Boolean esAdmin;

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

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Date getFechaHoraCreacion() {
        return fechaHoraCreacion;
    }

    public void setFechaHoraCreacion(Date fechaHoraCreacion) {
        this.fechaHoraCreacion = fechaHoraCreacion;
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

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Time getHoraDesayuno() {
        return horaDesayuno;
    }

    public void setHoraDesayuno(Time horaDesayuno) {
        this.horaDesayuno = horaDesayuno;
    }

    public GeneroEntity getGenero() {
        return genero;
    }

    public void setGenero(GeneroEntity genero) {
        this.genero = genero;
    }

    public PuestoDeTrabajoEntity getPuestoDeTrabajo() {
        return puestoDeTrabajo;
    }

    public void setPuestoDeTrabajo(PuestoDeTrabajoEntity puestoDeTrabajo) {
        this.puestoDeTrabajo = puestoDeTrabajo;
    }

    public Boolean getEsAdmin() {
        return esAdmin;
    }

    public void setEsAdmin(Boolean esAdmin) {
        this.esAdmin = esAdmin;
    }
}

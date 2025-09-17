package com.carloschacon.ApiAhorcadoIN5BM.model;


import jakarta.persistence.*;

@Entity
@Table(name="Usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_usuario")
    private Integer codigoUsuario;

    @Column(name = "correo")
    private String correo;

    @Column(name = "contra")
    private String contra;

    public Usuario() {
    }

    public Usuario(Integer codigoUsuario, String correo, String contra) {
        this.codigoUsuario = codigoUsuario;
        this.correo = correo;
        this.contra = contra;
    }

    public Integer getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(Integer codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }
}


package com.carloschacon.ApiAhorcadoIN5BM.model;


import jakarta.persistence.*;

@Entity
@Table(name="Palabras")
public class Palabra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="codigo_palabra")
    private Integer codigoPalabra;

    @Column(name="nombre")
    private String nombre;

    @Column(name="cualidad_uno")
    private String cualidadUno;

    @Column(name="cualidad_dos")
    private String cualidadDos;

    @Column(name="cualidad_tres")
    private String cualidadTres;

    public Palabra() {
    }

    public Palabra(Integer codigoPalabra, String nombre, String cualidadUno, String cualidadDos, String cualidadTres) {
        this.codigoPalabra = codigoPalabra;
        this.nombre = nombre;
        this.cualidadUno = cualidadUno;
        this.cualidadDos = cualidadDos;
        this.cualidadTres = cualidadTres;
    }

    public Integer getCodigoPalabra() {
        return codigoPalabra;
    }

    public void setCodigoPalabra(Integer codigoPalabra) {
        this.codigoPalabra = codigoPalabra;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCualidadUno() {
        return cualidadUno;
    }

    public void setCualidadUno(String cualidadUno) {
        this.cualidadUno = cualidadUno;
    }

    public String getCualidadDos() {
        return cualidadDos;
    }

    public void setCualidadDos(String cualidadDos) {
        this.cualidadDos = cualidadDos;
    }

    public String getCualidadTres() {
        return cualidadTres;
    }

    public void setCualidadTres(String cualidadTres) {
        this.cualidadTres = cualidadTres;
    }
}

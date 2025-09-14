package Modelo;

public class Palabra {
    private int codigoPalabra;
    private String nombre;
    private String cualidadUno;
    private String cualidadDos;
    private String cualidadTres;

    public int getCodigoPalabra() {
        return codigoPalabra;
    }
    public void setCodigoPalabra(int codigoPalabra) {
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

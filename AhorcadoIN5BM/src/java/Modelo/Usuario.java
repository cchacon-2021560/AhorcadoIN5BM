package Modelo;

public class Usuario {
    
    private int codigoUsuario;
    private String correo;
    private String contra;

    public Usuario() {
    }

    public Usuario(int codigoUsuario, String correo, String contra) {
        this.codigoUsuario = codigoUsuario;
        this.correo = correo;
        this.contra = contra;
    }

    public int getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(int codigoUsuario) {
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

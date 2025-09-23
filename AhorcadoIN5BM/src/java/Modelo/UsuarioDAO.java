package Modelo;

import Config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int resp;

    public List<Usuario> listar() {
        String sql = "call sp_ListarUsuarios()";
        List<Usuario> listaUsuarios = new ArrayList<>();

        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Usuario p = new Usuario();
                p.setCodigoUsuario(rs.getInt("codigo_usuario"));
                p.setCorreo(rs.getString("correo"));
                p.setContra(rs.getString("contra"));

                listaUsuarios.add(p);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaUsuarios;
    }

    public int agregar(Usuario u) {
        String sql = "call sp_AgregarUsuario(?, ?)";

        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);

            ps.setString(1, u.getCorreo());
            ps.setString(2, u.getContra());

            resp = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resp;
    }
    
    public Usuario validar(String correo, String contra) {
    Usuario u = null;
    String sql = "SELECT * FROM Usuarios WHERE correo=? AND contra=?";
    try {
        con = cn.Conexion();
        ps = con.prepareStatement(sql);
        ps.setString(1, correo);
        ps.setString(2, contra);
        rs = ps.executeQuery();
        if (rs.next()) {
            u = new Usuario();
            u.setCodigoUsuario(rs.getInt("codigo_usuario"));
            u.setCorreo(rs.getString("correo"));
            u.setContra(rs.getString("contra"));
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return u;
}

    
}

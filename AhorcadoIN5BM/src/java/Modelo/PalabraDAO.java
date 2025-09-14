package Modelo;

import Config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PalabraDAO {

    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int resp;

    public List<Palabra> listar() {
        String sql = "call sp_ListarPalabras()";
        List<Palabra> listaPalabras = new ArrayList<>();

        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Palabra p = new Palabra();
                p.setCodigoPalabra(rs.getInt("codigoPalabra"));
                p.setNombre(rs.getString("nombre"));
                p.setCualidadUno(rs.getString("cualidadUno"));
                p.setCualidadDos(rs.getString("cualidadDos"));
                p.setCualidadTres(rs.getString("cualidadTres"));

                listaPalabras.add(p);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaPalabras;
    }

    public int agregar(Palabra p) {
        String sql = "call sp_AgregarPalabra(?, ?, ?, ?)";

        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);

            ps.setString(1, p.getNombre());
            ps.setString(2, p.getCualidadUno());
            ps.setString(3, p.getCualidadDos());
            ps.setString(4, p.getCualidadTres());

            resp = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resp;
    }
}

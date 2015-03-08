package DAO;

import Bean.Articulos;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class blogDAO implements Serializable{
    private static DataSource ds;
    private static Connection conexion;
    public static Articulos articulo;
    public blogDAO() throws SQLException, NamingException {
   
    }
    public static Connection conectarbd() throws SQLException, NamingException {
        try {
            Context ctx = new InitialContext();
            ds = (DataSource)ctx.lookup("java:comp/env/jdbc/Blog");
        } catch (NamingException e) {
            e.printStackTrace();
        }
        if(ds==null) { 
            throw new SQLException("No se pueden obtener datos."); 
        }
        Connection con = ds.getConnection();
        if(con==null) { 
            throw new SQLException("No se puede obtener conexion con la base de datos"); 
        }
        else {
            System.out.println("Contectado a la base de datos");
        }
        
        return con;
    }
    public static boolean añadir (Articulos art) throws SQLException, NamingException {
        try {
            Date fecha = new Date();
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
            String FechaFormato = sdf.format(fecha);
            
            conexion = conectarbd();
            PreparedStatement ps = conexion.prepareStatement("INSERT INTO Articulos (Titulo, Contenido, Fecha) VALUES (?,?,?)"); 
            ps.setString(1, art.getTitulo());       
            ps.setString(2, art.getContenido());
            ps.setString(3, FechaFormato);
            ps.executeUpdate();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
	}   
        return true;
    }
    public static boolean borrar (Articulos art) throws SQLException, NamingException {
        try {
            conexion = conectarbd();
            PreparedStatement ps = conexion.prepareStatement("DELETE Articulos WHERE Id = ?"); 
            ps.setInt(1, art.getId());
            ps.executeUpdate();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
	}   
        return true;
    }
    public static boolean actualizar (Articulos art) throws SQLException, NamingException {
        try {
            conexion = conectarbd();
            PreparedStatement ps = conexion.prepareStatement("UPDATE Articulos SET Titulo = ?, Contenido = ? "+ " WHERE Id = ?");
            ps.setString(1, art.getTitulo());
            ps.setString(2, art.getContenido());
            ps.setInt(3, art.getId());
            ps.executeUpdate();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
	}   
        return true;
    }
    public static List<Articulos> listar() throws NamingException, SQLException {
        List<Articulos> articulos = new ArrayList<Articulos>();
        conexion = conectarbd();
        PreparedStatement ps = conexion.prepareStatement("select * from Articulos"); 
        ResultSet result =  ps.executeQuery();
            while(result.next()){
                    Articulos ArtRow = new Articulos();
                    ArtRow.setId(result.getInt("Id"));
                    ArtRow.setTitulo(result.getString("Titulo"));
                    ArtRow.setContenido(result.getString("Contenido"));
                    articulos.add(ArtRow);
            }
         return articulos;
    }
    public static Articulos articuloID(int id) throws NamingException, SQLException {
        Articulos articulo = new Articulos();
        conexion = conectarbd();
        PreparedStatement ps = conexion.prepareStatement("select * from Articulos where Id = ?");
        ps.setInt(1, id);
        ResultSet result =  ps.executeQuery();
        
        while(result.next()){
            articulo.setId(result.getInt("Id"));
            articulo.setTitulo(result.getString("Titulo")); 
            articulo.setContenido(result.getString("Contenido")); 
        }
         return articulo;
    }
}
package DAO;

import Bean.Autores;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

public class autorDAO implements Serializable{
    private static DataSource ds;
    private static Connection conexion;
    public static Autores autor;

    public autorDAO() throws SQLException, NamingException {
        HttpSession miSession=(HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        miSession.setMaxInactiveInterval(5000);        
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
        return con;
    }
    public static boolean añadir (Autores aut) throws SQLException, NamingException {
        try {
            conexion = conectarbd();
            PreparedStatement ps = conexion.prepareStatement("INSERT INTO Autores (Nombre, Apellidos, Usuario, Contraseña) VALUES (?,?,?,?)"); 
            ps.setString(1, aut.getNombre());       
            ps.setString(2, aut.getApellidos());
            ps.setString(3, aut.getUsuario());
            ps.setString(4, aut.getContraseña());
            ps.executeUpdate();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
	}   
        return true;
    }
    public static boolean borrar (Autores aut) throws SQLException, NamingException {
        try {
            conexion = conectarbd();
            PreparedStatement ps = conexion.prepareStatement("DELETE FROM Blog.Autores WHERE Autores.Id = ?"); 
            ps.setInt(1, aut.getId());
            ps.executeUpdate();
            System.out.println("¡Borrado!");
        }catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
	}   
        return true;
    }
    public static boolean actualizar (Autores aut) throws SQLException, NamingException {
        try {
            conexion = conectarbd();
            PreparedStatement ps = conexion.prepareStatement("UPDATE Autores SET Nombre = ?, Apellidos = ?, Usuario = ?, Contraseña = ?" + " WHERE Id = ?");
            ps.setString(1, aut.getNombre());
            ps.setString(2, aut.getApellidos());
            ps.setString(3, aut.getUsuario());
            ps.setString(4, aut.getContraseña());
            ps.setInt(5, aut.getId());
            ps.executeUpdate();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
	}   
        return true;
    }
    public static List<Autores> listar() throws NamingException, SQLException {
        List<Autores> autores = new ArrayList<Autores>();
        conexion = conectarbd();
        PreparedStatement ps = conexion.prepareStatement("select * from Autores"); 
        ResultSet result =  ps.executeQuery();
            while(result.next()){
                    Autores AutRow = new Autores();
                    AutRow.setId(result.getInt("Id"));
                    AutRow.setNombre(result.getString("Nombre"));
                    AutRow.setApellidos(result.getString("Apellidos"));
                    AutRow.setUsuario(result.getString("Usuario"));
                    autores.add(AutRow);
            }
         return autores;
    }
    public static Autores autorID(int id) throws NamingException, SQLException {
        Autores autorAr = new Autores();
        conexion = conectarbd();
        PreparedStatement ps = conexion.prepareStatement("select * from Articulos where Id = ?");
        ps.setInt(1, id);
        ResultSet result =  ps.executeQuery();
        
        while(result.next()){
            autorAr.setId(result.getInt("Id"));
            autorAr.setNombre(result.getString("Nombre")); 
            autorAr.setApellidos(result.getString("Apellidos")); 
            autorAr.setUsuario(result.getString("Usuario")); 
        }
         return autorAr;
    }
    public static boolean login(Autores aut) throws NamingException {
        try {
            conexion = conectarbd();
            PreparedStatement ps = conexion.prepareStatement("select * from Autores where Usuario = ? and Contraseña = ?");
            ps.setString(1, aut.getUsuario());
            ps.setString(2, aut.getContraseña());
            ResultSet result =  ps.executeQuery();
            if (result.next()) { // Encontrado
                System.out.println("Encontrado en la base de datos");
                HttpSession httpSession=(HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
                httpSession.setAttribute("usuario", aut.getUsuario());
                System.out.println("Sesion abierta");
                return true;
            }
            else {
                System.out.println("No encontrado en la base de datos");
                return false;
            }
        }catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
	}   
    }
    
    public static void cerrarSession() {
        HttpSession httpSession=(HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        httpSession.invalidate();
        System.out.println("Sesion cerrada");
    }
}
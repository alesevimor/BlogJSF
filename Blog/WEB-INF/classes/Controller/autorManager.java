package Controller;
import Bean.Articulos;
import Bean.Autores;
import DAO.autorDAO;
import DAO.blogDAO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.naming.NamingException;
@ManagedBean(name="autorMg")
@ApplicationScoped

public class autorManager implements Serializable {
    private Autores autor;
    private List<Autores> lista;
    public boolean logueado;
    
    public autorManager() {
        autor = new Autores();
        logueado = false;
    }
    public String añadir() throws SQLException, NamingException {
        if (autorDAO.añadir(autor)) {
            return "index";
        }
        else {
            return "error";
        }   
    }
    
    public String borrar(Autores aut) throws SQLException, NamingException {
        if (autorDAO.borrar(aut)) {
            return "gestionAutores";
        }
        else {
            return "error";
        }   
    }
    
    public String actualizar() throws SQLException, NamingException {
        if (autorDAO.actualizar(autor)) {
            return "gestionAutores";
        }
        else {
            return "error";
        }   
    }
    
    public List listar() throws NamingException, SQLException {
        lista=autorDAO.listar();
        return lista;
    }

    public String login() throws SQLException, NamingException {
        if (autorDAO.login(autor)) {
            logueado = true;
            return "index";
        } 
        else {
            logueado = false;
            return "error";
        }
    }

    public String cerrarSession() {
        autorDAO.cerrarSession();
        logueado = false;
        return "index";
    }
    
    public Autores getAutor() {
        return autor;
    }

    public void setAutor(Autores autor) {
        this.autor = autor;
    }
    public boolean isLogueado() {
        return logueado;
    }

    public void setLogueado(boolean logueado) {
        this.logueado = logueado;
    }
}

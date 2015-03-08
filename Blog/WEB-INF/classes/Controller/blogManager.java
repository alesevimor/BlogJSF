package Controller;
import Bean.Articulos;
import DAO.autorDAO;
import DAO.blogDAO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.naming.NamingException;
import javax.sql.DataSource;
@ManagedBean(name="blog")
@ApplicationScoped

public class blogManager implements Serializable {
    private Articulos articulo;
    private List<Articulos> lista;
    
    public blogManager() {
        articulo = new Articulos();
    }
    
    public String añadir() throws SQLException, NamingException {
        if (blogDAO.añadir(articulo)) {
            return "index";
        }
        else {
            return "error";
        }   
    }
    
    public String borrar() throws SQLException, NamingException {
        if (blogDAO.borrar(articulo)) {
            return "index";
        }
        else {
            return "error";
        }   
    }
    
    public String actualizar() throws SQLException, NamingException {
        if (blogDAO.actualizar(articulo)) {
            return "index";
        }
        else {
            return "error";
        }   
    }
    
    public List listar() throws NamingException, SQLException {
        lista=blogDAO.listar();
        return lista;
    }
    
    public Articulos getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulos articulo) {
        this.articulo = articulo;
    }
}

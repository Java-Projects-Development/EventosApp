package com.eventos.eventosapp.controller;


import com.eventos.eventosapp.ejb.ProveedorFacadeLocal;
import com.eventos.eventosapp.model.Proveedor;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.ServletContext;

/**
 *
 * @author said
 */
@Named
@ViewScoped
public class ProveedorController implements Serializable {

    @EJB
    private ProveedorFacadeLocal proveedorEJB;

    private Proveedor proveedor;
    private List<Proveedor> proveedores;

    @PostConstruct
    public void init() {

        proveedor = new Proveedor();
        proveedores = proveedorEJB.findAll();

    }

    /**
     * Metodo para registrar un proveedor
     *
     */
    public void registrar() {
        try {
            proveedorEJB.create(proveedor);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Proveedor registrado con éxito"));
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error!"));

        }
    }

    public void modificar() {
        try {
            proveedorEJB.edit(proveedor);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Proveedor modificado con éxito"));
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error!"));

        }
    }

//    public void verReporte() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
//
//        //Instancia hacia la clase reporteClientes        
//        ReporteProveedor rProveedor = new ReporteProveedor();
//
//        FacesContext facesContext = FacesContext.getCurrentInstance();
//        ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
//        String ruta = servletContext.getRealPath("/reportes/reporteProveedor.jasper");
//
//        rProveedor.getReporte(ruta);
//        FacesContext.getCurrentInstance().responseComplete();
//    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public List<Proveedor> getProveedores() {
        return proveedores;
    }

    public void setProveedores(List<Proveedor> proveedores) {
        this.proveedores = proveedores;
    }

}

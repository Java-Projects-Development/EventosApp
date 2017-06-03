/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eventos.eventosapp.controller;

import com.eventos.eventosapp.ejb.ProductoFacadeLocal;
import com.eventos.eventosapp.ejb.TipoProductoFacadeLocal;
import com.eventos.eventosapp.model.Producto;
import com.eventos.eventosapp.model.TipoProducto;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

/**
 *
 * @author elgor
 */
@ViewScoped
@ManagedBean
public class ProductoController implements Serializable {

    @EJB
    private ProductoFacadeLocal productoEJB;
    @EJB
    private TipoProductoFacadeLocal tipoProductoEJB;

    private Producto producto;
    private TipoProducto tipoProducto;

    private List<Producto> productos;
    private List<TipoProducto> tipos;

    @PostConstruct
    public void init() {
        tipoProducto = new TipoProducto();
        producto = new Producto();
        productos = productoEJB.findAll();
        tipos = tipoProductoEJB.findAll();

    }

    public void registrar() {
        try {
            this.producto.setTipoProducto(tipoProducto);
            productoEJB.create(producto);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Producto registrado con éxito"));
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error!"));

        }

    }

    public void modificar() {
        try {
            this.producto.setTipoProducto(tipoProducto);
            productoEJB.edit(producto);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Productro modificado con éxito"));
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error!"));

        }

    }
    
    public void eliminar() {
        try {
            
            productoEJB.remove(producto);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Removido con éxito"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error!"));

        }

    }

    //Getter y Setter
    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public TipoProducto getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(TipoProducto tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    public List<TipoProducto> getTipos() {
        return tipos;
    }

    public void setTipos(List<TipoProducto> tipos) {
        this.tipos = tipos;
    }
    
    

}

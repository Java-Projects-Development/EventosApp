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
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author elgor
 */
@Named
@ViewScoped
public class ProductoController implements Serializable {

    @EJB
    private ProductoFacadeLocal productoEJB;
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
        

    }

    public void registrar() {
        try {
            producto.setTipoProducto(tipoProducto);
            productoEJB.create(producto);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se registró"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error!"));

        }

    }

    public void modificar() {
        try {
            producto.setTipoProducto(tipoProducto);
            productoEJB.edit(producto);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se registró"));
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

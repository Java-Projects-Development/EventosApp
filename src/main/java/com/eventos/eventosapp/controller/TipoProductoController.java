/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eventos.eventosapp.controller;

import com.eventos.eventosapp.ejb.TipoProductoFacadeLocal;
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
 * @author NERV
 */
@Named
@ViewScoped
public class TipoProductoController implements Serializable{
    @EJB
    private TipoProductoFacadeLocal tipoProductoEJB;
    
    private TipoProducto tipoProducto;
    
    private List<TipoProducto> tipos;
    
    @PostConstruct
    public void init(){
        tipoProducto = new TipoProducto();
        tipos = tipoProductoEJB.findAll();
    }

    public void registrar(){
        try {
            tipoProductoEJB.create(tipoProducto);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Nuevo Tipo de Producto Registrado"));
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error!"));
        }
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

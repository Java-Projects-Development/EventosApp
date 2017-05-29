/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eventos.eventosapp.controller;


import com.eventos.eventosapp.ejb.DetalleFacturaFacadeLocal;
import com.eventos.eventosapp.ejb.FacturaFacadeLocal;
import com.eventos.eventosapp.ejb.ProductoFacadeLocal;
import com.eventos.eventosapp.ejb.ProveedorFacadeLocal;
import com.eventos.eventosapp.model.DetalleFactura;
import com.eventos.eventosapp.model.Factura;
import com.eventos.eventosapp.model.Producto;
import com.eventos.eventosapp.model.Proveedor;
import com.eventos.eventosapp.model.TipoProducto;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.event.CellEditEvent;

/**
 *
 * @author said
 */
@Named
@ViewScoped
public class EntradaProductoController implements Serializable {

      private Proveedor proveedorSeleccionado;
      private Producto productoSeleccionado;
      private Double cantidadProducto;
      private Factura factura;
      private BigDecimal precioUnitario;
      private DetalleFactura detalleFactura;
      private TipoProducto tipoProducto;
      @EJB
      private DetalleFacturaFacadeLocal detalleEJB;
      private BigDecimal totalDetalle;

      @EJB
      private FacturaFacadeLocal facturaEJB;
      @EJB
      private ProveedorFacadeLocal proveedorEJB;
      @EJB
      private ProductoFacadeLocal productoEJB;

      private List<Proveedor> proveedores;

      private List<DetalleFactura> listaDetalleFactura;

      @PostConstruct
      public void init() {

            proveedorSeleccionado = new Proveedor();
            productoSeleccionado = new Producto();
            factura = new Factura();
            proveedores = proveedorEJB.findAll();
            listaDetalleFactura = new ArrayList<>();
            detalleFactura = new DetalleFactura();

      }

      public void modificar() {
            try {
                  calcularTotalFacturaCompra();
                  detalleEJB.edit(detalleFactura);
                  FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se modifico"));

            } catch (Exception e) {
                  FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "No se modifico!"));

            }
      }


      public void calcularTotal() {
            BigDecimal totalCompraPorProducto = detalleFactura.getPrecioUnitario().multiply(new BigDecimal(detalleFactura.getCantidad()));
            
      }

      public void guardarFactura() {
            facturaEJB.create(factura);
      }

      public void guardarDetalle() {
            try {

                  if (detalleFactura.getCantidad() == 0 || detalleFactura.getPrecioUnitario().floatValue() == 0) {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "EL valor ingresado es incorrecto"));
                  } else {
                        this.listaDetalleFactura.add(detalleFactura = new DetalleFactura(factura, productoSeleccionado, detalleFactura.getCantidad(), detalleFactura.getPrecioUnitario(), BigDecimal.valueOf(detalleFactura.getPrecioUnitario().floatValue() * detalleFactura.getCantidad().floatValue())));
                        this.detalleFactura = new DetalleFactura();
                        this.productoSeleccionado = new Producto();
                        //Calcula total de la venta
                        calcularTotalFacturaCompra();
                  }
            } catch (Exception e) {
            }
      }

      public void seleccion(Proveedor seleccion) {
            proveedorSeleccionado = seleccion;
      }

      public void handleKeyEvent() {
            FacesContext.getCurrentInstance().getAttributes().keySet().add(cantidadProducto);
      }

      public Proveedor getProveedorSeleccionado() {
            return proveedorSeleccionado;

      }

      public void setProveedorSeleccionado(Proveedor proveedorSeleccionado) {
            this.proveedorSeleccionado = proveedorSeleccionado;
      }

      public List<Proveedor> getProveedores() {
            return proveedores;
      }

      public void setProveedores(List<Proveedor> proveedores) {
            this.proveedores = proveedores;
      }

      public List<DetalleFactura> getListaDetalleFactura() {
            return listaDetalleFactura;
      }

      public void setListaDetalleFactura(List<DetalleFactura> listaDetalleFactura) {
            this.listaDetalleFactura = listaDetalleFactura;
      }

      public Producto getProductoSeleccionado() {
            return productoSeleccionado;
      }

      public void setProductoSeleccionado(Producto productoSeleccionado) {
            this.productoSeleccionado = productoSeleccionado;
      }

      public Double getCantidadProducto() {
            return cantidadProducto;
      }

      public void setCantidadProducto(Double cantidadProducto) {
            this.cantidadProducto = cantidadProducto;
      }

      public Factura getFactura() {
            return factura;
      }

      public void setFactura(Factura factura) {
            this.factura = factura;
      }

      public BigDecimal getPrecioUnitario() {
            return precioUnitario;
      }

      public void setPrecioUnitario(BigDecimal precioUnitario) {
            this.precioUnitario = precioUnitario;
      }

      public void onCellEditPrecio(CellEditEvent event) {
            Object oldValue = event.getOldValue();
            Object newValue = event.getNewValue();

            detalleFactura.setPrecioUnitario((BigDecimal) event.getNewValue());

      }

      public void onCellEditCantidad(CellEditEvent event) {
            Object oldValue = event.getOldValue();
            Object newValue = event.getNewValue();

            detalleFactura.setCantidad((Double) event.getNewValue());

      }

      public void generarTotal() {

            this.detalleFactura.setTotalCompra(this.detalleFactura.getPrecioUnitario().multiply(new BigDecimal(this.detalleFactura.getCantidad().floatValue())));

      }

      public DetalleFactura getDetalleFactura() {
            return detalleFactura;
      }

      public void setDetalleFactura(DetalleFactura detalleFactura) {
            this.detalleFactura = detalleFactura;
      }

      public void calcularTotalFacturaCompra() {
            BigDecimal totalFacturaCompra = new BigDecimal(0);

            try {
                  for (DetalleFactura item : listaDetalleFactura) {
                        BigDecimal totalCompraPorProducto = item.getPrecioUnitario().multiply(new BigDecimal(item.getCantidad()));
                        item.setTotalCompra(totalCompraPorProducto);
                        totalFacturaCompra = totalFacturaCompra.add(totalCompraPorProducto);
                        this.factura.setTotalVenta(totalFacturaCompra);
                  }
                  this.factura.setTotalVenta(totalFacturaCompra);
            } catch (Exception e) {
                  System.out.println(e.getMessage());
            }

      }

      public void quitarProductoDetalleFactura() {
            detalleEJB.remove(detalleFactura);
            this.calcularTotalFacturaCompra();

      }

      //Metodo para limpiar Factura
      public void limpiarFactura() {
            this.proveedorSeleccionado = new Proveedor();
            this.factura = new Factura();
            this.listaDetalleFactura = new ArrayList<>();
            this.totalDetalle = null;
            this.productoSeleccionado = new Producto();
      }

      //Metodo para guardar Factura de compra
      public void guardarCompra() {
            try {
                  this.guardarFactura();
                  for (DetalleFactura item : listaDetalleFactura) {
                        detalleEJB.create(item);
                  }
            } catch (Exception e) {
            }
      }

      public TipoProducto getTipoProducto() {
            return tipoProducto;
      }

      public void setTipoProducto(TipoProducto tipoProducto) {
            this.tipoProducto = tipoProducto;
      }
      
}

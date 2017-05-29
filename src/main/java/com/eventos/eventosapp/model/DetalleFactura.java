/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eventos.eventosapp.model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author said
 */
@Entity
@Table(name = "detallefactura")
public class DetalleFactura implements Serializable {

      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private int idDetalleFactura;

      @JoinColumn(name = "idFactura", referencedColumnName = "idFactura")
      @ManyToOne
      private Factura idFactura;

      @JoinColumn(name = "idProducto", referencedColumnName = "idProducto")
      @ManyToOne
      private Producto idProducto;

      @Column(name = "cantidad")
      private Double cantidad;

      @Column(name = "precioUnitario")
      private BigDecimal precioUnitario;

      @Column(name = "totalCompra")
      private BigDecimal totalCompra;

      public DetalleFactura() {
      }

      public DetalleFactura(Factura idFactura, Producto idProducto, Double cantidad, BigDecimal precioUnitario, BigDecimal totalCompra) {
            this.idFactura = idFactura;
            this.idProducto = idProducto;
            this.cantidad = cantidad;
            this.precioUnitario = precioUnitario;
            this.totalCompra = totalCompra;
      }

      public int getIdDetalleFactura() {
            return idDetalleFactura;
      }

      public void setIdDetalleFactura(int idDetalleFactura) {
            this.idDetalleFactura = idDetalleFactura;
      }

      public Double getCantidad() {
            return cantidad;
      }

      public void setCantidad(Double cantidad) {
            this.cantidad = cantidad;
      }

      public BigDecimal getPrecioUnitario() {
            return precioUnitario;
      }

      public void setPrecioUnitario(BigDecimal precioUnitario) {
            this.precioUnitario = precioUnitario;
      }

      public BigDecimal getTotalCompra() {
            return totalCompra;
      }

      public void setTotalCompra(BigDecimal totalCompra) {
            this.totalCompra = totalCompra;
      }

      public Factura getIdFactura() {
            return idFactura;
      }

      public void setIdFactura(Factura idFactura) {
            this.idFactura = idFactura;
      }

      public Producto getIdProducto() {
            return idProducto;
      }

      public void setIdProducto(Producto idProducto) {
            this.idProducto = idProducto;
      }

      @Override
      public String toString() {
            return "DetalleFactura{" + "idDetalleFactura=" + idDetalleFactura + '}';
      }

}

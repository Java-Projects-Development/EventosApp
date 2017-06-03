CREATE DATABASE bd_eventos;
USE bd_eventos;

CREATE TABLE persona (
  idPersona int auto_increment primary key,
  nombres varchar(50) DEFAULT NULL,
  apellidos varchar(50) DEFAULT NULL,
  sexo varchar(1) DEFAULT NULL,
  dui varchar(10) DEFAULT NULL,
  nit varchar(17) DEFAULT NULL,
  fechaNacimiento date DEFAULT NULL,
  direccion varchar(200) DEFAULT NULL,
  telefono varchar(9) DEFAULT NULL);

CREATE TABLE usuario (
  idUsuario int auto_increment primary key, FOREIGN KEY (idUsuario) REFERENCES persona (idPersona),
  usuario varchar(50) DEFAULT NULL,
  clave varchar(50) DEFAULT NULL,
  tipo varchar(15) DEFAULT NULL,
  estado varchar(15) DEFAULT 'ACTIVO');

CREATE TABLE tipo_producto(idTipo INT auto_increment primary key, nombre varchar(50));

CREATE TABLE producto (
  idProducto int AUTO_INCREMENT primary key,
  nombre_producto varchar(45) DEFAULT NULL,
  id_tipo int, FOREIGN KEY (id_tipo) REFERENCES tipo_producto(idTipo),
  stockActual int DEFAULT NULL,
  stockMinimo int DEFAULT NULL,
  descripcion varchar(50),
  precio_compra decimal(10,2),
  precio_venta decimal(10,2));
                                                                                               

 CREATE TABLE proveedor (
  idProveedor int AUTO_INCREMENT primary key,
  proveedor varchar(45) DEFAULT NULL,
  propietario varchar(45) DEFAULT NULL,
  nrc varchar(8),
  tipo_producto varchar(25),
  forma_entrega varchar(25),
  direccion varchar(200) DEFAULT NULL,
  telefono varchar(9) DEFAULT NULL);                                                                                                                          

CREATE TABLE factura (
  idFactura int AUTO_INCREMENT PRIMARY KEY,
  idProveedor int, FOREIGN KEY (idProveedor) REFERENCES proveedor(idProveedor),
  numeroFactura int(5) DEFAULT NULL,
  totalVenta decimal(10,2) DEFAULT NULL,
  fechaRegistro timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP);
  
CREATE TABLE detallefactura (
  idDetalleFactura int AUTO_INCREMENT primary key,
  idFactura int DEFAULT NULL, foreign key (idFactura) REFERENCES factura(idFactura),
  idProducto int DEFAULT NULL, foreign key (idProducto) REFERENCES producto(idProducto),
  cantidad double DEFAULT NULL,
  precioUnitario decimal(10,2) DEFAULT NULL,
  totalCompra decimal(10,2) DEFAULT NULL);



CREATE TABLE Tipo_de_Catalogo(id_tipo_catalogo INT auto_increment primary key, nombre varchar(50));
CREATE TABLE CatalogoXProducto(id_catalogoXproducto INT auto_increment primary key,id_producto INT ,id_tipo_catalogo INT,FOREIGN KEY (id_tipo_catalogo) REFERENCES Tipo_de_Catalogo (id_tipo_catalogo),FOREIGN KEY (id_producto) REFERENCES Producto (id_producto)); 

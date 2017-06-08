CREATE DATABASE bd_eventos_decoraciones1;
USE bd_eventos_decoraciones1;

CREATE TABLE Tipo_de_Usuario(id_tipo INT IDENTITY (1,1)primary key, nombre varchar(20));
CREATE TABLE Empleado(id_empleado INT IDENTITY (1,1) primary key,nombres varchar(20),apellidos varchar(20),id_tipo INT, FOREIGN KEY (id_tipo) REFERENCES Tipo_de_Usuario(id_tipo)); 
CREATE TABLE Usuario(id_usuario INT IDENTITY (1,1) primary key,usuario varchar(20),contraseña NVARCHAR(50),id_empleado INT, FOREIGN KEY (id_empleado ) REFERENCES  Empleado(id_empleado)); 
                                                                                               

CREATE TABLE Tipo_de_Producto(id_tipo INT IDENTITY (1,1)primary key, nombre varchar(20));
CREATE TABLE Producto(id_producto INT IDENTITY (1,1) primary key, nombre varchar(20),descripcion VARCHAR(50),id_tipo INT, FOREIGN KEY (id_tipo) REFERENCES Tipo_de_Producto(id_tipo));
CREATE TABLE Existensia(id_existensia INT IDENTITY (1,1) primary key,id_producto INT, precio FLOAT,precio_de_venta FLOAT,existencia_producto INT,FOREIGN KEY (id_producto) REFERENCES Producto (id_producto));
                                                                                                                            


CREATE TABLE Proveedor(id_proveedor INT IDENTITY (1,1) primary key,nombre varchar(20),apellido varchar(20),direccion varchar(20),telefono varchar(20)); 
CREATE TABLE ProveedorXProducto(id_proveedorXproducto INT IDENTITY (1,1) primary key,id_proveedor INT ,id_producto INT,FOREIGN KEY (id_producto) REFERENCES Producto (id_producto),FOREIGN KEY (id_proveedor) REFERENCES Proveedor (id_proveedor)); 

CREATE TABLE Venta(id_venta INT IDENTITY (1,1) primary key, fecha_de_registro date,cantidad INT); 
CREATE TABLE VentaXProducto(id_ventaXproducto INT IDENTITY (1,1) primary key,id_venta INT ,id_producto INT,FOREIGN KEY (id_venta) REFERENCES Venta (id_venta),FOREIGN KEY (id_producto) REFERENCES Producto (id_producto)); 



CREATE TABLE Tipo_de_Catalogo(id_tipo_catalogo INT IDENTITY (1,1)primary key, nombre varchar(20));
CREATE TABLE CatalogoXProducto(id_catalogoXproducto INT IDENTITY (1,1) primary key,id_producto INT ,id_tipo_catalogo INT,FOREIGN KEY (id_tipo_catalogo) REFERENCES Tipo_de_Catalogo (id_tipo_catalogo),FOREIGN KEY (id_producto) REFERENCES Producto (id_producto)); 

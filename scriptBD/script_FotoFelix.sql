CREATE DATABASE fotofelix;
USE fotofelix;

CREATE TABLE Categoria (
  idCategoria INT NOT NULL AUTO_INCREMENT,
  nombre VARCHAR(15) NOT NULL,
  PRIMARY KEY (idCategoria)
);

CREATE TABLE Marca (
  idMarca INT NOT NULL AUTO_INCREMENT,
  nombre VARCHAR(15) NOT NULL,
  PRIMARY KEY (idMarca)
);

CREATE TABLE UnidadMedida (
  idUnidadMedida INT NOT NULL AUTO_INCREMENT,
  nombre VARCHAR(15) NOT NULL,
  abreviatura VARCHAR(5) NOT NULL,
  PRIMARY KEY (idUnidadMedida)
);

CREATE TABLE Producto (
  idProducto INT NOT NULL AUTO_INCREMENT,
  descripcion VARCHAR(45) NOT NULL,
  precioVenta DECIMAL(6,2) NOT NULL,
  codigoBarras VARCHAR(20) ,
  cantidadAlmacen INT UNSIGNED ,
  cantidadMostrador INT UNSIGNED ,
  idCategoria INT NOT NULL,
  idMarca INT NOT NULL,
  idUnidadMedida INT NOT NULL,
  PRIMARY KEY (idProducto),
  CONSTRAINT idCategoria_fk
    FOREIGN KEY (idCategoria)
    REFERENCES Categoria (idCategoria)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT idMarca_fk
    FOREIGN KEY (idMarca)
    REFERENCES Marca (idMarca)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT idUnidadMedida_fk
    FOREIGN KEY (idUnidadMedida)
    REFERENCES UnidadMedida (idUnidadMedida)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

CREATE TABLE Proveedor (
  rucProveedor VARCHAR(15) NOT NULL,
  razonSocial VARCHAR(20) NOT NULL,
  telefono VARCHAR(10) ,
  celular VARCHAR(10) ,
  correo VARCHAR(30) ,
  direccion VARCHAR(30) ,
  PRIMARY KEY (rucProveedor)
);

CREATE TABLE Cliente (
  idClienteDniRuc VARCHAR(10) NOT NULL,
  nombres VARCHAR(25) NOT NULL,
  apellidos VARCHAR(25) ,
  sexo INT UNSIGNED NOT NULL,
  celular VARCHAR(10) ,
  correo VARCHAR(30) ,
  direccion VARCHAR(30) ,
  PRIMARY KEY (idClienteDniRuc)
);

CREATE TABLE DocumentoVenta (
  idDocumentoVenta INT NOT NULL AUTO_INCREMENT,
  serie INT UNSIGNED NOT NULL,
  numero INT UNSIGNED NOT NULL,
  fecha DATE NOT NULL,
  igv DECIMAL(6,2) NOT NULL,
  idClienteDniRuc varchar(10) NOT NULL,
  PRIMARY KEY (idDocumentoVenta),
  CONSTRAINT idClienteDniRuc_fk
    FOREIGN KEY (idClienteDniRuc)
    REFERENCES Cliente (idClienteDniRuc)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

CREATE TABLE DetalleVenta (
  idDocumentoVenta INT NOT NULL,
  idProducto INT NOT NULL,
  cantidad INT NOT NULL,
  precioVenta DECIMAL(6,2) NOT NULL,
  PRIMARY KEY (idDocumentoVenta, idProducto),
  CONSTRAINT idDocumentoVenta_fk
    FOREIGN KEY (idDocumentoVenta)
    REFERENCES DocumentoVenta (idDocumentoVenta)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT idProducto_fk
    FOREIGN KEY (idProducto)
    REFERENCES Producto (idProducto)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

CREATE TABLE DocumentoCompra (
  idDocumentoCompra INT NOT NULL AUTO_INCREMENT,
  serie INT UNSIGNED NOT NULL,
  numero INT UNSIGNED NOT NULL,
  fecha DATE NOT NULL,
  rucProveedor VARCHAR(15) NOT NULL,
  PRIMARY KEY (idDocumentoCompra),
  CONSTRAINT rucProveedor_fk
    FOREIGN KEY (rucProveedor)
    REFERENCES Proveedor (rucProveedor)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

CREATE TABLE DetalleCompra (
  idProducto INT NOT NULL,
  idDocumentoCompra INT NOT NULL,
  precioCompra DECIMAL(6,2) NOT NULL,
  cantidad INT UNSIGNED NOT NULL,
  PRIMARY KEY (idProducto, idDocumentoCompra),
  CONSTRAINT idDocumentoCompra_fk
    FOREIGN KEY (idDocumentoCompra)
    REFERENCES DocumentoCompra (idDocumentoCompra)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT idProducto_fk2
    FOREIGN KEY (idProducto)
    REFERENCES Producto (idProducto)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);
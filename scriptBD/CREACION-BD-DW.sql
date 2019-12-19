use PRODUCTO_TIENDA_DW

#Creacion dimencion Producto
CREATE TABLE Producto(
    idProducto int PRIMARY key ,
    descripcion VARCHAR(255) not null,
    marca VARCHAR(255) not null,
    categoria varchar(15) NOT NULL
)

#Creacion dimencion DocumentoVenta
CREATE TABLE DocumentoVenta
( idDocumentoVenta int Primary Key NOT NULL,
cliente varchar(255) NOT NULL,
precioVenta decimal(6,2) NOT NULL
)

#Creacion dimencion Tiempo
CREATE TABLE Tiempo
( idTiempo int PRIMARY KEY IDENTITY(1,1),
    fecha date NOT NULL,
año int NOT NULL,
trimestre int NOT NULL,
mes varchar(20) NOT NULL,
semana int NOT NULL,
dia int NOT NULL
)


#Creacion dimencion Venta
CREATE TABLE Venta
( productoID int NOT NULL, 
tiempoID int NOT NULL, 
documentoID int NOT NULL,
venta_total float NOT NULL,
cantidad_total int NOT NULL,
promedio_precio float NOT NULL,
constraint Venta_Pro_fk1 Foreign Key (productoID)
references Producto (idProducto)
on delete NO ACTION
on update NO ACTION,
constraint Venta_dimTiem_fk2 Foreign Key (tiempoID)
references Tiempo (idTiempo)
on delete NO ACTION
on update NO ACTION,
constraint FacVenta_dimDocVen_fk3 Foreign Key (documentoID)
references DocumentoVenta (idDocumentoVenta)
on delete NO ACTION
on update NO ACTION,
)
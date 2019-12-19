INSERT INTO categoria (nombre) values('marcos');
INSERT INTO categoria (nombre) values('impresion');
INSERT INTO categoria (nombre) values('edicion');
INSERT INTO categoria (nombre) values('archivo');
INSERT INTO categoria (nombre) values('fotoRegalo');
INSERT INTO categoria (nombre) values('evento');

INSERT INTO marca (nombre) values('Sayuri ');
INSERT INTO marca (nombre) values('Kodak');

INSERT INTO UnidadMedida (nombre,abreviatura) values('unidad','und');
INSERT INTO UnidadMedida (nombre,abreviatura) values('mediaDocena','mdoc');
INSERT INTO UnidadMedida (nombre,abreviatura) values('paquete','paq');


INSERT INTO producto (descripcion, precioVenta, codigoBarras, cantidadAlmacen, cantidadMostrador, idCategoria, idMarca, idUnidadMedida) values('foto 10x15', 1.0,'123',0, 0, 2, 2, 1);
INSERT INTO producto (descripcion, precioVenta, codigoBarras, cantidadAlmacen, cantidadMostrador, idCategoria, idMarca, idUnidadMedida) values('foto 10x15', 0.5,'123',0, 0, 2, 2, 3);
INSERT INTO producto (descripcion, precioVenta, codigoBarras, cantidadAlmacen, cantidadMostrador, idCategoria, idMarca, idUnidadMedida) values('foto 15x21', 4.0,'123', 0, 0, 2, 2, 1);
INSERT INTO producto (descripcion, precioVenta, codigoBarras, cantidadAlmacen, cantidadMostrador, idCategoria, idMarca, idUnidadMedida) values('foto 20x30', 9.0,'123', 0, 0, 2, 2, 1);
INSERT INTO producto (descripcion, precioVenta, codigoBarras, cantidadAlmacen, cantidadMostrador, idCategoria, idMarca, idUnidadMedida) values('foto 30x45', 25.0,'123', 0, 0, 2, 2, 1);

INSERT INTO producto (descripcion, precioVenta, codigoBarras, cantidadAlmacen, cantidadMostrador, idCategoria, idMarca, idUnidadMedida) values('marco pino 10x15', 12.0,'123', 0, 0, 1, 1, 1);
INSERT INTO producto (descripcion, precioVenta, codigoBarras, cantidadAlmacen, cantidadMostrador, idCategoria, idMarca, idUnidadMedida) values('marco pino 15x21', 18.0,'123', 0, 0, 1, 1, 1);
INSERT INTO producto (descripcion, precioVenta, codigoBarras, cantidadAlmacen, cantidadMostrador, idCategoria, idMarca, idUnidadMedida) values('marco pino 20x30', 25.0,'123', 0, 0, 1, 1, 1);
INSERT INTO producto (descripcion, precioVenta, codigoBarras, cantidadAlmacen, cantidadMostrador, idCategoria, idMarca, idUnidadMedida) values('marco pino 30x45', 35.0,'123', 0, 0, 1, 1, 1);

INSERT INTO cliente (idClienteDniRuc,nombres,apellidos,sexo, correo, direccion) VALUES(70074526,'Félix Gonzalo','Castro Cubas',1,'felix@gmail.com','Los Jardines A-30');
INSERT INTO cliente (idClienteDniRuc,nombres,apellidos,sexo, correo, direccion) VALUES(1,'Anonimo','Anonimo',1,'anonimo@gmail.com','Anonimo');

INSERT INTO proveedor (rucProveedor,razonSocial,telefono,celular,correo, direccion) VALUES("1",'Anonimo','1','1','anonimo@gmail.com',"calle 15");
INSERT INTO proveedor (rucProveedor,razonSocial,telefono,celular,correo, direccion) VALUES("2",'Sayuri','348529','930446846','Sayuri@gmail.com',"calle Sayuri 15");
INSERT INTO proveedor (rucProveedor,razonSocial,telefono,celular,correo, direccion) VALUES("3",'Kodak','754126','920456896','Kodak@gmail.com',"");

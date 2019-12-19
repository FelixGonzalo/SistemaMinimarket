create trigger actualizaStockVenta 
after insert on detalleventa
for each row
update producto set producto.cantidadAlmacen = producto.cantidadAlmacen - new.cantidad where producto.idProducto= new.idProducto;

create trigger actualizaStockCompra
after insert on detallecompra
for each row
update producto set producto.cantidadAlmacen = producto.cantidadAlmacen + new.cantidad where producto.idProducto= new.idProducto;

/*use fotofelix2;*/

/*DROP TRIGGER actualizaStockCompra;*/
/*
truncate detallecompra;
truncate detalleventa;
truncate documentoventa;
truncate documentocompra;*/

SELECT doc.idDocumentoCompra,doc.serie, 
	doc.numero,doc.fecha, doc.rucProveedor, p.RazonSocial/*, 
SUM(dv.cantidad) as cantidadProductos, 
    CAST(SUM(dv.cantidad*dv.precioVenta) /(doc.igv+1) as decimal(6,2)) as subTotal,
    CAST(SUM(dv.cantidad*dv.precioVenta) /(doc.igv+1)*0.18 as decimal(6,2)) as montoIGV, 
    SUM(dv.cantidad*dv.precioVenta) as importeTotal*/
FROM documentoCompra as doc
join Proveedor as p
	on p.rucProveedor = doc.rucProveedor;

SELECT doc.idDocumentoCompra,doc.serie, 
	doc.numero,doc.fecha, doc.rucProveedor, p.RazonSocial, 
	SUM(dc.cantidad) as cantidadProductos, 
    SUM(dc.cantidad*dc.preciocompra) as importeTotal
FROM documentoCompra as doc
join Proveedor as p
	on p.rucProveedor = doc.rucProveedor
join detalleCompra as dc
	on doc.idDocumentoCompra = dc.idDocumentoCompra
    group by doc.idDocumentoCompra
    ORDER BY doc.fecha, doc.serie,doc.numero;

select * from documentocompra;
select * from detallecompra;

select * from proveedor;


SELECT doc.serie, doc.numero,doc.fecha, doc.igv, doc.idClienteDniRuc, c.nombres , p.descripcion, dv.cantidad, dv.precioVenta
FROM documentoVenta as doc
join detalleVenta as dv
	on doc.idDocumentoVenta = dv.idDocumentoVenta
join producto as p
	on p.idProducto = dv.idProducto
join cliente as c
	on c.idClienteDniRuc = doc.idClienteDniRuc;
    
select * from DocumentoVenta;
    select * from detalleVenta;
	
SELECT doc.serie, doc.numero,doc.fecha, doc.igv, doc.idClienteDniRuc, c.nombres, c.apellidos
FROM documentoVenta as doc
join cliente as c
	on c.idClienteDniRuc = doc.idClienteDniRuc;
    
SELECT doc.serie, doc.numero,doc.fecha, doc.igv, doc.idClienteDniRuc, c.nombres, c.nombres, SUM(dv.cantidad) as cantidadProductos, SUM(dv.cantidad*dv.precioVenta) as ingresoTotal, doc.igv * SUM(dv.cantidad*dv.precioVenta) as igvTotal
FROM documentoVenta as doc
join cliente as c
	on c.idClienteDniRuc = doc.idClienteDniRuc
join detalleVenta as dv
	on doc.idDocumentoVenta = dv.idDocumentoVenta
where doc.serie =1 and doc.numero =2;

SELECT doc.idDocumentoVenta,doc.serie, 
	doc.numero,doc.fecha, doc.igv, doc.idClienteDniRuc,c.nombres, c.apellidos/*, 
SUM(dv.cantidad) as cantidadProductos, 
    CAST(SUM(dv.cantidad*dv.precioVenta) /(doc.igv+1) as decimal(6,2)) as subTotal,
    CAST(SUM(dv.cantidad*dv.precioVenta) /(doc.igv+1)*0.18 as decimal(6,2)) as montoIGV, 
    SUM(dv.cantidad*dv.precioVenta) as importeTotal*/
FROM documentoVenta as doc
join cliente as c
	on c.idClienteDniRuc = doc.idClienteDniRuc
join detalleVenta as dv
	on doc.idDocumentoVenta = dv.idDocumentoVenta
    group by doc.idDocumentoVenta
    ORDER BY doc.fecha, doc.serie,doc.numero;


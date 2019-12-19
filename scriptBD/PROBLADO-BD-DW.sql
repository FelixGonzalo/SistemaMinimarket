#Limpiando DATAMART
DELETE FROM Producto;
DELETE FROM DocumentoVenta;
DELETE FROM Tiempo;
DELETE FROM Venta;

#Poblar Producto
SELECT prod.idProducto,prod.descripcion,m.nombre as marca,c.nombre as categoria
from Producto prod,Marca m, Categoria c
where prod.idMarca = m.idMarca and prod.idCategoria = c.idCategoria

#Poblar Documento Venta
select distinct docv.idDocumentoVenta AS idDocumento, cli.nombres AS Cliente, 
                sum(dev.precioVenta) AS Precio_de_Venta
from DocumentoVenta docv 
INNER JOIN Cliente cli ON (docv.idClienteDniRuc = cli.idClienteDniRuc)
INNER JOIN DetalleVenta dev ON (docv.idDocumentoVenta = dev.idDocumentoVenta)
group by docv.idDocumentoVenta,cli.nombres

#Poblar Tiempo
select distinct fecha, Convert(int, Datepart(year, fecha)) AS Año,
Convert(int, Datepart(quarter, fecha)) AS Trimestre,
Convert(varchar,DATEPART(MONth, fecha)) AS Mes,
Convert(varchar,DATEPART(week, fecha)) AS semana,
Convert(int,DATEPART(day, fecha)) AS Dia
from DocumentoVenta


#Poblar Hecho Venta
select pro.idProducto AS ProductoID,tid.idTiempo AS TiempoID, docv.idDocumentoVenta AS DocumentoID, 
sum(dv.cantidad*pro.precioVenta) AS ventaTotal, sum(dv.cantidad) AS CantidadTotal, avg(pro.precioVenta) prom_precio
from PRODUCTO_TIENDA.dbo.Producto pro
INNER JOIN PRODUCTO_TIENDA.dbo.Marca mar ON (pro.idMarca = mar.idMarca)
INNER JOIN PRODUCTO_TIENDA.dbo.DetalleVenta dv ON (dv.idProducto=pro.idProducto) 
INNER JOIN PRODUCTO_TIENDA.dbo.DocumentoVenta docv ON (docv.idDocumentoVenta = dv.idDocumentoVenta)
INNER JOIN PRODUCTO_TIENDA.dbo.Cliente cli ON (cli.idClienteDniRuc = docv.idClienteDniRuc)
INNER JOIN PRODUCTO_TIENDA.dbo.Categoria cat ON (pro.idCategoria = cat.idCategoria)
INNER JOIN PRODUCTO_TIENDA_DW.dbo.Producto prod ON (prod.idProducto=pro.idProducto)
INNER JOIN PRODUCTO_TIENDA_DW.dbo.DocumentoVenta docvd ON (docvd.idDocumentoVenta=docv.idDocumentoVenta)
INNER JOIN PRODUCTO_TIENDA_DW.dbo.Tiempo tid ON (tid.fecha=docv.fecha)
group by pro.idProducto,docv.idDocumentoVenta,tid.idTiempo

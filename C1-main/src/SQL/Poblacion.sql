-- Datos para la tabla CIUDADES
INSERT INTO CIUDADES (ID, NOMBRE) VALUES (1, 'Bogotá');
INSERT INTO CIUDADES (ID, NOMBRE) VALUES (2, 'Medellín');
INSERT INTO CIUDADES (ID, NOMBRE) VALUES (3, 'Cali');
INSERT INTO CIUDADES (ID, NOMBRE) VALUES (4, 'Barranquilla');
INSERT INTO CIUDADES (ID, NOMBRE) VALUES (5, 'Cartagena');

-- Datos para la tabla CATEGORIAS
INSERT INTO CATEGORIAS (ID, CODIGO, NOMBRE, DESCRIPCION, CARACTERISTICAS_ALMACENAMIENTO)
VALUES (1, 100, 'Electrónica', 'Dispositivos electrónicos', 'Almacenaje en ambiente seco');
INSERT INTO CATEGORIAS (ID, CODIGO, NOMBRE, DESCRIPCION, CARACTERISTICAS_ALMACENAMIENTO)
VALUES (2, 200, 'Ropa', 'Vestimenta de moda', 'Almacenaje en racks');
INSERT INTO CATEGORIAS (ID, CODIGO, NOMBRE, DESCRIPCION, CARACTERISTICAS_ALMACENAMIENTO)
VALUES (3, 300, 'Alimentos', 'Comestibles', 'Refrigeración necesaria');
INSERT INTO CATEGORIAS (ID, CODIGO, NOMBRE, DESCRIPCION, CARACTERISTICAS_ALMACENAMIENTO)
VALUES (4, 400, 'Muebles', 'Mobiliario de hogar', 'Almacenaje en ambiente seco');
INSERT INTO CATEGORIAS (ID, CODIGO, NOMBRE, DESCRIPCION, CARACTERISTICAS_ALMACENAMIENTO)
VALUES (5, 500, 'Juguetes', 'Juguetes para niños', 'Almacenaje en ambiente seco');

-- Datos para la tabla SUCURSALES
INSERT INTO SUCURSALES (nombre, direccion, telefono, tamanio, ciudad)
VALUES ('Sucursal Central', 'Calle 1', '3001234567', 600, 1);
INSERT INTO SUCURSALES (nombre, direccion, telefono, tamanio, ciudad)
VALUES ('Sucursal Norte', 'Calle 2', '3009876543', 400, 2);
INSERT INTO SUCURSALES (nombre, direccion, telefono, tamanio, ciudad)
VALUES ('Sucursal Sur', 'Calle 3', '3006543211', 500, 3);
INSERT INTO SUCURSALES (nombre, direccion, telefono, tamanio, ciudad)
VALUES ('Sucursal Este', 'Calle 4', '3001122334', 450, 4);
INSERT INTO SUCURSALES (nombre, direccion, telefono, tamanio, ciudad)
VALUES ('Sucursal Oeste', 'Calle 5', '3002233445', 550, 5);

-- Datos para la tabla PROVEEDORES
INSERT INTO PROVEEDORES (nit, nombre, direccion, personaContacto, telefonoContacto)
VALUES (123456789, 'Proveedor A', 'Calle 10', 'Carlos Pérez', '3101234567');
INSERT INTO PROVEEDORES (nit, nombre, direccion, personaContacto, telefonoContacto)
VALUES (987654321, 'Proveedor B', 'Calle 11', 'Laura Gómez', '3109876543');
INSERT INTO PROVEEDORES (nit, nombre, direccion, personaContacto, telefonoContacto)
VALUES (192837465, 'Proveedor C', 'Calle 12', 'Ana Martínez', '3106543211');
INSERT INTO PROVEEDORES (nit, nombre, direccion, personaContacto, telefonoContacto)
VALUES (564738291, 'Proveedor D', 'Calle 13', 'Juan López', '3101122334');
INSERT INTO PROVEEDORES (nit, nombre, direccion, personaContacto, telefonoContacto)
VALUES (374859201, 'Proveedor E', 'Calle 14', 'Sofía Ramírez', '3102233445');

-- Datos para la tabla PRODUCTOS
INSERT INTO PRODUCTOS (codigoDeBarras, nombre, costoEnBodega, precioUnitario, presentacion, cantidadPresentacion, unidadPresentacion, especEmpaque, fechaVencimiento, categoria)
VALUES (123456789, 'Producto A', 5000, 10000, 'Caja', 20, 'Unidad', 'Cartón', TO_DATE('2025-05-01', 'YYYY-MM-DD'), 1);
INSERT INTO PRODUCTOS (codigoDeBarras, nombre, costoEnBodega, precioUnitario, presentacion, cantidadPresentacion, unidadPresentacion, especEmpaque, fechaVencimiento, categoria)
VALUES (987654321, 'Producto B', 3000, 6000, 'Botella', 12, 'Litro', 'Plástico', TO_DATE('2024-12-15', 'YYYY-MM-DD'), 2);
INSERT INTO PRODUCTOS (codigoDeBarras, nombre, costoEnBodega, precioUnitario, presentacion, cantidadPresentacion, unidadPresentacion, especEmpaque, fechaVencimiento, categoria)
VALUES (192837465, 'Producto C', 8000, 16000, 'Saco', 50, 'Kg', 'Papel', TO_DATE('2023-11-30', 'YYYY-MM-DD'), 3);
INSERT INTO PRODUCTOS (codigoDeBarras, nombre, costoEnBodega, precioUnitario, presentacion, cantidadPresentacion, unidadPresentacion, especEmpaque, fechaVencimiento, categoria)
VALUES (564738291, 'Producto D', 7000, 14000, 'Caja', 30, 'Unidad', 'Cartón', TO_DATE('2024-07-01', 'YYYY-MM-DD'), 4);
INSERT INTO PRODUCTOS (codigoDeBarras, nombre, costoEnBodega, precioUnitario, presentacion, cantidadPresentacion, unidadPresentacion, especEmpaque, fechaVencimiento, categoria)
VALUES (374859201, 'Producto E', 9000, 18000, 'Botella', 24, 'Litro', 'Vidrio', TO_DATE('2023-10-25', 'YYYY-MM-DD'), 5);

-- Datos para la tabla BODEGAS
INSERT INTO BODEGAS (ID, NOMBRE, TAMANIO, SUCURSAL)
VALUES (superandes_sequence.NEXTVAL, 'Bodega Central', 500, 1);
INSERT INTO BODEGAS (ID, NOMBRE, TAMANIO, SUCURSAL)
VALUES (superandes_sequence.NEXTVAL, 'Bodega Norte', 300, 2);
INSERT INTO BODEGAS (ID, NOMBRE, TAMANIO, SUCURSAL)
VALUES (superandes_sequence.NEXTVAL, 'Bodega Sur', 400, 3);
INSERT INTO BODEGAS (ID, NOMBRE, TAMANIO, SUCURSAL)
VALUES (superandes_sequence.NEXTVAL, 'Bodega Este', 350, 4);
INSERT INTO BODEGAS (ID, NOMBRE, TAMANIO, SUCURSAL)
VALUES (superandes_sequence.NEXTVAL, 'Bodega Oeste', 450, 5);

-- Datos para la tabla INVENTARIOS
INSERT INTO INVENTARIOS (ID_PRODUCTO, ID_BODEGA, COSTO_PROMEDIO, CAPACIDAD, NUMERO_REORDEN, CANTIDAD)
VALUES (1, 1, 5500, 200, 20, 100);
INSERT INTO INVENTARIOS (ID_PRODUCTO, ID_BODEGA, COSTO_PROMEDIO, CAPACIDAD, NUMERO_REORDEN, CANTIDAD)
VALUES (2, 2, 3500, 150, 10, 50);
INSERT INTO INVENTARIOS (ID_PRODUCTO, ID_BODEGA, COSTO_PROMEDIO, CAPACIDAD, NUMERO_REORDEN, CANTIDAD)
VALUES (3, 3, 8200, 100, 15, 75);
INSERT INTO INVENTARIOS (ID_PRODUCTO, ID_BODEGA, COSTO_PROMEDIO, CAPACIDAD, NUMERO_REORDEN, CANTIDAD)
VALUES (4, 4, 7200, 250, 18, 120);
INSERT INTO INVENTARIOS (ID_PRODUCTO, ID_BODEGA, COSTO_PROMEDIO, CAPACIDAD, NUMERO_REORDEN, CANTIDAD)
VALUES (5, 5, 9500, 300, 25, 200);

-- Datos para la tabla ORDENESDECOMPRA
INSERT INTO ordenesDeCompra (cantidadProducto, precioProducto, fechaEntregaEsperada, sucursal, proveedor, producto)
VALUES (100, 100000, TO_DATE('2023-10-01', 'YYYY-MM-DD'), 1, 1, 1);
INSERT INTO ordenesDeCompra (cantidadProducto, precioProducto, fechaEntregaEsperada, sucursal, proveedor, producto)
VALUES (200, 200000, TO_DATE('2023-11-01', 'YYYY-MM-DD'), 2, 2, 2);
INSERT INTO ordenesDeCompra (cantidadProducto, precioProducto, fechaEntregaEsperada, sucursal, proveedor, producto)
VALUES (150, 150000, TO_DATE('2023-12-01', 'YYYY-MM-DD'), 3, 3, 3);
INSERT INTO ordenesDeCompra (cantidadProducto, precioProducto, fechaEntregaEsperada, sucursal, proveedor, producto)
VALUES (250, 250000, TO_DATE('2024-01-01', 'YYYY-MM-DD'), 4, 4, 4);
INSERT INTO ordenesDeCompra (cantidadProducto, precioProducto, fechaEntregaEsperada, sucursal, proveedor, producto)
VALUES (300, 300000, TO_DATE('2024-02-01', 'YYYY-MM-DD'), 5, 5, 5);

-- Datos para la tabla PEDIDOS
INSERT INTO PEDIDOS (id_producto, id_ordenDeCompra, fechaEntrega, estado)
VALUES (1, 1, TO_DATE('2023-10-15', 'YYYY-MM-DD'), 'En camino');
INSERT INTO PEDIDOS (id_producto, id_ordenDeCompra, fechaEntrega, estado)
VALUES (2, 2, TO_DATE('2023-11-20', 'YYYY-MM-DD'), 'Entregado');
INSERT INTO PEDIDOS (id_producto, id_ordenDeCompra, fechaEntrega, estado)
VALUES (3, 3, TO_DATE('2023-12-05', 'YYYY-MM-DD'), 'Pendiente');
INSERT INTO PEDIDOS (id_producto, id_ordenDeCompra, fechaEntrega, estado)
VALUES (4, 4, TO_DATE('2024-01-10', 'YYYY-MM-DD'), 'En camino');
INSERT INTO PEDIDOS (id_producto, id_ordenDeCompra, fechaEntrega, estado)
VALUES (5, 5, TO_DATE('2024-02-15', 'YYYY-MM-DD'), 'Pendiente');

-- Datos para la tabla productos_bodegas
INSERT INTO productos_bodegas (bodega_id, producto_id, cantidad)
VALUES (1, 1, 50);
INSERT INTO productos_bodegas (bodega_id, producto_id, cantidad)
VALUES (2, 2, 30);
INSERT INTO productos_bodegas (bodega_id, producto_id, cantidad)
VALUES (3, 3, 40);
INSERT INTO productos_bodegas (bodega_id, producto_id, cantidad)
VALUES (4, 4, 25);
INSERT INTO productos_bodegas (bodega_id, producto_id, cantidad)
VALUES (5, 5, 15);

INSERT INTO INVENTARIOS (ID_PRODUCTO, ID_BODEGA, COSTO_PROMEDIO, CAPACIDAD, NUMERO_REORDEN, CANTIDAD)
VALUES (1, 1, 5500, 200, 20, 100);

COMMIT;



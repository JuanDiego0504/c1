-- Creación del secuenciador principal
CREATE SEQUENCE superandes_sequence START WITH 1 INCREMENT BY 1;

-- Creación de la tabla CIUDADES
CREATE TABLE CIUDADES (
    ID NUMBER PRIMARY KEY,
    NOMBRE VARCHAR2(255 BYTE) NOT NULL,
    CONSTRAINT UN_CIUDAD_NOMBRE UNIQUE (NOMBRE)
);

-- Creación de la tabla SUCURSALES
CREATE TABLE SUCURSALES (
    id INTEGER PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    direccion VARCHAR(255),
    telefono VARCHAR(15),
    tamanio INTEGER,
    ciudad INTEGER,
    FOREIGN KEY (ciudad) REFERENCES ciudades(id)
);

CREATE SEQUENCE sucursales_seq START WITH 1 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER sucursales_before_insert
BEFORE INSERT ON SUCURSALES
FOR EACH ROW
BEGIN
    SELECT sucursales_seq.NEXTVAL INTO :NEW.id FROM dual;
END;
/

-- Creación de la tabla BODEGAS
CREATE TABLE BODEGAS (
    ID NUMBER PRIMARY KEY,
    NOMBRE VARCHAR2(255 BYTE) NOT NULL,
    TAMANIO NUMBER CHECK (TAMANIO > 0),
    SUCURSAL NUMBER,
    CONSTRAINT FK_SUCURSAL FOREIGN KEY (SUCURSAL) REFERENCES SUCURSALES(ID) ON DELETE CASCADE,
    CONSTRAINT UN_BODEGA_NOMBRE UNIQUE (NOMBRE)
);

-- Creación de la tabla CATEGORIAS
CREATE TABLE CATEGORIAS (
    ID NUMBER PRIMARY KEY,
    CODIGO NUMBER,
    NOMBRE VARCHAR2(255 BYTE) NOT NULL,
    DESCRIPCION VARCHAR2(255 BYTE),
    CARACTERISTICAS_ALMACENAMIENTO VARCHAR2(255 BYTE),
    CONSTRAINT UN_CATEGORIA_NOMBRE UNIQUE (NOMBRE)
);

-- Creación de la tabla PRODUCTOS
CREATE TABLE PRODUCTOS (
    id INTEGER PRIMARY KEY,
    codigoDeBarras INTEGER,
    nombre VARCHAR(255) NOT NULL,
    costoEnBodega INTEGER,
    precioUnitario INTEGER,
    presentacion VARCHAR(100),
    cantidadPresentacion INTEGER,
    unidadPresentacion VARCHAR(50),
    especEmpaque VARCHAR(100),
    fechaVencimiento DATE,
    categoria INTEGER,
    FOREIGN KEY (categoria) REFERENCES categorias(id)
);

CREATE SEQUENCE productos_seq START WITH 1 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER productos_before_insert
BEFORE INSERT ON PRODUCTOS
FOR EACH ROW
BEGIN
    SELECT productos_seq.NEXTVAL INTO :NEW.id FROM dual;
END;
/

-- Creación de la tabla PROVEEDORES
CREATE TABLE PROVEEDORES (
    id INTEGER PRIMARY KEY,
    nit INTEGER NOT NULL,
    nombre VARCHAR(255) NOT NULL,
    direccion VARCHAR(255),
    personaContacto VARCHAR(100),
    telefonoContacto VARCHAR(15)
);

CREATE SEQUENCE proveedores_seq START WITH 1 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER proveedores_before_insert
BEFORE INSERT ON PROVEEDORES
FOR EACH ROW
BEGIN
    SELECT proveedores_seq.NEXTVAL INTO :NEW.id FROM dual;
END;
/

-- Creación de la tabla INVENTARIOS
-- Creación de la tabla INVENTARIOS con la columna cantidad incluida
CREATE TABLE INVENTARIOS (
    ID_PRODUCTO INTEGER,
    ID_BODEGA INTEGER,
    COSTO_PROMEDIO NUMBER,
    CAPACIDAD INTEGER,
    NUMERO_REORDEN INTEGER,
    CANTIDAD NUMBER(38), -- Añadido este campo
    CONSTRAINT INVENTARIOS_PK PRIMARY KEY (ID_PRODUCTO, ID_BODEGA)
);

ALTER TABLE INVENTARIOS
ADD CONSTRAINT fk_i_productos
    FOREIGN KEY (ID_PRODUCTO)
    REFERENCES PRODUCTOS(ID)
    ON DELETE CASCADE
ENABLE;

ALTER TABLE INVENTARIOS
ADD CONSTRAINT fk_i_bodegas
    FOREIGN KEY (ID_BODEGA)
    REFERENCES BODEGAS(ID)
    ON DELETE CASCADE
ENABLE;

ALTER TABLE INVENTARIOS
ADD CONSTRAINT CK_I_CAPACIDAD
    CHECK (CAPACIDAD >= 0)
ENABLE;

ALTER TABLE INVENTARIOS ADD cantidad NUMBER(38);

-- Creación de la tabla ordenesDeCompra
CREATE TABLE ordenesDeCompra (
    id INTEGER PRIMARY KEY,
    cantidadProducto INTEGER NOT NULL,
    precioProducto INTEGER NOT NULL,
    fechaEntregaEsperada DATE,
    fechaCreacion DATE, -- Fecha de creación
    estado VARCHAR2(50 BYTE) DEFAULT 'vigente' NOT NULL, -- Estado inicial
    sucursal INTEGER NOT NULL,
    proveedor INTEGER NOT NULL,
    producto INTEGER NOT NULL,
    FOREIGN KEY (sucursal) REFERENCES sucursales(id) ON DELETE CASCADE,
    FOREIGN KEY (proveedor) REFERENCES proveedores(id) ON DELETE CASCADE,
    FOREIGN KEY (producto) REFERENCES productos(id) ON DELETE CASCADE
);

CREATE SEQUENCE ordenesDeCompra_seq START WITH 1 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER ordenesDeCompra_before_insert
BEFORE INSERT ON ordenesDeCompra
FOR EACH ROW
BEGIN
    SELECT ordenesDeCompra_seq.NEXTVAL INTO :NEW.id FROM dual;
    -- Asignación automática de la fecha de creación
    :NEW.fechaCreacion := SYSDATE;
END;
/

-- Creación de la tabla PEDIDOS
CREATE TABLE PEDIDOS (
    id_producto INTEGER NOT NULL,
    id_ordenDeCompra INTEGER NOT NULL,
    fechaEntrega DATE NOT NULL,
    estado VARCHAR(50) NOT NULL,
    PRIMARY KEY (id_producto, id_ordenDeCompra, fechaEntrega, estado),
    FOREIGN KEY (id_producto) REFERENCES productos(id),
    FOREIGN KEY (id_ordenDeCompra) REFERENCES ordenesDeCompra(id)
);

CREATE TABLE productos_bodegas (
    bodega_id NUMBER(38) NOT NULL,
    producto_id NUMBER(38) NOT NULL,
    cantidad NUMBER(38),
    PRIMARY KEY (bodega_id, producto_id),
    FOREIGN KEY (bodega_id) REFERENCES bodegas(id) ON DELETE CASCADE,
    FOREIGN KEY (producto_id) REFERENCES productos(id) ON DELETE CASCADE
);

CREATE TABLE DOCUMENTOS_INGRESO (
    id INTEGER PRIMARY KEY,
    numero_documento VARCHAR2(50) NOT NULL,
    fecha DATE NOT NULL,
    proveedor_id INTEGER NOT NULL,
    bodega_id INTEGER NOT NULL,
    FOREIGN KEY (proveedor_id) REFERENCES PROVEEDORES(id),
    FOREIGN KEY (bodega_id) REFERENCES BODEGAS(id)
);

CREATE SEQUENCE documentos_ingreso_seq START WITH 1 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER documentos_ingreso_before_insert
BEFORE INSERT ON DOCUMENTOS_INGRESO
FOR EACH ROW
BEGIN
    SELECT documentos_ingreso_seq.NEXTVAL INTO :NEW.id FROM dual;
END;
/


COMMIT;

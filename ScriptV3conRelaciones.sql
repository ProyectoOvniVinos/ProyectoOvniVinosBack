create schema basededatos_ovni_vinos;
use basededatos_ovni_vinos;

create table administrador
(
	correo_admin char(30) primary key not null,
	nombre_admin char(30),
	apellido_admin char(30),
	direccion_admin varchar(100),
	telefono_admin char(12),
	password_admin char(20)
);

create table compra
(
	codigo_compra int primary key not null auto_increment,
	precio_compra double, 
	cantidad_compra int, 
	fecha_compra datetime,
	administrador_compra char(30) not null,
	id_registro_contabilidad_diaria_compra int not null default 0
);


create table cliente
( 
	correo_cliente char(30) primary key not null,
	nombre_cliente char(30),
	apellido_cliente char(30),
	direccion_cliente varchar(100),
	telefono_cliente char(12),
	password_cliente VARCHAR(255)
);

create table carrito_cliente
(
  id_carrito int auto_increment primary key,
  precio_carrito double,
  cantidad_carrito int,
  cliente char(30)
);

create table item_carrito
(
  id_puente int primary key not null auto_increment,
  carrito int,
  codigo_producto int not null,
  cantidad_producto int,
  precio_item double
);


create table venta 
(
	codigo_venta int primary key not null auto_increment,
	correo_cliente char(30) not null,
	precio_venta double,
	fecha_venta datetime,
    id_registro_contabilidad_diaria int
);
ALTER TABLE `basededatos_ovni_vinos`.`venta` 
ADD COLUMN `cantidad_venta` INT NULL DEFAULT NULL AFTER `fecha_venta`;



create table producto 
(
	codigo_producto int primary key not null auto_increment,
	nombre_producto varchar(50),
	precio_producto double,
	precio_producto_proveedor double,
	descripcion_producto varchar(200),
    foto_producto varchar(200),
    estado char(1)
);

create table venta_cliente
(
	id_puente int primary key not null auto_increment,
  codigo_venta int not null,
  precio_venta_detalle double,
  codigo_producto int not null,
  cantidad_producto int
);
  
create table contabilidad_diaria 
(
	id_registro_contabilidad_diaria int primary key not null auto_increment,
	ventas_contabilidad_diaria double,
	egresos_contabilidad_diaria double,
	ingresos_contabilidad_diaria double,
	id_registro_contabilidad_mensual int,
	fecha date
);
create table contabilidad_mensual 
(
	id_registro_contabilidad_mensual int primary key not null auto_increment,
  ventas_contabilidad_mensual double, 
  egresos_contabilidad_mensual double,
	ingresos_contabilidad_mensual double,
  id_registro_contabilidad_anual int,
  fecha date
);

create table contabilidad_anual
(
	id_registro_contabilidad_anual int primary key not null auto_increment, 
  ventas_contabilidad_anual double, 
  egresos_contabilidad_anual double, 
  ingresos_contabilidad_anual double,
  fecha date
); 

create table inventario_general
(
	id_registro int primary key not null auto_increment,
	codigo_producto int,
	cantidad_producto int
);
create table inventario_detalles
(
	id_detalles int primary key not null auto_increment,
  id_registro int,
  cantidad_producto int,
  fecha_ultimo_ingreso_inventario date
);
create table compra_admin
(
	id_puente int primary key not null auto_increment,
  compra int,
  codigo_producto int not null,
  precio_compra_detalle double,
  cantidad_producto int
);

ALTER TABLE `basededatos_ovni_vinos`.`carrito_cliente` 
ADD CONSTRAINT `fk_carrito_cliente_cliente`
  FOREIGN KEY (`cliente`)
  REFERENCES `basededatos_ovni_vinos`.`cliente` (`correo_cliente`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `basededatos_ovni_vinos`.`compra` 
ADD INDEX `fk_compra_id_registro_contabilidad_diaria_compra_idx` (`id_registro_contabilidad_diaria_compra` ASC) ;
;

ALTER TABLE `basededatos_ovni_vinos`.`compra` 
ADD CONSTRAINT `fk_compra_id_registro_contabilidad_diaria_compra`
  FOREIGN KEY (`id_registro_contabilidad_diaria_compra`)
  REFERENCES `basededatos_ovni_vinos`.`contabilidad_diaria` (`id_registro_contabilidad_diaria`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `basededatos_ovni_vinos`.`item_carrito` 
CHANGE COLUMN `codigo_producto` `codigo_producto` INT NULL ,
ADD INDEX `fk_item_carrito_codigo_producto_idx` (`codigo_producto` ASC) ;
;
ALTER TABLE `basededatos_ovni_vinos`.`item_carrito` 
ADD CONSTRAINT `fk_item_carrito_codigo_producto`
  FOREIGN KEY (`codigo_producto`)
  REFERENCES `basededatos_ovni_vinos`.`producto` (`codigo_producto`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;


ALTER TABLE `basededatos_ovni_vinos`.`item_carrito` 
ADD INDEX `fk_item_carrito_carrito_idx` (`carrito` ASC) ;
;
ALTER TABLE `basededatos_ovni_vinos`.`item_carrito` 
ADD CONSTRAINT `fk_item_carrito_carrito`
  FOREIGN KEY (`carrito`)
  REFERENCES `basededatos_ovni_vinos`.`carrito_cliente` (`id_carrito`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;



/*
drop schema basededatos_ovni_vinos

INSERT INTO `basededatos_ovni_vinos`.`cliente` (`correo_cliente`, `nombre_cliente`, `apellido_cliente`, `direccion_cliente`, `telefono_cliente`, `password_cliente`) VALUES ('correoClienteOvni@gmail.com', 'cliente', 'cliente', 'cliente', '323', '12345');
INSERT INTO `basededatos_ovni_vinos`.`administrador` (`correo_admin`, `nombre_admin`, `apellido_admin`, `direccion_admin`, `telefono_admin`, `password_admin`) VALUES ('cristian@gmail.com', 'Cristian', 'Amador', 'centenario', '323', '12345');

INSERT INTO `basededatos_ovni_vinos`.`cliente` (`correo_cliente`, `nombre_cliente`, `apellido_cliente`, `direccion_cliente`, `telefono_cliente`, `password_cliente`) VALUES ('crissis2004@gmail.com', 'Cristian', 'Amador', 'centenario', '323', '12345');

http://res.cloudinary.com/dqbrhsn8x/image/upload/v1660433864/images_ovnivinos/efiguf1otkgvnvknomtg.jpg

<<<<<<< HEAD
INSERT INTO `basededatos_ovni_vinos`.`producto` (`nombre_producto`, `precio_producto`, `precio_producto_proveedor`, `descripcion_producto`, `estado`,`foto_producto`) VALUES ('Vino santuario', '13000', '10000', 'delicioso vino dulce', '1','http://res.cloudinary.com/dqbrhsn8x/image/upload/v1660433864/images_ovnivinos/efiguf1otkgvnvknomtg.jpg');
INSERT INTO `basededatos_ovni_vinos`.`producto` (`nombre_producto`, `precio_producto`, `precio_producto_proveedor`, `descripcion_producto`, `estado`,`foto_producto`) VALUES ('Vino Sano', '13000', '10000', 'delicioso vino dulce', '1','http://res.cloudinary.com/dqbrhsn8x/image/upload/v1660433864/images_ovnivinos/efiguf1otkgvnvknomtg.jpg');
INSERT INTO `basededatos_ovni_vinos`.`producto` (`nombre_producto`, `precio_producto`, `precio_producto_proveedor`, `descripcion_producto`, `estado`,`foto_producto`) VALUES ('Vino Anco', '13000', '10000', 'delicioso vino dulce', '1','http://res.cloudinary.com/dqbrhsn8x/image/upload/v1660433864/images_ovnivinos/efiguf1otkgvnvknomtg.jpg');
INSERT INTO `basededatos_ovni_vinos`.`producto` (`nombre_producto`, `precio_producto`, `precio_producto_proveedor`, `descripcion_producto`, `estado`,`foto_producto`) VALUES ('Vino Xs', '13000', '10000', 'delicioso vino dulce', '1','http://res.cloudinary.com/dqbrhsn8x/image/upload/v1660433864/images_ovnivinos/efiguf1otkgvnvknomtg.jpg');

=======

INSERT INTO `basededatos_ovni_vinos`.`producto` (`nombre_producto`, `precio_producto`, `precio_producto_proveedor`, `descripcion_producto`, `estado`,`foto_producto`) VALUES ('Vino abocado', '13000', '10000', 'delicioso vino dulce', '1','http://res.cloudinary.com/dqbrhsn8x/image/upload/v1660433864/images_ovnivinos/efiguf1otkgvnvknomtg.jpg');
INSERT INTO `basededatos_ovni_vinos`.`producto` (`nombre_producto`, `precio_producto`, `precio_producto_proveedor`, `descripcion_producto`, `estado`) VALUES ('Vino tinto', '13000', '10000', 'delicioso vino poco dulce', '1');
INSERT INTO `basededatos_ovni_vinos`.`producto` (`nombre_producto`, `precio_producto`, `precio_producto_proveedor`, `descripcion_producto`, `estado`) VALUES ('nectar de uva', '10000', '7000', 'delicioso zumo de uva sin alcohol', '1');
INSERT INTO `basededatos_ovni_vinos`.`producto` (`nombre_producto`, `precio_producto`, `precio_producto_proveedor`, `descripcion_producto`, `estado`) VALUES ('nectar de manzana', '12000', '8000', 'delicioso zumo de manzana sin alcohol', '1');
>>>>>>> fbfa91f4094b8c4551a44687f0122a8e644a1107



*/

ALTER TABLE `basededatos_ovni_vinos`.`compra_admin` 
ADD INDEX `fk_compra_admin_codigo_producto_idx` (`codigo_producto` ASC) ;
;
ALTER TABLE `basededatos_ovni_vinos`.`compra_admin` 
ADD CONSTRAINT `fk_compra_admin_codigo_producto`
  FOREIGN KEY (`codigo_producto`)
  REFERENCES `basededatos_ovni_vinos`.`producto` (`codigo_producto`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `basededatos_ovni_vinos`.`compra_admin` 
ADD INDEX `fk_compra_admin_codigo_compra_idx` (`compra` ASC) ;
;
ALTER TABLE `basededatos_ovni_vinos`.`compra_admin` 
ADD CONSTRAINT `fk_compra_admin_codigo_compra`
  FOREIGN KEY (`compra`)
  REFERENCES `basededatos_ovni_vinos`.`compra` (`codigo_compra`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

  
ALTER TABLE `basededatos_ovni_vinos`.`venta_cliente` 
ADD INDEX `fk_venta_cliente_codigo_venta_idx` (`codigo_venta` ASC);
;
ALTER TABLE `basededatos_ovni_vinos`.`venta_cliente` 
ADD CONSTRAINT `fk_venta_cliente_codigo_venta`
  FOREIGN KEY (`codigo_venta`)
  REFERENCES `basededatos_ovni_vinos`.`venta` (`codigo_venta`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
  
ALTER TABLE `basededatos_ovni_vinos`.`venta_cliente` 
ADD INDEX `fk_venta_cliente_codigo_producto_idx` (`codigo_producto` ASC);
;
ALTER TABLE `basededatos_ovni_vinos`.`venta_cliente` 
ADD CONSTRAINT `fk_venta_cliente_codigo_producto`
  FOREIGN KEY (`codigo_producto`)
  REFERENCES `basededatos_ovni_vinos`.`producto` (`codigo_producto`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
  ALTER TABLE `basededatos_ovni_vinos`.`venta` 
ADD INDEX `fk_venta_correo_cliente_idx` (`correo_cliente` ASC);
;

ALTER TABLE `basededatos_ovni_vinos`.`venta` 
ADD CONSTRAINT `fk_venta_correo_cliente`
  FOREIGN KEY (`correo_cliente`)
  REFERENCES `basededatos_ovni_vinos`.`cliente` (`correo_cliente`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
  ALTER TABLE `basededatos_ovni_vinos`.`compra` 
ADD INDEX `fk_compra_administrador_compra_idx` (`administrador_compra` ASC);
;
ALTER TABLE `basededatos_ovni_vinos`.`compra` 
ADD CONSTRAINT `fk_compra_administrador_compra`
  FOREIGN KEY (`administrador_compra`)
  REFERENCES `basededatos_ovni_vinos`.`administrador` (`correo_admin`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
  ALTER TABLE `basededatos_ovni_vinos`.`inventario_detalles` 
ADD INDEX `fk_inventario_detalles_id_registro_idx` (`id_registro` ASC);
;
ALTER TABLE `basededatos_ovni_vinos`.`inventario_detalles` 
ADD CONSTRAINT `fk_inventario_detalles_id_registro`
  FOREIGN KEY (`id_registro`)
  REFERENCES `basededatos_ovni_vinos`.`inventario_general` (`id_registro`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
  ALTER TABLE `basededatos_ovni_vinos`.`venta` 
ADD INDEX `fk_venta_id_registro_contabilidad_diaria_idx` (`id_registro_contabilidad_diaria` ASC);
;
ALTER TABLE `basededatos_ovni_vinos`.`venta` 
ADD CONSTRAINT `fk_venta_id_registro_contabilidad_diaria`
  FOREIGN KEY (`id_registro_contabilidad_diaria`)
  REFERENCES `basededatos_ovni_vinos`.`contabilidad_diaria` (`id_registro_contabilidad_diaria`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
  ALTER TABLE `basededatos_ovni_vinos`.`contabilidad_diaria` 
ADD INDEX `fk_contabilidad_diaria_id_registro_contabilidad_mensual_idx` (`id_registro_contabilidad_mensual` ASC) ;
;
ALTER TABLE `basededatos_ovni_vinos`.`contabilidad_diaria` 
ADD CONSTRAINT `fk_contabilidad_diaria_id_registro_contabilidad_mensual`
  FOREIGN KEY (`id_registro_contabilidad_mensual`)
  REFERENCES `basededatos_ovni_vinos`.`contabilidad_mensual` (`id_registro_contabilidad_mensual`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
ALTER TABLE `basededatos_ovni_vinos`.`contabilidad_mensual` 
ADD INDEX `fk_contabilidad_mensual_id_registro_contabilidad_anual_idx` (`id_registro_contabilidad_anual` ASC) ;
;
ALTER TABLE `basededatos_ovni_vinos`.`contabilidad_mensual` 
ADD CONSTRAINT `fk_contabilidad_mensual_id_registro_contabilidad_anual`
  FOREIGN KEY (`id_registro_contabilidad_anual`)
  REFERENCES `basededatos_ovni_vinos`.`contabilidad_anual` (`id_registro_contabilidad_anual`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `basededatos_ovni_vinos`.`inventario_general` 
ADD INDEX `fk_inventario_general_producto_inx` (`codigo_producto` ASC) ;
;
ALTER TABLE `basededatos_ovni_vinos`.`inventario_general` 
ADD CONSTRAINT `fk_inventario_general_producto`
  FOREIGN KEY (`codigo_producto`)
  REFERENCES `basededatos_ovni_vinos`.`producto` (`codigo_producto`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
;

/* CAMBIO DE NOMBRE ATRIBUTO de codigo_venta a venta EN TABLA venta_cliente*/
ALTER TABLE `basededatos_ovni_vinos`.`venta_cliente` 
DROP FOREIGN KEY `fk_venta_cliente_codigo_venta`;
ALTER TABLE `basededatos_ovni_vinos`.`venta_cliente` 
CHANGE COLUMN `codigo_venta` `venta` INT(11) NULL DEFAULT NULL ;
ALTER TABLE `basededatos_ovni_vinos`.`venta_cliente` 
ADD CONSTRAINT `fk_venta_cliente_codigo_venta`
  FOREIGN KEY (`venta`)
  REFERENCES `basededatos_ovni_vinos`.`venta` (`codigo_venta`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

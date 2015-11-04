
USE serviciotecnico
;




CREATE TABLE nota_entrega
(
	id INT NOT NULL AUTO_INCREMENT,
	id_nota_perito INT,
	fecha DATE,
	eliminado BOOL,
	PRIMARY KEY (id)

) 
;


CREATE TABLE nota_perito
(
	id INT NOT NULL AUTO_INCREMENT,
	id_nota INT,
	fecha DATE,
	id_perito INT,
	eliminado BOOL,
	PRIMARY KEY (id)

) 
;


CREATE TABLE detalle_nota
(
	id INT NOT NULL AUTO_INCREMENT,
	id_extintor INT,
	id_nota INT,
	cantidad INT,
	PRIMARY KEY (id)

) 
;


CREATE TABLE detalle_perito
(
	id INT NOT NULL AUTO_INCREMENT,
	id_nota_perito INT,
	id_servicio INT,
	cantidad INT,
	precio_total FLOAT(7,2),
	PRIMARY KEY (id)

) 
;


CREATE TABLE nota_servicio
(
	id INT NOT NULL AUTO_INCREMENT,
	id_cliente INT,
	fecha DATE,
	descripcion_cliente TEXT,
	eliminado BOOL,
	PRIMARY KEY (id)

) 
;


CREATE TABLE servicio
(
	id INT NOT NULL AUTO_INCREMENT,
	descripcion TEXT,
	precio FLOAT(7,2),
	eliminado BOOL,
	PRIMARY KEY (id)

) 
;


CREATE TABLE tipo_persona
(
	id INT NOT NULL AUTO_INCREMENT,
	nombre TEXT,
	eliminado BOOL,
	descripcion TEXT,
	PRIMARY KEY (id)

) 
;


CREATE TABLE persona
(
	id INT NOT NULL AUTO_INCREMENT,
	nombre TEXT,
	apellido TEXT,
	ci INT,
	telefono INT,
	email TEXT,
	nombre_empresa TEXT,
	id_tipo_persona INT,
	eliminado BOOL,
	PRIMARY KEY (id)

) 
;


CREATE TABLE agente_quimico
(
	id INT NOT NULL AUTO_INCREMENT,
	nombre TEXT,
	idclasificacion INT,
	eliminado BOOL,
	PRIMARY KEY (id)

) 
;


CREATE TABLE extintor
(
	id_agente_quimico INT,
	id INT NOT NULL AUTO_INCREMENT,
	clasificacion TEXT,
	eliminado BOOL,
	peso INT,
	PRIMARY KEY (id)

) 
;





ALTER TABLE nota_entrega ADD CONSTRAINT FK_nota_entrega_nota_perito 
	FOREIGN KEY (id_nota_perito) REFERENCES nota_perito (id)
	ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE nota_perito ADD CONSTRAINT FK_nota_perito_persona 
	FOREIGN KEY (id_perito) REFERENCES persona (id)
	ON DELETE NO ACTION ON UPDATE CASCADE
;

ALTER TABLE nota_perito ADD CONSTRAINT FK_nota_perito_nota_servicio 
	FOREIGN KEY (id_nota) REFERENCES nota_servicio (id)
	ON DELETE NO ACTION ON UPDATE CASCADE
;

ALTER TABLE detalle_nota ADD CONSTRAINT FK_detalle_nota_extintor 
	FOREIGN KEY (id_extintor) REFERENCES extintor (id)
	ON DELETE NO ACTION
;

ALTER TABLE detalle_nota ADD CONSTRAINT FK_detalle_nota_nota_servicio 
	FOREIGN KEY (id_nota) REFERENCES nota_servicio (id)
	ON DELETE CASCADE ON UPDATE CASCADE
;

ALTER TABLE detalle_perito ADD CONSTRAINT FK_detalle_servicio_servicio 
	FOREIGN KEY (id_servicio) REFERENCES servicio (id)
	ON DELETE NO ACTION ON UPDATE CASCADE
;

ALTER TABLE detalle_perito ADD CONSTRAINT FK_detalle_perito_nota_perito 
	FOREIGN KEY (id_nota_perito) REFERENCES nota_perito (id)
	ON DELETE CASCADE ON UPDATE CASCADE
;

ALTER TABLE nota_servicio ADD CONSTRAINT FK_nota_servicio_persona 
	FOREIGN KEY (id_cliente) REFERENCES persona (id)
	ON DELETE NO ACTION ON UPDATE CASCADE
;

ALTER TABLE persona ADD CONSTRAINT FK_persona_tipo_persona 
	FOREIGN KEY (id_tipo_persona) REFERENCES tipo_persona (id)
	ON DELETE NO ACTION ON UPDATE CASCADE
;

ALTER TABLE extintor ADD CONSTRAINT FK_extintor_agente_quimico 
	FOREIGN KEY (id_agente_quimico) REFERENCES agente_quimico (id)
	ON DELETE NO ACTION ON UPDATE CASCADE
;
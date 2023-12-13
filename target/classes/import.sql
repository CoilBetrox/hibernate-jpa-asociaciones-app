INSERT INTO clientes (id, nombre, apellido, forma_pago, creado_en, editado_en) VALUES (1,'Roberth','Troya','debito',NULL,NULL),(2,'Jhon','Ala','credito',NULL,NULL),(3,'Andrea','Mora','debito',NULL,NULL),(4,'Alex','Zambrano','paypal',NULL,NULL),(5,'Sam','ApeSAm','paypal',NULL,NULL),(6,'Lucio','ApeLucio','debito',NULL,NULL),(7,'Luna2','ApeLuna2','debito',NULL,NULL),(10,'Name1','Ape1','mercado pago',NULL,NULL),(11,'Roberth','Troya','credito',NULL,NULL),(12,'Lalo','Mena','webpay','2023-11-13 20:10:35','2023-11-13 20:11:25'),(13,'Pia','Perez','paypalplus','2023-11-13 20:17:31','2023-11-13 20:18:07');
Insert INTO alumnos (id, nombre, apellido) values(1, 'Johana', 'Doe'),(2, 'Pepe', 'Gon') ;
Insert INTO cursos (id, titulo, profesor) values(1, 'Curso Spring', 'Andres'),(2, 'Curso Oracle', 'Daniel') ;
Insert INTO direcciones (calle, numero) values ('vaticano', 123),('colon', 456);
INSERT INTO tbl_clientes_direcciones (id_cliente, id_direccion)  values (1, 1),(1, 2);
INSERT INTO clientes_detalles (prime, puntos_acumulados, cliente_detalle_id) values (1, 8000, 1);
Insert InTO tbl_alumnos_cursos (alumno_id, curso_id) values (1, 1), (1,2);
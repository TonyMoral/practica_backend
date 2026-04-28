INSERT INTO genero (nombre) VALUES ('Hombre');
INSERT INTO genero (nombre) VALUES ('Mujer');

INSERT INTO puesto_de_trabajo (nombre) VALUES ('Desarrollador');
INSERT INTO puesto_de_trabajo (nombre) VALUES ('Diseñador');
INSERT INTO puesto_de_trabajo (nombre) VALUES ('Analista');
INSERT INTO puesto_de_trabajo (nombre) VALUES ('Tester');

INSERT INTO usuario (
  contrasena,
  fecha_hora_creacion,
  fecha_nacimiento,
  hora_desayuno,
  nick_usuario,
  nombre,
  primer_apellido,
  segundo_apellido,
  genero_id,
  puesto_de_trabajo_id
)
VALUES (
  'password123',
  '2024-01-01',
  '1990-01-01',
  '08:00',
  'Beren',
  'Tony',
  'Moral',
  'Correa',
  1,
  1
);

INSERT INTO direccion (
  direccion_principal,
  nombre_calle,
  numero_calle,
  usuario_id
)
VALUES (
  1,
  'Calle Principal',
  123,
  1
);
INSERT INTO direccion (
    id,
    nombre_calle,
    numero_calle,
    usuario_id,
    direccion_principal
) VALUES (
    2,
    'Avenida Andalucía',
    25,
    1,
    false
);
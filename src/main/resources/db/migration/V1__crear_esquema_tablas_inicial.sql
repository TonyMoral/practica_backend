CREATE TABLE `genero` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKh1v2uv0jpjuiwvw33pdh046gp` (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `puesto_de_trabajo` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `usuario` (
  `id` int NOT NULL AUTO_INCREMENT,
  `contrasena` varchar(255) NOT NULL,
  `fecha_hora_creacion` date DEFAULT NULL,
  `fecha_nacimiento` varchar(255) NOT NULL,
  `hora_desayuno` time(6) DEFAULT NULL,
  `nick_usuario` varchar(255) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `primer_apellido` varchar(255) NOT NULL,
  `segundo_apellido` varchar(255) DEFAULT NULL,
  `genero_id` int NOT NULL,
  `puesto_de_trabajo_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3ww4fyvyctas314maygwlt62s` (`genero_id`),
  KEY `FKggxfta56s22jhgrmbbc54945d` (`puesto_de_trabajo_id`),
  CONSTRAINT `FK3ww4fyvyctas314maygwlt62s` FOREIGN KEY (`genero_id`) REFERENCES `genero` (`id`),
  CONSTRAINT `FKggxfta56s22jhgrmbbc54945d` FOREIGN KEY (`puesto_de_trabajo_id`) REFERENCES `puesto_de_trabajo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `direccion` (
  `id` int NOT NULL AUTO_INCREMENT,
  `direccion_principal` bit(1) NOT NULL,
  `nombre_calle` varchar(255) NOT NULL,
  `numero_calle` int DEFAULT NULL,
  `usuario_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK73vv55f8i3n3b5me7kprxjr6o` (`usuario_id`),
  CONSTRAINT `FK73vv55f8i3n3b5me7kprxjr6o` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
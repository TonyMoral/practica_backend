CREATE TABLE imagen_usuario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario_id INT NOT NULL,
    imagen LONGTEXT NOT NULL,
    CONSTRAINT fk_imagen_usuario_usuario FOREIGN KEY (usuario_id) REFERENCES usuario(id) ON DELETE CASCADE
);

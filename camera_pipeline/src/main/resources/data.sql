INSERT INTO camera_pipeline.`user`
(id, email, `password`)
VALUES(1, 'joao@gmail.com', 
'$2a$10$gBWh9jv/DCCz5Wqtih8xSuie0UJokYjbHEauxMUg63riMt7zM37Ry')
ON DUPLICATE KEY UPDATE `password` = `password`;
-- Passwort -> 123456

INSERT INTO camera_pipeline.`role`
(`role`)
VALUES('ROLE_ADMIM'),
('ROLE_MANAGEMENT'),
('ROLE_SALES'),
('ROLE_USER')
ON DUPLICATE KEY UPDATE `role` = `role`;

INSERT INTO camera_pipeline.role_user
(user_id, role_id)
VALUES(1, 'ROLE_USER')
ON DUPLICATE KEY UPDATE role_id = role_id;

INSERT INTO camera_pipeline.group_pipeline
(id, name, user_id)
VALUES(1, 'Deteccao de objetos', 1)
ON DUPLICATE KEY UPDATE name = name;

INSERT INTO camera_pipeline.pipeline
(id, creation_date, description, is_active, last_change, name, group_pipeline_id)
VALUES(1, '2022-06-26 14:30:30', 
    'Servico que reduz o tamanho da imagem para um tamanho especifico determinado pelo usuario', 
    0, '2022-06-26 14:30:30', 'Reduzir o tamanho da imagem', 1)
ON DUPLICATE KEY UPDATE name = name;

INSERT INTO camera_pipeline.camera
(ip, fps, heigth, is_colored, manufacturer, model, name, night_vision, view_angle, width, user_id)
VALUES('192.168.0.10', 100, 20, 1, 'Intelbras', 'Bullet HD', 'Camera Garagem', 1, 360, 15, 1)
ON DUPLICATE KEY UPDATE name = name;


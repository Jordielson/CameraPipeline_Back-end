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

INSERT INTO camera_pipeline.pipeline
(id, creation_date, description, is_active, last_change, name, user_id)
VALUES(1, '2022-06-26 14:30:30', 
    'Servico que reduz o tamanho da imagem para um tamanho especifico determinado pelo usuario', 
    0, '2022-06-26 14:30:30', 'Reduzir o tamanho da imagem', 1)
ON DUPLICATE KEY UPDATE name = name;

INSERT INTO camera_pipeline.camera
(id, url, fps_limiter, is_private, is_active, name, user_id)
VALUES(1, 'http://localhost:5000/api', 60, 1, 1, 'Camera 01', 1)
ON DUPLICATE KEY UPDATE url = url;

INSERT INTO camera_pipeline.camera_pipeline
(pipeline_id, camera_id)
VALUES(1, 1)
ON DUPLICATE KEY UPDATE pipeline_id = pipeline_id;

INSERT INTO camera_pipeline.model_pdi
(id, url, category, name, user_id)
VALUES(1, 'http://localhost:5000/api', 1, 'Redimensionar imagem', 1)
ON DUPLICATE KEY UPDATE id = id;

INSERT INTO camera_pipeline.pdi
(id, model_pdi_id, pipeline_id)
VALUES(1, 1, 1)
ON DUPLICATE KEY UPDATE id = id;

INSERT INTO camera_pipeline.`parameter`
(id, name, `type`, model_pdi_id)
VALUES(1, 'Tamanho da imagem', 0, 1)
ON DUPLICATE KEY UPDATE id = id;

INSERT INTO camera_pipeline.value_parameter
(id, value, parameter_id, pdi_id)
VALUES(1, '25x30', 1, 1)
ON DUPLICATE KEY UPDATE id = id;



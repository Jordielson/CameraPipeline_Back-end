CREATE USER IF NOT EXISTS 'cp_dev' IDENTIFIED BY 'cp_dev';

CREATE DATABASE IF NOT EXISTS camera_pipeline;

GRANT ALL ON camera_pipeline.* TO 'cp_dev';
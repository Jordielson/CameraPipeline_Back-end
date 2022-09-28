CREATE USER cp_dev WITH PASSWORD 'cp_dev';

CREATE DATABASE camera_pipeline;

GRANT ALL PRIVILEGES ON DATABASE camera_pipeline TO cp_dev;
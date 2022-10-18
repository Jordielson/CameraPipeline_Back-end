INSERT INTO public.users
(id, email, "password")
VALUES(1, 'admin@admin.com', '$2a$10$gBWh9jv/DCCz5Wqtih8xSuie0UJokYjbHEauxMUg63riMt7zM37Ry') ON CONFLICT DO NOTHING;
-- Passwort -> 123456

INSERT INTO public.role
("role")
VALUES('ROLE_ADMIM'),('ROLE_MANAGEMENT'),('ROLE_SALES'),('ROLE_USER')
ON CONFLICT DO NOTHING;

INSERT INTO public.role_user
(user_id, role_id)
VALUES(1, 'ROLE_USER')
ON CONFLICT DO NOTHING;

-- UUID generator for Postgres
CREATE EXTENSION IF NOT EXISTS "uuid-ossp"; 
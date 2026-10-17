--liquibase formatted sql

--changeset Saad Mo:1
--comment remove UNIQUE from role_id and add UNIQUE to email

ALTER TABLE users DROP CONSTRAINT IF EXISTS users_role_id_key;

ALTER TABLE users ADD CONSTRAINT uq_users_email UNIQUE (email);



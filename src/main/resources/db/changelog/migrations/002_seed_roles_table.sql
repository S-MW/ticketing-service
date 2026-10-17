--liquibase formatted sql

--changeset Saad Mo:1
--comment seeding roles table


INSERT INTO roles (name, code, create_at, created_by, modify_at, modify_by)
values
    ('Agent',10,CURRENT_TIMESTAMP,'SYSTEM',CURRENT_TIMESTAMP ,'SYSTEM'),
    ('Customer',20,CURRENT_TIMESTAMP,'SYSTEM',CURRENT_TIMESTAMP ,'SYSTEM'),
    ('Monitor',30,CURRENT_TIMESTAMP,'SYSTEM',CURRENT_TIMESTAMP ,'SYSTEM');


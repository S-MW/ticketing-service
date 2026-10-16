--liquibase formatted sql

--changeset Saad Mo:1
--comment add roles table


CREATE TABLE roles (
                      id INT GENERATED ALWAYS AS IDENTITY (START WITH 1)  PRIMARY KEY ,
                      name VARCHAR(255) NOT NULL,
                      code INTEGER UNIQUE NOT NULL ,
                      is_enabled boolean default TRUE NOT NULL,
                      is_deleted boolean default FALSE NOT NULL,
                      create_at TIMESTAMP NOT NULL,
                      created_by VARCHAR(255) NOT NULL,
                      modify_at TIMESTAMP NOT NULL,
                      modify_by VARCHAR(255) NOT NULL
);
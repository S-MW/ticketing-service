--liquibase formatted sql

--changeset Saad Mo:1
--comment add tickets table


CREATE TYPE ticket_status AS ENUM ('NEW', 'RESOLVED', 'EXPIRED');


CREATE TABLE tickets (
                      id INT GENERATED ALWAYS AS IDENTITY (START WITH 1)  PRIMARY KEY ,
                      text VARCHAR(250) NOT NULL,
                      status ticket_status,
                      is_deleted boolean default FALSE NOT NULL,
                      create_at TIMESTAMP NOT NULL,
                      created_by VARCHAR(255) NOT NULL,
                      modify_at TIMESTAMP NOT NULL,
                      modify_by VARCHAR(255) NOT NULL
);
--liquibase formatted sql

--changeset Saad Mo:1
--comment add login_history_logs table


CREATE TABLE login_history_logs (
                      id INT GENERATED ALWAYS AS IDENTITY (START WITH 1)  PRIMARY KEY ,
                      email VARCHAR(255) NOT NULL,
                      success BOOLEAN NOT NULL,
                      login_at TIMESTAMP NOT NULL
);

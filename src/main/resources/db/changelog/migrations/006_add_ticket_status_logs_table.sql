--liquibase formatted sql

--changeset Saad Mo:1
--comment add ticket_status_logs table


CREATE TABLE ticket_status_logs (
                      id INT GENERATED ALWAYS AS IDENTITY (START WITH 1)  PRIMARY KEY ,
                      ticket_id INT NOT NULL,
                      old_status ticket_status,
                      new_status ticket_status NOT NULL,
                      changed_by VARCHAR(255) NOT NULL,
                      changed_at TIMESTAMP NOT NULL
);

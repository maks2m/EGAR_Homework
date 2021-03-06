/*
CREATE DATABASE "JDBC_homework_egar";
*/

DROP TABLE IF EXISTS public.production_order;
DROP TABLE IF EXISTS public.user_role;
DROP TABLE IF EXISTS public.assignment;
DROP TABLE IF EXISTS public.usr;
DROP TABLE IF EXISTS public.role;


CREATE TABLE IF NOT EXISTS public.assignment
(
    id bigint NOT NULL GENERATED BY DEFAULT AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    date_assignment date,
    general_contractor character varying(255),
    name_npp character varying(255),
    number_assignment character varying(255),
    number_block character varying(255),
    number_contract character varying(255),
    CONSTRAINT assignment_pkey PRIMARY KEY (id)
    );

CREATE TABLE IF NOT EXISTS public.usr
(
    id bigint NOT NULL GENERATED BY DEFAULT AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    active boolean NOT NULL,
    first_name character varying(255),
    full_name character varying(255),
    last_name character varying(255),
    password character varying(255) NOT NULL,
    username character varying(255) NOT NULL,
    CONSTRAINT user_pkey PRIMARY KEY (id)
    );

CREATE TABLE IF NOT EXISTS public.production_order
(
    id bigint NOT NULL GENERATED BY DEFAULT AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    date_developer_contract date,
    date_production_order date,
    identifier_production_order character varying(255),
    manufacturer character varying(255),
    name_equipment character varying(1024),
    notes character varying(1024) ,
    number_case integer NOT NULL,
    number_developer_contract character varying(255),
    number_production_order character varying(255),
    status character varying(255),
    id_assignment bigint NOT NULL,
    id_responsible_user bigint,
    CONSTRAINT production_order_pkey PRIMARY KEY (id),
    CONSTRAINT production_order_assignment_fk FOREIGN KEY (id_assignment)
    REFERENCES public.assignment (id) MATCH SIMPLE
    ON UPDATE CASCADE
    ON DELETE CASCADE,
    CONSTRAINT production_order_responsible_user_fk FOREIGN KEY (id_responsible_user)
    REFERENCES public.usr (id) MATCH SIMPLE
    ON UPDATE CASCADE
    ON DELETE SET NULL
    );

CREATE TABLE IF NOT EXISTS public.role
(
    id bigint NOT NULL GENERATED BY DEFAULT AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    role_text character varying(255),
    CONSTRAINT role_pkey PRIMARY KEY (id)

);

CREATE TABLE IF NOT EXISTS public.user_role
(
    id_user bigint NOT NULL,
    id_role bigint NOT NULL,
    CONSTRAINT usr_role_pkey PRIMARY KEY (id_user, id_role),
    CONSTRAINT role_user_fk FOREIGN KEY (id_user)
    REFERENCES public.usr (id) MATCH SIMPLE
    ON UPDATE CASCADE
    ON DELETE CASCADE,
    CONSTRAINT user_role_fk FOREIGN KEY (id_role)
    REFERENCES public.role (id) MATCH SIMPLE
    ON UPDATE CASCADE
    ON DELETE CASCADE

    );

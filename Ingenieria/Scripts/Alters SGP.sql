-- 20170526 Alter table para la tabla, clientes manejar por lógica de negocio.
-- Si se va a correr el script de creación por primera vez no es necesario correr los siguientes dos alter.
alter table cliente
alter column telefono_contacto drop not null;

alter table cliente
alter column celular_contacto drop not null;


-- 20170526 Alter a la tabla cliente para agregar la ciudad.
-- Si se corre el script de creación de tablas no es necesario ejecutar este script.
create table CIUDAD(
    CIUD_ID		SERIAL			not null,
    NOMBRE_CIUDAD	CHARACTER VARYING(200)		not null,
    DEPA_ID		BIGINT			not null,
    constraint PK_CIUDAD primary key (CIUD_ID)
);

create table DEPARTAMENTO(
    DEPA_ID		SERIAL			not null,
    NOMBRE_DEPARTAMENTO	CHARACTER VARYING(200)		not null,
    PAIS_ID		BIGINT			not null,
    constraint PK_DEPARTAMENTO primary key (DEPA_ID)
);

create table PAIS(
    PAIS_ID		SERIAL			not null,
    NOMBRE_PAIS		CHARACTER VARYING(200)		not null,
    constraint PK_PAIS primary key (PAIS_ID)
);

alter table DEPARTAMENTO
   add constraint FK_DEPA_REFERENCE_PAIS foreign key (PAIS_ID)
      references PAIS (PAIS_ID)
      on delete restrict on update restrict;

alter table CIUDAD
   add constraint FK_CIUD_REFERENCE_DEPA foreign key (DEPA_ID)
      references DEPARTAMENTO (DEPA_ID)
      on delete restrict on update restrict;

delete from CLIENTE;

alter table CLIENTE
   add column CIUD_ID BIGINT not null;

alter table CLIENTE
   add constraint FK_CLIE_REFERENCE_CIUD foreign key (CIUD_ID)
      references CIUDAD (CIUD_ID)
      on delete restrict on update restrict;
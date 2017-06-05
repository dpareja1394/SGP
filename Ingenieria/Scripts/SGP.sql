/*==============================================================*/
/* DBMS name:      PostgreSQL 8                                 */
/* Created on:     24/06/2016 10:59:25 p. m.                    */
/*==============================================================*/


drop table ACTIVIDAD;

drop table CASO_SOPORTE;

drop table CLIENTE;

drop table ESTADO_HISTORIA_USUARIO;

drop table ESTADO_PROYECTO;

drop table HISTORIA_DE_USUARIO;

drop table PARAMETRO;

drop table PROYECTO;

drop table PROYECTO_USUARIO_ROL;

drop table REQUERIMIENTO;

drop table ROL;

drop table SEGUIMIENTO_CASO;

drop table TIPO_ACTIVIDAD;

drop table USUARIO;

/*==============================================================*/
/* Table: ACTIVIDAD                                             */
/*==============================================================*/
create table ACTIVIDAD (
   ACTI_ID              SERIAL               not null,
   TIAC_ID              BIGINT                 not null,
   USUA_ID              BIGINT                 not null,
   DESCRIPCION_ACTIVIDAD CHARACTER VARYING(2000)        not null,
   FECHA_HORA_INICIO    DATE                 not null,
   FECHA_HORA_FIN       DATE                 not null,
   constraint PK_ACTIVIDAD primary key (ACTI_ID)
);

/*==============================================================*/
/* Table: CASO_SOPORTE                                          */
/*==============================================================*/
create table CASO_SOPORTE (
   CASO_ID              SERIAL               not null,
   PROY_ID              BIGINT                 not null,
   USUARIO_RECEPTOR     BIGINT                 not null,
   TITULO_CASO          CHARACTER VARYING(200)         not null,
   DESCRIPCION_CASO     CHARACTER VARYING(2000)        not null,
   constraint PK_CASO_SOPORTE primary key (CASO_ID)
);

/*==============================================================*/
/* Table: CLIENTE                                               */
/*==============================================================*/
create table CLIENTE (
   CLIE_ID              SERIAL               not null,
   NOMBRE_EMPRESA       CHARACTER VARYING(200)         null,
   NIT                  CHARACTER VARYING(30)          null,
   TELEFONO_CONTACTO    CHARACTER VARYING(20)          null,
   DIRECCION_CONTACTO   CHARACTER VARYING(200)         null,
   NOMBRE_CONTACTO      CHARACTER VARYING(200)         not null,
   CELULAR_CONTACTO     CHARACTER VARYING(200)         null,
   ENLACE_WEB           CHARACTER VARYING(200)         null,
   CIUD_ID				BIGINT						   not null,
   FECHA_CREACION 		DATE 						   not null,
   FECHA_MODIFICACION 	DATE						   null,
   USUARIO_CREACION 	BIGINT 						   not null,
   USUARIO_MODIFICACION BIGINT						   null,
   constraint PK_CLIENTE primary key (CLIE_ID)
);

/*==============================================================*/
/* Table: ESTADO_HISTORIA_USUARIO                               */
/*==============================================================*/
create table ESTADO_HISTORIA_USUARIO (
   ESHI_ID              SERIAL               not null,
   DESCRIPCION_ESTADO   CHARACTER VARYING(200)         not null,
   NOMBRE_CORTO         CHARACTER VARYING(3)           not null,
   constraint PK_ESTADO_HISTORIA_USUARIO primary key (ESHI_ID)
);

/*==============================================================*/
/* Table: ESTADO_PROYECTO                                       */
/*==============================================================*/
create table ESTADO_PROYECTO (
   ESPR_ID              SERIAL               not null,
   DESCRIPCION_ESTADO   CHARACTER VARYING(200)         not null,
   NOMBRE_CORTO         CHARACTER VARYING(3)           not null,
   constraint PK_ESTADO_PROYECTO primary key (ESPR_ID)
);

/*==============================================================*/
/* Table: HISTORIA_DE_USUARIO                                   */
/*==============================================================*/
create table HISTORIA_DE_USUARIO (
   HIUS_ID              SERIAL               not null,
   USUA_ID              BIGINT                 not null,
   ESTADO_HU            BIGINT                 not null,
   REQU_ID              BIGINT                 not null,
   DETALLE_HISTORIA     CHARACTER VARYING(2000)        not null,
   TITULO_HISTORIA      CHARACTER VARYING(150)         not null,
   constraint PK_HISTORIA_DE_USUARIO primary key (HIUS_ID)
);

/*==============================================================*/
/* Table: PARAMETRO                                             */
/*==============================================================*/
create table PARAMETRO (
   ID_PARA              SERIAL               not null,
   DESCRIPCION_PARAMETRO CHARACTER VARYING(20)          not null,
   VALOR_PARAMETRO      CHARACTER VARYING(2500)        not null,
   NOMBRE_CORTO         CHARACTER VARYING(15)          not null,
   constraint PK_PARAMETRO primary key (ID_PARA),
   constraint UNIQUE_NOMBRE_CORTO unique (NOMBRE_CORTO)
);

/*==============================================================*/
/* Table: PROYECTO                                              */
/*==============================================================*/
create table PROYECTO (
   PROY_ID              SERIAL               not null,
   CLIE_ID              BIGINT                 not null,
   ESTADO_PROYECTO      BIGINT                 not null,
   DESC_PROYECTO        CHARACTER VARYING(2000)        not null,
   constraint PK_PROYECTO primary key (PROY_ID)
);

/*==============================================================*/
/* Table: PROYECTO_USUARIO_ROL                                  */
/*==============================================================*/
create table PROYECTO_USUARIO_ROL (
   ROL_ID               BIGINT                 not null,
   USUA_ID              BIGINT                 not null,
   PROY_ID              BIGINT                 not null,
   constraint PK_PROYECTO_USUARIO_ROL primary key (USUA_ID, PROY_ID)
);

/*==============================================================*/
/* Table: REQUERIMIENTO                                         */
/*==============================================================*/
create table REQUERIMIENTO (
   REQU_ID              SERIAL               not null,
   PROY_ID              BIGINT                 not null,
   NOMBRE_REQUERIMIENTO CHARACTER VARYING(200)         not null,
   DESCRIPCION_REQUERIMIENTO CHARACTER VARYING(2000)        not null,
   constraint PK_REQUERIMIENTO primary key (REQU_ID)
);

/*==============================================================*/
/* Table: ROL                                                   */
/*==============================================================*/
create table ROL (
   ROL_ID               SERIAL               not null,
   DESC_ROL             CHARACTER VARYING(150)         not null,
   NOMBRE_CORTO         CHARACTER VARYING(3)           not null,
   constraint PK_ROL primary key (ROL_ID)
);

/*==============================================================*/
/* Table: SEGUIMIENTO_CASO                                      */
/*==============================================================*/
create table SEGUIMIENTO_CASO (
   SECA_ID              SERIAL               not null,
   CASO_ID              BIGINT                 not null,
   USUARIO_ACTIVIDAD    BIGINT                 not null,
   DESCRIPCION_ACTIVIDAD CHARACTER VARYING(2000)        not null,
   constraint PK_SEGUIMIENTO_CASO primary key (SECA_ID)
);

/*==============================================================*/
/* Table: TIPO_ACTIVIDAD                                        */
/*==============================================================*/
create table TIPO_ACTIVIDAD (
   TIAC_ID              SERIAL               not null,
   DESCRIPCION_TIACT    CHARACTER VARYING(150)         not null,
   NOMBRE_CORTO         CHARACTER VARYING(3)           not null,
   constraint PK_TIPO_ACTIVIDAD primary key (TIAC_ID)
);

/*==============================================================*/
/* Table: USUARIO                                               */
/*==============================================================*/
create table USUARIO (
   USUA_ID              SERIAL               not null,
   NOMBRE_USUARIO       CHARACTER VARYING(150)         not null,
   APELLIDO_USUARIO     CHARACTER VARYING(150)         not null,
   EMAIL_USUARIO        CHARACTER VARYING(150)         not null,
   PASSWORD_USUARIO     CHARACTER VARYING(20)          not null,
   ESTADO_REGISTRO_USUARIO CHARACTER VARYING(1)           not null,
   NUDOC_USUARIO        CHARACTER VARYING(15)          not null,
   constraint PK_USUARIO primary key (USUA_ID)
);

/*==============================================================*/
/* Table: CIUDAD                                                */
/*==============================================================*/
create table CIUDAD(
    CIUD_ID		SERIAL			not null,
    NOMBRE_CIUDAD	CHARACTER VARYING(200)		not null,
    DEPA_ID		BIGINT			not null,
    constraint PK_CIUDAD primary key (CIUD_ID)
);

/*==============================================================*/
/* Table: DEPARTAMENTO                                          */
/*==============================================================*/
create table DEPARTAMENTO(
    DEPA_ID		SERIAL			not null,
    NOMBRE_DEPARTAMENTO	CHARACTER VARYING(200)		not null,
    PAIS_ID		BIGINT			not null,
    constraint PK_DEPARTAMENTO primary key (DEPA_ID)
);

/*==============================================================*/
/* Table: PAIS                                                  */
/*==============================================================*/
create table PAIS(
    PAIS_ID		SERIAL			not null,
    NOMBRE_PAIS		CHARACTER VARYING(200)		not null,
    constraint PK_PAIS primary key (PAIS_ID)
);


alter table ACTIVIDAD
   add constraint FK_ACTIVIDA_REFERENCE_TIPO_ACT foreign key (TIAC_ID)
      references TIPO_ACTIVIDAD (TIAC_ID)
      on delete restrict on update restrict;

alter table ACTIVIDAD
   add constraint FK_ACTIVIDA_REFERENCE_USUARIO foreign key (USUA_ID)
      references USUARIO (USUA_ID)
      on delete restrict on update restrict;

alter table CASO_SOPORTE
   add constraint FK_CASO_SOP_REFERENCE_PROYECTO foreign key (PROY_ID)
      references PROYECTO (PROY_ID)
      on delete restrict on update restrict;

alter table CASO_SOPORTE
   add constraint FK_CASO_SOP_REFERENCE_USUARIO foreign key (USUARIO_RECEPTOR)
      references USUARIO (USUA_ID)
      on delete restrict on update restrict;

alter table HISTORIA_DE_USUARIO
   add constraint FK_HISTORIA_REFERENCE_REQUERIM foreign key (REQU_ID)
      references REQUERIMIENTO (REQU_ID)
      on delete restrict on update restrict;

alter table HISTORIA_DE_USUARIO
   add constraint FK_HISTORIA_REFERENCE_USUARIO foreign key (USUA_ID)
      references USUARIO (USUA_ID)
      on delete restrict on update restrict;

alter table HISTORIA_DE_USUARIO
   add constraint FK_HISTORIA_REFERENCE_ESTADO_H foreign key (ESTADO_HU)
      references ESTADO_HISTORIA_USUARIO (ESHI_ID)
      on delete restrict on update restrict;

alter table PROYECTO
   add constraint FK_PROYECTO_REFERENCE_CLIENTE foreign key (CLIE_ID)
      references CLIENTE (CLIE_ID)
      on delete restrict on update restrict;

alter table PROYECTO
   add constraint FK_PROYECTO_REFERENCE_ESTADO_P foreign key (ESTADO_PROYECTO)
      references ESTADO_PROYECTO (ESPR_ID)
      on delete restrict on update restrict;

alter table PROYECTO_USUARIO_ROL
   add constraint FK_PROYECTO_REFERENCE_ROL foreign key (ROL_ID)
      references ROL (ROL_ID)
      on delete restrict on update restrict;

alter table PROYECTO_USUARIO_ROL
   add constraint FK_PROYECTO_REFERENCE_USUARIO foreign key (USUA_ID)
      references USUARIO (USUA_ID)
      on delete restrict on update restrict;

alter table PROYECTO_USUARIO_ROL
   add constraint FK_PROYECTO_REFERENCE_PROYECTO foreign key (PROY_ID)
      references PROYECTO (PROY_ID)
      on delete restrict on update restrict;

alter table REQUERIMIENTO
   add constraint FK_REQUERIM_REFERENCE_PROYECTO foreign key (PROY_ID)
      references PROYECTO (PROY_ID)
      on delete restrict on update restrict;

alter table SEGUIMIENTO_CASO
   add constraint FK_SEGUIMIE_REFERENCE_CASO_SOP foreign key (CASO_ID)
      references CASO_SOPORTE (CASO_ID)
      on delete restrict on update restrict;

alter table SEGUIMIENTO_CASO
   add constraint FK_SEGUIMIE_REFERENCE_USUARIO foreign key (USUARIO_ACTIVIDAD)
      references USUARIO (USUA_ID)
      on delete restrict on update restrict;
      
alter table DEPARTAMENTO
   add constraint FK_DEPA_REFERENCE_PAIS foreign key (PAIS_ID)
      references PAIS (PAIS_ID)
      on delete restrict on update restrict;

alter table CIUDAD
   add constraint FK_CIUD_REFERENCE_DEPA foreign key (DEPA_ID)
      references DEPARTAMENTO (DEPA_ID)
      on delete restrict on update restrict;

alter table CLIENTE
   add constraint FK_CLIE_REFERENCE_CIUD foreign key (CIUD_ID)
      references CIUDAD (CIUD_ID)
      on delete restrict on update restrict;

alter table CLIENTE
   add constraint FK_CLIE_REFER_USUA1 foreign key (USUARIO_CREACION)
      references USUARIO (USUA_ID)
      on delete restrict on update restrict;
      
alter table CLIENTE
   add constraint FK_CLIE_REFER_USUA2 foreign key (USUARIO_MODIFICACION)
      references USUARIO (USUA_ID)
      on delete restrict on update restrict;
-- 20170526 Alter table para la tabla, clientes manejar por lógica de negocio.
-- Si se va a correr el script de creación por primera vez no es necesario correr los siguientes dos alter.
alter table cliente
alter column telefono_contacto drop not null;

alter table cliente
alter column celular_contacto drop not null;
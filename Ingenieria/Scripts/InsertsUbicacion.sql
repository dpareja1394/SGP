insert into pais(nombre_pais) values('COLOMBIA');
insert into pais(nombre_pais) values('ARGENTINA');
insert into pais(nombre_pais) values('MEXICO');
insert into pais(nombre_pais) values('BOLIVIA');
insert into pais(nombre_pais) values('VENEZUELA');

select * from pais where nombre_pais = 'COLOMBIA';

insert into departamento(pais_id, nombre_departamento) values (1, 'ANTIOQUIA');
insert into departamento(pais_id, nombre_departamento) values (1, 'CALDAS');
insert into departamento(pais_id, nombre_departamento) values (1, 'VALLE DEL CAUCA');

select * from departamento where nombre_departamento = 'CALDAS';

insert into ciudad(depa_id, nombre_ciudad)values(3, 'SANTIAGO DE CALI');
insert into ciudad(depa_id, nombre_ciudad)values(3, 'JAMUNDÍ');
insert into ciudad(depa_id, nombre_ciudad)values(3, 'YUMBO');
insert into ciudad(depa_id, nombre_ciudad)values(3, 'PALMIRA');
insert into ciudad(depa_id, nombre_ciudad)values(3, 'CANDELARIA');


--Adicionar otras ciudades
insert into ciudad(depa_id, nombre_ciudad)values(1, 'MEDELLÍN');
insert into ciudad(depa_id, nombre_ciudad)values(1, 'RIONEGRO');
insert into ciudad(depa_id, nombre_ciudad)values(1, 'CALDAS');
insert into ciudad(depa_id, nombre_ciudad)values(1, 'ITAGÜÍ');

insert into ciudad(depa_id, nombre_ciudad)values(2, 'MANIZALES');
insert into ciudad(depa_id, nombre_ciudad)values(2, 'CHINCHINÁ');

select * from ciudad order by depa_id asc;

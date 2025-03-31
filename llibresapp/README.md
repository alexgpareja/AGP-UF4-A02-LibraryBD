# UF4 Activitat 02

Aquesta activitat té com a objectiu canviar la manera en la que accedim a les dades. Utilitzant una base de dades (MariaDB) per poder persistir els registres dels llibres.

## Usuari creat

```sql
-- Crear l'usuari
create user 'bibliotecari'@'localhost' identified by 'llibre';

-- Assignar tots els permisos sobre la base de dades
GRANT ALL PRIVILEGES ON llibresapp.* TO 'bibliotecari'@'localhost';

-- Aplicar els canvis
FLUSH PRIVILEGES;

-- Verificar els permisos
SHOW GRANTS FOR 'bibliotecari'@'localhost';
```

## Log in

Si l'usuari per accedir no existeix, reenvia de nou al formulari per iniciar sessió:

![alt text](image.png)

## Index

Si s'accedeix correctament, es redirigeix al index:

![alt text](image-1.png)

## Llistat

![alt text](image-2.png)

## Cerca per ID

Si el ID existeix:

![alt text](image-3.png)

Si el ID no existeix:

![alt text](image-4.png)

## Inserir un llibre

![alt text](image-5.png)

![alt text](image-6.png)

## preguntes de reflexió

1. Per què al servei estem utilitzant mètodes que no hem declarat explícitament al repositori? Com és possible?
   
2. 

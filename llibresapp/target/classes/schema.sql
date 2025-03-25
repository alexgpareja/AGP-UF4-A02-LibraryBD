Create database IF NOT EXISTS llibresapp;

USE llibresapp;

CREATE TABLE llibres (
    id_llibre INT PRIMARY KEY AUTO_INCREMENT,
    isbn VARCHAR(13) UNIQUE NOT NULL,
    titol VARCHAR(255) UNIQUE NOT NULL,
    autor VARCHAR(255) NOT NULL,
    editorial VARCHAR(255) NOT NULL,
    datapublicacio DATE NOT NULL,
    tematica VARCHAR(255) NOT NULL
);
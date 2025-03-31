package com.iticbcn.mywebapp.llibresapp.Service;

import com.iticbcn.mywebapp.llibresapp.Model.Llibre;

import java.util.Optional;
import java.util.Set;

public interface ILlibreService {

    // Mètode per trobar tots els llibres
    Set<Llibre> findAll();

    // Mètode per cercar un llibre pel seu títol
    Llibre findByTitol(String titol);

    // Mètode per cercar llibres pel seu títol i editorial
    Set<Llibre> findByTitolAndEditorial(String titol, String editorial);

    // Mètode per validar el format de l'ISBN
    boolean validarISBN(String isbn);

    // Mètode per cercar un llibre per ID (retorna un Optional)
    Optional<Llibre> findByIdLlibre(int id);

    // Mètode per desar un nou llibre
    void save(Llibre llibre);

    // Valida el format de la data de publicació
    public boolean validarDataPublicacio(String data);
}
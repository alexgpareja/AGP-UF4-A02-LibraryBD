package com.iticbcn.mywebapp.llibresapp.Service;

import com.iticbcn.mywebapp.llibresapp.Model.Llibre;
import com.iticbcn.mywebapp.llibresapp.Repository.ILlibreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.Set;

@Service
public class LlibreServiceImpl implements ILlibreService {

    @Autowired // Injecta el repositori de llibres i els metodes
    private ILlibreRepository llibreRepository;

    // Troba tots els llibres
    @Override
    public Set<Llibre> findAll() {
        return llibreRepository.findAll();
    }

    // Cerca un llibre pel seu títol
    @Override
    public Llibre findByTitol(String titol) {
        return llibreRepository.findByTitol(titol);
    }

    // Cerca llibres pel seu títol i editorial
    @Override
    public Set<Llibre> findByTitolAndEditorial(String titol, String editorial) {
        return llibreRepository.findByTitolAndEditorial(titol, editorial);
    }

    // Valida el format de l'ISBN
    @Override
    public boolean validarISBN(String isbn) {
        return isbn != null && isbn.matches("\\d{13}");
    }

    // Cerca un llibre per ID (retorna un Optional)
    @Override
    public Optional<Llibre> findByIdLlibre(int id) {
        return Optional.ofNullable(llibreRepository.findById(id).orElse(null));
    }

    // Desa un nou llibre
    @Override
    public void save(Llibre llibre) {
        llibreRepository.save(llibre);
    }

    // Valida el format de la data de publicació
    public boolean validarDataPublicacio(String data) {
        try {
            LocalDate.parse(data, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
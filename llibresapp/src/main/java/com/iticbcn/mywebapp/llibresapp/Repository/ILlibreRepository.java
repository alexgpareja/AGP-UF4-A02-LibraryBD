package com.iticbcn.mywebapp.llibresapp.Repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;

import java.util.Set;
import com.iticbcn.mywebapp.llibresapp.Model.Llibre;

@Repository
public interface ILlibreRepository extends CrudRepository<Llibre, Integer> {

    // Mètode findAll que retorna un Set<Llibre>
    @Override
    @NonNull
    Set<Llibre> findAll();

    // Mètode per cercar un llibre pel seu títol
    Llibre findByTitol(String titol);

    // Mètode per cercar llibres pel seu títol i editorial
    Set<Llibre> findByTitolAndEditorial(String titol, String editorial);
}
package com.iticbcn.mywebapp.llibresapp.Model;

import lombok.*;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Data // Genera automàticament getters, setters, toString, equals i hashCode
@AllArgsConstructor // Constructor amb tots els arguments
@NoArgsConstructor // Constructor sense arguments
@Entity // Indica que aquesta classe és una entitat JPA
@Table(name = "llibres") // Especifica el nom de la taula a la base de dades
public class Llibre implements Serializable {

    @Id // Indica que aquest camp és la clau primària
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Estratègia per autoincrementar l'ID
    private int idLlibre;

    @Column(nullable = false) // El camp no pot ser nul
    private String titol;

    @Column(nullable = false)
    private String autor;

    @Column(nullable = false)
    private String editorial;

    @Column(nullable = false)
    private LocalDate datapublicacio; // Canviem el tipus a LocalDate

    @Column(nullable = false)
    private String tematica;

    @Column(nullable = false, unique = true) // El camp ha de ser únic i no nul
    private String isbn;
}
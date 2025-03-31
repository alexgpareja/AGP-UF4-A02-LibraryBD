package com.iticbcn.mywebapp.llibresapp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.iticbcn.mywebapp.llibresapp.Model.Llibre;
import com.iticbcn.mywebapp.llibresapp.Service.*;

import jakarta.servlet.http.HttpSession;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class LlibreController {

    @Autowired // Injectar el servei de llibres i els seus mètodes
    private ILlibreService llibreService;

    // Mostra la pàgina de login
    @GetMapping("/")
    public String iniciar() {
        return "login";
    }

    // Validació de l'accés
    @PostMapping("/index")
    public String login(
            @RequestParam(name = "usuari") String usuari,
            @RequestParam(name = "password") String password,
            HttpSession session,
            Model model) {

        if (usuari.equals("toni") && password.equals("h3ll0!!")) {
            session.setAttribute("usuari", usuari); // Guardar en la sesión
            return "index";
        } else {
            model.addAttribute("error", "Credencials incorrectes");
            return "login";
        }
    }

    // Tancar sessió
    @PostMapping("/logout")
    public String logout() {
        return "redirect:/"; // Redirigir a la pàgina de login
    }

    // Mostra la pàgina d'index
    @GetMapping("/index")
    public String index(@ModelAttribute("users") Model model) {
        return "index";
    }

    // Mostra tots els llibres
    @GetMapping("/consulta")
    public String consulta(Model model) {
        model.addAttribute("llibres", llibreService.findAll());
        return "consulta";
    }

    // Formulari per inserir un nou llibre
    @GetMapping("/inserir")
    public String mostrarFormulariInserir(Model model) {
        model.addAttribute("llibre", new Llibre());
        return "inserir";
    }

    // Processar la inserció d'un nou llibre
    @PostMapping("/inserir")
    public String inserirLlibre(
            @RequestParam(name = "titol") String titol,
            @RequestParam(name = "autor") String autor,
            @RequestParam(name = "editorial") String editorial,
            @RequestParam(name = "datapublicacio") String datapublicacio,
            @RequestParam(name = "tematica") String tematica,
            @RequestParam(name = "isbn") String isbn,
            Model model) {

        // Validar la data de publicació
        LocalDate data;
        if (llibreService.validarDataPublicacio(datapublicacio)) {
            data = LocalDate.parse(datapublicacio, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } else {
            model.addAttribute("error", "Data de publicació no vàlida");
            System.out.println("Data de publicació no vàlida");
            return "inserir";
        }

        // Validar el ISBN
        if (!llibreService.validarISBN(isbn)) {
            model.addAttribute("error", "ISBN no vàlid");
            System.out.println("ISBN no vàlid");
            return "inserir";
        }

        // Crear el llibre
        Llibre llibre = new Llibre();
        llibre.setTitol(titol);
        llibre.setAutor(autor);
        llibre.setEditorial(editorial);
        llibre.setDatapublicacio(data);
        llibre.setTematica(tematica);
        llibre.setIsbn(isbn);

        // Guardar el llibre
        try {
            System.out.println("Intentant guardar el llibre: " + llibre);
            llibreService.save(llibre);
        } catch (Exception e) {
            model.addAttribute("error", "Error al guardar el llibre: " + e.getMessage());
            System.out.println("Error al guardar el llibre: " + e.getMessage());
            return "inserir";
        }

        // Redirigir a la pàgina de consulta
        return "redirect:/consulta";
    }

    // Mostra el formulari de cerca per ID
    @GetMapping("/cercaid")
    public String inputCerca(Model model) {

        Llibre llibre = new Llibre();
        llibre.setIdLlibre(0);
        model.addAttribute("llibreErr", true);
        model.addAttribute("message", "");
        model.addAttribute("llibre", llibre);

        return "cercaid";
    }

    // Cerca un llibre per ID
    @PostMapping("/cercaid")
    public String cercaId(@RequestParam int idLlibre, Model model) {
        Optional<Llibre> optionalLlibre = llibreService.findByIdLlibre(idLlibre);

        if (optionalLlibre.isPresent()) {
            Llibre llibre = optionalLlibre.get();
            model.addAttribute("llibre", llibre);
            model.addAttribute("llibreErr", false);
        } else {
            model.addAttribute("llibreErr", true);
            model.addAttribute("message", "No s'ha trobat cap llibre amb aquest ID.");
        }

        return "cercaid";
    }

}
package com.example.ArquiteturaWebSpringBoot.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.ArquiteturaWebSpringBoot.model.Author;
import com.example.ArquiteturaWebSpringBoot.service.AuthorService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

/**
 * Controller MVC responsável pelas telas de cadastro e listagem de autores.
 * Utiliza Thymeleaf para renderizar as views.
 */
@Controller
@RequiredArgsConstructor
public class AuthorViewController {

    private final AuthorService authorService;

    /**
     * Exibe a lista de autores cadastrados.
     */
    @GetMapping("/authors")
    public String authors(Model model) {
        model.addAttribute("authors", authorService.getAllAuthors());
        return "authors";
    }

    /**
     * Exibe o formulário de cadastro de autor.
     */
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/authors/form")
    public String authorForm(@RequestParam(value = "id", required = false) Long id, Model model) {
        Author author = (id != null) ? authorService.getAuthorById(id).orElse(new Author()) : new Author();
        model.addAttribute("author", author);
        return "authorform";
    }

    /**
     * Processa o cadastro de um novo autor.
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/authors")
    public String saveAuthor(@Valid @ModelAttribute("author") Author author, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "authorform";
        }
        authorService.saveAuthor(author);
        model.addAttribute("successMessage", "Autor cadastrado com sucesso!");
        model.addAttribute("authors", authorService.getAllAuthors());
        return "authors";
    }

    /**
     * Processa a exclusão de um autor existente.
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/authors/{id}/delete")
    public String deleteAuthor(@PathVariable Long id, Model model) {
        boolean deleted = authorService.deleteAuthor(id);
        if (deleted) {
            model.addAttribute("successMessage", "Autor excluído com sucesso!");
        } else {
            model.addAttribute("errorMessage", "Autor não encontrado.");
        }
        model.addAttribute("authors", authorService.getAllAuthors());
        return "authors";
    }
}

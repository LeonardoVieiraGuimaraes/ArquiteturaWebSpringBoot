package com.example.ArquiteturaWebSpringBoot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ArquiteturaWebSpringBoot.model.Book;
import com.example.ArquiteturaWebSpringBoot.repository.BookRepository;

@Service // Indica que esta classe é um serviço gerenciado pelo Spring
public class BookService {

    @Autowired // Injeta automaticamente a dependência do repositório BookRepository
    private BookRepository bookRepository;

    public Book saveBook(Book book) {
        // Salva o livro no banco de dados
        return bookRepository.save(book);
    }

    public List<Book> getAllBooks() {
        // Recupera todos os livros do banco de dados
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(Long id) {
        // Busca um livro pelo ID
        return bookRepository.findById(id);
    }

    public Optional<Book> updateBook(Long id, Book updatedBook) {
        return bookRepository.findById(id).map(existingBook -> {
            existingBook.setIsbn(updatedBook.getIsbn());
            existingBook.setTitulo(updatedBook.getTitulo());
            existingBook.setDataPublicacao(updatedBook.getDataPublicacao());
            existingBook.setGenero(updatedBook.getGenero());
            existingBook.setPreco(updatedBook.getPreco());
            existingBook.setAutor(updatedBook.getAutor());
            return bookRepository.save(existingBook);
        });
    }

    public boolean deleteBook(Long id) {
        // Deleta um livro pelo ID, se ele existir
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

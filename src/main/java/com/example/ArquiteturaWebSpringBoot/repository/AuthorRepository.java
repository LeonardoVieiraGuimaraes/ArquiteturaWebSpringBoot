package com.example.ArquiteturaWebSpringBoot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ArquiteturaWebSpringBoot.model.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}

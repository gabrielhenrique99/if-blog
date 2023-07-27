package com.io.blog.repository;

import com.io.blog.model.Autor;
import com.io.blog.model.Postagem;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostagemRepository extends JpaRepository<Postagem, Long> {
    List<Postagem> findByAutor(Autor autor);
}


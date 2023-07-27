package com.io.blog.service;

import com.io.blog.model.Autor;
import com.io.blog.model.Postagem;
import com.io.blog.repository.PostagemRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostagemService {

    private final PostagemRepository postagemRepository;

    @Autowired
    public PostagemService(PostagemRepository postagemRepository) {
        this.postagemRepository = postagemRepository;
    }

    public void salvarPostagem(Postagem postagem) {
        postagemRepository.save(postagem);
    }

    public List<Postagem> buscarPostagensPorAutor(Autor autor) {
        return postagemRepository.findByAutor(autor);
    }

}


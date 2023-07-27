package com.io.blog.controller;

import com.io.blog.model.Autor;
import com.io.blog.model.Postagem;
import com.io.blog.model.Categoria;
import com.io.blog.service.AutorService;
import com.io.blog.service.PostagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/post")
public class PostagemController {

    private final PostagemService postagemService;
    private final AutorService autorService;

    @Autowired
    public PostagemController(PostagemService postagemService, AutorService autorService) {
        this.postagemService = postagemService;
        this.autorService = autorService;
    }

    @GetMapping("/{autorId}")
    public String exibirPostagens(@PathVariable String matricula, Model model) {
        Autor autor = autorService.buscarAutorPorMatricula(matricula);
        if (autor != null) {
            model.addAttribute("autor", autor);
            model.addAttribute("postagens", postagemService.buscarPostagensPorAutor(autor));
            return "postagens";
        } else {
            return "error";
        }
    }

    @PostMapping("/salvarpost")
    public String salvarPostagem(@ModelAttribute Postagem postagem) {
        postagemService.salvarPostagem(postagem);
        return "redirect:/post/" + postagem.getAutor().getId();
    }

}



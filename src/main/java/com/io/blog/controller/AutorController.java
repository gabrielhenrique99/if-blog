package com.io.blog.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import com.io.blog.model.Autor;
import com.io.blog.service.AutorService;

@CrossOrigin(origins = "http://localhost:5174")
@RestController
@RequestMapping("/autor")
public class AutorController {
	
	@Autowired
	private AutorService serviceAutor;
	
	@PostMapping("/salvar")
	public String salvar(@RequestBody Autor autor) {
		try {
			System.out.println(autor);
			serviceAutor.cadastrarAutor(autor);
			return "{\"status\":\"Usuário cadastrado com sucesso\"}";
		} catch (Exception e) {
			return "{\"status\":\"Erro no cadastro\"}";
		}

	}
	
	@PostMapping("/login")
    public ModelAndView login(@RequestBody Autor autor) {
        ModelAndView modelAndView = new ModelAndView();
        Autor autorEncontrado = serviceAutor.buscarAutorPorMatricula(autor.getMatricula());

        if (autorEncontrado != null) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            if (encoder.matches(autor.getSenha(), autorEncontrado.getSenha())) {
                modelAndView.setViewName("redirect:/post");
                modelAndView.addObject("nomeAutor", autorEncontrado.getNome());
                return modelAndView;
            }
        }

        modelAndView.setViewName("redirect:/login");
        return modelAndView;
    }
	
}


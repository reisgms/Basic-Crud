package com.tasklist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.tasklist.config.SpringJdbcConfig;
import com.tasklist.model.Tarefas;
import com.tasklist.config.*;

@Controller
public class TaskListController {
	
	@GetMapping({"/incluirtarefa", "tasklist"})
	public String CriarTarefa(Model model) {
		model.addAttribute("tarefas", new Tarefas());
		return "incluirtarefa.html";
	}
	
	@GetMapping("/index")
	public String MostrarTarefas(@ModelAttribute Tarefas tarefas, Model model) {
		model.addAttribute("tarefas", tarefas);
		return "index.html";
	}
	
	@PostMapping("/incluirtarefa/salvar")
	public String IncluirTarefaSalvar(@ModelAttribute Tarefas tarefas, Model model) {
		model.addAttribute("tarefas", tarefas);
		System.out.println("foi");
		
		
		SpringJdbcConfig spring = new SpringJdbcConfig();

		/*
		PessoaService ps = new PessoaService(spring.mysqlDataSource());
		Number pessoaid = ps.insertAndGetId("pessoaid");
		try {
			usuario.setPessoa_pessoaid(pessoaid);
			System.out.println("pessoaid>>> " + usuario.getPessoa_pessoaid());
			UsuarioService usuarioService = new UsuarioService();
			usuarioService.criarUsuario(usuario);
		} catch (SQLException e) {
			e.printStackTrace();
		}
*/
		return "index.html";
	}

}

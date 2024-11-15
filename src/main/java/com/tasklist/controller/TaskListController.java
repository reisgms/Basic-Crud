package com.tasklist.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.tasklist.model.Tarefas;
import com.tasklist.services.TasklistServices;


@Controller
public class TaskListController {
	@Autowired
	private TasklistServices tasklistService;
	
	@GetMapping({"/incluirtarefa", "tasklist"})
	public String CriarTarefa(Model model) {
		model.addAttribute("tarefas", new Tarefas());
		return "incluirtarefa.html";
	}
	
	@GetMapping("/index")
	public String MostrarTarefas(@ModelAttribute Tarefas tarefas, Model model) {
		model.addAttribute("tarefas", tasklistService.listarTodos());
		return "index.html";
	}
	
	@PostMapping("/incluirtarefa/salvar")
	public String IncluirTarefaSalvar(@ModelAttribute Tarefas tarefas, Model model) {
		model.addAttribute("tarefas", tarefas);
		System.out.println("foi");
		
		try {
			System.out.println("tarefa >>>" + tarefas.getTarefa() +
					"custo >>>" + tarefas.getCusto() + 
					"data >>>" + tarefas.getDatalimite());
			
			TasklistServices tasklistService = new TasklistServices();
			tasklistService.inseirTarefa(tarefas);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return "index.html";
	}

}

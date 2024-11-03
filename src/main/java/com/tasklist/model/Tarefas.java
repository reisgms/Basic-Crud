package com.tasklist.model;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Service;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType; 

@jakarta.persistence.Table(name="tarefas") 
public class Tarefas {
	@Id
	private long idtarefas;
	private String tarefa;
	private double custo;
	private LocalDate datalimite; 
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int ordemapresentacao;
	
	public long getIdtarefas() {
		return idtarefas;
	}
	public void setIdtarefas(long idtarefas) {
		this.idtarefas = idtarefas;
	}
	public String getTarefa() {
		return tarefa;
	}
	public void setTarefa(String tarefa) {
		this.tarefa = tarefa;
	}
	public double getCusto() {
		return custo;
	}
	public void setCusto(double custo) {
		this.custo = custo;
	}

	public int getOrdemapresentacao() {
		return ordemapresentacao;
	}
	public void setOrdemapresentacao(int ordemapresentacao) {
		this.ordemapresentacao = ordemapresentacao;
	}
	public LocalDate getDatalimite() {
		return datalimite;
	}
	public void setDatalimite(LocalDate datalimite) {
		this.datalimite = datalimite;
	}
	
	
}

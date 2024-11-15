package com.tasklist.repository;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.tasklist.model.Tarefas;

@Repository
public class TasklistRepository {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<Tarefas> listarTarefas(){
		String sql = "SELECT idtarefa, tarefa, custo, datalimite FROM tarefas";
		return jdbcTemplate.query(sql, (ResultSet rs, int rowNum) -> mapTarefas(rs));
	}
	
	private Tarefas mapTarefas(ResultSet rs) throws SQLException {
        Tarefas tarefas = new Tarefas();
        tarefas.setIdtarefas(rs.getLong("idtarefas"));
        tarefas.setTarefa(rs.getString("tarefa"));
        tarefas.setCusto(rs.getDouble("custo"));
        Date dataSql = rs.getDate("datalimite");
        if (dataSql != null) {
            tarefas.setDatalimite(dataSql.toLocalDate());
        }
        return tarefas;
    }
}

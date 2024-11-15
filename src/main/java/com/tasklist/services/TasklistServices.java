package com.tasklist.services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tasklist.config.*;
import com.tasklist.model.Tarefas;
import com.tasklist.repository.TasklistRepository;

@Service
public class TasklistServices {

	public int GerarOrdemDeApresentacao() {
		int maiorNumeroDeOrdemDeApresentacao = 0;

		return maiorNumeroDeOrdemDeApresentacao + 1;
	}

	public int inseirTarefa(Tarefas tarefa) throws SQLException {

		DataSource dataSource = SpringJdbcConfig.mysqlDataSource();
		Connection connection = dataSource.getConnection();
		Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		String query = "insert into tarefas " + "(tarefa, custo, datalimite) " + "value " + "('" + tarefa.getTarefa()
				+ "', " + tarefa.getCusto() + ", '" + tarefa.getDatalimite() + "')";

		return statement.executeUpdate(query);
	}
	
	@Autowired
    private TasklistRepository tasklistRepository;

    public List<Tarefas> listarTodos() {
        return tasklistRepository.listarTarefas();
    }

}

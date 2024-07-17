package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexaoBdSingleton {

	private Connection conexao;
	private static ConexaoBdSingleton instance;
	
	private ConexaoBdSingleton() {
		try {
			conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "alunoifba");
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
	public Connection getConexao() {
        return conexao;
    }
	
	public static ConexaoBdSingleton getInstance() {
		if (instance == null) {
			instance = new ConexaoBdSingleton();
		}
		return instance;
	}
	
}

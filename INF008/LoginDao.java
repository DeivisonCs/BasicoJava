package dao;

import java.sql.SQLException;

import model.Usuario;

public interface LoginDao {
	
	public boolean logar(Usuario usuario) throws SQLException;

}

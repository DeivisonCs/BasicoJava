package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Usuario;

public class LoginDaoPostreges implements LoginDao{

	@Override
	public boolean logar(Usuario usuario) throws SQLException {
		PreparedStatement ps = ConexaoBdSingleton.getInstance()
				.getConexao()
				.prepareStatement("SELECT * FROM usuarios WHERE login = ? AND password = ?");
		ps.setString(1, usuario.getLogin());
		ps.setString(2, usuario.getSenha());
		
		return ps.executeQuery().next();
	}

}

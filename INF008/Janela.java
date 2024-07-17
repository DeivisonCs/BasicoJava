package visao;

import java.awt.EventQueue;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import dao.LoginDao;
import dao.LoginDaoPostreges;
import model.Usuario;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Janela {

	private JFrame frame;
	private JPasswordField txtpassword;
	private JTextField txtuser;
	private LoginDao dao = new LoginDaoPostreges();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Janela window = new Janela();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Janela() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 290, 222);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("User:");
		lblNewLabel.setBounds(39, 44, 46, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password:");
		lblNewLabel_1.setBounds(39, 91, 72, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		txtpassword = new JPasswordField();
		txtpassword.setBounds(108, 88, 129, 20);
		frame.getContentPane().add(txtpassword);
		
		txtuser = new JTextField();
		txtuser.setBounds(108, 41, 129, 20);
		frame.getContentPane().add(txtuser);
		txtuser.setColumns(10);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				var login=new Usuario(txtuser.getText(), txtpassword.getText());
				try {
					if(dao.logar(login)) {
					    JOptionPane.showMessageDialog(null, "Login realizado com sucesso");
					} else {
						JOptionPane.showMessageDialog(null, "Login ou senha incorretos");
					}
				} catch (HeadlessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}	
		});
		btnNewButton.setBounds(39, 129, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Clear");
		btnNewButton_1.setBounds(148, 129, 89, 23);
		frame.getContentPane().add(btnNewButton_1);
	}
}

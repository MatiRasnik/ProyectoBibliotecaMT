package Biblioteca;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class LogIn extends JFrame {

	private JPanel contentPane1;

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		buscaradmin();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuInicio frame = new MenuInicio();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private static void buscaradmin() {
		 try {
			 	
	            ConexionBD conexion = new ConexionBD();
	            Connection con = conexion.conectarConBase(); 
	            String[] usuarios = new String[] {"id", "usuario", "contra", "tipo" };
	            
	            String buscarAdmin = "select * from usuario";
	            Statement cs =  con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				ResultSet resultado = cs.executeQuery(buscarAdmin);
				Object[][] datos = new Object[getResultSetCount(resultado)][4];
				
				int i = 0;
				while (resultado.next())
				{
					String tipousuario;
					int tipo = resultado.getInt("tipo");
					//0-->ADMIN
					//1-->Biblotecologo
					if(tipo == 1) {
						String insertString = "INSERT INTO usuarios" + "(id,usuario,contra,tipo) values" + "('admin', 'admin', '0')";
							int cant = cs.executeUpdate(insertString);
							System.out.println("Cantidad de lineas afectadas "+ cant);
					}}
				} catch (ClassNotFoundException  e) {
					e.printStackTrace();
					System.out.println("Error en la conexion con el Driver");
					} catch (SQLException e) {
					e.printStackTrace();
					System.out.println("Error en la conexion con la Base");
					}
		 }

	private static int getResultSetCount(ResultSet resultSet) {
		int tamaño = 0;
    	try {
    	    resultSet.last();
    	    tamaño = resultSet.getRow();
    	    resultSet.beforeFirst();
    	}
    	catch(Exception ex) {
    	    return 0;
    	}
    	return tamaño;    
	}

	/**
	 * Create the frame.
	 */
	public LogIn() {
		setForeground(Color.WHITE);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 607);
		contentPane1 = new JPanel();
		contentPane1.setBackground(new Color(204, 0, 102));
		contentPane1.setForeground(new Color(0, 0, 0));
		contentPane1.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane1);
		contentPane1.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(394, 0, 600, 578);
		contentPane1.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("BIBMT");
		lblNewLabel.setBounds(181, 11, 318, 139);
		panel.add(lblNewLabel);
		lblNewLabel.setForeground(SystemColor.menu);
		lblNewLabel.setFont(new Font("Verdana Pro Semibold", Font.BOLD, 84));
		
		JButton btnIngresarSesion = new JButton("INICIAR SESION");
		btnIngresarSesion.setBackground(SystemColor.menu);
		btnIngresarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_button_actionPerformed(e);
			}
		});
		btnIngresarSesion.setFont(new Font("Serif", Font.BOLD, 20));
		btnIngresarSesion.setBounds(391, 532, 199, 35);
		panel.add(btnIngresarSesion);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.menu);
		panel_1.setBounds(49, 135, 580, 5);
		panel.add(panel_1);
		
		JLabel lblNewLabel_1 = new JLabel("Usuario:");
		lblNewLabel_1.setForeground(SystemColor.menu);
		lblNewLabel_1.setFont(new Font("Verdana Pro Semibold", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(280, 161, 90, 56);
		panel.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(425, 183, 139, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setForeground(SystemColor.menu);
		lblContrasea.setFont(new Font("Verdana Pro Semibold", Font.PLAIN, 18));
		lblContrasea.setBounds(244, 268, 126, 56);
		panel.add(lblContrasea);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(425, 290, 139, 20);
		panel.add(passwordField);
	}
	protected void do_button_actionPerformed(ActionEvent e) {
	}
}

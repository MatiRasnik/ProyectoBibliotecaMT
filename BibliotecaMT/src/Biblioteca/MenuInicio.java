package Biblioteca;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Color;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Window.Type;
import javax.swing.SwingConstants;

public class MenuInicio extends JFrame {

	private JPanel contentPane;

	public static void main(String[] args) {
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

	
	public MenuInicio() {
		setForeground(Color.WHITE);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 827, 607);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 0, 102));
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(90, 0, 731, 578);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("BIBMT");
		lblNewLabel.setBounds(181, 11, 318, 139);
		panel.add(lblNewLabel);
		lblNewLabel.setForeground(SystemColor.menu);
		lblNewLabel.setFont(new Font("Verdana Pro Semibold", Font.BOLD, 84));
		
		JButton btnNewButton = new JButton("\uD83D\uDD0E BUSQUEDA");
		btnNewButton.setBackground(SystemColor.menu);
		btnNewButton.setFont(new Font("Serif", Font.BOLD, 26));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				do_btnNewButton_actionPerformed(arg0);
			}
		});
		btnNewButton.setBounds(181, 216, 300, 74);
		panel.add(btnNewButton);
		
		JButton btnIngresarSesion = new JButton("INICIAR SESION");
		btnIngresarSesion.setBackground(SystemColor.menu);
		btnIngresarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_button_actionPerformed(e);
			}
		});
		btnIngresarSesion.setFont(new Font("Serif", Font.BOLD, 26));
		btnIngresarSesion.setBounds(181, 348, 300, 74);
		panel.add(btnIngresarSesion);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.menu);
		panel_1.setBounds(49, 135, 580, 5);
		panel.add(panel_1);
	}
	protected void do_btnNewButton_actionPerformed(ActionEvent arg0) {
	}
	protected void do_button_actionPerformed(ActionEvent e) {
	}
}

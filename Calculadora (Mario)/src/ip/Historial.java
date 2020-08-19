package ip;
import javax.swing.*;

import db.DataBase;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Historial {
	private JPanel panel;
	private JTable tabla;
	private JButton botonsito;
	String[][] data;
	public Historial(JFrame cuadro, JPanel anterior) {
		String[] columnNames = {"IP","Estado","APIPA","Reservada","Clase","BroadCast","Gateway","Rango","Dirrecion","Cast","Mask"};
		
		DataBase db = DataBase.getInstances();
		
		data = new String[db.cantidad()][11];
		
		
		db.dbStatement("select * from ip", data);
		
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		
		tabla = new JTable(data,columnNames);
		tabla.setEnabled(false);
		tabla.setSize(300,200);
		
		botonsito = new JButton("Regresar");
		botonsito.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cuadro.setSize(350, 400);
				cuadro.setTitle("IPv4");
				cuadro.setContentPane(anterior);
				cuadro.revalidate();				
				}			
		});
		
		panel.add(tabla, BorderLayout.CENTER);
		panel.add(botonsito, BorderLayout.SOUTH);
		panel.setVisible(true);
		
	}
	
	JPanel getPanel() {
		return this.panel;
		}
	
	void setPanel(JPanel panel) {
		this.panel=panel;
		}
}

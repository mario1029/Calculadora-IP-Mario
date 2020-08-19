package ip;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

public class NumHost {
	private JPanel panel;
	private JTextField num;
	private JLabel etiqueta, respuesta;
	private JButton volver, calcular;
	public NumHost(JFrame cuadro, JPanel anterior) {
		panel = new JPanel();
		
		num = new JTextField(4);
		num.setBounds(110, 10,100,20);
		num.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(!Character.isDigit(e.getKeyChar())) {
					e.consume();
				}
			}
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub	
			}
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		etiqueta = new JLabel("Num. de Hosts:");
		etiqueta.setBounds(10, 10,100,20);
		
		respuesta = new JLabel("-->Resultado");
		respuesta.setBounds(230, 10,100,20);
		
		volver = new JButton("Volver");
		volver.setBounds(50, 50, 100, 20);
		volver.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cuadro.setSize(350, 400);
				cuadro.setTitle("IPv4");
				cuadro.setContentPane(anterior);
				
			}
			
		});
		
		calcular = new JButton("Calcular");
		calcular.setBounds(160, 50, 100, 20);
		calcular.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int numero;
				if(num.getText().isBlank()) {
					numero=0;
				}else {
					numero=Integer.parseInt(num.getText());
				}
				
				int mascara = (int) (+32-(Math.log(numero+2)/Math.log(2)));
				
				//Las siguientes ip son solo de muestra, lo importante 
				//es el valor de la mascara en notacion CIDR
				if(mascara<=8) {
					respuesta.setText("-->"+"10.0.0.0/"+mascara);
				}else if(mascara<=16) {
					respuesta.setText("-->"+"172.16.0.0/"+mascara);
				}else if(mascara<=24) {
					respuesta.setText("-->"+"192.168.1.0/"+mascara);
				}else if(mascara<=30){
					respuesta.setText("-->"+"224.0.0.0/"+mascara);
				}else {
					respuesta.setText("-->"+"Invalid:"+mascara);
				}
			}
			
		});
		
		
		panel.setLayout(null);
		panel.add(etiqueta);
		panel.add(num);
		panel.add(respuesta);
		panel.add(volver);
		panel.add(calcular);
	}

	public JPanel getPanel() {
		return this.panel;
	}
}

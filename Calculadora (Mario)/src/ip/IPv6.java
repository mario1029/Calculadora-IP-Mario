package ip;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.*;
public class IPv6 {
	private JPanel[] panel;
	private JLabel ipname;
	private JTextField ip;
	private JTextArea info;
	private JScrollPane barra;
	private JButton volver,calcular;
	private InetAddress ipv6;
	
	
	
	public IPv6(JFrame cuadro, JPanel anterior) {
		panel = new JPanel[3];
		
		for(int i=0;i<3;i++) {
			panel[i]=new JPanel();
		}
		
		calcular = new JButton("Calcular");
		calcular.setSize(100, 50);
		
		volver = new JButton("Volver");
		volver.setSize(100, 50);
		
		info= new JTextArea(15,20);
		info.setDisabledTextColor(Color.BLACK);
		info.setEnabled(false);
		barra=new JScrollPane(info);
		
		ip=new JTextField(20);
		ip.setText("");
		ip.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(Character.digit(e.getKeyChar(),16)==-1 && !(e.getKeyChar()==':')) {
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
		
		ipname = new JLabel("IPv6:");
		
		panel[1].add(ipname);
		panel[1].add(ip);
		panel[1].setBackground(Color.LIGHT_GRAY);
		
		panel[2].add(volver);
		panel[2].add(calcular);
		panel[2].setBackground(Color.DARK_GRAY);
		
		panel[0].setLayout(new BoxLayout(panel[0],BoxLayout.PAGE_AXIS));
		panel[0].add(panel[1]);
		panel[0].add(barra);
		panel[0].add(panel[2]);
		panel[0].setBackground(Color.LIGHT_GRAY);
		calcular.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				/*NOTA: el metodo analizar se creo con el unico motivo
				 * de demostrar las reglas de simplificacion por ejemplo
				 * 0FF0:0000:0000:0000:0000:0000:0000:FFFF se puede simplificar como
				 * FF0:0:0:0:0:0:0:FFFF usando la primera regla de simplificacion y como
				 * FF0::FFFF usando la 2da regla de simplificacion, la forma mas eficiente 
				 * de trabajar con IPv6 seria  usar la clase InetAddress que fue diseñado para ello*/
				info.setText("");
				String[] prueba=analizar(ip.getText());
				
				info.append("IP Ingresada: ");
				for(int i=0;i<prueba.length;i++) {
				
					if(!(i==(prueba.length-1))) {
						info.append(prueba[i]+":");
					}else {
						info.append(prueba[i]+"\n");
					}
					
				}
				try {
					ipv6= Inet6Address.getByName(ip.getText());
					info.append("IP identificada:"+ipv6.getHostAddress().toUpperCase()+"\n");			
					if(ipv6.isAnyLocalAddress()) {
						info.append("Tipo: Anycast\n");
					}else if(ipv6.isLoopbackAddress()) {
						info.append("Tipo: LoopBack\n");
						//Loopback: Aquella IP que sean ::1
					}else if(ipv6.isMCGlobal()) {
						info.append("Tipo: Multicast Global\n");
						//Global Multicast:aquellas ip que sean ffxe:: (Donde x es un valor entre 0 a F)
					}else if(ipv6.isMCLinkLocal()) {
						info.append("Tipo: Multicast LocalLink\n");
						//Link-Local Multicast tiene el primer campo como FF02
					}else if(ipv6.isMCNodeLocal()) {
						info.append("Tipo: Multicast NodeLocal");
						//Node-Local Multicast tiene el primer campo como FF01
					}else if(ipv6.isMCOrgLocal()) {
						info.append("Tipo: Multicast Orgazation Local");
						//Organization Local: aquellas ip tal que ffx8:: (Donde x es un valor entre 0 a F)
					}else if(ipv6.isMCSiteLocal()) {
						info.append("Tipo: Multicast Site-Local");
						//Site-Local tal que sea ffx5::  (Donde x es un valor entre 0 a F)
					}else if(ipv6.isMulticastAddress()) {
						info.append("Tipo: Multicast");
					}else {
						info.append("Tipo: -");
					}
					
				} catch (UnknownHostException e1) {
					JOptionPane.showMessageDialog(cuadro, "IP invalida");
				}
			}
			
		});
		
		volver.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cuadro.setContentPane(anterior);
				cuadro.setTitle("IPv4");
				cuadro.revalidate();
				
			}
			
		});
		
	}
	
	public JPanel getPanel() {
		return this.panel[0];
	}
	
	public String[] analizar(String ip) {
		String[] hex = new String[8];
		
		int i;
		hex = ip.split(":");// se  divide en hextetos
		
		//Se aplica la regla #1 de simplificacion para eliminar 0 redundantes
		for(i=0;i<hex.length;i++) {
			
			
			if(hex[i].length()==2) {
				
				if(hex[i].substring(0, 1).equalsIgnoreCase("0")) 
					hex[i]=hex[i].substring(1);
				
			}else if(hex[i].length()==3) {

				if(hex[i].substring(0, 2).equalsIgnoreCase("00")) { 
					hex[i]=hex[i].substring(2);
				}else if(hex[i].substring(0, 1).equalsIgnoreCase("0")) {
					hex[i]=hex[i].substring(1);
					}

			}else if(hex[i].length()==4) {
				
				if(hex[i].substring(0, 3).equalsIgnoreCase("000")) { 
				
					hex[i]=hex[i].substring(3);
					
				}else if(hex[i].substring(0, 2).equalsIgnoreCase("00")) {
				
					hex[i]=hex[i].substring(2);
				}else if(hex[i].substring(0, 1).equalsIgnoreCase("0")) {
				
					hex[i]=hex[i].substring(1);
					}
				}
		
			}
		
		for(i=0;i<hex.length;i++) {
			if(hex[i].isBlank()) {
				
			}
		}
		
		return hex;
		
	}
}

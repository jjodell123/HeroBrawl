package jj.multiplayer;

import javax.swing.*;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.*;
import java.awt.event.*;
import java.net.*;

public class ServerStarter extends JFrame {

	public ServerStarter(String name) {
		this.setSize(400, 250);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Server Starter");
		this.setResizable(false);
		this.setLayout(new GridLayout(3, 1));

		JPanel top = new JPanel();
		top.setBackground(Color.DARK_GRAY);
		top.setLayout(new FlowLayout());
		String ipv4_360NoScope = "";

		try {
			ipv4_360NoScope = Inet4Address.getLocalHost().getHostAddress(); // Gets IP
		} catch (Exception e) {
			e.printStackTrace();
		}
		final String ipv4_pass = ipv4_360NoScope;

		JLabel systemText = new JLabel(ipv4_360NoScope);
		systemText.setForeground(Color.WHITE);
		top.add(systemText);
		this.add(top);
		JPanel center = new JPanel();
		center.setBackground(Color.BLACK);
		center.setLayout(new GridLayout(2, 1));
		JPanel centertop = new JPanel();
		centertop.setBackground(Color.BLACK);
		center.add(centertop);
		JPanel centercenter = new JPanel();
		centercenter.setBackground(Color.WHITE);
		JLabel centerLabel1 = new JLabel("Host IP:");
		JTextField centerTextField1 = new JTextField("", 8);
		JLabel centerLabel2 = new JLabel("Host Port:");
		JTextField centerTextField2 = new JTextField("1", 3);
		JButton centerButton1 = new JButton("Find Server");
		centerButton1.setContentAreaFilled(false);
		centerButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// CREATING NEW CLIENT SIDE FOR SOCKET HANDLING
				new Thread(new Client(centerTextField1.getText(), centerTextField2.getText(), name));
				centerButton1.setEnabled(false);
			}
		});
		centercenter.add(centerLabel1);
		centercenter.add(centerTextField1);
		centercenter.add(centerLabel2);
		centercenter.add(centerTextField2);
		centercenter.add(centerButton1);
		center.add(centercenter);
		this.add(center);
		JPanel bottom = new JPanel();
		bottom.setBackground(Color.DARK_GRAY);
		bottom.setLayout(new GridLayout(2, 1));
		JPanel bottomtop = new JPanel();
		bottomtop.setBackground(Color.WHITE);

		JLabel bottomtopLabel1 = new JLabel("Open Port: ");
		JTextField bottomtopTextField1 = new JTextField("1", 3);
		JButton host = new JButton("Host Game");
		host.setContentAreaFilled(false);
		host.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Thread(new Host(ipv4_pass, bottomtopTextField1.getText()));
				host.setEnabled(false);
				bottomtopTextField1.setEnabled(false);
				bottomtopLabel1.setEnabled(false);
			}
		});

		bottomtop.add(bottomtopLabel1);
		bottomtop.add(bottomtopTextField1);
		bottomtop.add(host);
		bottom.add(bottomtop);

		JPanel bottombottomPanel = new JPanel();
		bottombottomPanel.setBackground(Color.BLACK);
		bottom.add(bottombottomPanel);

		this.add(bottom);
		this.setVisible(true);
	}
}
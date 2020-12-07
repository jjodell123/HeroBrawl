package jj.multiplayer;

import javax.swing.*;
import java.awt.GridLayout;
import java.awt.*;
import java.awt.event.*;
import java.net.ServerSocket;

public class Host extends JFrame implements Runnable {
	ServerSocket sockPuppet;

	// this one hosts the game
	public Host(String address, String port) {
		this.setSize(300, 200);
		this.setLocationRelativeTo(null);
		this.setTitle("Server");
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);

		JPanel mainPane = new JPanel();
		mainPane.setLayout(new GridLayout(0, 1));
		mainPane.setBackground(Color.BLACK);
		JLabel mainJLabel1 = new JLabel("Server Hosted at " + address + ", access port: " + port);
		mainJLabel1.setForeground(Color.WHITE);
		mainPane.add(mainJLabel1);
		JLabel mainJLabel2 = new JLabel("Attempting Connection Establishment...");
		mainJLabel2.setForeground(Color.WHITE);
		mainPane.add(mainJLabel2);

		this.add(mainPane);
		this.setVisible(true);

		try {
			sockPuppet = new ServerSocket(Integer.parseInt(port));
		} catch (Exception e) {
			JLabel youDoneFuckedUp = new JLabel("Connection Failed, check clogged ports");
			youDoneFuckedUp.setForeground(Color.RED);
			mainPane.add(youDoneFuckedUp);
		}
		if (sockPuppet != null) {
			JLabel mainJLabel3 = new JLabel("Connection Established!");
			mainJLabel3.setForeground(Color.GREEN);
			mainPane.add(mainJLabel3);
			JButton button1 = new JButton("Open Server");
			button1.setContentAreaFilled(false);
			button1.setForeground(Color.WHITE);
			button1.setFocusable(false);
			button1.setBackground(Color.WHITE);
			button1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// SERVO MOTORS ACTIVATE
					button1.setEnabled(false);
					new Server(sockPuppet);
				}
			});
			mainPane.add(button1);
		}
	}

	// does nothing, used in ServerStarter
	public void run() {
	}

}

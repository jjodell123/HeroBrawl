package jj.multiplayer;

import java.awt.Color;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.JLabel;
import javax.swing.JPanel;

import jj.Driver;
import jj.MapHandler;
import jj.characters.Character;
import jj.characters.CharacterType;

public class Client extends JFrame implements Runnable {

	private Socket socket;
	private Thread t = new Thread(this);
	private BufferedReader in;
	private static PrintWriter out;
	private static String name;
	private static ArrayList<String> recognized = new ArrayList<String>();

	public Client(String hostIp, String hostPort, String name) {
		this.setSize(300, 200);
		this.setLocationRelativeTo(null);
		this.setTitle("Client");
		this.setLayout(new GridLayout(0, 1));
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		JPanel mainPane = new JPanel();
		mainPane.setLayout(new GridLayout(0, 1));
		mainPane.setBackground(Color.BLACK);
		JLabel label1 = new JLabel("Attempting Connection at " + hostIp);
		label1.setForeground(Color.WHITE);
		mainPane.add(label1);
		JLabel label2 = new JLabel("on port " + hostPort);
		label2.setForeground(Color.WHITE);
		mainPane.add(label2);

		this.add(mainPane);

		this.setVisible(true);
		try {
			Thread.sleep(300);
			socket = new Socket(hostIp, Integer.parseInt(hostPort));
		} catch (Exception e/* IO Exception applic... */) {
			JLabel error = new JLabel("Connection failed");
			error.setForeground(Color.RED);
			mainPane.add(error);
		}
		if (socket != null) {
			JLabel label3 = new JLabel("Connection established!");
			label3.setForeground(Color.GREEN);
			mainPane.add(label3);
			t.start();
		}

	}

	public void run() {
		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(), true);
			out.println(name);
			Driver.startMap();
			name = in.readLine();
			while (true) {
				System.out.println();
				if (MapHandler.isPlayScreen()) {
					String line = in.readLine();
					String temp = line.substring(line.indexOf("~") + 1, line.indexOf(":"));
					boolean test = false;
					for (int i = 0; i < recognized.size(); i++) {
						if (recognized.get(i).equals(temp)) {
							test = true;
						}
					}

					if (test && !name.equals(temp)) {
						line = line.substring(line.indexOf(":") + 1);
						int tempX = Integer.parseInt(line.substring(0, line.indexOf(",")));
						line = line.substring(line.indexOf(",") + 1);
						int tempY = Integer.parseInt(line.substring(0, line.indexOf(",")));
						line = line.substring(line.indexOf(",") + 1);
						boolean tempHit = Boolean.parseBoolean(line.substring(0, line.indexOf(",")));
						line = line.substring(line.indexOf(",") + 1);
						boolean tempDir = Boolean.parseBoolean(line.substring(0, line.indexOf(",")));
						line = line.substring(line.indexOf(",") + 1);
						CharacterType tempChara = Character.parseCharacterType(line);
						MapHandler.adjustEnemy(temp, tempX, tempY, tempHit, tempDir, tempChara);
					} else if (!test && !name.equals(temp)) {
						if (!(line.substring(line.length() - 4).equals("null")))
							recognized.add(temp);
						line = line.substring(line.indexOf(":") + 1);
						int tempX = Integer.parseInt(line.substring(0, line.indexOf(",")));
						line = line.substring(line.indexOf(",") + 1);
						int tempY = Integer.parseInt(line.substring(0, line.indexOf(",")));
						line = line.substring(line.indexOf(",") + 1);
						line = line.substring(line.indexOf(",") + 1);
						line = line.substring(line.indexOf(",") + 1);
						CharacterType tempChara = Character.parseCharacterType(line);
						if (!line.equals("null"))
							MapHandler.addEnemy(temp, tempX, tempY, tempChara);
					}
				}
			}
		} catch (IOException e) {
		} finally {

			try {
				socket.close();
			} catch (IOException e) {
			}
		}
	}

	public static void update(int x, int y, boolean hit, boolean left, CharacterType chara) {
		if (!chara.equals(null)) {
			out.println(name + ":" + x + "," + y + "," + hit + "," + left + "," + chara.getName());
		}
	}
}
//test
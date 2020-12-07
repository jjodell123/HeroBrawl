package jj.multiplayer;

import java.awt.Color;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import jj.PlayerWriter;

public class Server extends JFrame {
	ServerSocket sergio;
	Allocator al;
	JPanel mainPane;
	ArrayList<PlayerWriter> players = new ArrayList<PlayerWriter>();
	ArrayList<PrintWriter> writers = new ArrayList<PrintWriter>();
	JTextArea text = new JTextArea();

	// this is the console
	public Server(ServerSocket s) {
		this.sergio = s;
		this.setLayout(new GridLayout(0, 1));
		this.setSize(500, 300);
		this.setLocationRelativeTo(null);
		this.setTitle("Server Console");
		mainPane = new JPanel();
		mainPane.setLayout(new GridLayout(0, 1));
		mainPane.setBackground(Color.BLACK);
		this.add(mainPane);
		this.setVisible(true);
		al = new Allocator(s);
		al.start();
	}

	private class Allocator extends Thread {
		ServerSocket serverSock;
		ArrayList<Handler> handCache = new ArrayList<Handler>();

		public Allocator(ServerSocket s) {
			serverSock = s;
		}

		public void run() {
			try {
				while (true) {
					try {
						text.setBackground(Color.BLACK);
						text.setForeground(Color.GREEN);
						Socket s = serverSock.accept();
						text.append(s + "\n");
						mainPane.add(text);
						handCache.add(new Handler(s));
					} catch (Exception e) {
					}
				}
			}

			finally {
				try {
					serverSock.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private class Handler extends Thread {
		private BufferedReader in;
		private PrintWriter out;
		private Socket s;
		private String name;

		public Handler(Socket s) {
			System.out.println("restart");
			this.s = s;
			start();
		}

		public void run() {
			try {
				in = new BufferedReader(new InputStreamReader(s.getInputStream()));
				out = new PrintWriter(s.getOutputStream(), true);
				while (true) {

					name = in.readLine();
					if (name == null) {
						return;
					}
					synchronized (players) {
						int time = 0;
						for (int i = 0; i < players.size(); i++) {
							if (players.get(i).getName().substring(0, name.length()).equals(name))
								time++;
						}
						if (time == 0) {
							players.add(new PlayerWriter(name));
							out.println(name);
							mainPane.add(text);
							text.append(name + " has joined\n");
							break;
						} else {
							name = name + "(" + time + ")";
							players.add(new PlayerWriter(name));
							out.println(name);
							mainPane.add(text);
							text.append(name + " has joined\n");
							break;
						}
					}
				}

				writers.add(out);

				while (true) {
					String input = in.readLine();
					if (input == null) {
						return;
					}

					String temp = input.substring(0, input.indexOf(":"));
					synchronized (players) {

						for (int i = 0; i < players.size(); i++) {
							if (players.get(i).getName().equals(temp)) {
								input = input.substring(input.indexOf(":") + 1);
								players.get(i).setX(input.substring(0, input.indexOf(",")));
								input = input.substring(input.indexOf(",") + 1);
								players.get(i).setY(input.substring(0, input.indexOf(",")));
								input = input.substring(input.indexOf(",") + 1);
								players.get(i).setHit(input.substring(0, input.indexOf(",")));
								input = input.substring(input.indexOf(",") + 1);
								players.get(i).setChara(input);
							}
						}
					}

					for (int i = 0; i < writers.size(); i++) {
						for (int x = 0; x < players.size(); x++) {
							if (!players.get(i).getName().equals(temp)) {
								writers.get(i).println(players.get(x));
							}
						}
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					for (int i = writers.size(); i > 0; i--)
						writers.remove(i);
					s.close();
				} catch (IOException e) {
				}
			}
		}
	}
}

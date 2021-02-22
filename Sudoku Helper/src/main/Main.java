package main;

import sudoku.boards.menu.MainMenu;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		
		MainMenu menu = MainMenu.getInstance();
		menu.setVisible(true);
		menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
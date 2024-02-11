import java.awt.Color;

import javax.swing.JFrame;

public class App {
	public static void main(String[] args) {
		
		JFrame screen = new JFrame();
		Gameplay gameplay = new Gameplay();
		
		screen.setBounds(10, 10, 905, 700);
		screen.setTitle("Snake");
		screen.setBackground(Color.black);
		screen.setResizable(false);
		screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		screen.add(gameplay);
		screen.setVisible(true);
	}

}

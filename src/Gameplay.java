import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.Timer;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Gameplay extends JPanel implements KeyListener, ActionListener {

	private int[] snakexLength = new int[750];
	private int[] snakeyLength = new int[750];

	private boolean left = false;
	private boolean right = false;
	private boolean up = false;
	private boolean down = false;

	private ImageIcon rightMouth;
	private ImageIcon upMouth;
	private ImageIcon downMouth;
	private ImageIcon leftMouth;

	private int snakeLength = 3;

	private Timer timer;
	private int delay = 100;
	private ImageIcon snakeImage;

	private int [] foodxPosition = {25,50,75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,
			450,475,500,525,550,575,600,625,650,
			675,700,725,750,775,800,825,850};
	private int [] foodyPosition = {75,100,125,150,175,200,225,250,275,300,325,350,
			375,400,425,450,475,500,525,550,575,600,625};

	private ImageIcon foodImage;

	private Random random = new Random();

	private int xpos = random.nextInt(34);
	private int ypos = random.nextInt(23);

	private int score = 0;

	private int mov = 0;

	private ImageIcon titleImage;

	public Gameplay() {
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);
		timer.start();
	}

	public void paint(Graphics g) {

		if(mov == 0) {
			snakexLength[2] = 50;
			snakexLength[1] = 75;
			snakexLength[0] = 100;

			snakeyLength[2] = 100;
			snakeyLength[1] = 100;
			snakeyLength[0] = 100;
		}

		g.setColor(Color.green);
		g.drawRect(24, 10, 851, 55);

		titleImage = new ImageIcon("assets/title.png");
		titleImage.paintIcon(this, g, 25, 11);

		g.setColor(Color.GREEN);
		g.drawRect(24, 74, 851, 577);

		g.setColor(Color.black);
		g.fillRect(25, 75, 850, 575);

		g.setColor(Color.white);
		g.setFont(new Font("arial", Font.PLAIN, 14));
		g.drawString("Score: "+score, 780, 30);

		g.setColor(Color.white);
		g.setFont(new Font("arial", Font.PLAIN, 14));
		g.drawString("Length: "+snakeLength, 780, 50);

		rightMouth = new ImageIcon("assets/rightmouth.png");
		rightMouth.paintIcon(this, g, snakexLength[0], snakeyLength[0]);

		foodImage = new ImageIcon("assets/food.png");

		if((foodxPosition[xpos] == snakexLength[0] && foodyPosition[ypos] == snakeyLength[0])) {
			score+=5;
			snakeLength++;
			xpos = random.nextInt(34);
			ypos = random.nextInt(23);
		}

		foodImage.paintIcon(this, g, foodxPosition[xpos], foodyPosition[ypos]);

		for(int b=1; b<snakeLength; b++) {
			if(snakexLength[b] == snakexLength[0] && snakeyLength[b] == snakeyLength[0]) {
				right = false;
				left = false;
				up = false;
				down = false;

				g.setColor(Color.white);
				g.setFont(new Font("arial", Font.BOLD, 50));
				g.drawString("Game Over", 350, 300);

				g.setFont(new Font("arial", Font.BOLD, 20));
				g.drawString("Press space to restart", 375, 340);
				repaint();
			}
		}
	
		for(int i=0;i<snakeLength;i++) {

			if(i==0 && right) {
				rightMouth = new ImageIcon("assets/rightmouth.png");
				rightMouth.paintIcon(this, g, snakexLength[i], snakeyLength[i]);
			}

			if(i==0 && left) {
				leftMouth = new ImageIcon("assets/leftmouth.png");
				leftMouth.paintIcon(this, g, snakexLength[i], snakeyLength[i]);
			}

			if(i==0 && up) {
				upMouth = new ImageIcon("assets/upmouth.png");
				upMouth.paintIcon(this, g, snakexLength[i], snakeyLength[i]);
			}

			if(i==0 && down) {
				downMouth = new ImageIcon("assets/downmouth.png");
				downMouth.paintIcon(this, g, snakexLength[i], snakeyLength[i]);
			}

			if(i!=0) {
				snakeImage = new ImageIcon("assets/snakeimage.png");
				snakeImage.paintIcon(this, g, snakexLength[i], snakeyLength[i]);
			}
		}
		g.dispose();
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		timer.start();
		if(right) {
			for(int r=snakeLength-1; r>=0; r--) {
				snakeyLength[r+1] = snakeyLength[r];
			}
			for(int r=snakeLength; r>=0; r--) {
				if(r==0) {
					snakexLength[r] = snakexLength[r] + 25;
				}
				else {
					snakexLength[r] = snakexLength[r-1];
				}
				if(snakexLength[r]>850) {
					snakexLength[r] = 25;
				}
			}
			repaint();
		}

		if(left) {
			for(int r=snakeLength-1; r>=0; r--) {
				snakeyLength[r+1] = snakeyLength[r];
			}
			for(int r=snakeLength; r>=0; r--) {
				if(r==0) {
					snakexLength[r] = snakexLength[r] - 25;
				}
				else {
					snakexLength[r] = snakexLength[r-1];
				}
				if(snakexLength[r]<25) {
					snakexLength[r] = 850;
				}
			}
			repaint();
		}

		if(up) {
			for(int r=snakeLength-1; r>=0; r--) {
				snakexLength[r+1] = snakexLength[r];
			}
			for(int r=snakeLength; r>=0; r--) {
				if(r==0) {
					snakeyLength[r] = snakeyLength[r] - 25;
				}
				else {
					snakeyLength[r] = snakeyLength[r-1];
				}
				if(snakeyLength[r]<75) {
					snakeyLength[r] = 625;
				}
			}
			repaint();
		}
		if(down) {
			for(int r=snakeLength-1; r>=0; r--) {
				snakexLength[r+1] = snakexLength[r];
			}
			for(int r=snakeLength; r>=0; r--) {
				if(r==0) {
					snakeyLength[r] = snakeyLength[r] + 25;
				}
				else {
					snakeyLength[r] = snakeyLength[r-1];
				}
				if(snakeyLength[r]>625) {
					snakeyLength[r] = 75;
				}
			}
			repaint();
		}
	}

	@Override
	public void keyPressed(KeyEvent p1) {
		if(p1.getKeyCode() == KeyEvent.VK_SPACE) {
			mov = 0;
			score = 0;
			snakeLength = 3;
			repaint();
		}

		if(p1.getKeyCode() == KeyEvent.VK_RIGHT) {
			mov++;
			right = true;
			if(!left) {
				right = true;
			}

			else {
				right = false;
				left = true;
			}

			up = false;
			down = false;
		}

		if(p1.getKeyCode() == KeyEvent.VK_D) {
			mov++;
			right = true;
			if(!left) {
				right = true;
			}

			else {
				right = false;
				left = true;
			}

			up = false;
			down = false;
		}

		if(p1.getKeyCode() == KeyEvent.VK_LEFT) {
			mov++;
			left = true;
			if(!right) {
				left = true;
			}

			else {
				left = false;
				right = true;
			}

			up = false;
			down = false;
		}

		if(p1.getKeyCode() == KeyEvent.VK_A) {
			mov++;
			left = true;
			if(!right) {
				left = true;
			}

			else {
				left = false;
				right = true;
			}

			up = false;
			down = false;
		}

		if(p1.getKeyCode() == KeyEvent.VK_UP) {
			mov++;
			up = true;
			if(!down) {
				up = true;
			}

			else {
				up = false;
				down = true;
			}

			left = false;
			right = false;
		}

		if(p1.getKeyCode() == KeyEvent.VK_W) {
			mov++;
			up = true;
			if(!down) {
				up = true;
			}

			else {
				up = false;
				down = true;
			}

			left = false;
			right = false;
		}

		if(p1.getKeyCode() == KeyEvent.VK_DOWN) {
			mov++;
			down = true;
			if(!up) {
				down = true;
			}

			else {
				down = false;
				up = true;
			}

			left = false;
			right = false;
		}

		if(p1.getKeyCode() == KeyEvent.VK_S) {
			mov++;
			down = true;
			if(!up) {
				down = true;
			}

			else {
				down = false;
				up = true;
			}

			left = false;
			right = false;
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}
}
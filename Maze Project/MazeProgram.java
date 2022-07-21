import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MazeProgram extends JPanel implements KeyListener{

	JFrame frame;
	String[][] maze= new String[23][72];
	Hero hero;
	ArrayList<Wall> walls;
	boolean draw3D = false;
	JLabel label1;
	JLabel label2;
	Timer timer;

	public MazeProgram() {
		walls = new ArrayList<Wall>();
		frame = new JFrame("MAZE GAME!");
		frame.add(this);
		setMaze();
		frame.addKeyListener(this);
		frame.setSize(1600, 750);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		label1 = new JLabel("Compass", SwingConstants.CENTER);
		label1.setPreferredSize(new Dimension(75, 10));
		label1.setFont(new Font("Verdana", Font.BOLD, 30));
		label1.setText("E");
		label1.setForeground(Color.black);
		label1.setBackground(new Color(106, 108, 109));

		label2 = new JLabel("Stopwatch", SwingConstants.CENTER);
		label2.setPreferredSize(new Dimension(75, 50));
		label2.setFont(new Font("Verdana", Font.BOLD, 20));
		label2.setText("0");
		label2.setForeground(Color.black);
		label2.setBackground(new Color(106, 108, 109));

		frame.add(label1, BorderLayout.EAST);
		frame.add(label2, BorderLayout.PAGE_END);
		frame.setVisible(true);
		Stopwatch stopwatch = new Stopwatch();
		timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				label2.setText("Total Moves: " + hero.getMoves() + "\t\t\t\t\tElapsed Time: " + stopwatch.elapsedTime() + " s");
			}
		}, 0, 10);
	}
	public void setMaze() {

		File name = new File("/Users/akshaymistry/Desktop/Data Structures/Maze/Maze.txt");
		try {

			BufferedReader input = new BufferedReader(new FileReader(name));

			String text = "";
			int r = 0;
			while ((text=input.readLine())!=null) {
				String[] stuff= text.split("");
				maze[r] = stuff;
				r++;
			}

		} catch(IOException e) {
			System.out.println("File does not exist");
		}

		if (draw3D) {
			createWalls();
		}
		hero = new Hero(1,0,1,maze, 'E', 0);
	}

	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(new Color(106, 108, 109));
		g2.fillRect(0,0,frame.getWidth(),frame.getHeight());

		if (!draw3D) {
			for (int r = 0; r < maze.length; r++) {
				for (int c = 0; c < maze[0].length; c++) {
					if (maze[r][c].equals("-"))
					{
						g2.setColor(Color.BLACK);
						g2.fillRect(c*20, r*20, 18, 18);
					}
					System.out.println();
				}
			}
			g2.setColor(Color.RED);
			g2.fillOval(hero.getCol()*20, hero.getRow()*20, hero.getDim()*18, hero.getDim()*18);
		}
		else {
			for (Wall wall : walls) {
				g2.setPaint(wall.getPaint());
				g2.fillPolygon(wall.getPoly());
				g2.setColor(Color.WHITE);
				g2.drawPolygon(wall.getPoly());
			}
		}
	}

	public void createWalls() {
		walls = new ArrayList<Wall>();
		int row = hero.getRow();
		int col = hero.getCol();
		char dir = hero.getDir();
		for (int x = 0; x < 5; x++) {
			walls.add(leftRect(x));
			walls.add(rightRect(x));
			walls.add(topWall(x));
			walls.add(bottomWall(x));
			switch (dir) {
				case 'N':
					try{
						if (!maze[row-x][col-1].equals(" ")) {
							walls.add(leftWall(x));
						}
						if (!maze[row-x][col+1].equals(" ")) {
							walls.add(rightWall(x));
						}
					}
					catch (ArrayIndexOutOfBoundsException e) {}
					break;
				case 'S':
					try {
						if (!maze[row+x][col+1].equals(" ")) {
							walls.add(leftWall(x));
						}
						if (!maze[row+x][col-1].equals(" ")) {
							walls.add(rightWall(x));
						}
					}
					catch (ArrayIndexOutOfBoundsException e) {}
					break;
				case 'W':
					try {
						if (!maze[row+1][col-x].equals(" ")) {
							walls.add(leftWall(x));
						}
						if (!maze[row-1][col-x].equals(" ")) {
							walls.add(rightWall(x));
						}
					}
					catch (ArrayIndexOutOfBoundsException e) {}
					break;
				case 'E':
					try {
						if (!maze[row-1][col+x].equals(" ")) {
							walls.add(leftWall(x));
						}
						if (!maze[row+1][col+x].equals(" ")) {
							walls.add(rightWall(x));
						}
					}
					catch (ArrayIndexOutOfBoundsException e) {}
					break;
			}
		}

		for (int x = 5; x >= 0; x--)
		{
			switch (dir) {

				case 'N':
					try{
						if (!maze[row-x][col].equals(" ")) {
							walls.add(endWall(x));
						}
					}
					catch (ArrayIndexOutOfBoundsException e) {}
					break;
				case 'S':
					try {
						if (!maze[row+x][col].equals(" ")) {
							walls.add(endWall(x));
						}
					}
					catch (ArrayIndexOutOfBoundsException e) {}
					break;
				case 'W':
					try {
						if (!maze[row][col-x].equals(" ")) {
							walls.add(endWall(x));
						}
					}
					catch (ArrayIndexOutOfBoundsException e) {}
					break;
				case 'E':
					try {
						if (!maze[row][col+x].equals(" ")) {
							walls.add(endWall(x));
						}
					}
					catch (ArrayIndexOutOfBoundsException e) {}
					break;
			}
		}
	}

	public Wall leftWall(int num) {
		int[] x = {350+50*num, 400+50*num, 400+50*num, 350+50*num};
		int[] y = {50+50*num, 100+50*num, 600-50*num, 650-50*num};
		return new Wall(x, y, 255 - 50*num, 255 - 50*num, 255 - 50*num, "left", 50);
	}

	public Wall rightWall(int num) {
		int[] x = {1100-50*num, 1050-50*num, 1050-50*num, 1100-50*num};
		int[] y = {50+50*num, 100+50*num, 600-50*num, 650-50*num};
		return new Wall(x, y, 255 - 50*num, 255 - 50*num, 255 - 50*num, "right", 50);
	}

	public Wall topWall(int num) {
		int[] x = {350+50*num, 400+50*num, 1050-50*num, 1100-50*num};
		int[] y = {50+50*num, 100+50*num, 100+50*num, 50+50*num};
		return new Wall(x, y, 255 - 50*num, 255 - 50*num, 255 - 50*num, "top", 50);
	}

	public Wall bottomWall(int num) {
		int[] x = {350+50*num, 400+50*num, 1050-50*num, 1100-50*num};
		int[] y = {650-50*num, 600-50*num, 600-50*num, 650-50*num};
		return new Wall(x, y, 255 - 50*num, 255 - 50*num, 255 - 50*num, "bottom", 50);
	}

	public Wall endWall(int num) {
		int[] x = {400+50*num, 1050-50*num, 1050-50*num, 400+50*num};
		int[] y = {100+50*num, 100+50*num, 600-50*num, 600-50*num};
		return new Wall(x, y, 255 - 50*num, 255 - 50*num, 255 - 50*num, "end", 50);
	}

	public Wall leftRect(int num) {
		int[] x = {350+50*num, 400+50*num, 400+50*num, 350+50*num};
		int[] y = {100+50*num, 100+50*num, 600-50*num, 600-50*num};
		return new Wall(x, y, 255 - 50*num, 255 - 50*num, 255 - 50*num, "left", 50);
	}

	public Wall rightRect(int num) {
		int[] x = {1100-50*num, 1050-50*num, 1050-50*num, 1100-50*num};
		int[] y = {100+50*num, 100+50*num, 600-50*num, 600-50*num};
		return new Wall(x, y, 255 - 50*num, 255 - 50*num, 255 - 50*num, "right", 50);
	}

	public void keyPressed (KeyEvent e) {

		int key = e.getKeyCode();
		if (key == 32) {
			draw3D = !draw3D;
		}
		else{
			hero.changeDirection(key);
			System.out.println(hero.getDir());
			if (hero.getRow() == 21 && hero.getCol() == 70) {
				label1.setText("!!");
				timer.cancel();
				label2.setText("CONGRATULATIONS! YOU WIN!");
			}
			else {
				label1.setText(String.valueOf(hero.getDir()));
			}
			if (key == 38) {
				hero.move();

			}
		}
		if (draw3D) {
			createWalls();
		}
		repaint();
	}

	public void keyReleased(KeyEvent e) {}

	public void keyTyped(KeyEvent e) {}

	public static void main (String[] args) {
		MazeProgram mazeProgram = new MazeProgram();
	}

	public class Stopwatch {

		private final long startTime;

		public Stopwatch() {
			startTime = System.currentTimeMillis();
		}
		public int elapsedTime() {
			long currentTimeMillis = System.currentTimeMillis();
			return (int) ((currentTimeMillis - startTime) / 1000);
		}
	}
}
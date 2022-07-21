import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Timer;
import java.util.TimerTask;

public class Minesweeper extends JPanel implements ActionListener, MouseListener {

    JFrame frame;
    JToggleButton[][] grid;
    int dimR=6,dimC=6,numMines=6;
    JPanel gridPanel;
    boolean firstClick=true;
    int numNotClicked;
    boolean gameOn = true;
    ImageIcon mineIcon, flag, smile, wait, win, dead;
    ImageIcon[] numbers;
    int buttonSize = 80;
    JMenuBar bar;
    JMenu difficulty;
    JMenuItem beginner, intermediate, expert;
    JButton reset;
    JTextField time;
    Timer timer;
    int timePassed = 0;
    GraphicsEnvironment ge;
    Font clockFont;

    public Minesweeper() {

        frame=new JFrame("Minesweeper");
        frame.add(this);

        try {
            ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            clockFont = Font.createFont(Font.TRUETYPE_FONT, new File("/Users/akshaymistry/Desktop/Data Structures/Minesweeper/digital-7.ttf"));
            ge.registerFont(clockFont);

        } catch (IOException | FontFormatException e) {

        }

        mineIcon = new ImageIcon("/Users/akshaymistry/Desktop/Data Structures/Minesweeper/mine.png");
        mineIcon = new ImageIcon(mineIcon.getImage().getScaledInstance(buttonSize-10, buttonSize-10, Image.SCALE_SMOOTH));
        flag = new ImageIcon("/Users/akshaymistry/Desktop/Data Structures/Minesweeper/flag.png");
        flag = new ImageIcon(flag.getImage().getScaledInstance(buttonSize-5, buttonSize-5, Image.SCALE_SMOOTH));
        smile = new ImageIcon("/Users/akshaymistry/Desktop/Data Structures/Minesweeper/smile1.png");
        smile = new ImageIcon(smile.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        wait = new ImageIcon("/Users/akshaymistry/Desktop/Data Structures/Minesweeper/wait1.png");
        wait = new ImageIcon(wait.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        win = new ImageIcon("/Users/akshaymistry/Desktop/Data Structures/Minesweeper/win1.png");
        win = new ImageIcon(win.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
        dead = new ImageIcon("/Users/akshaymistry/Desktop/Data Structures/Minesweeper/dead1.png");
        dead = new ImageIcon(dead.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));

        numbers = new ImageIcon[9];

        for (int x = 1; x <= 8; x++) {
            numbers[x-1] = new ImageIcon("/Users/akshaymistry/Desktop/Data Structures/Minesweeper/"+ x + ".png");
            numbers[x-1] = new ImageIcon(numbers[x-1].getImage().getScaledInstance(buttonSize-10, buttonSize-10, Image.SCALE_SMOOTH));
        }

        setGrid(dimR,dimC);

        bar = new JMenuBar();
        bar.setLayout(new GridLayout(1,3));
        frame.setJMenuBar(bar);

        difficulty = new JMenu("Difficulty");
        difficulty.setFont(clockFont.deriveFont(22f));
        bar.add(difficulty);
        beginner = new JMenuItem("Beginner");
        beginner.addActionListener(this);
        difficulty.add(beginner);
        intermediate = new JMenuItem("Intermediate");
        intermediate.addActionListener(this);
        difficulty.add(intermediate);
        expert = new JMenuItem("Expert");
        expert.addActionListener(this);
        difficulty.add(expert);

        time = new JTextField("0");
        time.setEditable(false);
        time.setBackground(Color.DARK_GRAY);
        time.setForeground(Color.WHITE);
        bar.add(time);

        reset = new JButton();
        reset.addActionListener(this);
        reset.setIcon(smile);
        bar.add(reset);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void setGrid(int dimR,int dimC) {

        if(gridPanel!=null)
            frame.remove(gridPanel);

        gridPanel=new JPanel();
        grid=new JToggleButton[dimR][dimC];
        gridPanel.setLayout(new GridLayout(dimR,dimC));

        for(int r=0;r<dimR;r++) {
            for(int c=0;c<dimC;c++) {
                grid[r][c]=new JToggleButton();
                grid[r][c].addMouseListener(this);
                grid[r][c].setFocusable(false);
                grid[r][c].setEnabled(true);
                grid[r][c].putClientProperty("row",r);
                grid[r][c].putClientProperty("column",c);
                grid[r][c].putClientProperty("state",0);
                gridPanel.add(grid[r][c]);
            }
        }
        frame.setSize(buttonSize*dimC,buttonSize*dimR);
        frame.setResizable(false);
        frame.add(gridPanel,BorderLayout.CENTER);
        frame.revalidate();
    }

    public void actionPerformed(ActionEvent e) {

        Object o = e.getSource();
        if (o == beginner) {
            dimR = 6;
            dimC = 6;
            numMines = 6;
            buttonSize = 80;
        }
        if (o == intermediate) {
            dimR = 9;
            dimC = 15;
            numMines = 30;
            buttonSize = 70;
        }
        if (o == expert) {
            dimR = 14;
            dimC = 25;
            numMines = 99;
            buttonSize = 60;
        }

        reset.setIcon(smile);
        if (timer != null)
            timer.cancel();
        timePassed = 0;
        time.setText(" " + timePassed);
        gameOn = true;
        firstClick = true;
        setGrid(dimR, dimC);
        timer.schedule(new UpdateTimer(), 0, 1000);
    }

    public void dropMines(int currRow,int currCol) {

        int count=numMines;
        while(count>0) {
            int row=(int)(Math.random()*dimR);
            int col=(int)(Math.random()*dimC);
            int state=(int)grid[row][col].getClientProperty("state");
            if(state==0 && Math.abs(row-currRow)>1 && Math.abs(col-currCol)>1) {
                grid[row][col].putClientProperty("state",-1);
                count--;
            }
        }

        for(int r=0;r<dimR;r++) {
            for(int c=0;c<dimC;c++) {
                int mineCount=0;
                int buttonState=(int)grid[r][c].getClientProperty("state");
                if(buttonState!=-1) {
                    for(int rr=r-1;rr<=r+1;rr++) {
                        for(int cc=c-1;cc<=c+1;cc++) {
                            try{
                                int state=(int)grid[rr][cc].getClientProperty("state");
                                if(state==-1)
                                    mineCount++;
                            }catch(ArrayIndexOutOfBoundsException e)
                            {
                            }
                        }
                    }
                    grid[r][c].putClientProperty("state",mineCount);
                }
            }
        }
        numNotClicked=dimR*dimC;
    }

    public void expand(int row, int col) {

        if(!grid[row][col].isSelected()) {
            grid[row][col].setContentAreaFilled(false);
            grid[row][col].setOpaque(true);
            grid[row][col].setBackground(new Color(174, 198, 207));
            grid[row][col].setSelected(true);
            grid[row][col].setEnabled(false);
            numNotClicked--;
        }
        int state=(int)grid[row][col].getClientProperty("state");
        if(state!=0) {
            grid[row][col].setIcon(numbers[state-1]);
            grid[row][col].setDisabledIcon(numbers[state-1]);
        }
        else {
            for(int r=row-1;r<=row+1;r++) {
                for(int c=col-1;c<=col+1;c++) {
                    try {
                        if(!grid[r][c].isSelected()) {
                            expand(r,c);
                        }
                    } catch(ArrayIndexOutOfBoundsException e)
                    {
                    }
                }
            }
        }
    }

    public void mouseReleased(MouseEvent e) {

        reset.setIcon(smile);
        int row=(int)((JToggleButton)e.getComponent()).getClientProperty("row");
        int column=(int)((JToggleButton)e.getComponent()).getClientProperty("column");

        if (gameOn) {
            if (e.getButton() == MouseEvent.BUTTON1 && grid[row][column].isEnabled()) {
                if (firstClick) {
                    timer = new Timer();
                    timer.schedule(new UpdateTimer(), 0, 1000);
                    dropMines(row, column);
                    firstClick = false;
                }
                grid[row][column].setContentAreaFilled(false);
                grid[row][column].setOpaque(true);
                grid[row][column].setBackground(new Color(174, 198, 207));
                grid[row][column].setSelected(true);
                grid[row][column].setEnabled(false);
                int state = (int) grid[row][column].getClientProperty("state");
                if (state == -1) {
                    displayMine();
                    timer.cancel();
                    reset.setIcon(dead);
                    JOptionPane.showMessageDialog(null,"You are a loser!", "GAME OVER", JOptionPane.INFORMATION_MESSAGE, dead);
                    gameOn = false;
                } else {
                    numNotClicked--;
                    expand(row, column);
                    checkWin();
                }
            }
        }
        if (e.getButton() == MouseEvent.BUTTON3) {
            if (!grid[row][column].isSelected()) {
                if (grid[row][column].getIcon() == null) {
                    grid[row][column].setIcon(flag);
                    grid[row][column].setDisabledIcon(flag);
                }
                else {
                    grid[row][column].setIcon(null);
                    grid[row][column].setDisabledIcon(flag);
                    grid[row][column].setEnabled(true);
                }
            }
        }
    }

    public void checkWin() {

        if(numMines==numNotClicked) {
            timer.cancel();
            reset.setIcon(win);
            JOptionPane.showMessageDialog(null,"You are a winner!", "GAME OVER", JOptionPane.INFORMATION_MESSAGE, win);
            gameOn = false;
            disableButtons();
        }
    }

    public void displayMine() {

        for(int r=0;r<grid.length;r++) {
            for(int c=0;c<grid[0].length;c++) {
                if((int)grid[r][c].getClientProperty("state")==-1) {
                    grid[r][c].setContentAreaFilled(false);
                    grid[r][c].setOpaque(true);
                    grid[r][c].setBackground(Color.RED);
                    grid[r][c].setIcon(mineIcon);
                    grid[r][c].setDisabledIcon(mineIcon);
                }
                grid[r][c].setEnabled(false);
            }
        }
    }

    public void disableButtons() {

        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                grid[r][c].setEnabled(false);
            }
        }
    }

    public void mousePressed(MouseEvent e) {
        reset.setIcon(wait);
    }

    public void mouseClicked(MouseEvent e)
    {
    }

    public void mouseEntered(MouseEvent e)
    {
    }

    public void mouseExited(MouseEvent e)
    {
    }

    public static void main(String[] args) {
        Minesweeper minesweeper = new Minesweeper();
    }

    public class UpdateTimer extends TimerTask {
        @Override
        public void run() {
            if (gameOn) {
                timePassed++;
                time.setText(" "+timePassed);
            }
        }
    }
}
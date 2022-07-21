import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class SierpinskiTriangle extends JPanel implements KeyListener {

    JFrame frame;
    ArrayList<Point> pointArrayList;
    Polygon polygon;

    public SierpinskiTriangle() {
        frame = new JFrame("Sierpinski Triangle");
        frame.add(this);
        frame.setSize(1200,700);
        frame.addKeyListener(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        pointArrayList = new ArrayList<>();
        sierpinskiProcess();
        buildPoints(5);
    }

    public void sierpinskiProcess() {
        pointArrayList.add(new Point(500, 100, Color.WHITE));
        pointArrayList.add(new Point(100, 600, Color.WHITE));
        pointArrayList.add(new Point(900, 600, Color.WHITE));
        int[] xValues = {500,100,900};
        int[] yValues = {100,700,700};
        polygon = new Polygon(xValues, yValues, 3);
        int x;
        int y;
        do {
            x = (int)(Math.random()*801)+100;
            y = (int)(Math.random()*501)+100;
        } while (!polygon.contains(x,y));
        pointArrayList.add(new Point(x,y,Color.RED));
    }

    public void buildPoints(int count) {
        for (int i = 0; i < count; i++) {
            int rand = (int) (Math.random() * 3);
            Point vertex = pointArrayList.get(rand);
            int newX = (vertex.getX() + pointArrayList.get(pointArrayList.size() - 1).getX()) / 2;
            int newY = (vertex.getY() + pointArrayList.get(pointArrayList.size() - 1).getY()) / 2;
            pointArrayList.add(new Point(newX, newY, Color.RED));
        }
        repaint();
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int count = e.getKeyCode()-48;
        buildPoints(count);
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0,0,1200,700);
        for (Point p : pointArrayList) {
            g.setColor(p.getColor());
            g.fillOval(p.getX(), p.getY(),2,2);
        }
    }

    public static void main (String[] args) {
        SierpinskiTriangle sierpinskiTriangle = new SierpinskiTriangle();
    }

    public class Point {

        int x;
        int y;
        Color color;

        public Point(int x, int y, Color color) {
            this.x = x;
            this.y = y;
            this.color = color;

        }

        public int getX() {
            return x;
        }
        public int getY() {
            return y;
        }
        public Color getColor() {
            return color;
        }
    }
}

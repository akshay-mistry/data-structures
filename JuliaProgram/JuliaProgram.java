import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class JuliaProgram extends JPanel implements AdjustmentListener, ActionListener {

    JFrame frame;
    JScrollBar ABar, BBar, zoomBar, hueBar, satBar, brightBar, iterBar, eyeBar, eyeBrightBar, eyeSatBar;
    JLabel ALabel, BLabel, zoomLabel, hueLabel, satLabel, brightLabel, iterLabel, eyeLabel, eyeBrightLabel, eyeSatLabel;
    JPanel sliderPanel, labelPanel, buttonPanel, bigPanel;
    JButton reset, save;
    JFileChooser fileChooser;
    BufferedImage juliaImage;
    double A, B, zoom;
    float maxIter, hue, sat, bright, eye, eyeBright, eyeSat;

    public JuliaProgram() {
        frame = new JFrame("Julia Program");

        ABar = new JScrollBar(JScrollBar.HORIZONTAL, 0, 0, -2000, 2000);
        A = ABar.getValue()/1000.0;
        ABar.addAdjustmentListener(this);

        BBar = new JScrollBar(JScrollBar.HORIZONTAL, 0, 0, -2000, 2000);
        B = BBar.getValue()/1000.0;
        BBar.addAdjustmentListener(this);

        iterBar = new JScrollBar(JScrollBar.HORIZONTAL, 300, 0, 25, 1000);
        maxIter = iterBar.getValue()/1.0f;
        iterBar.addAdjustmentListener(this);

        zoomBar = new JScrollBar(JScrollBar.HORIZONTAL, 100, 0, 0, 1000);
        zoom = zoomBar.getValue()/100.0;
        zoomBar.addAdjustmentListener(this);

        hueBar = new JScrollBar(JScrollBar.HORIZONTAL, 500, 0, 0, 1000);
        hue = hueBar.getValue()/1000.0f;
        hueBar.addAdjustmentListener(this);

        satBar = new JScrollBar(JScrollBar.HORIZONTAL, 500, 0, 0, 1000);
        sat = satBar.getValue()/1000.0f;
        satBar.addAdjustmentListener(this);

        brightBar = new JScrollBar(JScrollBar.HORIZONTAL, 500, 0, 0, 1000);
        bright = brightBar.getValue()/1000.0f;
        brightBar.addAdjustmentListener(this);

        eyeBar = new JScrollBar(JScrollBar.HORIZONTAL, 1000, 0, 0, 1000);
        eye = eyeBar.getValue()/1000.0f;
        eyeBar.addAdjustmentListener(this);

        eyeBrightBar = new JScrollBar(JScrollBar.HORIZONTAL, 500, 0, 0, 1000);
        eyeBright = eyeBrightBar.getValue()/1000.0f;
        eyeBrightBar.addAdjustmentListener(this);

        eyeSatBar = new JScrollBar(JScrollBar.HORIZONTAL, 500, 0, 0, 1000);
        eyeSat = eyeSatBar.getValue()/1000.0f;
        eyeSatBar.addAdjustmentListener(this);

        ALabel = new JLabel(" A: " + A);
        BLabel = new JLabel(" B: " + B);
        iterLabel = new JLabel(" Iterations: " + maxIter);
        zoomLabel = new JLabel(" Zoom: " + zoom);
        hueLabel = new JLabel(" Hue: " + hue);
        satLabel = new JLabel(" Saturation: " + sat);
        brightLabel = new JLabel(" Brightness: " + bright);
        eyeLabel = new JLabel(" Eye Hue: " + eye);
        eyeBrightLabel = new JLabel(" Eye Brightness: " + eyeBright);
        eyeSatLabel = new JLabel(" Eye Saturation: " + eyeSat);

        GridLayout grid = new GridLayout(10, 1);
        sliderPanel = new JPanel();
        sliderPanel.setLayout(grid);
        sliderPanel.add(ABar);
        sliderPanel.add(BBar);
        sliderPanel.add(iterBar);
        sliderPanel.add(zoomBar);
        sliderPanel.add(hueBar);
        sliderPanel.add(satBar);
        sliderPanel.add(brightBar);
        sliderPanel.add(eyeBar);
        sliderPanel.add(eyeBrightBar);
        sliderPanel.add(eyeSatBar);

        buttonPanel = new JPanel();
        GridLayout buttonGrid = new GridLayout(2, 1);
        buttonPanel.setLayout(buttonGrid);
        reset = new JButton("Reset");
        reset.addActionListener(this);
        save = new JButton("Save Image");
        save.addActionListener(this);
        buttonPanel.add(reset);
        buttonPanel.add(save);

        labelPanel = new JPanel();
        labelPanel.setLayout(grid);
        labelPanel.add(ALabel);
        labelPanel.add(BLabel);
        labelPanel.add(iterLabel);
        labelPanel.add(zoomLabel);
        labelPanel.add(hueLabel);
        labelPanel.add(satLabel);
        labelPanel.add(brightLabel);
        labelPanel.add(eyeLabel);
        labelPanel.add(eyeBrightLabel);
        labelPanel.add(eyeSatLabel);

        bigPanel = new JPanel();
        bigPanel.setLayout(new BorderLayout());
        bigPanel.add(sliderPanel, BorderLayout.CENTER);
        bigPanel.add(labelPanel, BorderLayout.WEST);
        bigPanel.add(buttonPanel, BorderLayout.EAST);

        frame.setLayout(new BorderLayout());
        frame.add(this, BorderLayout.CENTER);
        frame.add(bigPanel, BorderLayout.SOUTH);

        frame.setSize(1000,1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        String currDir=System.getProperty("user.dir");
        fileChooser=new JFileChooser(currDir);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(drawJulia(), 0, 0, null);
    }

    public BufferedImage drawJulia() {
        int w = frame.getWidth();
        int h = frame.getHeight();
        juliaImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);

        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {

                float iteration = maxIter;
                double zx = 1.5 * (i-w/2.0) / (zoom * w/2.0);
                double zy = (j-h/2.0) / (zoom * h/2.0);

                while (zx * zx + zy * zy < 6 && iteration > 0) {
                    double diff = zx * zx - zy * zy + A;
                    zy = 2.0 * zx * zy + B;
                    zx = diff;
                    iteration--;
                }
                int c;
                if (iteration > 0) {
                    c = Color.HSBtoRGB(hue*(iteration/maxIter) % 1, sat, bright);
                }
                else {
                    c = Color.HSBtoRGB(eye, eyeSat, eyeBright);
                }
                juliaImage.setRGB(i, j, c);
            }
        }
        return juliaImage;
    }

    public static void main (String[] args) {
        JuliaProgram program = new JuliaProgram();
    }

    @Override
    public void adjustmentValueChanged(AdjustmentEvent e) {
        if (e.getSource() == ABar) {
            A = ABar.getValue()/1000.0;
            ALabel.setText(" A: " + A + "\t\t");
        }
        else if (e.getSource() == BBar) {
            B = BBar.getValue()/1000.0;
            BLabel.setText(" B: " + B + "\t\t");
        }
        else if (e.getSource() == iterBar) {
            maxIter = iterBar.getValue()/1.0f;
            iterLabel.setText(" Iterations: " + maxIter + "\t\t");
        }
        else if (e.getSource() == zoomBar) {
            zoom = zoomBar.getValue()/100.0;
            zoomLabel.setText(" Zoom: " + zoom + "\t\t");
        }
        else if (e.getSource() == hueBar) {
            hue = hueBar.getValue()/1000.0f;
            hueLabel.setText(" Hue: " + hue + "\t\t");
        }
        else if (e.getSource() == satBar) {
            sat = satBar.getValue()/1000.0f;
            satLabel.setText(" Saturation: " + sat + "\t\t");
        }
        else if (e.getSource() == brightBar) {
            bright = brightBar.getValue()/1000.0f;
            brightLabel.setText(" Brightness: " + bright + "\t\t");
        }
        else if (e.getSource() == eyeBar) {
            eye = eyeBar.getValue()/1000.0f;
            eyeLabel.setText(" Eye Hue: " + eye + "\t\t");
        }
        else if (e.getSource() == eyeBrightBar) {
            eyeBright = eyeBrightBar.getValue()/1000.0f;
            eyeBrightLabel.setText(" Eye Brightness: " + eyeBright + "\t\t");
        }
        else if (e.getSource() == eyeSatBar) {
            eyeSat = eyeSatBar.getValue()/1000.0f;
            eyeSatLabel.setText(" Eye Saturation: " + eyeSat + "\t\t");
        }
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == reset) {
            ABar.setValue(0);
            BBar.setValue(0);
            iterBar.setValue(300);
            zoomBar.setValue(100);
            hueBar.setValue(500);
            satBar.setValue(500);
            brightBar.setValue(500);
            eyeBar.setValue(1000);
            eyeBrightBar.setValue(500);
            eyeSatBar.setValue(500);
            A = 0;
            B = 0;
            maxIter = 300f;
            zoom = 1;
            hue = 0.5f;
            sat = 0.5f;
            bright = 0.5f;
            eye = 1.0f;
            eyeBright = 0.5f;
            eyeSat = 0.5f;
            repaint();
        }
        if (e.getSource() == save) {
            if(juliaImage!=null)
            {
                FileFilter filter=new FileNameExtensionFilter("*.png","png");
                fileChooser.setFileFilter(filter);
                if(fileChooser.showSaveDialog(null)==JFileChooser.APPROVE_OPTION)
                {
                    File file=fileChooser.getSelectedFile();
                    try
                    {
                        String st=file.getAbsolutePath();
                        if(st.indexOf(".png")>=0)
                            st=st.substring(0,st.length()-4);
                        ImageIO.write(juliaImage,"png",new File(st+".png"));
                    }
                    catch(IOException exception)
                    {

                    }

                }
            }
        }
    }
}

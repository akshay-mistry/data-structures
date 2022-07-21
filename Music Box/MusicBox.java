import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.io.*;
import java.net.URL;
import java.nio.Buffer;

public class MusicBox extends JFrame implements Runnable, ActionListener, AdjustmentListener {

    JFrame frame;
    JPanel panel, additionalButtonPanel, tempoPanel;
    JScrollPane buttonPane;
    JScrollBar tempoBar;
    JMenuBar menuBar;
    JMenu instrumentMenu, fileMenu;
    JMenuItem bell, glockenspiel, marimba, oboe, oh_ah, piano, save, load;
    JButton startStop, clear;
    JLabel tempoLabel;
    JFileChooser fileChooser;
    Thread timer;
    Clip[] clip;
    int column;
    boolean currentlyPlaying = false;
    String instrument;
    int tempo = 200;
    int dimR = 37; int dimC = 50;
    JToggleButton[][] toggleButtons = new JToggleButton[dimR][dimC];

    String path = "/Users/akshaymistry/Desktop/Data Structures/Music Box/";
    String[] clipNames = new String[] {"A1", "A2", "A3", "ASharp1", "ASharp2", "ASharp3", "B1", "B2", "B3", "C0", "C1", "C2", "C3", "CSharp1", "CSharp2", "CSharp3", "D1", "D2", "D3", "D4", "DSharp1", "DSharp2", "DSharp3", "E1", "E2", "E3", "F1", "F2", "F3", "FSharp1", "FSharp2", "FSharp3", "G1", "G2", "G3", "GSharp1", "GSharp2", "GSharp3"};
    String[] notes = new String[] {"C3", "B3", "A#3", "A3", "G#3", "G3", "F#3", "F3", "E3", "D#3", "D3", "C#3", "C2", "B2", "A#2", "A2", "G#2", "G2", "F#2", "F2", "E2", "D#2", "D2", "C#2", "C1", "B1", "A#1", "A1", "G#1", "G1", "F#1", "F1", "E1", "D#1", "D1", "C#1", "C0"};
    String[] instrumentNames = new String[] {"Bell", "Glockenspiel", "Marimba", "Oboe", "Oh_Ah", "Piano"};

    public MusicBox() {

        frame = new JFrame("Music Box");
        frame.setSize(800,800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(new GridLayout(dimR, dimC));

        for (int i = 0; i < dimR; i++) {
            for (int j = 0; j < dimC; j++) {
                toggleButtons[i][j] = new JToggleButton();
                toggleButtons[i][j].setPreferredSize(new Dimension(30,30));
                toggleButtons[i][j].setMargin(new Insets(0,0,0,0));
                toggleButtons[i][j].setText(notes[i]);
                panel.add(toggleButtons[i][j]);
            }
        }
        buttonPane = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        frame.add(buttonPane, BorderLayout.CENTER);

        timer = new Thread(this);
        timer.start();
        clip = new Clip[38];
        instrument = instrumentNames[0];
        loadTones(instrument);

        menuBar = new JMenuBar();
        fileMenu = new JMenu("File");
        fileMenu.addActionListener(this);
        save = new JMenuItem("Save");
        save.addActionListener(this);
        fileMenu.add(save);
        load = new JMenuItem("Load");
        load.addActionListener(this);
        fileMenu.add(load);
        menuBar.add(fileMenu);

        String currentDir = System.getProperty("user.dir");
        fileChooser = new JFileChooser(currentDir);

        instrumentMenu = new JMenu("Instrument");
        instrumentMenu.addActionListener(this);
        instrumentMenu.putClientProperty("Menu", 0);
        bell = new JMenuItem("Bell");
        bell.putClientProperty("Bell", 0);
        bell.addActionListener(this);
        instrumentMenu.add(bell);
        glockenspiel = new JMenuItem("Glockenspiel");
        glockenspiel.putClientProperty("Glockenspiel", 0);
        glockenspiel.addActionListener(this);
        instrumentMenu.add(glockenspiel);
        marimba = new JMenuItem("Marimba");
        marimba.putClientProperty("Marimba", 0);
        marimba.addActionListener(this);
        instrumentMenu.add(marimba);
        oboe = new JMenuItem("Oboe");
        oboe.putClientProperty("Oboe", 0);
        oboe.addActionListener(this);
        instrumentMenu.add(oboe);
        oh_ah = new JMenuItem("Oh_Ah");
        oh_ah.putClientProperty("Oh_Ah", 0);
        oh_ah.addActionListener(this);
        instrumentMenu.add(oh_ah);
        piano = new JMenuItem("Piano");
        piano.putClientProperty("Piano", 0);
        piano.addActionListener(this);
        instrumentMenu.add(piano);
        menuBar.add(instrumentMenu);

        additionalButtonPanel = new JPanel();
        additionalButtonPanel.setLayout(new GridLayout(1, 2));
        startStop = new JButton("Play");
        startStop.addActionListener(this);
        additionalButtonPanel.add(startStop);
        clear = new JButton("Clear");
        clear.addActionListener(this);
        additionalButtonPanel.add(clear);
        menuBar.add(additionalButtonPanel);

        tempoBar = new JScrollBar(JScrollBar.HORIZONTAL, 200, 0, 50, 350);
        tempoBar.addAdjustmentListener(this);
        tempoLabel = new JLabel("Tempo: " + tempo);
        tempoPanel = new JPanel();
        tempoPanel.setLayout(new BorderLayout());
        tempoPanel.add(tempoLabel, BorderLayout.WEST);
        tempoPanel.add(tempoBar, BorderLayout.CENTER);

        frame.add(tempoPanel, BorderLayout.SOUTH);
        frame.add(menuBar, BorderLayout.NORTH);
        frame.setVisible(true);
    }

    public void reset() {
        column = 0;
        currentlyPlaying = false;
        startStop.setText("Play");
    }

    public void loadTones (String instrument) {
        try {
            for(int x=0;x<clipNames.length;x++) {
                File url = new File(path + instrument+ "/" + instrument+" - " + clipNames[x]+".wav");
                AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
                clip[x] = AudioSystem.getClip();
                clip[x].open(audioIn);
            }
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {}
    }

    public void saveSong() {

        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "*.txt", ".txt");
        fileChooser.setFileFilter(filter);
        if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                String path = file.getAbsolutePath();
                if(path.contains(".txt")) {
                    path = path.substring(0, path.indexOf('.'));
                }
                String currSong = "";
                String[] notes = {" ","c ","b ","a-","a ","g-","g ","f-","f ","e ","d-","d ","c-","c ","b ","a-","a ","g-","g ",
                        "f-","f ","e ","d-","d ","c-","c ","b ","a-","a ","g-","g ","f-","f ","e ","d-","d ","c-","c"};

                for(int i = 0; i < toggleButtons.length; i++) {
                    if (i == 0) {
                        currSong += tempo + " " + toggleButtons[0].length + "\n";
                    }
                    currSong += notes[i];

                    for(int j = 0; j < toggleButtons[0].length; j++) {
                        if(toggleButtons[i][j].isSelected()) {
                            currSong += "x";
                        }
                        else {
                            currSong += "-";
                        }
                    }
                    currSong += "\n";

                    BufferedWriter outputStream = new BufferedWriter(new FileWriter(path));
                    outputStream.write(currSong.substring(0, currSong.length() - 1));
                    outputStream.close();
                }
            }
            catch(IOException e) {}
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o == startStop) {
            if (currentlyPlaying) {
                startStop.setText("Play");
                currentlyPlaying = false;
            }
            else {
                startStop.setText("Stop");
                currentlyPlaying = true;
            }
        }
        else if (o == clear) {
            for (int r = 0; r < toggleButtons.length; r++) {
                for (int c = 0; c < toggleButtons[0].length; c++) {
                    toggleButtons[r][c].setSelected(false);
                }
            }
            reset();
        }
        else if (o == save) {
            reset();
            saveSong();
        }
        else if (o == load) {
            reset();
            loadFile();
        }
        else if (o == bell) {
            loadTones("Bell");
            reset();
        }
        else if (o == glockenspiel) {
            loadTones("Glockenspiel");
            reset();
        }
        else if (o == marimba) {
            loadTones("Marimba");
            reset();
        }
        else if (o == oboe) {
            loadTones("Oboe");
            reset();
        }
        else if (o == oh_ah) {
            loadTones("Oh_Ah");
            reset();
        }
        else if (o == piano) {
            loadTones("Piano");
            reset();
        }
    }

    public void loadFile() {
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                File loadFile = fileChooser.getSelectedFile();
                BufferedReader input = new BufferedReader(new FileReader(loadFile));
                String temp = input.readLine();
                String[] arr = temp.split(" ");
                tempo = Integer.valueOf(arr[0]);
                dimC = Integer.valueOf(arr[1]);
                char[][] song = new char[38][dimC];
                int row = 0;
                while((temp=input.readLine())!=null) {
                    for (int i = 2; i < dimC+1; i++) {
                        song[row][i-2] = temp.charAt(i);
                    }
                    row++;
                }
                setNotes(song);
            } catch (IOException e) {}
        }
    }

    public void setNotes(char[][] song) {
        buttonPane.remove(panel);
        panel = new JPanel();
        panel.setLayout(new GridLayout(dimR, dimC));
        toggleButtons = new JToggleButton[dimR][dimC];

        for (int i = 0; i < dimR; i++) {
            for (int j = 0; j < dimC; j++) {
                toggleButtons[i][j] = new JToggleButton();
                toggleButtons[i][j].setPreferredSize(new Dimension(30,30));
                toggleButtons[i][j].setMargin(new Insets(0,0,0,0));
                toggleButtons[i][j].setText(notes[i]);
                panel.add(toggleButtons[i][j]);
            }
        }
        frame.remove(buttonPane);
        buttonPane = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        frame.add(buttonPane);

        for (int i = 0; i < dimR; i++) {
            for (int j = 0; j < dimC; j++) {
                if (song[i][j] == 'x') {
                    toggleButtons[i][j].setSelected(true);
                }

            }
        }
        frame.revalidate();
    }

    @Override
    public void adjustmentValueChanged(AdjustmentEvent e) {
        Object o = e.getSource();
        if (o == tempoBar) {
            tempo = tempoBar.getValue();
            tempoLabel.setText("Tempo: " + tempo);
            reset();
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (!currentlyPlaying) {
                   timer.sleep(0);
                }
                else {
                    for (int r = 0; r < toggleButtons.length; r++) {
                        if (toggleButtons[r][column].isSelected()) {
                            clip[r].start();
                        }
                    }
                    timer.sleep(350-tempo);
                    for (int r = 0; r < toggleButtons.length; r++) {
                        if (toggleButtons[r][column].isSelected()) {
                            clip[r].stop();
                            clip[r].setFramePosition(0);

                        }
                    }
                    column++;
                    if (column == toggleButtons[0].length)
                        column = 0;
                }

            } catch (InterruptedException e) {}
        }
    }

    public static void main (String[] args) {
        MusicBox musicBox = new MusicBox();
    }
}

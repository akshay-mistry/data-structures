import java.io.*;

public class GuitarHero {

    public GuitarHero() {
        try {
            BufferedReader input = new BufferedReader(new FileReader("/Users/akshaymistry/Desktop/Data Structures/Refresher Tasks/Guitar Song.txt"));
            String text;
            String[][] output = null;

            String[] notes = {"G#", "G", "F#", "F", "E", "D#", "D", "C#", "C", "B", "A#", "A", "G#", "G",
                                        "F#", "F", "E", "D#", "D", "C#", "C", "B", "A#", "A", "G#", "G", "F#", "F", "E"};

            int[][] helper = { {29,24,19,14,10,5},
                                {28,23,18,13,9,4},
                                {27,22,17,12,8,3},
                                {26,21,16,11,7,2},
                                {25,20,15,10,6,1}
                            };

            int row = 0;

            while((text=input.readLine()) != null) {
                String[] music = text.split(",");

                if(output == null) {
                    output = new String[30][music.length+1];
                    output[0][0] = "Measure";

                    for (int i = 1; i <= notes.length; i++) {
                        output[i][0] = notes[i-1];
                    }
                    for (int j = 0; j <= music.length; j++) {
                        output[0][j] = ""+j;
                    }
                }

                for (int measure = 0; measure < music.length; measure++)
                {
                    for (int col = 0; col < 6; col++) {
                        if(music[measure].charAt(col) == '*' || music[measure].charAt(col) == 'o') {
                            output[helper[row][col]][measure+1] = "O";
                        }
                    }
                }
                row++;
            }
            output(output);

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
        }
    }

    public void output(String[][] output) {
        for (int row = 0; row < output.length; row++) {
            for (int col = 0; col < output[0].length; col++) {
                if (output[row][col] == null) {
                    System.out.print("\t\t");
                } else {
                    System.out.print(output[row][col] + "\t\t");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        GuitarHero guitarHero = new GuitarHero();
    }
}


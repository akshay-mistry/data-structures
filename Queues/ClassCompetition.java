import java.awt.*;
import java.io.*;

public class ClassCompetition {

    public ClassCompetition() {
        File name = new File("/Users/akshaymistry/Desktop/Data Structures/Queues/asciiPhoto5.txt");

        try {
            BufferedReader input = new BufferedReader(new FileReader(name));

            String text = input.readLine();
            int windowWidth = 156;
            int index = 0;
            while (index + windowWidth < text.length()) {
                String print = text.substring(index, index + windowWidth);
                System.out.println(print);
                index+=windowWidth;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main (String[] args) {
        ClassCompetition classCompetition = new ClassCompetition();
    }
}

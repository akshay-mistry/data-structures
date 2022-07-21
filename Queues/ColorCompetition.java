import java.io.*;
import java.util.ArrayList;

public class ColorCompetition {

    ArrayList<Integer> rgbValues;

    public ColorCompetition() {
        File name = new File("/Users/akshaymistry/Desktop/Data Structures/Queues/input1.txt");
        rgbValues = new ArrayList<>();
        try {

            BufferedReader input = new BufferedReader(new FileReader(name));

            String text;
            while ((text=input.readLine())!= null) {
                int index = 0;
                while (index + 3 < text.length()) {
                    int num = Integer.valueOf(text.substring(index, index +3));
                    if (num >= 0 && num <= 255) {
                        rgbValues.add(num);
                    }
                    index+=3;
                }

            }
            System.out.println(rgbValues);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main (String[] args) {
        ColorCompetition colorCompetition = new ColorCompetition();
    }
}
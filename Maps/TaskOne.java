import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeMap;

public class TaskOne {

    public TaskOne() {

        //Write a program that will read in Strings worth of text from a text file and store the letters as
        //the keys of a HashMap/TreeMap and the frequencies of each letter as the values for each
        //key.

        try {
            TreeMap<Character, Integer> letterMap = new TreeMap<>();
            BufferedReader input = new BufferedReader(new FileReader("/Users/akshaymistry/Desktop/Data Structures/Maps/file.txt"));
            String text = "";

            while ((text=input.readLine())!= null) {
                String[] arr = text.split(" ");
                for (int i = 0; i < arr.length; i++) {
                    String str = arr[i].toLowerCase();
                    for (int j = 0; j < str.length(); j++) {
                        if (Character.isLetter(str.charAt(j))) {
                            letterMap.put(str.charAt(j), 0);
                        }
                    }
                }

                for (int i = 0; i < arr.length; i++) {
                    String str = arr[i].toLowerCase();
                    for (int j = 0; j < str.length(); j++) {
                        if (Character.isLetter(str.charAt(j))) {
                            int count = letterMap.get(str.charAt(j));
                            if (letterMap.containsKey(str.charAt(j))) {
                                count++;
                                letterMap.put(str.charAt(j), count);
                            }
                        }
                    }
                }
            }
            System.out.println(letterMap);
        }
        catch(IOException io) {
            System.err.println("File does not exist");
        }
    }

    public static void main (String[] args) {
        TaskOne taskOne = new TaskOne();
    }
}

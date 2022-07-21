import java.io.*;
import java.util.ArrayList;

public class LockCompetition {

    ArrayList<Integer> lockValues;

    public LockCompetition() {
        File name = new File("/Users/akshaymistry/Desktop/Data Structures/Queues/comboFile.txt");
        lockValues = new ArrayList<>();
        try {

            BufferedReader input = new BufferedReader(new FileReader(name));

            String text;
            String nums = "";
            while ((text=input.readLine())!= null) {
                for (int i = 0; i < text.length(); i++) {
                    char c = text.charAt(i);
                    //System.out.println(c);
                    if (c == '0' || c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6' || c == '7' || c == '8' || c == '9') {
                        nums+=c;
                    }
                }

            }
           // System.out.println(nums);
            int index = 0;
            while (index + 2 < nums.length()) {
                int num = Integer.valueOf(nums.substring(index, index +2));
                if (num >= 0 && num <= 35) {
                    lockValues.add(num);
                }
                index+=2;
            }
            System.out.println(lockValues);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main (String[] args) {
        LockCompetition lockCompetition = new LockCompetition();
    }
}
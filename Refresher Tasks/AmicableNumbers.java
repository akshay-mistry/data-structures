import java.io.*;
import java.util.ArrayList;

public class AmicableNumbers {

    public AmicableNumbers() {

        File name = new File("AmicableInput.txt");
        try {
            BufferedReader input = new BufferedReader(new FileReader(name));

            String text="";

            while ((text=input.readLine())!= null) {
                String[] arr = text.split(" ");
                int num1 = Integer.parseInt(arr[0]);
                int num2 = Integer.parseInt(arr[1]);
                String isAmicable = "";
                ArrayList<Integer> factors1 = getFactors(num1);
                ArrayList<Integer> factors2 = getFactors(num2);
                int sum1 = getSum(factors1);
                int sum2 = getSum(factors2);
                if (sum1 == num2 && sum2 == num1) {
                    isAmicable = "amicable";
                } else {
                    isAmicable = "not amicable";
                }
                System.out.println("The numbers " + num1 + " and " + num2 + " are " + isAmicable + ".");
                System.out.println("\tFactors of " + num1 + " are " + printFactors(factors1) + " Sum is " + sum1 + ".");
                System.out.println("\tFactors of " + num2 + " are " + printFactors(factors2) + " Sum is " + sum2 + ".");
            }
        }

        catch (IOException io) {
            System.err.println("File does not exist");
        }
    }

    public ArrayList<Integer> getFactors(int num) {
        ArrayList<Integer> factors = new ArrayList<>();
        for (int i = 1; i < num; i++) {
            if (num % i == 0) {
                factors.add(i);
            }
        }
        return factors;
    }

    public String printFactors(ArrayList<Integer> factors) {
        String print = "";
        for (int i = 0; i < factors.size(); i++) {
            if (i == factors.size()-1) {
                print+="and " + factors.get(i) + ".";
            }
            else if (i < factors.size()-2) {
                print += factors.get(i) + ", ";
            }
            else {
                print += factors.get(i) + " ";
            }
        }
        return print;
    }

    public int getSum (ArrayList<Integer> factors) {
        int sum = 0;
        for (int i : factors) {
            sum += i;
        }
        return sum;
    }

    public static void main (String[] args) {
        AmicableNumbers amicableNumbers = new AmicableNumbers();
    }
}

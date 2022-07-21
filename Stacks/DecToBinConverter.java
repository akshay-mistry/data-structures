import java.io.*;
import java.util.*;

public class DecToBinConverter {

	private Stack<Integer> stack;
	public DecToBinConverter() {
			try {
				BufferedReader input = new BufferedReader(new FileReader("DecToBin.txt"));

				String text="";

				while ((text=input.readLine())!= null) {
					stack = new Stack<Integer>();
					int dec = Integer.parseInt(text);
					int num = dec;
					while (dec != 0) {
						stack.push(dec % 2);
						dec/=2;
					}
					if (dec == 0) {
						stack.push(0);
					}

					System.out.print(num+ ": ");
					int size = stack.size();
					for (int i = 0; i < size; i++) {
						System.out.print(stack.pop() + " ");
					}
					System.out.println();
				}

			}

		        catch (IOException io) {
		            System.err.println("File does not exist");
        }

	}


	public static void main (String[] args) {

		DecToBinConverter app = new DecToBinConverter();
	}


}
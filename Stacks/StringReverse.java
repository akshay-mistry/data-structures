import java.io.*;
import java.util.*;

public class StringReverse {

	private Stack<String> stack;
	public StringReverse() {
		try {
			BufferedReader input = new BufferedReader(new FileReader("StringRev.txt"));

			String text="";

			while ((text=input.readLine())!= null) {
				stack = new Stack<String>();
				String[] arr = text.split("");
				for (String s: arr) {
					stack.push(s);
				}
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

		StringReverse app = new StringReverse();
	}


}
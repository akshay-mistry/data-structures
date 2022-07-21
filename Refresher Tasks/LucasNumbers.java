import java.io.*;	//this is the only one that is necessary for this template.
//You might need others depending on the task.
import java.util.ArrayList;
import java.math.BigInteger;


public class LucasNumbers
{
	public static void main(String args[])
	{
		File name = new File("LucasInput.txt");
		try
		{
			BufferedReader input = new BufferedReader(new FileReader(name));

			String text="";
			while( (text=input.readLine())!= null)
			{
				int n = Integer.parseInt(text);
				System.out.println(nthValue(n));
			}
		}
		catch (IOException io)
		{
			System.err.println("File does not exist");
		}
	}

	public static BigInteger nthValue(int n) {

		ArrayList<BigInteger> lucas = new ArrayList<BigInteger>();
		lucas.add(BigInteger.valueOf(2));
		lucas.add(BigInteger.valueOf(1));
		for (int i = 0; i <= n; i++) {
			if (i - 2 >= 0)
			{
				BigInteger val = lucas.get(i-2).add(lucas.get(i-1));
				lucas.add(val);
			}
		}
		return lucas.get(n);
	}
}
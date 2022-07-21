import java.io.*;	//this is the only one that is necessary for this template.
//You might need others depending on the task.

public class ReducerTest
{

	public ReducerTest() {


				int numerator = 4;
				int denominator = 2;
				System.out.println(numerator);
				System.out.println(denominator);
				reduceIt(numerator, denominator);
				System.out.println();

	}

	public void reduceIt(int n, int d) {

		int gcf = findGCF(n, d);
		System.out.println("GCF: " + gcf);
		int newNum = n/gcf;
		int newDen = d/gcf;
		System.out.println("Improper Fraction: " + newNum + "/" + newDen);
		if (newDen == 1)
		{
			System.out.println(newNum);

		}
		else if (newNum > newDen) {
			int quotient = newNum/newDen;
			System.out.println("q: " + quotient);
			int newNewNum = newNum - (quotient*newDen);
			int newGCF = findGCF(newNewNum, newDen);
			int newNewNewNum = newNewNum/newGCF;
			int newNewDen = newDen/newGCF;
			System.out.println("Mixed Number: " + quotient + " " + newNewNewNum + "/" + newNewDen);
		}
	}

	public int findGCF(int n, int d)
	{

		int factor = n;
		if (n > d)
			factor = d;

		while (!(n % factor == 0 && d % factor == 0))
		{
			factor--;
		}

		return factor;
	}
	public static void main(String args[])
	{
		ReducerTest reducer = new ReducerTest();
	}


}
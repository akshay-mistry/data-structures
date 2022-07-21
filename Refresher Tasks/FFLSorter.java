import java.io.*;	//this is the only one that is necessary for this template.
//You might need others depending on the task.
import java.util.ArrayList;

public class FFLSorter
{
	public FFLSorter() {
		File name = new File("FFLAverages.txt");
		try
		{
			BufferedReader input = new BufferedReader(new FileReader(name));

			String text="";

			ArrayList<FootballPlayer> players = new ArrayList<FootballPlayer>();
			while( (text=input.readLine())!= null)
			{
				String arr[] = text.split(";");
				FootballPlayer player = new FootballPlayer(Double.parseDouble(arr[0]), arr[1], arr[2], arr[3], Integer.parseInt(arr[4]), Double.parseDouble(arr[5]), Double.parseDouble(arr[6]), Double.parseDouble(arr[7]), Double.parseDouble(arr[8]), Integer.parseInt(arr[9]));
				players.add(player);
				System.out.println(player.toString());
			}
		}
		catch (IOException io)
		{
			System.err.println("File does not exist");
		}


	}


	public static void main(String args[])
	{
		FFLSorter sorter = new FFLSorter();
	}



	public class FootballPlayer {

		private double numPick;
		private String name;
		private String pos;
		private String team;
		private int bye;
		private double overall;
		private double stdDev;
		private double highRd;
		private double lowRd;
		private int timesDrafted;


		public FootballPlayer(double num, String n, String p, String t, int b, double o, double s, double h, double l, int times) {
			numPick = num;
			name = n;
			pos = p;
			team = t;
			bye = b;
			overall = o;
			stdDev = s;
			highRd = h;
			lowRd = l;
			timesDrafted = times;

		}

		public String toString() {
			return name;
		}

		/*public FootballPlayer compareTo(FootballPlayer one, FootballPlayer two) {
			FootballPlayer three = null;

			return three;

		}
*/


	}
}



import java.io.*;
import java.util.Calendar;

public class TimeTravel {

	public TimeTravel() {

		File name = new File("fileName.txt");
		try
		{
			BufferedReader input = new BufferedReader(new FileReader(name));

			String text,output="";

			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.DATE, 18);
			calendar.set(Calendar.YEAR, 2018);
			calendar.set(Calendar.HOUR, 10);
			calendar.set(Calendar.MINUTE, 26);


		/*	while( (text=input.readLine())!= null)
			{
				System.out.println(text);
				output+=text;
			} */
		}
		catch (IOException io)
		{
			System.err.println("File does not exist");
		}


	}



	public static void main (String[] args) {

		TimeTravel timeTravel = new TimeTravel();

	}





}
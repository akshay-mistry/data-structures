import java.io.*;
import java.util.*;

public class StarWars {

	private Stack<Character> males;
	private Stack<Character> females;
	private Stack<Character> droids;
	private Stack<Character> years;

	public StarWars() {
		try {
			BufferedReader input = new BufferedReader(new FileReader("/Users/akshaymistry/Desktop/Data Structures/Stack/StarWarsCharacters.csv"));

			String text=input.readLine();
			males = new Stack<>();
			females = new Stack<>();
			droids = new Stack<>();
			years = new Stack<>();

			while ((text=input.readLine())!= null) {
				String[] arr = text.split(",");
				Character character = new Character(arr[0], arr[5], arr[6], arr[7], arr[8]);
				if (character.getGender().equals("male")) {
					males.push(character);
				}
				else {
					females.push(character);
				}
				if (character.getSpecies().equals("Droid")) {
					droids.push(character);
				}
				if (character.getYear().contains("BBY")) {
					years.push(character);
				}
			}
			printStack("Male Characters", males);
			printStack("Female Characters", females);
			printStack("Droids", droids);
			printStack("Ages", years);
		}

		catch (IOException io) {
		    System.err.println("File does not exist");
        }
	}

	public void printStack(String title, Stack<Character> stack) {
		System.out.println(title);
		for (int i = 0; i < stack.size(); i++) {
			Character character1 = stack.pop();
			String str = "";
			if (title.equals("Ages")) {
				str = String.format(character1.getName() + "%" + (40 - character1.getName().length()) + "s" + character1.getHomeworld() + "%" + (40 - character1.getHomeworld().length()) + "s" + character1.getYear().substring(0, character1.getYear().indexOf("BBY")), "", "");
			}
			else {
				str = String.format(character1.getName() + "%" + (40 - character1.getName().length()) + "s", character1.getHomeworld());

			}
			System.out.println(str);
		}
		System.out.println();
	}

	public static void main (String[] args) {
		StarWars app = new StarWars();
	}

	public class Character {

		String name;
		String year;
		String gender;
		String homeworld;
		String species;

		public Character(String n, String y, String g, String h, String s) {
			name = n;
			year = y;
			gender = g;
			homeworld = h;
			species = s;

		}

		public String getName() {
			return name;
		}
		public String getYear() {
			return year;
		}
		public String getGender() {
			return gender;
		}
		public String getHomeworld() {
			return homeworld;
		}
		public String getSpecies() {
			return species;
		}
	}
}
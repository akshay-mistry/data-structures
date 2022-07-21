import java.io.*;

public class Spiraling {

	public Spiraling() {
		File name = new File("/Users/akshaymistry/Desktop/Data Structures/Refresher Tasks/SpiralInput.txt");
		try {
			BufferedReader input = new BufferedReader(new FileReader(name));

			String text = "";

			while ((text = input.readLine()) != null) {
				int row = 0;
				int endRow = Integer.parseInt(text);
				int col = 0;
				int endCol = Integer.parseInt(text);

				String[][] arr = new String[endRow][endCol];
				endRow-=1;
				endCol-=1;
				int num = 0;

				while (row <= endRow && col <= endCol) {

					int val = col + num;
					if (num != 0)
						val-=1;

					for (int i = val; i <= endCol; i++) {
						arr[row][i] = "\t*\t";
					}

					for (int j = row; j <= endRow; j++) {
						arr[j][endCol] = "\t*\t";
					}

					for (int i = endCol; i > col + num; i--) {
						arr[endRow][i] = "\t*\t";
					}

					for (int j = endRow; j > row + 1; j--) {
						arr[j][col + num] = "\t*\t";
					}

					col++;
					row+=2;
					endCol-=2;
					endRow-=2;
					num++;
				}

				for (int r = 0; r < arr.length; r++) {
					for (int c = 0; c < arr[0].length; c++) {
						if (arr[r][c] == null) {
							arr[r][c] = "\t-\t";
						}
					}
				}


				for (int r = 0; r < arr.length; r++) {
					for (int c = 0; c < arr[0].length; c++) {
						System.out.print(arr[r][c] + "\t");
					}
					System.out.println();
				}
				System.out.println();
			}
		} catch (IOException e) {
			System.out.println("File not found");
		}
	}

	public static void main(String[] args) {
		Spiraling spiraling = new Spiraling();
	}
}
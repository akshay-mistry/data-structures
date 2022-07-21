import java.io.*;
import java.util.ArrayList;

public class TheMatrix {

    private ArrayList<int[][]> matrices;

    public TheMatrix() {

        matrices = new ArrayList<>();
        File name = new File("MatrixInput.txt");
        try {
            BufferedReader input = new BufferedReader(new FileReader(name));

            String text="";

            while ((text=input.readLine())!= null) {
                printMatrices(text);
                printSumDifference();
                printProduct();
            }

        }

        catch (IOException io) {
            System.err.println("File does not exist");
        }
    }

    public void printMatrices(String text) {
        String[] arr = text.split("\t");

        for (int i = 0; i < arr.length; i++) {
            String noBraces = arr[i].substring(2, arr[i].length() - 2);
            String[] rows = noBraces.split("},\\{");
            String[] cols = rows[0].split(",");
            int numRows = rows.length;
            int numCols = cols.length;
            int[][] matrix = new int[numRows][numCols];

            for (int row = 0; row < numRows; row++) {
                for (int col = 0; col < numCols; col++) {
                    matrix[row][col] = Integer.parseInt(rows[row].split(",")[col]);
                }
            }

            System.out.println("Matrix " + (i+1) + ":");
            printMatrix(matrix);
            System.out.println();
            matrices.add(matrix);
        }
    }

    public void printSumDifference() {
        int[][] matrix1 = matrices.get(0);
        int[][] matrix2 = matrices.get(1);
        if (matrix1.length == matrix2.length && matrix1[0].length == matrix2[0].length) {
            int numRows = matrix1.length;
            int numCols = matrix1[0].length;
            int[][] sumMatrix = new int[numRows][numCols];
            int[][] differenceMatrix = new int[numRows][numCols];

            for (int row = 0; row < numRows; row++) {
                for (int col = 0; col < numCols; col++) {
                    sumMatrix[row][col] = matrix1[row][col] + matrix2[row][col];
                    differenceMatrix[row][col] = matrix1[row][col] - matrix2[row][col];
                }
            }

            System.out.println("Sum:");
            printMatrix(sumMatrix);
            System.out.println();
            System.out.println("Difference:");
            printMatrix(differenceMatrix);
        }
        else {
            System.out.println("Sum:");
            System.out.println("\tSum is not possible.");
            System.out.println();
            System.out.println("Difference:");
            System.out.println("\tDifference is not possible.");
        }
        System.out.println();
    }

    public void printProduct() {
        int[][] matrix1 = matrices.get(0);
        int[][] matrix2 = matrices.get(1);
        if (matrix1[0].length == matrix2.length) {
            int numRows = matrix1.length;
            int numCols = matrix2[0].length;
            int[][] productMatrix = new int[numRows][numCols];

            for (int row = 0; row < numRows; row++) {
                for (int col = 0; col < numCols; col++) {
                    for (int i = 0; i < matrix1[0].length; i++) {
                        productMatrix[row][col] += matrix1[row][i] * matrix2[i][col];
                    }
                }
            }

            System.out.println("Product:");
            printMatrix(productMatrix);
        }
        else {
            System.out.println("Product:");
            System.out.println("\tProduct is not possible.");
        }
        System.out.println();
    }

    public void printMatrix(int[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                System.out.print(matrix[row][col] + "\t");
            }
            System.out.println();
        }
    }

    public static void main (String[] args) {
        TheMatrix theMatrix = new TheMatrix();
    }
}
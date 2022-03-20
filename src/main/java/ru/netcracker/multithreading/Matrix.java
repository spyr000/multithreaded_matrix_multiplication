package ru.netcracker.multithreading;

import java.util.Arrays;

public class Matrix {
    double[][] matrix;

    public Matrix(double[][] matrix) {
        alignMatrix(matrix);
        this.matrix = matrix;
    }

    public static void alignMatrix(double[][] matrix) {
        int maxRowLength = matrix[0].length;
        for (int i = 1; i < matrix.length; i++) {
            maxRowLength = matrix[i].length > maxRowLength ? matrix.length : maxRowLength;
        }

        for (int i = 0, matrixLength = matrix.length; i < matrixLength; i++) {
            double[] row = matrix[i];
            if (row.length < maxRowLength) {
                double[] newRow = new double[maxRowLength];
                System.arraycopy(row, 0, newRow, 0, row.length);
                double[] zerosArr = new double[maxRowLength - row.length];
                Arrays.fill(zerosArr, 0.d);
                System.arraycopy(zerosArr, 0, newRow, row.length, zerosArr.length);
                matrix[i] = newRow;
            }
        }
    }

    public int getRowsAmt() {
        return matrix.length;
    }
    public int getColsAmt() {
        return matrix[0].length;
    }

    public double[][] getRows() {
        return matrix;
    }

    public double[][] getCols() {
        int cols = getColsAmt();
        int rows = getRowsAmt();
        double[][] result = new double[cols][rows];
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                result[i][j] = matrix[j][i];
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return Arrays.deepToString(matrix).replace("],","]\n");
    }

}

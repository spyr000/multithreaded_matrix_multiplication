package ru.netcracker.multithreading;

import ru.netcracker.multithreading.exceptions.MismatchedMatricesException;

import java.util.Arrays;

public class MatrixMultiplication {

    public static double[][] multiplyMatrices(double[][] firstMatrix, double[][] secondMatrix) throws MismatchedMatricesException {
//        for (double[] row : firstMatrix) {
//            if (row.length != secondMatrix.length) throw new MismatchedMatricesException("Inconsistent matrix sizes");
//        }
        System.out.println(Arrays.deepToString(firstMatrix));
        fillNullVals(firstMatrix);
        System.out.println(Arrays.deepToString(firstMatrix));

        return new double[][]{new double[]{0.0d}};

    }

    public static double[][] fillNullVals(double[][] matrix) {
        int maxRowLength = matrix[0].length;
        for (int i = 1; i < matrix.length; i++) {
            maxRowLength = matrix[i].length > maxRowLength ? matrix.length : maxRowLength;
        }

        for (double[] row : matrix) {
            if (row.length < maxRowLength) {
                double[] zerosArr = new double[maxRowLength - row.length];
                Arrays.fill(zerosArr, 0.d);
                System.arraycopy(zerosArr, 0, row, row.length, zerosArr.length);
            }
        }
        return matrix;
    }

    public static void main(String[] args) {
        var firstMatrix = new double[][]{
                new double[]{1.d, 2.d},
                new double[]{0.d, 3.d, 4.d},
                new double[]{0.d}
        };
        var secondMatrix = new double[][]{
                new double[]{1.d, 2.d},
                new double[]{0.d, 3.d, 4.d},
                new double[]{0.d}
        };
        try {
            MatrixMultiplication.multiplyMatrices(firstMatrix,secondMatrix);
        } catch (MismatchedMatricesException e) {
            e.printStackTrace();
        }
    }
}

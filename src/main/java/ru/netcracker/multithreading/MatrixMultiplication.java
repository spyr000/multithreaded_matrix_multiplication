package ru.netcracker.multithreading;

import ru.netcracker.multithreading.exceptions.MismatchedMatricesException;

import java.util.ArrayList;
import java.util.List;

public class MatrixMultiplication {
    double[][] result;
    Matrix matrix1, matrix2;

    MatrixMultiplication(Matrix matrix1, Matrix matrix2) {
        this.matrix1 = matrix1;
        this.matrix2 = matrix2;
        result = new double[matrix1.getRowsAmt()][matrix2.getColsAmt()];
    }

    public static void main(String[] args) {


    }

    public Matrix multiplyMatrices() throws MismatchedMatricesException {
        double[][] matrix2Cols = matrix2.getCols();
        if (matrix1.getColsAmt() != matrix2.getRowsAmt()) {
            throw new MismatchedMatricesException(
                    "Unable to multiply a " + matrix1.getColsAmt()
                    + "-column matrix by a "
                    + matrix2.getRowsAmt()+ "-row matrix "
            );
        }

        int rowsAmt = matrix1.getRowsAmt();
        int colsAmt = matrix2.getColsAmt();
        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < rowsAmt; i++) {
            for (int j = 0; j < colsAmt; j++) {
                threads.add(new Thread(new VectorMultiplier(matrix1.getRows()[i], matrix2Cols[j], i, j)));
            }
        }
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return new Matrix(result);
    }

    protected class VectorMultiplier implements Runnable {
        private final double[] vec1, vec2;
        private final int row, col;

        public VectorMultiplier(double[] vec1, double[] vec2, int row, int col) {
            this.vec1 = vec1;
            this.vec2 = vec2;
            this.row = row;
            this.col = col;
        }

        @Override
        public void run() {
            double res = 0;
            for (int i = 0; i < vec1.length; i++) {
                res += vec1[i] * vec2[i];
            }
            result[row][col] = res;
        }
    }
}

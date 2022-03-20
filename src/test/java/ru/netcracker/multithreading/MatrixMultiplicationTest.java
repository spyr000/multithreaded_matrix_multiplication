package ru.netcracker.multithreading;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions.*;
import ru.netcracker.multithreading.exceptions.MismatchedMatricesException;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class MatrixMultiplicationTest {
    double[][] firstArr = new double[][]{
            new double[]{4, 2, 0},
            new double[]{0, 8, 1},
            new double[]{0, 1, 0}
    };
    double[][] secondArr = new double[][]{
            new double[]{4, 2, 1},
            new double[]{2, 0, 4},
            new double[]{9, 4, 2}
    };
    double[][] multResult = new double[][]{
            new double[]{20, 8, 12},
            new double[]{25, 4, 34},
            new double[]{2, 0, 4}
    };
    @Test
    void multiplyMatrices() {
        Matrix matrix1 = new Matrix(firstArr);
        Matrix matrix2 = new Matrix(secondArr);
        MatrixMultiplication matrixMultiplication = new MatrixMultiplication(matrix1, matrix2);
        Matrix mMultResult = null;
        try {
            mMultResult = matrixMultiplication.multiplyMatrices();
        } catch (MismatchedMatricesException e) {
            e.printStackTrace();
        }
        System.out.println(mMultResult);
        Assertions.assertArrayEquals(multResult, Objects.requireNonNull(mMultResult).getRows());
    }
}
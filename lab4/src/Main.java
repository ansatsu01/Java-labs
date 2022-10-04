import java.io.IOException;
import java.util.Arrays;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        File nameFile = new File("createMatrix.txt");
        try {
            float[][] A = fillMatrix(nameFile);
            System.out.println(Arrays.deepToString(A));
            reduceToTriangularForm(A);
            System.out.println(Arrays.deepToString(A));
            System.out.println("answer: " + Arrays.toString(findSolution(A)));
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println("not enough elements in matrix");
        }catch (FileNotFoundException e){
            System.out.println("file's not found");
        }catch(IOException e) {
            System.out.println("too much elements in matrix");
        }catch(NumberFormatException e){
            System.out.println("not a number in matrix");
        }catch (ZeroDeterminantException e){
            System.out.println("matrix is degenerate");
        }
    }

    public static float[][] fillMatrix(File f)throws NoSuchElementException,
            FileNotFoundException,NumberFormatException, IOException, ArrayIndexOutOfBoundsException {
        Scanner sc =new Scanner(f);
        String str = sc.nextLine();
        String[] row1 =str.split(" ");
        int n= row1.length-1;
        if(n<=0){
            throw new NoSuchElementException();
        }
        float[][] matrix = new float[n][n+1];
        for(int i=0;i<=n;i++){
            matrix[0][i]=Float.parseFloat(row1[i]);
        }
        for(int i = 1; i < n; i++){
            String row = sc.nextLine();
            String[] row_ =row.split(" ");
            for(int j = 0; j < n +1; j++){
                matrix[i][j] = Float.parseFloat(row_[j]);
            }
        }
        if(sc.hasNext()){
            throw new IOException("");
        }
        return matrix;
    }
    public static void reduceToTriangularForm(float[][] A) throws ZeroDeterminantException {
        for(int i = 0; i < A.length; i++){
            int max = maxInColumn(A,i);
            if(i != max){
                float[] temp = A[i];
                A[i] = A[max];
                A[max] = temp;
            }
            for(int j = i+1; j < A.length; j++){
                if(isEqualToZero(A[i][i])){
                    throw new ZeroDeterminantException("the matrix is degenerate!");
                }
                    float mul = A[j][i] / A[i][i];
                    for(int k = 0; k < A[i].length; k++){
                        A[j][k] -= mul * A[i][k];
                    }
            }
        }
    }
    public static float[] findSolution(float[][] A){
        float[] X = new float[A.length];
        X[A.length - 1] = A[A.length -1][A.length] / A[A.length -1][A.length-1];
        for(int i = A.length -2; i >= 0; i--) {
            float sum = A[i][A.length];
            for(int j = A.length - 1; j >= 0; j--){
                sum -= A[i][j]*X[j];
            }
            X[i] = sum/A[i][i];
        }
        return X;
    }
    public static int maxInColumn(float[][] A, int column){
        float max = Math.abs(A[column][column]);
        int maxPos = column;
        for(int i = column+1; i < A.length; i++){
            if(Math.abs(A[i][column]) > max){
                max = Math.abs(A[i][column]);
                maxPos = i;
            }
        }
        return maxPos;
    }
    public static boolean isEqualToZero(float a){
        return Math.abs(a) < 1e-9;
    }
    private static class ZeroDeterminantException extends Exception{
        public ZeroDeterminantException(String message){
            super(message);
        }
    }
}


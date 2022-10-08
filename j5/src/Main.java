import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        File file = new File("file.txt");
        Series series;
        System.out.println("Enter 1 for linear, 2 for exponential");
        int var = scanner.nextInt();
        if (var == 1) {
            series = new Linear(1, 2, 3);
        } else if(var == 2){
            series = new Exponential(1, 2, 3);
        }
        System.out.println(series.toString());

    }
}

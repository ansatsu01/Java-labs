import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public abstract class Series {
    double first;
    double delta;
    int n;
    Series(double first, double delta, int n){
        this.first = first;
        this.delta = delta;
        this.n = n;
    }
    abstract double  findElement(int j);
    public double countSum(){
        double sum = 0;
        for(int i = 0; i < n; i++){
            sum += findElement(i+1);
        }
        return sum;
    }
    public String toString(){
        StringBuilder str = new StringBuilder();
        for(int i = 0; i < n; i++){
            str.append(Double.toString(findElement(i + 1)));
            str.append(" ");
        }
        return str.toString();
    }
    public void saveToFile(File file) throws IOException {
        FileWriter writer = new FileWriter(file);
        writer.write(toString());
        writer.close();
    }
}

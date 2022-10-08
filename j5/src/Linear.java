public class Linear extends Series {

    Linear(double first, double delta, int n){
        super(first, delta, n);
    }
    @Override
    double findElement(int j) {
        return first + (j - 1)*delta;
    }
    public double countSum(){
        return ((first + findElement(n))*n) / 2;
    }
}

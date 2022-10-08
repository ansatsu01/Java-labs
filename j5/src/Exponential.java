public class Exponential extends Series{
    Exponential(double first, double delta, int n){
        super(first, delta, n);
    }
    @Override
    double findElement(int j) {
        return first * Math.pow(delta, j - 1);
    }
    public double countSum(){
        return first*(1 - Math.pow(delta, n)) / (1 - delta);
    }
}

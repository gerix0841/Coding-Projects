public class Complex {
    double real;
    double imag;

    public Complex(double real,double imag){
        this.real = real;
        this.imag = imag;
    }

    public Complex(){
    }

    public double getReal(){
        return  real;
    }

    public double getImag(){
        return  imag;
    }

    public void setReal(double real){
        this.real = real;
    }

    public void setImag(double imag){
        this.imag = imag;
    }

    public String toString(){
        return "(" + String.valueOf(real) + "+" + String.valueOf(imag) + "i)";
    }

    public void set(Complex complex){
        this.real = complex.getReal();
        this.imag = complex.getImag();
    }

    public Complex add(Complex complex2){
        Complex complex = new Complex();
        double compReal = this.getReal()+complex2.getReal();
        double compImag = this.getImag()+complex2.getImag();
        complex.setReal(compReal);
        complex.setImag(compImag);

        return complex;
    }

    public Complex subtract(Complex complex2){
        Complex complex = new Complex();
        double compReal = this.getReal()-complex2.getReal();
        double compImag = this.getImag()-complex2.getImag();
        complex.setReal(compReal);
        complex.setImag(compImag);

        return complex;
    }

    public Complex multiply(Complex complex2){
        Complex complex = new Complex();
        double compReal = this.getReal()*complex2.getReal() - this.getImag()*complex2.getImag();
        double compImag = this.getImag()*complex2.getReal() + this.getReal()*complex2.getImag();
        complex.setReal(compReal);
        complex.setImag(compImag);

        return complex;
    }

    public double abs(){
        return Math.sqrt(this.getReal()*this.getReal()+this.getImag()*this.getImag());
    }
}

package util;

import org.apache.commons.math3.complex.Complex;

public class ComputationResults {
    Complex start_point;
    boolean converges;
    Complex root;


    public ComputationResults(Complex start_point, boolean converges, Complex root){
        this.start_point = start_point;
        this.converges = converges;
        this.root = root;
    }

    public Complex getStart_point() {
        return start_point;
    }

    public void setStart_point(Complex start_point) {
        this.start_point = start_point;
    }

    public boolean isConverges() {
        return converges;
    }

    public void setConverges(boolean converges) {
        this.converges = converges;
    }

    public Complex getRoot() {
        return root;
    }

    public void setRoot(Complex root) {
        this.root = root;
    }
}

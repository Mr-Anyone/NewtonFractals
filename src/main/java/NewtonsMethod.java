import derivative.Derivative;
import function.Function;
import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.complex.ComplexFormat;
import util.ComputationResults;

public class NewtonsMethod {
    public static double tolerance = 1e-7;
    public static double same_number_tolerance = 1e-3;

    public Complex getStart_point() {
        return start_point;
    }

    public void setStart_point(Complex start_point) {
        this.start_point = start_point;
    }

    private Complex start_point;
    private Function f;
    private Derivative d;

    NewtonsMethod(Complex start_point, Function f, Derivative d) {
        this.start_point = start_point;
        this.f = f;
        this.d = d;
    }

    static boolean is_same(Complex a, Complex b){
        if(a.subtract(b).abs() < same_number_tolerance){
            return true;
        }

        return false;
    }

    ComputationResults compute(int iteration_count){
        for(int i = 0; i<iteration_count; i++){
            if(d.at(start_point).equals(0)){
                return new ComputationResults (this.start_point, false, Complex.NaN);
            }

            Complex left_term = f.of(start_point).divide(d.at(start_point));
            start_point = start_point.subtract(left_term);

            if(f.of(start_point).abs() < tolerance){
                return new ComputationResults (this.start_point, true, start_point);
            }
        }

        return new ComputationResults(this.start_point, false, Complex.NaN);
    }

}

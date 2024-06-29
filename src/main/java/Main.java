import derivative.Derivative;
import function.Function;
import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import util.ColorGradientRGB;

import java.io.IOException;

public class Main {
    static public void main(String[] args) {
        System.out.println("The program has been started!");

        Function f = new Function() {
            @Override
            public Complex of(Complex x) {
                return x.multiply(x).multiply(x).add(-1); // x^3 - 1
            }
        };

        Derivative d = new Derivative() {
            @Override
            public Complex at(Complex x) {
                return x.multiply(x).multiply(3); // 3x^2;
            }
        };


        NewtonMethodGrid grid = new NewtonMethodGrid(1920, 1080, f, d);
        ColorGradientRGB gradient = new ColorGradientRGB(new Vector3D(1, 17, 37), new Vector3D(255, 255, 255), grid.numberOfUniqueRoots());

        try {
            grid.writeToImage(gradient);
        } catch (IOException e) {
            System.out.println("An error occured!");
        }

        System.out.println("I've finished executing!");
    }
}

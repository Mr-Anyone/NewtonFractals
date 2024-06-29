import derivative.Derivative;
import function.Function;
import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import org.apache.commons.math3.util.Pair;
import util.ColorGradientRGB;
import util.ComputationResults;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class NewtonMethodGrid {
    ComputationResults[][] computation_result;
    NewtonsMethod newtons_method;
    List<Complex> unique_roots;

    int width;
    int height;

    NewtonMethodGrid(int width, int height, Function f, Derivative d) {
        this.computation_result = new ComputationResults[height][width]; //  1920 x 1080, height is 1080
        this.width = width;
        this.height = height;
        this.newtons_method = new NewtonsMethod(new Complex(0, 0), f, d);
        this.unique_roots = new ArrayList<Complex>();

        generate(f, d);
    }

    boolean isNumberInList(Complex num) {
        if (num.isInfinite()) {
            return true;
        }

        if (num.isNaN()) {
            return true;
        }

        for (Complex root : unique_roots) {
            if (NewtonsMethod.is_same(root, num)) {
                return true;
            }
        }

        return false;
    }


    void generate(Function f, Derivative d) {
        for (int row = 0; row < height; ++row) {
            for (int col = 0; col < width; ++col) {

                Complex start_point = new Complex(row, col);
                this.newtons_method.setStart_point(start_point);
                ComputationResults result = this.newtons_method.compute(100);

                computation_result[row][col] = result;

                // pushing this to the list
                if (!isNumberInList(result.getRoot())) {
                    unique_roots.add(result.getRoot());
                }

            }
        }
    }

    int numberOfUniqueRoots() {
        return unique_roots.size();
    }

    void printToScreen() {
        // assuming of size 3

    }

    Optional<Integer> getRootIndex(ComputationResults result){
        int count = 0;

        for(Complex root : unique_roots){
            if(NewtonsMethod.is_same(root, result.getRoot())){
                return Optional.of(count);
            }

            ++count;
        }

        return Optional.empty();
    }

    void writeToImage(ColorGradientRGB gradient) throws  IOException {
        BufferedImage image = new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_RGB);
        for (int row = 0; row < this.height; ++row) {
            for(int col = 0; col < this.width; ++col) {
                Optional<Integer> index = getRootIndex(computation_result[row][col]);

                int color = 0;
                if(index.isPresent()) {
                    Vector3D vec_color = gradient.getColor(index.get());
                    int a = 255 << 24;

                    int r = (int) vec_color.getX() << 16;
                    int g = (int) vec_color.getY() << 8;
                    int b = (int) vec_color.getZ() << 0;
                    color += r +g + b + a;
                }

                image.setRGB(col, row, color);
            }
        }

        File file = new File("/tmp/testing.png");
        ImageIO.write(image, "png", file);

    }
}

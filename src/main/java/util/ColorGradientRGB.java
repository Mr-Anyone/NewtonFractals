package util;

import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

public class ColorGradientRGB {
    Vector3D start_color_hsv;
    Vector3D end_color_hsv;
    int elements_count = 0;

    public ColorGradientRGB(Vector3D start_color_hsv, Vector3D end_color_hsv, int elements_count) {
        this.start_color_hsv = start_color_hsv;
        this.end_color_hsv = end_color_hsv;
        this.elements_count = elements_count;

        assert elements_count >= 0;
    }

    public Vector3D getColor(int index){
        assert index >= 0;

        if(this.elements_count == 1){
            return start_color_hsv;
        }

        Vector3D slope = end_color_hsv.subtract(start_color_hsv).scalarMultiply(1.0/elements_count); // the slope (y2 - y1) / count
        return slope.scalarMultiply(index).add(start_color_hsv);
    }
}

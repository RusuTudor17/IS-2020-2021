package com.solid.l.good;

import com.solid.l.bad.BadRectangle;
import com.solid.l.bad.BadSquare;

import java.awt.*;

public class GoodGraphicsService {

    public void checkForArea(ShapeL rectangle) {
        int height = 20;
        int width = 5;
        rectangle.setHeight(height);
        rectangle.setWidth(width);

        if (rectangle instanceof GoodSquare) {
            verify(rectangle, width, width, "Square");
        } else {
            verify(rectangle, width, height, "Rectangle");
        }
    }

    private void verify(ShapeL rectangle, int width, int height, String entity) {
        if (rectangle.getArea() == (width * height)) {
            System.out.println("Great " + entity + " you got there!");
        } else {
            System.out.println("Well, not a " + entity + ".");
        }
    }

}

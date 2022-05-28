package creational.factory;

import java.util.Scanner;

interface Shape {
    void draw();
}

class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("Circle");
    }
}

class Rectangle implements Shape {
    @Override
    public void draw() {
        System.out.println("Rectangle");
    }
}

class Square implements Shape {
    @Override
    public void draw() {
        System.out.println("Square");
    }
}

class Triangle implements Shape {
    @Override
    public void draw() {
        System.out.println("Triangle");
    }
}

enum AvailableShape {
    CIRCLE,RECTANGLE,SQUARE,TRIANGLE
}

abstract class ShapeFactory {
    public static Shape getShape(AvailableShape type ){
        switch (type) {
            case CIRCLE: return new Circle();
            case SQUARE: return new Square();
            case RECTANGLE: return new Rectangle();
            case TRIANGLE: return new Triangle();
            default: throw new IllegalArgumentException("Invalid shape type");
        }
    }
}


public class FactoryPatternDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AvailableShape[] shapes = AvailableShape.values();
        loop: while (true){
            int i=1;
            System.out.println("Enter type of shape");
            for (AvailableShape shapeTypes: shapes){
                System.out.println((i++)+". " + shapeTypes);
            }
            System.out.println("5. '-1' to exit.");
            int input = sc.nextInt();
            switch (input) {
                case -1: break loop;
                case 1: ShapeFactory.getShape(shapes[0]).draw(); break;
                case 2: ShapeFactory.getShape(shapes[1]).draw(); break;
                case 3: ShapeFactory.getShape(shapes[2]).draw(); break;
                case 4: ShapeFactory.getShape(shapes[3]).draw(); break;
                default: throw new IllegalArgumentException("Invalid input.");
            }
        }
        sc.close();
    }
}

package creational.abstractfactory;

enum AvailableShape {
    CIRCLE,
    ELLIPSE,
    RECTANGLE,
    SQUARE,
    EUILATERAL_TRIANGLE,
    RIGHT_TRIANGLE
}

enum Factory {
    ROUND,
    QUAD,
    TRI
}

interface Shape {
    void draw();
}

class Ellipse implements Shape {
    @Override
    public void draw() {
        System.out.println("Ellipse");
    }
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

class EquilateralTriangle implements Shape {
    @Override
    public void draw() {
        System.out.println("EquilateralTriangle");
    }
}

class RightTriangle implements Shape {
    @Override
    public void draw() {
        System.out.println("RightTriangle");
    }
}

interface ShapeFactory {
    public Shape getShape(AvailableShape type);
}
class QuadShapeFactory implements ShapeFactory {
    public Shape getShape(AvailableShape type){
        switch (type) {
            case SQUARE: return new Square();
            case RECTANGLE: return new Rectangle();
            default: throw new IllegalArgumentException("Invalid shape type");
        }
    }
}

class RoundShapeFactory implements ShapeFactory {
    public Shape getShape(AvailableShape type){
        switch (type) {
            case CIRCLE: return new Circle();
            case ELLIPSE: return new Ellipse();
            default: throw new IllegalArgumentException("Invalid shape type");
        }
    }
}

class TriShapeFactory implements ShapeFactory {
    public Shape getShape(AvailableShape type){
        switch (type) {
            case EUILATERAL_TRIANGLE: return new EquilateralTriangle();
            case RIGHT_TRIANGLE: return new RightTriangle();
            default: throw new IllegalArgumentException("Invalid shape type");
        }
    }
}

abstract class FactoryProducer {
    public static ShapeFactory getFactory(Factory factory) {
        switch (factory) {
            case ROUND: return new RoundShapeFactory();
            case QUAD: return new QuadShapeFactory();
            case TRI: return new TriShapeFactory();
            default: throw new IllegalArgumentException("Invalid factory type");
        }
    }

}

public class AbstractFactoryPatternDemo {
    public static void main(String[] args) {

        FactoryProducer.getFactory(Factory.QUAD).getShape(AvailableShape.RECTANGLE).draw();
        FactoryProducer.getFactory(Factory.QUAD).getShape(AvailableShape.SQUARE).draw();

        FactoryProducer.getFactory(Factory.ROUND).getShape(AvailableShape.CIRCLE).draw();
        FactoryProducer.getFactory(Factory.ROUND).getShape(AvailableShape.ELLIPSE).draw();

        FactoryProducer.getFactory(Factory.TRI).getShape(AvailableShape.EUILATERAL_TRIANGLE).draw();
        FactoryProducer.getFactory(Factory.TRI).getShape(AvailableShape.RIGHT_TRIANGLE).draw();


    }
}

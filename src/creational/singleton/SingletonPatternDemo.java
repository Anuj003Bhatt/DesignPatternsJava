package creational.singleton;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Singleton with static factory method.
 */
class SimpleSingleton {
    private static final SimpleSingleton INSTANCE = new SimpleSingleton();

    private SimpleSingleton() {}

    public static SimpleSingleton getInstance() {
        return SimpleSingleton.INSTANCE;
    }

}

/**
 * Singleton with lazy initialization. Useful in case the class is complex and large and have large creation
 * expenses.
 */
class SingletonLazy {
    private static SingletonLazy INSTANCE;

    private SingletonLazy() {}

    public static SingletonLazy getInstance() {
        if (INSTANCE == null) {
            synchronized (SingletonLazy.class) {
                INSTANCE = new SingletonLazy();
            }

        }
        return INSTANCE;
    }
}

/**
 * Singleton with lazy initialization and it's problems.
 * Deserialization creates a new object again and hence the 2 instances are never equal.
 *
 * This violates the singleton property as well as we have created 2 objects.
 */
class SingletonProblemWithDeserialization implements Serializable {
    private static SingletonProblemWithDeserialization INSTANCE;
    private Integer value = 10;

    public void setValue(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }


    private SingletonProblemWithDeserialization() {}

    public static SingletonProblemWithDeserialization getInstance() {
        if (INSTANCE == null) {
            synchronized (SingletonLazy.class) {
                INSTANCE = new SingletonProblemWithDeserialization();
            }

        }
        return INSTANCE;
    }

    /**
     *
     * Solution to this serialization-deserialization problem is the readResolve method which is called just beore
     * returning the object to the caller.
     *
     * @return
     */
    protected Object readResolve() {
        return INSTANCE;
    }

//    public static void main(String[] args) throws IOException, ClassNotFoundException {
//        // Serializing the singleton object
//        SingletonProblemWithDeserialization object = SingletonProblemWithDeserialization.getInstance();
//        try (
//                FileOutputStream fos = new FileOutputStream("out.obj");
//                ObjectOutputStream oos = new ObjectOutputStream(fos);
//        ) {
//            oos.writeObject(object);
//        }
//
//        object.setValue(12);
//
//        // De-serializing the singleton object
//        SingletonProblemWithDeserialization object2 = null;
//        try (
//                FileInputStream fos = new FileInputStream("out.obj");
//                ObjectInputStream oos = new ObjectInputStream(fos);
//        ) {
//            object2 = (SingletonProblemWithDeserialization)oos.readObject();
//        }
//
//        if (object == object2) {
//            System.out.println("Equal");
//        } else {
//            System.out.println("Not Equal");
//        }
//
//        System.out.println(object.getValue());
//        System.out.println(object2.getValue());
//    }

}

/**
 * Singleton with lazy initialization and it's problems.
 * Deserialization creates a new object again and hence the 2 instances are never equal.
 *
 * This violates the singleton property as well as we have created 2 objects.
 */
class SingletonProblemWithReflection implements Serializable {
    private static SingletonProblemWithReflection INSTANCE = new SingletonProblemWithReflection();
    private Integer value = 10;

    public void setValue(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public static SingletonProblemWithReflection getInstance() {
        return INSTANCE;
    }



    private SingletonProblemWithReflection() {
    }

//    /**
//     *  Test function
//      */
//    public static void main(String[] args) {
//        SingletonProblemWithReflection object1 = SingletonProblemWithReflection.getInstance();
//
//        Constructor constructor = SingletonProblemWithReflection.class.getDeclaredConstructor(new Class[0]);
//        constructor.setAccessible(true);
//
//        SingletonProblemWithReflection object2 = (SingletonProblemWithReflection)constructor.newInstance();
//
//        if (object1 == object2) {
//            System.out.println("Singleton Achieved");
//        } else {
//            System.out.println("Singleton pattern broken");
//        }
//    }

}

/**
 * Implementing singletons with enums
 *
 * Enums do not serialize data members so if this class instance is deserialized then the
 * value of the integer data member is lost.
 */
enum SingletonWithEnum {
    INSTANCE;

    private Integer value=10;

    public void setValue(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return this.value;
    }
}

public class SingletonPatternDemo {

    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        SingletonWithEnum singleton = SingletonWithEnum.INSTANCE;
        System.out.println(singleton.getValue());

    }
}

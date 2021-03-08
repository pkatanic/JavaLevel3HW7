import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.TreeMap;

public class Main {
    public static void start(Class<?> className) {
        final int MIN_PRIORITY = 1;
        final int MAX_PRIORITY = 10;
        Map<Integer, Method> map = new TreeMap<>();

        for (Method method : className.getDeclaredMethods()) {
            if (method.getAnnotation(BeforeSuite.class) != null) {
                map.put(MIN_PRIORITY - 1, method);
            }
            if (method.getAnnotation(AfterSuite.class) != null) {
                map.put(MAX_PRIORITY + 1, method);
            }
            if (method.getAnnotation(Test.class) != null) {
                Test test = method.getAnnotation(Test.class);
                map.put(test.priority(), method); // сортировка автоматом
            }
        }

        System.out.println("Map:");
        for (Integer key : map.keySet()) {
            System.out.println("priority:" + key + " " + map.get(key).getName());
        }

        try {
            TestClass testCase = new TestClass(); // объект для Reflections
            for (Integer key : map.keySet()) {
                map.get(key).invoke(testCase);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args) {
        System.out.println("Annotations analyze:");
        start(TestClass.class);
    }
}

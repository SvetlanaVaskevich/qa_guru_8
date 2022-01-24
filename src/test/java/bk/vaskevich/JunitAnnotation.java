package bk.vaskevich;


import org.junit.jupiter.api.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class JunitAnnotation {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        for (Method declaredMethod:NumberComparisonTest.class.getDeclaredMethods()) {
            BeforeEach beforeEach = declaredMethod.getAnnotation(BeforeEach.class);
            AfterEach afterEach = declaredMethod.getAnnotation(AfterEach.class);
            Test test = declaredMethod.getAnnotation(Test.class);
            BeforeAll beforeAll = declaredMethod.getAnnotation(BeforeAll.class);
            AfterAll afterAll = declaredMethod.getAnnotation(AfterAll.class);
            if(test!=null){
                try{
                    Object obj = declaredMethod.invoke(
                            NumberComparisonTest.class.getConstructor().newInstance()
                    );
                    if(beforeAll!=null){
                        NumberComparisonTest.initAll();
                    }
                    if(beforeEach!=null){
                        NumberComparisonTest.class.getConstructor().newInstance().initEach();
                    }
                    if(afterEach!=null){
                        NumberComparisonTest.class.getConstructor().newInstance().closeEach();
                    }
                    if(afterAll!=null){
                        NumberComparisonTest.closeAll();
                    }
                }catch(InvocationTargetException e){
                    if(e.getCause() instanceof AssertionError){
                        System.out.println("Test " + declaredMethod.getName() + " failed " + e.getCause().getMessage());
                        continue;
                    }
                    else{
                        System.out.println("Test " + declaredMethod.getName() + " broken " + e.getCause().getMessage());
                        continue;
                    }
                }
                System.out.println("Test " + declaredMethod.getName() + " passed");
            }
        }
    }
}

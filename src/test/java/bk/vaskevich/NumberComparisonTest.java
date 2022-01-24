package bk.vaskevich;

import org.junit.jupiter.api.*;

public class NumberComparisonTest {

    @BeforeAll
    public static void initAll(){
        System.out.println("@BeforeAll");
    }

    @BeforeEach
    public void initEach(){
        System.out.println("@BeforeEach");
    }
    @Test
    public void comparisonTwoNumber1Test(){
        Assertions.assertTrue(10>7);
    }

    @Test
    public void comparisonTwoNumber2Test(){
        Assertions.assertTrue(25>38);
    }

    @AfterEach
    public void closeEach(){
        System.out.println("@AfterEach");
    }

    @AfterAll
    public static void closeAll(){
        System.out.println("@AfterAll");
    }
}

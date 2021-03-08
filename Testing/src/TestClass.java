public class TestClass {
    @BeforeSuite
    public void methodBefore() {
        System.out.println("TestCase.methodBefore");
    }
    @AfterSuite
    public void methodAfter() {
        System.out.println("TestCase.methodAfter");
    }

    @Test(priority = 1)
    public void methodTest1() {
        System.out.println("TestCase.methodTest1 priority 1 (highest)");
    }

    @Test(priority = 5)
    public void methodTest2() {
        System.out.println("TestCase.methodTest2 priority 5 (lowest)");
    }

    @Test(priority = 2)
    public void methodTest3() {
        System.out.println("TestCase.methodTest3 priority 2(middle)");
    }


    public void commonMethod1() {
        System.out.println("TestCase.commonMethod1");
    }
    public void commonMethod2() {
        System.out.println("TestCase.commonMethod2");
    }
}

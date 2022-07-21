public class TestClass {

    public TestClass() {


        String str = "Akshay";
        int length = 0;

        while (true) {
            try {
                char c = str.charAt(length);
            }
            catch (StringIndexOutOfBoundsException e) {
                break;
            }
            length++;
        }
        System.out.println(length);


    }



    public static void main (String[] args) {
        TestClass testClass = new TestClass();
    }
}

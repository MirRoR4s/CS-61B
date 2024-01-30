public class Client {
    public static void main(String[] args) {
        Monkey jimmy = new Monkey("Jimmy");
        // Dog limmy = (Dog) jimmy;

        /*
         * 第三题代码块
         */
        Monkey orangutan = new Monkey("fruitful");
        Dog mangotan = (Dog)(Animal) orangutan;

    }
}

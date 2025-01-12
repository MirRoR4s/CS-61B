public class Dog {
    public void bark() {
        System.out.println("Moo");
    }

    public static void runFast() {
        System.out.println("Ruff Run");
    }

    public static void main(String[] args) {
        Dog poppa = new Dog();
        poppa.bark();
        // Dog.bark();
        poppa.runFast();
        Dog.runFast();
    }
}
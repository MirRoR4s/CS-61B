public class Cat extends Animal{
    
    public Cat(String name, int age) {
        super(name, age);
        this.noise = "Meow!";
    }
    @Override
    public void greet() {
        System.out.println("Cat " + name + " says: " + makeNoise());
    }
    public static void main(String[] args) {
        Animal cat1 = new Cat("Tom", 5);
        cat1.greet();
        Animal cat = new Cat("Tom", 4);
        cat.greet();
    }
}

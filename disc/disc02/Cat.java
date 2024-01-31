public class Cat {
    public String name;
    // 注意这是一个静态的字段，属于类而非其实例。
    public static String noise;

    public Cat(String name, String noise) {
        this.name = name;
        this.noise = noise;
    }

    public void play() {
        System.out.println(noise + "I'm " + name + " the cat!");
    }

    public static void anger() {
        noise = noise.toUpperCase();
    }

    public static void calm() {
        noise = noise.toLowerCase();
    }

    public static void main(String[] args) {
        Cat a = new Cat("Cream", "Meow!");
        Cat b = new Cat("Tubbs", "Nyan!");
        // Nyan! I'm Cream the cat!
        a.play();
        // Nyan! I'm Tubbs the cat!
        b.play();
        // a.noise = NYAN! b.noise = NYAN!
        Cat.anger();
        // a.noise = nyan! b.noise = nyan!
        a.calm();
        // nyan! I'm Cream the cat!
        a.play();
        // nyan! I'm Tubbs the cat!
        b.play();
    }
}

/**
 * @see https://sp18.datastructur.es/materials/discussion/disc02.pdf
 */
public class Pokemon {
    public String name;
    public int level;

    public Pokemon(String name, int level) {
        this.name = name;
        this.level = level;
    }

    public static void main(String[] args) {
        Pokemon p = new Pokemon("Pikachu", 17);
        int level = 100;
        change(p, level);
        //Pikachu 100
        System.out.println("Name: " + p.name + ", Level: " + p.level);
    }

    public static void change(Pokemon poke, int level) {
        poke.level = level;
        // 这里修改的是 change 方法的局部变量
        level = 50;
        poke = new Pokemon("Gengar", 1);
    }
}


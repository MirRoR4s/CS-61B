// public class Dog implements OurComparable{
public class Dog implements Comparable<Dog>{

    private String name;
    private int size;

    public Dog(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public void bark() {
        System.out.println("bark");
    }

    public void barkMany(int N) {
        for (int i = 0; i < N; i += 1) {
            bark();
        }
    }

    public int compareTo(Dog o) {
        return this.size - o.size;
        // if (this.size > tmpDog.size) {
        //     return 1;
        // }
        // else if (this.size < tmpDog.size) {
        //     return -1;
        // }
        // return 0;
    }
}

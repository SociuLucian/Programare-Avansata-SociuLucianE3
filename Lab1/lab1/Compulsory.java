package lab1;

// Sociu Lucian E3
public class Compulsory {

    public static void main(String[] args) {
        System.out.println("Hello world");
        String[] languages = new String[]{"C", "C++", "C#", "Python", "Go", "Rust", "JavaScript", "PHP", "Swift", "Java"};
        int n = (int) (Math.random() * 1_000_000);
        n *= 3;
        n += Integer.parseInt("10101", 2);
        n += Integer.parseInt("FF", 16);
        n *= 6;
        System.out.println(n);
        int m = 0;
        m = sumofdigits(n);
        while (m > 9) {
            m = sumofdigits(m);
        }
        System.out.println(m);
        System.out.println("Willy-nilly, this semester I will learn " + languages[m]);
    }

    public static int sumofdigits(int aux) {
        int nr = aux;
        int s = 0;
        while (nr != 0) {
            s += nr % 10;
            nr = nr / 10;
        }
        return s;
    }
}


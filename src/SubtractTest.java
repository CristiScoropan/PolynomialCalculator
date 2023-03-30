public class SubtractTest {
    public static void main(String[] args) {
        Polynomial p1 = new Polynomial();
        p1.addTerm(0, 4.0);

        Polynomial p2 = new Polynomial();
        p2.addTerm(0, 5.0);

        Polynomial sum = Operations.subtract(p1, p2);
        System.out.println(sum);
    }
}

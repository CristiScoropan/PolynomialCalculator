public class MultiplyTest {
    public static void main(String[] args) {
        Polynomial p1 = new Polynomial();
        p1.addTerm(1, 1.0);

        Polynomial p2 = new Polynomial();
        p2.addTerm(0, 0.0);

        Polynomial multiplication = Operations.multiply(p1, p2);
        System.out.println(multiplication);
    }
}

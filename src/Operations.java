import java.util.Map;
import java.util.*;

public class Operations {
    public static Polynomial add(Polynomial p1, Polynomial p2) {
        Polynomial result = new Polynomial();

        for (Map.Entry<Integer, Double> term : p1.terms.entrySet()) {
            int grade1 = term.getKey();
            double coefficient1 = term.getValue();
            result.addTerm(grade1, coefficient1);
        }

        for (Map.Entry<Integer, Double> term : p2.terms.entrySet()) {
            int grade2 = term.getKey();
            double coefficient2 = term.getValue();
            result.addTerm(grade2, coefficient2);
        }

        return result;
    }
    public static Polynomial subtract(Polynomial p1, Polynomial p2) {
        Polynomial result = new Polynomial();
        for (Map.Entry<Integer, Double> term : p1.terms.entrySet())
        {
            int grade1 = term.getKey();
            double coefficient1 = term.getValue();
            result.addTerm(grade1,coefficient1);
        }
        for(Map.Entry<Integer, Double> term: p2.terms.entrySet())
        {
            int grade2 = term.getKey();
            double coefficient2 = term.getValue() * -1;
            result.addTerm(grade2,coefficient2);
        }
        return result;
    }

    public static Polynomial multiply(Polynomial p1, Polynomial p2) {
        Polynomial result = new Polynomial();

        for (Map.Entry<Integer, Double> term1 : p1.terms.entrySet())
        {
            int power1 = term1.getKey();
            double coefficient1 = term1.getValue();
            for (Map.Entry<Integer, Double> term2 : p2.terms.entrySet())
            {
                int power2 = term2.getKey();
                double coefficient2 = term2.getValue();
                int power = power1 + power2;
                double coefficient = coefficient1 * coefficient2;
                double existingCoefficient = result.terms.containsKey(power) ? result.terms.get(power) : 0;
                result.addTerm(power, coefficient + existingCoefficient);
            }
        }
        return result;
    }

    public Polynomial derivative(Polynomial p1) {
        Polynomial result = new Polynomial();
        for (Map.Entry<Integer, Double> term : p1.terms.entrySet()) {
            int power = term.getKey();
            double coefficient = term.getValue();
            if (power > 0) {
                result.addTerm(power - 1, coefficient * power);
            }
        }
        return result;
    }

    public static Polynomial integrate(Polynomial p1) {
        Polynomial result = new Polynomial();
        for (Map.Entry<Integer,Double> term: p1.terms.entrySet()) {
            double coefficient = term.getValue() / (term.getKey() + 1);
            int power = term.getKey() + 1;
            result.addTerm(power, coefficient);
        }
        return result;
    }


}








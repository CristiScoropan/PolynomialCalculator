import java.util.*;

public class Polynomial {
    public TreeMap<Integer, Double> terms;

    public Polynomial()
    {
        terms = new TreeMap<>();
    }

    public void addTerm(int degree, double coefficient) {
        if (coefficient != 0)
        {
            double existingCoefficient = terms.getOrDefault(degree, 0.0);
            double newCoefficient = existingCoefficient + coefficient;
            if (newCoefficient == 0.0)
            {
                terms.remove(degree);
            } else {
                terms.put(degree, newCoefficient);
            }
        }
    }

    @Override
    public String toString() {
        if (terms.isEmpty()) {
            return "0";
        }
        if (terms.size() == 1 && terms.containsValue(0.0)) {
            return "0";
        }
        StringBuilder builder = new StringBuilder();
        List<Integer> degrees = new ArrayList<>(terms.keySet());
        degrees.sort(Comparator.reverseOrder());
        for (int degree : degrees) {
            double coefficient = terms.get(degree);
            if (coefficient > 0) {
                builder.append("+");
            }
            builder.append(coefficient);
            if (degree > 1) {
                builder.append("x^").append(degree);
            } else if (degree == 1) {
                builder.append("x");
            }
        }
        String result = builder.toString();
        if (result.startsWith("+")) {
            result = result.substring(1);
        }
        return result;
    }

}

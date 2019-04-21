package utility;

import problem_domain.Variable;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Solution {
    List<Integer> solutions;

    public Solution(List<Integer> solutions) {
        this.solutions = solutions;
    }

    public static List<Solution> readSolutions(String filename) throws IOException {
        try(BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            List<Solution> solutions = new ArrayList<>();

            while ((line = reader.readLine()) != null) {
                List<Integer> assignments = new ArrayList<>();

                System.out.println(line);
                String[] s = line.split(" ");

                for (String assignment : s) {
                    assignments.add(Integer.parseInt(assignment));
                }
                solutions.add(new Solution(assignments));
            }


            return solutions;
        }
    }

    public static void main(String[] args) {
        try {
            List<Solution> solutions = Solution.readSolutions("solutions2/4Queens.csp");
            System.out.println(solutions);

            Variable variable1 = new Variable(0, new TreeSet<>(Collections.singletonList(1)));
            Variable variable2 = new Variable(1, new TreeSet<>(Collections.singletonList(3)));
            Variable variable3 = new Variable(2, new TreeSet<>(Collections.singletonList(0)));
            Variable variable4 = new Variable(3, new TreeSet<>(Collections.singletonList(2)));

            HashMap<Integer, Variable> varSpace = new HashMap<>();
            varSpace.put(0, variable1);
            varSpace.put(1, variable2);
            varSpace.put(2, variable3);
            varSpace.put(3, variable4);

            Solution solution = new Solution(new ArrayList<>(Arrays.asList(1, 3, 0, 2)));
            boolean b = solution.meetsSolution(varSpace);
            System.out.println(b);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Solution{" +
                "solutions=" + solutions +
                '}';
    }

    public boolean meetsSolution(HashMap<Integer, Variable> variables) {

        if(variables.size() != solutions.size()) {
            return false;
        }

        for(int i = 0 ; i < solutions.size(); i++) {
            TreeSet<Integer> domain = variables.get(i).getDomain();
            if(domain.size() != 1) {
                return false;
            } else {
                if(!domain.first().equals(solutions.get(i))) {
                    return false;
                }
            }
        }

        return true;
    }
}

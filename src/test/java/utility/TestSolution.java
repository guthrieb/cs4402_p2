package utility;

import org.junit.Assert;
import org.junit.Test;
import problem_domain.Variable;

import java.io.IOException;
import java.util.*;

public class TestSolution {

    public static final String SOLUTIONS_4_QUEENS_CSP = "solutions/4Queens.csp";

    @Test
    public void basicTest() throws IOException {

        List<Solution> solutions = Solution.readSolutions(SOLUTIONS_4_QUEENS_CSP);
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
        Assert.assertTrue(solution.meetsSolution(varSpace));


    }

    @Test
    public void basicTest2() throws IOException {

        List<Solution> solutions = Solution.readSolutions(SOLUTIONS_4_QUEENS_CSP);
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

        Solution solution = new Solution(new ArrayList<>(Arrays.asList(1, 2, 0, 2)));
        Assert.assertFalse(solution.meetsSolution(varSpace));


    }

    @Test
    public void basicTest3() throws IOException {

        List<Solution> solutions = Solution.readSolutions(SOLUTIONS_4_QUEENS_CSP);
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

        Solution solution = new Solution(new ArrayList<>(Arrays.asList(1, 3, 0)));
        Assert.assertFalse(solution.meetsSolution(varSpace));


    }

    @Test
    public void basicTest4() throws IOException {

        List<Solution> solutions = Solution.readSolutions(SOLUTIONS_4_QUEENS_CSP);
        System.out.println(solutions);

        Variable variable1 = new Variable(0, new TreeSet<>(Arrays.asList(1, 2)));
        Variable variable2 = new Variable(1, new TreeSet<>(Collections.singletonList(3)));
        Variable variable3 = new Variable(2, new TreeSet<>(Collections.singletonList(0)));
        Variable variable4 = new Variable(3, new TreeSet<>(Collections.singletonList(2)));

        HashMap<Integer, Variable> varSpace = new HashMap<>();
        varSpace.put(0, variable1);
        varSpace.put(1, variable2);
        varSpace.put(2, variable3);
        varSpace.put(3, variable4);

        Solution solution = new Solution(new ArrayList<>(Arrays.asList(1, 3, 0, 2)));
        Assert.assertFalse(solution.meetsSolution(varSpace));


    }
}

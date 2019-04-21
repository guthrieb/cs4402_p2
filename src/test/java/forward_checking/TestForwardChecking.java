package forward_checking;

import branching.TwoWayBranching;
import meta.Results;
import meta.VarSpaceBuilder;
import org.junit.Assert;
import org.junit.Test;
import premade.BinaryCSPReader;
import problem_domain.Variable;
import problem_domain.VariableSpace;
import utility.Solution;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class TestForwardChecking {
    private static final String SOLUTION_DIR = "solutions2/";
    private static final String PROBLEMS_DIR = "problems/";
    private static final String QUEENS_4 = "4Queens.csp";
    private static final String QUEENS_6 = "6Queens.csp";
    private static final String QUEENS_8 = "8Queens.csp";
    private static final String FINNISH = "FinnishSudoku.csp";
    private static final String SIMONIS = "SimonisSudoku.csp";
    private static final String LANGFORD2_3 = "langfords2_3.csp";
    private static final String LANGFORD2_4 = "langfords2_4.csp";
    private static final String LANGFORD3_9 = "langfords3_9.csp";
    private static final String LANGFORD3_10 = "langfords3_10.csp";

    private static String getSolutionStr(String file) {
        return SOLUTION_DIR + file;
    }

    private static String getProblemStr(String file) {
        return PROBLEMS_DIR + file;
    }

    @Test
    public void basicSatisfactionQueens4() throws IOException {
        List<Solution> solutionList = Solution.readSolutions(getSolutionStr(QUEENS_4));

        BinaryCSPReader reader = new BinaryCSPReader() ;
        VariableSpace variableSpace = VarSpaceBuilder.buildVariableSpace(reader.readBinaryCSP(getProblemStr(QUEENS_4)));

        Results branch = TwoWayBranching.branch(variableSpace, true, new Results(false));

        Assert.assertNotNull(branch);
        HashMap<Integer, Variable> variables = branch.getResults().getVariables();

        boolean satisfies = false;
        for (Solution solution : solutionList) {
            if (solution.meetsSolution(variables)) {
                satisfies = true;
            }
        }

        Assert.assertTrue(satisfies);
    }

    @Test
    public void basicSatisfactionQueens6() throws IOException {
        List<Solution> solutionList = Solution.readSolutions(getSolutionStr(QUEENS_6));

        BinaryCSPReader reader = new BinaryCSPReader() ;
        VariableSpace variableSpace = VarSpaceBuilder.buildVariableSpace(reader.readBinaryCSP(getProblemStr(QUEENS_6)));

        Results branch = TwoWayBranching.branch(variableSpace, true, new Results(false));

        Assert.assertNotNull(branch);
        HashMap<Integer, Variable> variables = branch.getResults().getVariables();

        boolean satisfies = false;
        System.out.println();
        for (Solution solution : solutionList) {
            if (solution.meetsSolution(variables)) {
                satisfies = true;
            }
        }

        Assert.assertTrue(satisfies);
    }

    @Test
    public void basicSatisfactionQueens8() throws IOException {
        List<Solution> solutionList = Solution.readSolutions(getSolutionStr(QUEENS_8));

        BinaryCSPReader reader = new BinaryCSPReader() ;
        VariableSpace variableSpace = VarSpaceBuilder.buildVariableSpace(reader.readBinaryCSP(getProblemStr(QUEENS_8)));

        Results branch = TwoWayBranching.branch(variableSpace, true, new Results(false));

        Assert.assertNotNull(branch);
        HashMap<Integer, Variable> variables = branch.getResults().getVariables();

        boolean satisfies = false;
        for (Solution solution : solutionList) {
            if (solution.meetsSolution(variables)) {
                satisfies = true;
            }
        }

        Assert.assertTrue(satisfies);
    }

    @Test
    public void basicSatisfactionFinnish() throws IOException {
        List<Solution> solutionList = Solution.readSolutions(getSolutionStr(FINNISH));

        BinaryCSPReader reader = new BinaryCSPReader() ;
        VariableSpace variableSpace = VarSpaceBuilder.buildVariableSpace(reader.readBinaryCSP(getProblemStr(FINNISH)));

        Results branch = TwoWayBranching.branch(variableSpace, true, new Results(false));

        Assert.assertNotNull(branch);
        HashMap<Integer, Variable> variables = branch.getResults().getVariables();

        boolean satisfies = false;
        for (Solution solution : solutionList) {
            if (solution.meetsSolution(variables)) {
                satisfies = true;
            }
        }

        Assert.assertTrue(satisfies);
    }

    @Test
    public void basicSatisfactionSimonis() throws IOException {
        List<Solution> solutionList = Solution.readSolutions(getSolutionStr(SIMONIS));

        BinaryCSPReader reader = new BinaryCSPReader() ;
        VariableSpace variableSpace = VarSpaceBuilder.buildVariableSpace(reader.readBinaryCSP(getProblemStr(SIMONIS)));

        Results branch = TwoWayBranching.branch(variableSpace, true, new Results(false));

        Assert.assertNotNull(branch);
        HashMap<Integer, Variable> variables = branch.getResults().getVariables();

        boolean satisfies = false;
        for (Solution solution : solutionList) {
            if (solution.meetsSolution(variables)) {
                satisfies = true;
            }
        }

        Assert.assertTrue(satisfies);
    }

    @Test
    public void basicSatisfactionLangford23() throws IOException {
        List<Solution> solutionList = Solution.readSolutions(getSolutionStr(LANGFORD2_3));

        BinaryCSPReader reader = new BinaryCSPReader() ;
        VariableSpace variableSpace = VarSpaceBuilder.buildVariableSpace(reader.readBinaryCSP(getProblemStr(LANGFORD2_3)));

        Results branch = TwoWayBranching.branch(variableSpace, true, new Results(false));

        Assert.assertNotNull(branch);
        HashMap<Integer, Variable> variables = branch.getResults().getVariables();

        boolean satisfies = false;
        for (Solution solution : solutionList) {
            if (solution.meetsSolution(variables)) {
                satisfies = true;
            }
        }

        Assert.assertTrue(satisfies);
    }

    @Test
    public void basicSatisfactionLangford24() throws IOException {
        List<Solution> solutionList = Solution.readSolutions(getSolutionStr(LANGFORD2_4));

        BinaryCSPReader reader = new BinaryCSPReader() ;
        VariableSpace variableSpace = VarSpaceBuilder.buildVariableSpace(reader.readBinaryCSP(getProblemStr(LANGFORD2_4)));

        Results branch = TwoWayBranching.branch(variableSpace, true, new Results(false));

        Assert.assertNotNull(branch);
        HashMap<Integer, Variable> variables = branch.getResults().getVariables();

        boolean satisfies = false;
        for (Solution solution : solutionList) {
            if (solution.meetsSolution(variables)) {
                satisfies = true;
            }
        }

        Assert.assertTrue(satisfies);
    }

    @Test
    public void basicSatisfactionLangford39() throws IOException {
        List<Solution> solutionList = Solution.readSolutions(getSolutionStr(LANGFORD3_9));

        BinaryCSPReader reader = new BinaryCSPReader() ;
        VariableSpace variableSpace = VarSpaceBuilder.buildVariableSpace(reader.readBinaryCSP(getProblemStr(LANGFORD3_9)));

        Results branch = TwoWayBranching.branch(variableSpace, true, new Results(false));

        Assert.assertNotNull(branch);
        HashMap<Integer, Variable> variables = branch.getResults().getVariables();

        boolean satisfies = false;
        for (Solution solution : solutionList) {
            if (solution.meetsSolution(variables)) {
                satisfies = true;
            }
        }

        Assert.assertTrue(satisfies);
    }

//    @Test
    public void basicSatisfactionLangford310() throws IOException {
        List<Solution> solutionList = Solution.readSolutions(getSolutionStr(LANGFORD3_10));

        BinaryCSPReader reader = new BinaryCSPReader() ;
        VariableSpace variableSpace = VarSpaceBuilder.buildVariableSpace(reader.readBinaryCSP(getProblemStr(LANGFORD3_10)));

        Results branch = TwoWayBranching.branch(variableSpace, true, new Results(false));

        Assert.assertNotNull(branch);
        HashMap<Integer, Variable> variables = branch.getResults().getVariables();

        boolean satisfies = false;
        for (Solution solution : solutionList) {
            if (solution.meetsSolution(variables)) {
                satisfies = true;
            }
        }

        Assert.assertTrue(satisfies);
    }



}

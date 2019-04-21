package meta;

import branching.TwoWayBranching;
import premade.BinaryCSPReader;
import problem_domain.VariableSpace;

public class TestMain {
    public static void main(String[] args) {
        BinaryCSPReader reader = new BinaryCSPReader();

        VariableSpace variableSpace = VarSpaceBuilder.buildVariableSpace(reader.readBinaryCSP("problems/FinnishSudoku.csp"));
        Results results1 = new Results(false);

        Results results = TwoWayBranching.branch(variableSpace, false, results1);
        System.out.println(results.getTimeTaken());
        System.out.println(results.getAssignments());
    }
}

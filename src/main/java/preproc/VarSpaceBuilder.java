package preproc;

import premade.BinaryCSP;
import premade.BinaryCSPReader;
import premade.BinaryConstraint;
import premade.BinaryTuple;
import problem_domain.Variable;
import problem_domain.VariableSpace;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

public class VarSpaceBuilder {
    public static VariableSpace buildVariableSpace(BinaryCSP csp) {
        List<Variable> variables = getVariables(csp);
        HashMap<Integer, HashMap<Integer, List<BinaryTuple>>> constraints = getConstraints(csp);


        VariableSpace variableSpace = new VariableSpace(variables, constraints);

        return variableSpace;
    }

    private static List<Variable> getVariables(BinaryCSP csp) {
        List<Variable> variables = new ArrayList<>();

        for (int i = 0; i < csp.getNoVariables(); i++) {
            int lb = csp.getLB(i);
            int ub = csp.getUB(i);

            TreeSet<Integer> domain = new TreeSet<>();

            for (int j = lb; j <= ub; j++) {
                domain.add(j);
            }

            Variable variable = new Variable(i, domain);

            variables.add(variable);
        }

        return variables;
    }

    private static HashMap<Integer, HashMap<Integer, List<BinaryTuple>>> getConstraints(BinaryCSP csp) {
        HashMap<Integer, HashMap<Integer, List<BinaryTuple>>> constraints = new HashMap<>();

        for(int i = 0; i < csp.getNoVariables(); i++) {
            constraints.put(i, new HashMap<Integer, List<BinaryTuple>>());
        }

        for(BinaryConstraint constraint : csp.getConstraints()) {
            int firstVar = constraint.getFirstVar();
            int secondVar = constraint.getSecondVar();
            ArrayList<BinaryTuple> tuples = constraint.getTuples();

            constraints.get(firstVar).put(secondVar, tuples);
            constraints.get(secondVar).put(firstVar, invert(tuples));
        }

        return constraints;
    }

    private static ArrayList<BinaryTuple> invert(ArrayList<BinaryTuple> tuples) {
        ArrayList<BinaryTuple> inverted = new ArrayList<>();

        for (BinaryTuple tuple : tuples) {
            int val1 = tuple.getVal1();
            int val2 = tuple.getVal2();

            inverted.add(new BinaryTuple(val2, val1));
        }

        return inverted;
    }

    public static void main(String[] args) {
        VarSpaceBuilder varSpaceBuilder = new VarSpaceBuilder();

        BinaryCSPReader reader = new BinaryCSPReader();
        BinaryCSP binaryCSP = reader.readBinaryCSP("4Queens.csp");

        varSpaceBuilder.buildVariableSpace(binaryCSP);

    }


}

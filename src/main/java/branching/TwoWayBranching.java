package branching;

import arc_checker.ArcConsistencyMaintainer;
import arc_checker.EmptyDomainException;
import forward_checking.ForwardChecker;
import global_arc_consistency.ACThree;
import meta.Results;
import premade.BinaryTuple;
import problem_domain.NoAssignableVars;
import problem_domain.VariableSpace;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TwoWayBranching {


    public static Results branch(VariableSpace variableSpace, boolean fc, Results results) {



        if (variableSpace.solved()) {
            try {
                return getResults(results, variableSpace, new ArcConsistencyMaintainer(), variableSpace.getConstraints());
            } catch (EmptyDomainException e) {
                System.out.println("Incorrectly evaluated as finished");
            }
        }


        try {
//            System.out.println(variableSpace.getVariables());

//            System.out.println("Branching Left");

            branchLeft(variableSpace, fc, results);

            if (results.solved) {
                return results;            //            if (variable.getDomainSize() == 1) {
//                iterator.remove();
//                assignedVars.add(variable);
            }

//            System.out.println("Branching Right");
            branchRight(variableSpace, fc, results);

            if (results.solved) {            //            if (variable.getDomainSize() == 1) {
//                iterator.remove();
//                assignedVars.add(variable);
                return results;
            }
        } catch (NoAssignableVars noAssignableVars) {
            System.out.println("No assignable vars");
            try {
                ArcConsistencyMaintainer arcConsistencyMaintainer = new ArcConsistencyMaintainer();
                HashMap<Integer, HashMap<Integer, List<BinaryTuple>>> constraints = noAssignableVars.variableSpace.getConstraints();
                return getResults(results, noAssignableVars.variableSpace, arcConsistencyMaintainer, constraints);
            } catch (EmptyDomainException ignored) {
            }
        }

//        if (variableSpace2 != null) {
//            System.out.println("Complete");
//            return variableSpace2;
//        }

        return null;
    }

    private static Results getResults(Results results, VariableSpace variableSpace, ArcConsistencyMaintainer arcConsistencyMaintainer, HashMap<Integer, HashMap<Integer, List<BinaryTuple>>> constraints) throws EmptyDomainException {
        for (int var : constraints.keySet()) {
            for (int j : constraints.get(var).keySet()) {
                arcConsistencyMaintainer.revise(var, j, variableSpace);
            }
        }

        return completeExecution(variableSpace, results);
    }

    private static Results completeExecution(VariableSpace variableSpace, Results results) {
        results.finish(variableSpace);
        return results;
    }

    private static void branchLeft(VariableSpace space, boolean fc, Results results) throws NoAssignableVars {
        VariableSpace left = space.copy();

        int assign = left.assign();
        results.assign();

        if (fc) {
            ForwardChecker.performForwardChecking(left, assign, fc, results);
        } else {
            ACThree.performArcConsistency(left, assign, fc, results);
        }
    }

    private static void branchRight(VariableSpace variableSpace, boolean fc, Results results) throws NoAssignableVars {
        VariableSpace right = variableSpace.copy();


//        System.out.println("Pre-UNASSIGNING: " + variableSpace.getUnassigned());
        int assign = right.inverseAssign();
//        System.out.println("Post-UNASSIGNING: " + right.getUnassigned());
        results.inverseAssign();

        if (fc) {
            results = ForwardChecker.performForwardChecking(right, assign, fc, results);
        } else {
            results = ACThree.performArcConsistency(right, assign, fc, results);
        }


    }
}

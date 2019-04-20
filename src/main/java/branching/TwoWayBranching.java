package branching;

import forward_checking.ForwardChecker;
import global_arc_consistency.ACThree;
import preproc.Main;
import problem_domain.NoAssignableVars;
import problem_domain.VariableSpace;

public class TwoWayBranching {


    public static VariableSpace branch(VariableSpace variableSpace, boolean fc) {
        if (variableSpace.solved()) {
            completeExecution(variableSpace);
        }

        try {

        VariableSpace variableSpace1 = branchLeft(variableSpace, fc);

        if (variableSpace1 != null) {
            return variableSpace1;
        }

        VariableSpace variableSpace2 = branchRight(variableSpace, fc);

        } catch (NoAssignableVars noAssignableVars) {
            completeExecution(variableSpace);
        }

//        if (variableSpace2 != null) {
//            System.out.println("Complete");
//            return variableSpace2;
//        }

        return null;
    }

    private static void completeExecution(VariableSpace variableSpace) {
        System.out.println("Complete assignment: " + variableSpace);
        variableSpace.printSudoku();
        System.out.println("\n\n" + (System.nanoTime() - Main.t1) / 1000000000.0);
        System.exit(200);
    }

    private static VariableSpace branchLeft(VariableSpace space, boolean fc) throws NoAssignableVars {
        VariableSpace left = space.copy();


//            System.out.println("Assigning left: " + left);

        int assign = left.assign();

        VariableSpace variableSpace;
        if (fc) {
            variableSpace = ForwardChecker.performForwardChecking(left, assign, fc);
        } else {
            variableSpace = ACThree.performArcConsistency(left, assign, fc);
        }

        if (variableSpace != null) {
            return variableSpace;
        }
//            TwoWayBranching.branch(left);


        return null;
    }

    private static VariableSpace branchRight(VariableSpace variableSpace, boolean fc) throws NoAssignableVars {
        VariableSpace right = variableSpace.copy();


        int assign = right.inverseAssign();

        VariableSpace variableSpace1;
        if (fc) {
            variableSpace1 = ForwardChecker.performForwardChecking(right, assign, fc);
        } else {
            variableSpace1 = ACThree.performArcConsistency(right, assign, fc);
        }


        if (variableSpace1 != null) {
            return variableSpace1;
        }


        return null;
    }
}

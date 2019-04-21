package global_arc_consistency;

import arc_checker.ArcConsistencyMaintainer;
import arc_checker.EmptyDomainException;
import branching.TwoWayBranching;
import meta.Results;
import premade.BinaryTuple;
import problem_domain.VariableSpace;

import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;

public class ACThree {
    public static Results performArcConsistency(VariableSpace variableSpace, int var, boolean fc, Results results) {

        Queue<Pair> toCheck = buildQueue(variableSpace, var);

        try {
            while (!toCheck.isEmpty()) {
                Pair checkPair = toCheck.remove();

                ArcConsistencyMaintainer maintainer = new ArcConsistencyMaintainer();
                int var1 = checkPair.v1;
                int var2 = checkPair.v2;
                if(maintainer.revise(var1, var2, variableSpace)){
                    addArcs(var1, variableSpace, toCheck);
                }
            }

            return TwoWayBranching.branch(variableSpace, fc, results);
        } catch (EmptyDomainException ignored) {}

        return null;
    }

    private static void addArcs(int var1, VariableSpace variableSpace, Queue<Pair> toAddTo) {
        HashMap<Integer, HashMap<Integer, List<BinaryTuple>>> constraints = variableSpace.getConstraints();

        for(Map.Entry<Integer, HashMap<Integer, List<BinaryTuple>>> constraintEntry : constraints.entrySet()) {
            Integer var2 = constraintEntry.getKey();

            if(var2 != var1) {
                if(constraintEntry.getValue().containsKey(var1)) {
                    toAddTo.add(new Pair(var2, var1));
                }
            }
        }
    }

    private static Queue<Pair> buildQueue(VariableSpace variableSpace, int var) {
        Queue<Pair> pairs = new LinkedBlockingDeque<>();
//
//        HashMap<Integer, List<BinaryTuple>> integerListHashMap = variableSpace.getConstraints().get(var);
//
//        for(int var2 : integerListHashMap.keySet()) {
//            pairs.add(new Pair(var2, var));
//        }

        HashMap<Integer, HashMap<Integer, List<BinaryTuple>>> constraints = variableSpace.getConstraints();
        for(Map.Entry<Integer, HashMap<Integer, List<BinaryTuple>>> constraintEntry : constraints.entrySet()) {
            for(Map.Entry<Integer, List<BinaryTuple>> subConstraintEntry : constraintEntry.getValue().entrySet()) {
                pairs.add(new Pair(constraintEntry.getKey(), subConstraintEntry.getKey()));
            }
        }
        return pairs;
    }



}

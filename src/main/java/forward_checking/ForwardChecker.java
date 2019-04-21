package forward_checking;

import arc_checker.ArcConsistencyMaintainer;
import arc_checker.EmptyDomainException;
import branching.TwoWayBranching;
import meta.Results;
import premade.BinaryTuple;
import problem_domain.VariableSpace;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class ForwardChecker {
    public static Results performForwardChecking(VariableSpace space, int var, boolean fc, Results results) {
        HashMap<Integer, HashMap<Integer, List<BinaryTuple>>> constraints = space.getConstraints();
        Set<Integer> varsToCheckAgainst = constraints.get(var).keySet();

        ArcConsistencyMaintainer arcConsistencyMaintainer = new ArcConsistencyMaintainer();
        try {
            for (Integer toCheck : varsToCheckAgainst) {
                arcConsistencyMaintainer.revise(toCheck, var, space);
            }

            return TwoWayBranching.branch(space, fc, results);
        } catch (EmptyDomainException e) {
            return null;
        }
    }
}

package forward_checking;

import arc_checker.ArcConsistencyMaintainer;
import arc_checker.EmptyDomainException;
import branching.TwoWayBranching;
import premade.BinaryTuple;
import problem_domain.VariableSpace;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class ForwardChecker {
    public static VariableSpace performForwardChecking(VariableSpace space, int var, boolean fc) {
        HashMap<Integer, HashMap<Integer, List<BinaryTuple>>> constraints = space.getConstraints();
        Set<Integer> varsToCheckAgainst = constraints.get(var).keySet();

        ArcConsistencyMaintainer arcConsistencyMaintainer = new ArcConsistencyMaintainer();
        try {
            for (Integer toCheck : varsToCheckAgainst) {
                arcConsistencyMaintainer.revise(toCheck, var, space);
            }

            VariableSpace branch = TwoWayBranching.branch(space, fc);

            if(branch != null) {
                return branch;
            }
        } catch (EmptyDomainException ignored) {

        }

        return null;
    }
}

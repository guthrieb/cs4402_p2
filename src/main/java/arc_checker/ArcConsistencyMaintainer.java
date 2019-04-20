package arc_checker;

import premade.BinaryTuple;
import problem_domain.Variable;
import problem_domain.VariableSpace;

import java.util.Iterator;
import java.util.List;

public class ArcConsistencyMaintainer {
    public boolean revise(int var1, int var2, VariableSpace variableSpace) throws EmptyDomainException {
        boolean deleted = false;
        Variable var1Obj = variableSpace.getVariable(var1);
        Variable var2Obj = variableSpace.getVariable(var2);
        List<BinaryTuple> v1v2constraints = variableSpace.getConstraints(var1, var2);

        Iterator<Integer> iterator = var1Obj.getDomain().iterator();
        while (iterator.hasNext()){
            int d1 = iterator.next();

            boolean keepDomain = false;

            for(int d2 : var2Obj.getDomain()) {
                for(BinaryTuple constraint : v1v2constraints) {
                    if(constraint.matches(d1, d2)) {
                        keepDomain = true;
                        break;
                    }
                }

                //If found to be acceptable domain entry, leave loop
                if(keepDomain) {
                    break;
                }
            }

            //If there exists no d2 in D2 for which (d1, d2) is an acceptable constraint
            if(!keepDomain) {
                iterator.remove();
                deleted = true;

                if(var1Obj.getDomain().size() == 0) {
                    throw new EmptyDomainException("Empty domain");
                }
            }
        }

        return deleted;
    }
}

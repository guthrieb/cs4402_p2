package problem_domain;

import meta.Results;

public class NoAssignableVars extends Exception {

    public VariableSpace variableSpace;

    public NoAssignableVars(String message, VariableSpace variableSpace) {
        super(message);
        this.variableSpace = variableSpace;
    }
}

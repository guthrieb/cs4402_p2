package problem_domain;

import premade.BinaryTuple;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class VariableSpace {
    private HashMap<Integer, Variable> vars = new HashMap<>();
    private HashSet<Variable> unassignedVars = new HashSet<Variable>();
    private HashSet<Variable> assignedVars = new HashSet<>();
    private HashMap<Integer, HashMap<Integer, List<BinaryTuple>>> constraints;

    public VariableSpace(List<Variable> variableList, HashMap<Integer, HashMap<Integer, List<BinaryTuple>>> constraints) {
        this.unassignedVars.addAll(variableList);

        for (Variable variable : variableList) {
            vars.put(variable.varNo, variable);
        }

        this.constraints = constraints;
    }

    private VariableSpace(HashMap<Integer, Variable> vars, HashSet<Variable> unassignedVars, HashSet<Variable> assignedVars, HashMap<Integer, HashMap<Integer, List<BinaryTuple>>> constraints) {
        this.vars = vars;
        this.unassignedVars = unassignedVars;
        this.assignedVars = assignedVars;
        this.constraints = constraints;
    }

    public int assign() throws NoAssignableVars {

        Variable minSizeVar = getMinDomainVariable();

        if (minSizeVar != null) {
            minSizeVar.assign();
            assignedVars.add(minSizeVar);
            unassignedVars.remove(minSizeVar);
            return minSizeVar.varNo;
        } else {
            throw new NoAssignableVars("No vars assignable", this);
        }
    }

    public int inverseAssign() throws NoAssignableVars {
        Variable minSizeVar = getMinDomainVariable();

        if (minSizeVar != null) {
            minSizeVar.inverseAssign();
            return minSizeVar.varNo;
        } else {
            throw new NoAssignableVars("No vars assignable", this);
        }
    }

    private Variable getMinDomainVariable() {
        int minSize = Integer.MAX_VALUE;
        Variable minSizeVar = null;
        int lowestVarNo = Integer.MAX_VALUE;

        for (Variable variable : unassignedVars) {
            if (variable.getDomainSize() < minSize) {
                minSize = variable.getDomainSize();
                minSizeVar = variable;
            }

            if (variable.getDomainSize() == minSize && variable.getVarNo() < lowestVarNo) {
                minSizeVar = variable;
                lowestVarNo = variable.getVarNo();
            }
        }
        return minSizeVar;
    }

    private boolean fullAssignment() {
        return unassignedVars.size() == 0;
    }


    @Override
    public String toString() {
        return "VariableSpace{" +
                "unassignedVars=" + unassignedVars + "," +
                "assignedVars=" + assignedVars +
                '}';
    }

//    public static void main(String[] args) {
//        Variable variable2 = new Variable(0, new TreeSet<>(Arrays.asList(0, 4, 2, 3, 5)));
//        Variable variable = new Variable(2, new TreeSet<>(Arrays.asList(0, 2, 1, 3)));
//        Variable variable3 = new Variable(1, new TreeSet<>(Arrays.asList(0, 1)));
////
//        ArrayList<Variable> vars = new ArrayList<>(Arrays.asList(variable, variable2, variable3));
//
////        VariableSpace varpace = new VariableSpace(vars);
//
//        System.out.println(varpace);
////
//        try {
//            while (!varpace.fullAssignment()) {
//                varpace.inverseAssign();
//            }
//        } catch (NoAssignableVars noAssignableVars) {
//            System.out.println("FINISHED ASSIGNMENT: " + varpace.vars);
//            System.out.println(varpace.unassignedVars);
//            System.out.println(varpace.assignedVars);
//        }
//    }

    public VariableSpace copy() {
        HashMap<Integer, Variable> newVars = new HashMap<>();
        HashSet<Variable> newUnassigned = new HashSet<>();
        HashSet<Variable> newAssigned = new HashSet<>();

        for (Map.Entry<Integer, Variable> variableEntry : vars.entrySet()) {
            Variable varCopy = variableEntry.getValue().copy();

            if(unassignedVars.contains(variableEntry.getValue())) {
                newUnassigned.add(varCopy);
            } else {
                newAssigned.add(varCopy);
            }

            newVars.put(variableEntry.getKey(), varCopy);
        }

        return new VariableSpace(newVars, newUnassigned, newAssigned, this.constraints);
    }

    public Variable getVariable(int var) {
        return vars.get(var);
    }

    public HashMap<Integer, HashMap<Integer, List<BinaryTuple>>> getConstraints() {
        return constraints;
    }

    public List<BinaryTuple> getConstraints(int var1, int var2) {
        return constraints.get(var1).get(var2);
    }

    public boolean solved() {
        return unassignedVars.size() == 0;
    }

    public void printSudoku() {
        for (int i = 0; i < vars.size(); i++) {
            if (i % 9 == 0) {
                System.out.println("\n");
            }

            System.out.print(vars.get(i).domain);

        }
    }

    public HashMap<Integer, Variable> getVariables() {
        return vars;
    }

    public HashSet<Variable> getUnassigned() {
        return unassignedVars;
    }
}

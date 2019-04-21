package meta;

import problem_domain.Variable;
import problem_domain.VariableSpace;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class Results {
    public boolean solved;
    private VariableSpace results;
    private int assignments = 0;
    private int removeAssignments = 0;
    private long startTime;
    private long finishTime;
    private long totalTime;
    private boolean multiSolutions;

    public Results(boolean multiSolutions) {
        this.startTime = System.nanoTime();
        this.multiSolutions = multiSolutions;
    }

    public void finish(VariableSpace finalSolution) {
        this.results = finalSolution;
        this.finishTime = System.nanoTime();
        this.totalTime = finishTime - startTime;
        this.solved = true;
    }

    public double getTimeTaken() {
        return totalTime/1000000000.0;
    }

    public void assign() {
        assignments++;
    }

    public void inverseAssign() {
        removeAssignments++;
    }

    public boolean isSolved() {
        return solved;
    }

    public VariableSpace getResults() {
        return results;
    }

    public int getAssignments() {
        return assignments;
    }

    public int getInverseAssignments() {
        return removeAssignments;
    }

    public long totalTime() {
        return finishTime - startTime;
    }

    public String getSolution() {
        HashMap<Integer, Variable> variables = results.getVariables();

        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < variables.size(); i++) {
            builder.append(", (").append(i).append(" = ").append(variables.get(i).getDomain()).append(")");
        }

        return builder.toString();
    }

    public long getStartTime() {
        return startTime;
    }
}

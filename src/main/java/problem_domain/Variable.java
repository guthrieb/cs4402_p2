package problem_domain;

import java.util.Collections;
import java.util.Objects;
import java.util.TreeSet;

public class Variable {
    int varNo;
    TreeSet<Integer> domain;

    public Variable(int varNo, TreeSet<Integer> domain) {
        this.domain = domain;
        this.varNo = varNo;
    }

    public void assign() {
        Integer first = domain.first();
        System.out.println("p: " + " (varno=" + varNo + ",val="  + first + ")");
        this.domain = new TreeSet<>(Collections.singleton(first));
    }

    public void inverseAssign() {
        System.out.println("p_rem: " + " (varno=" + varNo + ",val="  + domain.first() + ")");


        domain.pollFirst();
    }

    public Variable copy() {
        TreeSet<Integer> copyDomain = new TreeSet<>(domain);

        return new Variable(varNo, copyDomain);
    }

    @Override
    public String toString() {
        return "Variable{" +
                "varNo=" + varNo +
                ", domain=" + domain +
                '}';
    }

    int getDomainSize() {
        return domain.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Variable variable = (Variable) o;
        return varNo == variable.varNo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(varNo);
    }

    public TreeSet<Integer> getDomain() {
        return domain;
    }

    public int getVarNo() {
        return varNo;
    }
}

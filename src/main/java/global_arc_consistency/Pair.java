package global_arc_consistency;

public class Pair {

    int v1;
    int v2;

    public Pair(int v1, int v2) {
        this.v1 = v1;
        this.v2 = v2;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "v1=" + v1 +
                ", v2=" + v2 +
                '}';
    }
}

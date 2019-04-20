package preproc;

import branching.TwoWayBranching;
import premade.BinaryCSPReader;
import problem_domain.VariableSpace;

public class Main {
    public static long t1;

    public static void main(String[] args) {
//        if (args.length != 1) {
//            System.out.println("Usage: java BinaryCSPReader <file.csp>") ;
//            return ;
//        }
        BinaryCSPReader reader = new BinaryCSPReader() ;
        t1 = System.nanoTime();
        VariableSpace variableSpace = VarSpaceBuilder.buildVariableSpace(reader.readBinaryCSP("FinnishSudoku.csp"));


        TwoWayBranching.branch(variableSpace, true);
        variableSpace.printSudoku();
    }
}

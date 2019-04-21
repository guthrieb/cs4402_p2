package meta;

import branching.TwoWayBranching;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import premade.BinaryCSPReader;
import problem_domain.VariableSpace;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ResultWriter {
    private static final String staticFileNameQueens = "Queens";
    private static final String staticFileNameLangfords = "langfords2_";
    private static final boolean pre = true;
    private static final String PROBLEM_DIR = "problems/";
    public static long t1;
    private static String[] columns = {"size", "solver_time", "assignments", "inverse_assignments"};

    public static void main(String[] args) {
//        if (args.length != 1) {
//            System.out.println("Usage: java BinaryCSPReader <file.csp>") ;
//            return ;
//        }
//        String current = staticFileNameQueens;

        Workbook workbook = new XSSFWorkbook();
        CreationHelper createHelper = workbook.getCreationHelper();

        // Create a Sheet
        Sheet queensFc = workbook.createSheet("queensFc");
        makeHeaders(queensFc);

        Sheet queensAc = workbook.createSheet("queensAc");
        makeHeaders(queensAc);

        Sheet langfordsFc = workbook.createSheet("langfordsFc");
        makeHeaders(langfordsFc);

        Sheet langfordsAc = workbook.createSheet("langfordsAc");
        makeHeaders(langfordsAc);


        runTests(queensFc, "Queens", true, true, 4, 10);
        runTests(queensAc, "Queens", false, true, 4, 10);
        runTests(langfordsFc, "langfords2_", true, false, 3, 8);
        runTests(langfordsAc, "langfords2_", false, false, 3, 8);

        writeWorkBookOut(workbook, "out.xlsx");

//        for (int i = lr; i <= ur; i++) {
//            String problem = current;
//
//            if (pre) {
//                problem = i + problem + ".csp";
//            } else {
//                problem = problem + i + ".csp";
//            }


//            System.out.println(problem);

    }

    private static void runTests(Sheet sheet, String generalProblem, boolean fc, boolean pre, int lr, int ur) {
        for(int i = lr; i <= ur; i++) {
            double sumTime = 0;
            double sumAssign = 0;
            double sumInverse = 0;

            String problem = getProblem(generalProblem, i, pre);
            System.out.println("Solving: " + generalProblem + " " + i);
            for(int j = 0; j < 3; j++) {
                BinaryCSPReader reader = new BinaryCSPReader();

                VariableSpace variableSpace = VarSpaceBuilder.buildVariableSpace(reader.readBinaryCSP(PROBLEM_DIR + problem));
                Results results1 = new Results(false);
                Results results = TwoWayBranching.branch(variableSpace, fc, results1);

                if(results == null) {
                    System.out.println("NO SOLUTION FOUND " + problem);
                    results = results1;
                    sumTime += System.nanoTime() - results.getStartTime();
                } else {
                    sumTime += results.getTimeTaken();
                }

                sumAssign += results.getAssignments();
                sumInverse += results.getInverseAssignments();

            }

            sumTime /= 3;
            sumAssign /= 3;
            sumInverse /= 3;



            Row row = sheet.createRow(1 + (i - lr));
            row.createCell(0).setCellValue(i);
            row.createCell(1).setCellValue(sumTime);
            row.createCell(2).setCellValue(sumAssign);
            row.createCell(3).setCellValue(sumInverse);
//            row.createCell(0).setCellValue();
        }
    }

    private static String getProblem(String foundation, int i, boolean pre) {
        if(pre) {
            return i + foundation + ".csp";
        } else {
            return foundation + i + ".csp";
        }
    }

    private static void writeWorkBookOut(Workbook workbook, String filename) {
        try {
            FileOutputStream fileOut = new FileOutputStream(filename);
            workbook.write(fileOut);
            fileOut.close();

            // Closing the workbook
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void makeHeaders(Sheet sheet) {
        Row headerRow = sheet.createRow(0);

        for(int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
        }
    }
}

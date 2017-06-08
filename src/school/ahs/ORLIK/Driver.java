package school.ahs.ORLIK;

import school.ahs.ORLIK.Instruction.Assignment;
import school.ahs.ORLIK.Runtime.Block;
import school.ahs.ORLIK.Runtime.Blueprint;
import school.ahs.ORLIK.Runtime.Constructor;
import school.ahs.ORLIK.Runtime.Runtime;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Driver {

    public static void main(String[] args) throws Exception {
        File file = new File(args[0]);
        Scanner input = new Scanner(file);
        String code = input.useDelimiter("\\Z").next();
        Runtime runtime = new Runtime(code);
        System.err.println("Main code: " + code);
        runtime.execute(new HashSet<>());
    }

}

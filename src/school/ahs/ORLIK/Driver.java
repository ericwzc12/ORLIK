package school.ahs.ORLIK;

import school.ahs.ORLIK.Instruction.Assignment;
import school.ahs.ORLIK.Runtime.Block;
import school.ahs.ORLIK.Runtime.Blueprint;
import school.ahs.ORLIK.Runtime.Constructor;
import school.ahs.ORLIK.Runtime.Runtime;
import java.io.File;
import java.util.*;

public class Driver {

    public static String[] swearWords = new String[] {"fuck", "shit", "nigger", "crap", "ass"};

    public static void main(String[] args) throws Throwable {
        File file = new File(args[0]);
        Scanner input = new Scanner(file);
        String code = input.useDelimiter("\\Z").next();
        Runtime runtime = new Runtime(code, true);
        if (new HashSet<String>(Arrays.asList(swearWords)).stream().anyMatch(s -> code.contains(s))) {
            System.out.println("MY VIRGIN EEARRSSS!!!");
            throw new Throwable("SWEAR JAR!");
        }
        runtime.execute(new HashSet<>());
    }

}

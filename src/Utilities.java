import java.util.Scanner;

public class Utilities {
    synchronized static int inputInt(String prompt){
        Scanner s = new Scanner(System.in);
        System.out.println(prompt);
        try {
            return Integer.parseInt(s.nextLine());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    synchronized static int[][] splitIntArray(int[] ogArr, int n){
        // TBD
        return null;
    }


}

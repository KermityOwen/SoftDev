import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

    synchronized static String inputString(String prompt){
        Scanner s = new Scanner(System.in);
        System.out.println(prompt);
        return s.nextLine();
    }

    // Since "ogArr" parameter taken is guarded to be divisible by n (where n is number of players)...
    // No need to account for cases where number of elements cannot be split evenly (semi hard-coded)
    synchronized static int[][] roundRobinSplit(int[] ogArr, int n){
        int [][] splitArr = new int [n][4];
        int playerPointer = 0;
        int timesRan = 0;
        for (int arrPointer = 0; arrPointer < ogArr.length; arrPointer++){
            // Every time the times ran is n the splitArr's 2nd pointer increases by 1
            splitArr[playerPointer][timesRan/n] = ogArr[arrPointer];
            // As the playerPointer increases past the number of players it will reset to 0
            playerPointer = (playerPointer+1)%n;
            timesRan++;
        }
        return splitArr;
    }

    synchronized static int[] parseDeckFile(String filePath) {
        try {
            Scanner scanner = new Scanner(new File("packs/"+filePath));
            List<Integer> al = new ArrayList<Integer>();

            while (scanner.hasNextLine()) {
                al.add(Integer.valueOf(scanner.nextLine()));
            }
            scanner.close();

            // Converting list to array (Sorry, bad planning)
            int[] arr = new int[al.size()];
            for (int i = 0; i < arr.length; i++) {
                arr[i] = al.get(i);
            }
            return arr;

        } catch (FileNotFoundException e) {
            System.out.println(e);
            return null;
        }
    }

    static void logFile(String filePath, String logs){
        File logfile = new File("logs/"+filePath);
        try {
            logfile.getParentFile().mkdirs();
            FileWriter writer = new FileWriter(logfile);
            writer.write(logs);
            writer.close();

        } catch (Exception e){
            System.out.println(e);
        }
    }
}

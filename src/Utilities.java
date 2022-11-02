import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.io.File;

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
    // No need to account for cases where number of elements cannot be split evenly
    synchronized static int[][] splitIntArray(int[] ogArr, int n){
        int [][] splitedArray = new int[n][];
        int length = ogArr.length / n;
        int x = 0;
        for(int i = 0; i < splitedArray.length; i++){
            splitedArray[i] = Arrays.copyOfRange(ogArr, x, x + length);
            x = x + length;
        }
        return splitedArray;
    }

    synchronized static int[] parseDeckFile(String filePath) {
        try {
            Scanner scanner = new Scanner(new File("src/Packs/"+filePath));
            List<Integer> al = new ArrayList<Integer>();

            while (scanner.hasNextLine()) {
                al.add(Integer.valueOf(scanner.nextLine()));
            }
            scanner.close();

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
}

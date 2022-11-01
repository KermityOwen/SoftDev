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

    synchronized static int[][] splitArray(int[] ogArr, int n){
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
            File myObj = new File("four.txt");
            Scanner myReader = new Scanner(myObj);
            List<Integer> al = new ArrayList<Integer>();
            while (myReader.hasNextLine()) {
                al.add(Integer.valueOf(myReader.nextLine()));
            }
            int[] array = new int[al.size()];
            for (int i = 0; i < array.length; i++) {
                array[i] = al.get(i);
            }
            myReader.close();
            return array;
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            return null;
        }
    }

    synchronized static String inputString(String prompt){
        Scanner s = new Scanner(System.in);
        System.out.println(prompt);
        return s.nextLine();
    }


}

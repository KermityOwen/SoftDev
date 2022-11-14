import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class UtilitiesTest {

    @Test
    void inputInt() {
        String input = "Please enter the number of players: ";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        assertEquals(0, Utilities.inputInt(input));
    }

    @Test
    void inputString() {
        String input = "Please enter location of the pack to load: ";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        assertEquals("Please enter location of the pack to load: ", Utilities.inputString(input));
    }

    @Test
    void roundRobinSplit() {
        int[] input = new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16};
        int n = 4;
        int[][] expected = new int[][]{{1,5,9,13}, {2,6,10,14}, {3,7,11,15},{4,8,12,16}};
        assertEquals(Arrays.deepToString(expected), Arrays.deepToString(Utilities.roundRobinSplit(input,n)));
    }

    @Test
    void parseDeckFile() {
        String filePath = "two.txt";
        int[] expected = new int[]{1,2,3,4,1,2,3,4,1,2,3,4,1,2,3,4};
        assertEquals(Arrays.toString(expected), Arrays.toString(Utilities.parseDeckFile(filePath)));
    }

    @Test
    void logFile(){
        String filePath = "src/logs/player0_output.txt";
        assertTrue(Files.exists(Path.of(filePath)));


    }
}
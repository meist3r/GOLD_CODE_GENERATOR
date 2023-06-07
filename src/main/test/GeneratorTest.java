import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class GeneratorTest {

    @Test
    void generateFullSequenceTest() {
        int[] seed1,seed2;
        int[] pair1, pair2;
        seed1 = new int[]{0,0,0,0,1};
        seed2 = new int[]{0,0,0,0,1};
        pair1 = new int[]{2,3,4,5};
        pair2 = new int[]{2,5};

        Generator g = new Generator(pair1,pair2,seed1,seed2);
        for (int i = 0; i<g.getLengthOfOptimalGoldCode();i++){  //generate full sequence
            g.generate();
        }
        assertArrayEquals(g.getInitialSeed1(),seed1);
        assertArrayEquals(g.getInitialSeed2(),seed2);

        g.generate();
        assertFalse(Arrays.equals(g.getInitialSeed1(),seed1));
        assertFalse(Arrays.equals(g.getInitialSeed2(),seed2));

    }

    @Test
    void errorTest() {
        int[] seed1,seed2;
        int[] pair1, pair2;
        seed1 = new int[]{0,0,0,1}; //seeds are different length
        seed2 = new int[]{0,0,0,0,1};
        pair1 = new int[]{2,3,4,5};
        pair2 = new int[]{2,5};
        try {
            new Generator(pair1,pair2,seed1,seed2);
        }catch (Exception e){
            assertThrows(Exception.class, (Executable) e);
        }

        seed1 = new int[]{0,0,0,0,1};
        seed2 = new int[]{0,0,0,0,1};
        pair1 = new int[]{2,3,4,6}; //polynomial level too high
        pair2 = new int[]{2,5};
        try {
            new Generator(pair1,pair2,seed1,seed2);
        }catch (Exception e){
            assertThrows(Exception.class, (Executable) e);
        }

        seed1 = new int[]{0,0,0,0,1};
        seed2 = new int[]{0,0,0,0,1};
        pair1 = new int[]{2,3,4,5};
        pair2 = new int[]{};        //polynomial is 0
        try {
            new Generator(pair1,pair2,seed1,seed2);
        }catch (Exception e){
            assertThrows(Exception.class, (Executable) e);
        }
    }
}
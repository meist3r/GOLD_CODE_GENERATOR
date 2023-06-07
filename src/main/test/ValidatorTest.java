import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorTest {

    @Test
    void isPreferredSequencesTest() {
        int[] seed1,seed2;
        int[] pair1, pair2;
        seed1 = new int[]{0,0,0,0,1};
        seed2 = new int[]{0,0,0,0,1};
        pair1 = new int[]{2,3,4,5};
        pair2 = new int[]{2,5};

        Generator g = new Generator(pair1,pair2,seed1,seed2);
        Validator v = new Validator(g);
        assertEquals(true, v.isPreferredSequences());

        pair2 = new int[]{2,4};
         g = new Generator(pair1,pair2,seed1,seed2);
         v = new Validator(g);
        assertEquals(false, v.isPreferredSequences());

    }
}
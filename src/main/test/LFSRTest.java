import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LFSRTest {


    /**
     * Values taken from website:
     * https://www.eetimes.com/tutorial-linear-feedback-shift-registers-lfsrs-part-1/
     */
    @Test
    void LFSRTest1() {
        int[] seed = new int[]{0, 0, 1};
        int[] polynomial = new int[]{0,2};
        LFSR lfsr = new LFSR(seed,polynomial);

        int out = lfsr.pop();
        int[] x = new int[]{1,0,0};
        assertEquals(1,out);
        assertArrayEquals(x,lfsr.getReg());

        out = lfsr.pop();
        x = new int[]{1,1,0};
        assertEquals(0,out);
        assertArrayEquals(x,lfsr.getReg());

        out = lfsr.pop();
        x = new int[]{1,1,1};
        assertEquals(0,out);
        assertArrayEquals(x,lfsr.getReg());

        out = lfsr.pop();
        x = new int[]{0,1,1};
        assertEquals(1,out);
        assertArrayEquals(x,lfsr.getReg());

        out = lfsr.pop();
        x = new int[]{1,0,1};
        assertEquals(1,out);
        assertArrayEquals(x,lfsr.getReg());

        out = lfsr.pop();
        x = new int[]{0,1,0};
        assertEquals(1,out);
        assertArrayEquals(x,lfsr.getReg());

        out = lfsr.pop();
        x = new int[]{0,0,1};
        assertEquals(0,out);
        assertArrayEquals(x,lfsr.getReg());
    }


    @Test
    void LFSRTest2() {
        int[] seed = new int[]{0, 0, 1};
        int[] polynomial = new int[]{1,2};
        LFSR lfsr = new LFSR(seed,polynomial);

        int out = lfsr.pop();
        int[] x = new int[]{1,0,0};
        assertEquals(1,out);
        assertArrayEquals(x,lfsr.getReg());

        out = lfsr.pop();
        x = new int[]{0,1,0};
        assertEquals(0,out);
        assertArrayEquals(x,lfsr.getReg());

        out = lfsr.pop();
        x = new int[]{1,0,1};
        assertEquals(0,out);
        assertArrayEquals(x,lfsr.getReg());

        out = lfsr.pop();
        x = new int[]{1,1,0};
        assertEquals(1,out);
        assertArrayEquals(x,lfsr.getReg());

        out = lfsr.pop();
        x = new int[]{1,1,1};
        assertEquals(0,out);
        assertArrayEquals(x,lfsr.getReg());

        out = lfsr.pop();
        x = new int[]{0,1,1};
        assertEquals(1,out);
        assertArrayEquals(x,lfsr.getReg());

        out = lfsr.pop();
        x = new int[]{0,0,1};
        assertEquals(1,out);
        assertArrayEquals(x,lfsr.getReg());
    }
}
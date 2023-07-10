import io.github.coolbong.util.Hex;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HexTest {


    @Test
    public void test_to_hex_001() {
        byte b = (byte)0xff;
        String answer = "FF";

        String ret = Hex.toHex(b);

        assertEquals(answer, ret);
    }

    @Test
    public void test_to_hex_002() {
        short s = (short)0xf0f0;
        String answer = "F0F0";

        String ret = Hex.toHex(s);

        assertEquals(answer, ret);
    }

    @Test
    public void test_to_hex_003() {
        int i = 0xf00dbabe;
        String answer = "F00DBABE";

        String ret = Hex.toHex(i);

        assertEquals(answer, ret);
    }
}

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


    @Test
    public void test_hex_to_var_001() {
        String food = "404142434445464748494A4B4C4D4E4F";
        String answer = "(byte)0x40, (byte)0x41, (byte)0x42, (byte)0x43, " +
                "(byte)0x44, (byte)0x45, (byte)0x46, (byte)0x47, " +
                "(byte)0x48, (byte)0x49, (byte)0x4a, (byte)0x4b, " +
                "(byte)0x4c, (byte)0x4d, (byte)0x4e, (byte)0x4f";
        String result = Hex.toVariable(food);

//        System.out.println(result);
        assertEquals(answer, result);

    }
}

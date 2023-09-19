import io.github.coolbong.tlv.Hex;
import org.junit.Test;

import java.nio.charset.StandardCharsets;

import static io.github.coolbong.tlv.Hex.toBytes;
import static org.junit.Assert.assertEquals;

public class AsciiTest {
    @Test
    public void test_to_ascii_001() {
        String hex = "435245444954"; // KLSC DUAL CREDIT
        String answer = "CREDIT";

        String ret = Hex.toAscii(hex);

        assertEquals(answer, ret);
    }

    @Test
    public void test_to_ascii_002() {
        String hex = "315041592E5359532E4444463031";
        String ret = Hex.toAscii(hex);
        assertEquals("1PAY.SYS.DDF01", ret);
    }

    @Test
    public void test_to_ascii_003() {
        String hex = "325041592E5359532E4444463031";
        String ret = Hex.toAscii(hex);
        assertEquals("2PAY.SYS.DDF01", ret);
    }

    @Test
    public void test_to_ascii_004() {
        String plaintext = "Hello world";
        byte[] arr = plaintext.getBytes(StandardCharsets.UTF_8);

        String ret = Hex.toAscii(arr);
        assertEquals(plaintext, ret);
    }


    @Test
    public void test_to_ascii_005() {
        byte[] input = toBytes("404142434445464748494A4B4C4D4E4F");

        String ret = Hex.toAscii(input, 1, 15);
        assertEquals("ABCDEFGHIJKLMNO", ret);
    }
}

import io.github.coolbong.util.Hex;
import org.junit.Test;

import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertEquals;

public class AsciiTest {
    @Test
    public void test_to_ascii_001() {
        String hex = "4B4C5343204455414C20435245444954"; // KLSC DUAL CREDIT
        String answer = "KLSC DUAL CREDIT";

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
}

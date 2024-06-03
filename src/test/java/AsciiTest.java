import io.github.coolbong.tlv.Hex;
import org.junit.Test;

import java.nio.charset.StandardCharsets;

import static io.github.coolbong.tlv.Hex.*;
import static io.github.coolbong.tlv.Hex.asciiToHex;
import static junit.framework.TestCase.assertNotNull;
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


    @Test
    public void test_to_ascii_006() {
        byte[] input = toBytes("9F4F119F27019F02065F2A029A039F36029F5206");

        String ret = toAscii(input);
        assertNotNull(ret);
    }

    @Test
    public void test_to_ascii_007() {
        String input = "55424A323147323676322E302E30323430323232";
        System.out.println(toAscii(input));

        input = "55424A3231473236";
        System.out.println(toAscii(input));

        input = "76322E302E313233";
        System.out.println(toAscii(input));

        input = "323430323232";
        System.out.println(toAscii(input));

        System.out.println(asciiToHex("240515"));
        System.out.println(asciiToHex("240515"));

        input = "55424A3231473236" + "76322E302E313233" + "323430353135";
        System.out.println(input);

        System.out.println(toAscii(input));
    }


    @Test
    public void test_ascii_to_hex_001() {
        String input = "hello world";
        String ret = asciiToHex(input);
        assertEquals("68656C6C6F20776F726C64", ret);
    }


    @Test
    public void test_ascii_to_hex_002() {
        String input = "Debit MasterCard";
        String ret = asciiToHex(input);
        assertEquals("4465626974204D617374657243617264", ret);
    }



}

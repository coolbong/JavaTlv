import io.github.coolbong.tlv.Hex;
import org.junit.Test;

import java.nio.charset.StandardCharsets;

import static io.github.coolbong.tlv.Hex.*;
import static org.junit.Assert.*;

public class HexTest {


    @Test
    public void test_to_hex_001() {
        byte b = (byte)0xff;
        String answer = "FF";

        String ret = toHex(b);

        assertEquals(answer, ret);
    }

    @Test
    public void test_to_hex_002() {
        short s = (short)0xf0f0;
        String answer = "F0F0";

        String ret = toHex(s);

        assertEquals(answer, ret);
    }

    @Test
    public void test_to_hex_003() {
        int i = 0xf00dbabe;
        String answer = "F00DBABE";

        String ret = toHex(i);

        assertEquals(answer, ret);
    }

    @Test
    public void test_to_hex_004() {
        byte[] data = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".getBytes(StandardCharsets.UTF_8);
        String ret = toHex(data, 0, 10);
        assertEquals("4142434445464748494A", ret);
    }


    @Test
    public void test_hex_to_var_001() {
        String food = "404142434445464748494A4B4C4D4E4F";
        String answer = "(byte)0x40, (byte)0x41, (byte)0x42, (byte)0x43, " +
                "(byte)0x44, (byte)0x45, (byte)0x46, (byte)0x47, " +
                "(byte)0x48, (byte)0x49, (byte)0x4a, (byte)0x4b, " +
                "(byte)0x4c, (byte)0x4d, (byte)0x4e, (byte)0x4f";
        String result = Hex.toVariable(food);
        //System.out.println(result);
        assertEquals(answer, result);
    }



    @Test
    public void test_hex_u1_001() {
        byte[] arr = { (byte)0x40, (byte)0x41, (byte)0x42, (byte)0x43, (byte)0x44, (byte)0x45, (byte)0x46, (byte)0x47,
                (byte)0x48, (byte)0x49, (byte)0x4a, (byte)0x4b, (byte)0x4c, (byte)0x4d, (byte)0x4e, (byte)0x4f };
        String ret;

        ret = Hex.u1(arr, 0);
        assertEquals("40", ret);

        ret = Hex.u1(arr, arr.length - 1);
        assertEquals("4F", ret);
    }

    @Test
    public void test_hex_u2_002() {
        byte[] arr  = {
                (byte)0x01, (byte)0x02, (byte)0x03, (byte)0x04
        };
        String ret;

        ret = u2(arr, 0);
        assertEquals("0102", ret);

        ret = u2(arr, arr.length - 2);
        assertEquals("0304", ret);
    }


    @Test
    public void test_hex_un_001() {
        byte[] arr = { (byte)0xA0, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x04, (byte)0x10, (byte)0x10 };

        String ret;

        ret = un(arr, 0, 2);
        assertEquals("A000", ret);

        ret = un(arr, 0, 4);
        assertEquals("A0000000", ret);

        ret = un(arr, 4, 3);
        assertEquals("041010", ret);
    }


    @Test
    public void test_hex_setshort_001() {
        byte[] arr = { (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00,(byte)0x00, (byte)0x00 };

        byte[] answer = { (byte)0x11, (byte)0x11, (byte)0x00, (byte)0x00,(byte)0x00, (byte)0x00 };

        setShort(arr, 0, 0x1111);

        assertArrayEquals(answer, arr);
    }

    @Test
    public void test_hex_toint_001() {
        byte[] arr = {(byte)0x00, (byte)0x00, (byte)0x01, (byte)0x00};

        int answer = 256;
        int ret = toInt(arr);
        assertEquals(ret, answer);


        arr[2] = 0;
        arr[3] = 0x7f;
        answer = 127;
        ret = toInt(arr);
        assertEquals(ret, answer);

        arr[1] = 1;
        arr[2] = 0;
        arr[3] = 0x00;
        answer = 65536;
        ret = toInt(arr);
        assertEquals(ret, answer);
    }

    @Test
    public void test_to_bytes_001() {
        String hex = "001122";
        byte[] answer = {
                (byte)0x00, (byte)0x11, (byte)0x22
        };

        byte[] ret = toBytes(hex);
        assertArrayEquals(answer, ret);
    }

    @Test
    public void test_to_bytes_002() {
        String hex = "babe";
        byte[] answer = {(byte)0xba, (byte)0xbe};
        byte[] ret = toBytes(hex);
        assertArrayEquals(answer, ret);
    }


    @Test
    public void test_to_bytes_003() {
        short input = (short)0xbab0;
        byte[] answer = {(byte)0xba, (byte)0xb0};
        byte[] ret = toBytes(input);
        assertArrayEquals(answer, ret);
    }

    @Test
    public void test_to_bytes_004() {
        String input = "82027800";
        byte[] answer = {(byte)0x82, (byte)0x02, (byte)0x78, (byte)0x00};
        byte[] ret = new byte[4];
        toBytes(input, ret, 0);
        assertArrayEquals(answer, ret);
    }

    @Test
    public void test_to_bytes_005() {
        // invalid odd hex string
        String input = "1122334";
        byte[] ret = toBytes(input);
        assertArrayEquals(new byte[0], ret);
    }



}

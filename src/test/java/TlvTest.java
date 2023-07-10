import io.github.coolbong.util.Tlv;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class TlvTest {


    @Test
    public void test_tlv_constructor_001() {
        Tlv tlv = new Tlv("84", "315041592E5359532E4444463031");
        String answer = "840E315041592E5359532E4444463031";
        byte[] tag = { (byte)0x84 };
        byte[] value = {
                (byte)0x31, (byte)0x50, (byte)0x41, (byte)0x59, (byte)0x2E, (byte)0x53, (byte)0x59, (byte)0x53,
                (byte)0x2E, (byte)0x44, (byte)0x44, (byte)0x46, (byte)0x30, (byte)0x31
        };

        //6F20840E315041592E5359532E4444463031A50E5F2D046B6F656E9F110101880101

        assertEquals(tlv.toString(), answer);
        assertEquals(tlv.getTag(), "84");
        assertEquals(tlv.getLength(), 14);
        assertEquals(tlv.getValue(), "315041592E5359532E4444463031");
        assertArrayEquals(tlv.getValueBytes(), value);
    }


    @Test
    public void test_tlv_constructor_002() {
        byte[] tag = { (byte)0x84 };
        byte[] value = {
                (byte)0x31, (byte)0x50, (byte)0x41, (byte)0x59, (byte)0x2E, (byte)0x53, (byte)0x59, (byte)0x53,
                (byte)0x2E, (byte)0x44, (byte)0x44, (byte)0x46, (byte)0x30, (byte)0x31
        };
        Tlv tlv = new Tlv(tag, value);
        String answer = "840E315041592E5359532E4444463031";

        assertEquals(tlv.toString(), answer);
        assertEquals(tlv.getTag(), "84");
        assertEquals(tlv.getLength(), 14);
        assertEquals(tlv.getValue(), "315041592E5359532E4444463031");
        assertArrayEquals(tlv.getValueBytes(), value);
    }

    @Test
    public void test_tlv_print_001() {
        Tlv tlv = new Tlv("84", "315041592E5359532E4444463031");
        tlv.print();
    }

}
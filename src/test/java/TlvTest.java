import io.github.coolbong.tlv.Tlv;
import io.github.coolbong.tlv.TlvParser;
import org.junit.Test;

import java.util.List;

import static io.github.coolbong.tlv.Hex.toBytes;
import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.*;

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

        assertEquals(answer, tlv.toString());
        assertEquals("84", tlv.getTag());
        assertEquals(14, tlv.getLength());
        assertEquals("315041592E5359532E4444463031", tlv.getValue());
        assertArrayEquals(tag, tlv.getTagBytes());
        assertArrayEquals(new byte[]{ (byte)0x0e }, tlv.getLengthBytes());
        assertArrayEquals(value, tlv.getValueBytes());
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

        assertEquals(answer, tlv.toString());
        assertEquals("84", tlv.getTag());
        assertEquals(14, tlv.getLength());
        assertEquals("315041592E5359532E4444463031", tlv.getValue());
        assertArrayEquals(tag, tlv.getTagBytes());
        assertArrayEquals(new byte[]{ (byte)0x0e }, tlv.getLengthBytes());
        assertArrayEquals(value, tlv.getValueBytes());
    }

    @Test
    public void test_tlv_constructor_003() {
        Tlv tlv = new Tlv("8000", "11223344", Tlv.DGI);

        assertEquals("8000", tlv.getTag());
        assertEquals(4, tlv.getLength());
        assertEquals("11223344", tlv.getValue());
    }

    @Test
    public void test_tlv_constructor_004() {
        byte[] t = {(byte)0x94};
        byte[] v = new byte[128];

        Tlv tlv = new Tlv(t, v);

        byte[] l = {(byte)0x81, (byte)0x80};

        assertEquals("94", tlv.getTag());
        assertEquals(128, tlv.getLength());
        assertArrayEquals(l, tlv.getLengthBytes());
    }

    @Test
    public void test_tlv_constructor_005() {
        byte[] t = {(byte)0x81, (byte)0x01};
        byte[] v = new byte[255];

        Tlv tlv = new Tlv(t, v, Tlv.DGI);

        byte[] l = {(byte)0xff, (byte)0x00, (byte)0xff};

        assertEquals("8101", tlv.getTag());
        assertEquals(255, tlv.getLength());
        assertArrayEquals(l, tlv.getLengthBytes());
    }

    @Test
    public void test_tlv_parse_buf_001() {
        String resp = "6F20840E315041592E5359532E4444463031A50E5F2D046B6F656E9F110101880101";

        Tlv tlv = Tlv.parse(toBytes(resp));
        assertNotNull(tlv);

        assertEquals("6F", tlv.getTag());
        assertEquals(32, tlv.getLength());
        assertEquals("840E315041592E5359532E4444463031A50E5F2D046B6F656E9F110101880101", tlv.getValue());
    }

    @Test
    public void test_tlv_parse_001() {
        String resp = "6F20840E315041592E5359532E4444463031A50E5F2D046B6F656E9F110101880101";

        Tlv tlv = Tlv.parse(resp);

        assertEquals("6F", tlv.getTag());
        assertEquals(32, tlv.getLength());
        assertEquals("840E315041592E5359532E4444463031A50E5F2D046B6F656E9F110101880101", tlv.getValue());
    }

    @Test
    public void test_tlv_parse_002() {
        // parse dgi
        Tlv tlv = Tlv.parse("910235A533BF0C3061164F07D410000001501050084E4557204B4C534387010161164F07D410000001101050084F4C44204B4C5343870102", Tlv.DGI);

        assertEquals("9102", tlv.getTag());
        assertEquals(0x35, tlv.getLength());
    }


    @Test
    public void test_tlv_parse_003() {
        // parse dgi
        Tlv tlv = Tlv.parse("800030FE5960267173B426A62024AF18E7D9783AA7393DE680CEA2194CFCB478201095EA054A594FD07C02843E11113B7A3AB0", Tlv.DGI);

        assertEquals("8000", tlv.getTag());
        assertEquals(0x30, tlv.getLength());
        assertEquals("FE5960267173B426A62024AF18E7D9783AA7393DE680CEA2194CFCB478201095EA054A594FD07C02843E11113B7A3AB0", tlv.getValue());
    }

    @Test
    public void test_tlv_parse_004() {
        // multibyte length check
        Tlv tlv;
        tlv = Tlv.parse("82027800");
        assertArrayEquals(tlv.getLengthBytes(), new byte[]{0x02});

        tlv = Tlv.parse("8281027800");
        assertArrayEquals(new byte[]{(byte)0x81, (byte)0x02}, tlv.getLengthBytes());
        assertEquals("8281027800", tlv.toString());
    }

    @Test
    public void test_tlv_parse_005() {
        String fciPropTemp = "A50E8801015F2D046B6F656E9F110101";

        Tlv tlv = Tlv.parse(fciPropTemp);
        assertEquals("A5", tlv.getTag());
        assertEquals(14, tlv.getLength());

        List<Tlv> child = tlv.getChild();

        Tlv sfiTlv = child.get(0);
        assertEquals("88", sfiTlv.getTag());
        assertEquals(0x01, sfiTlv.getLength());
        assertEquals("01", sfiTlv.getValue());

        Tlv langPrefTlv = child.get(1);
        assertEquals("5F2D", langPrefTlv.getTag());
        assertEquals(4, langPrefTlv.getLength());
        assertEquals("6B6F656E", langPrefTlv.getValue());

        Tlv issuerCodeTlv = child.get(2);
        assertEquals("9F11", issuerCodeTlv.getTag());
        assertEquals(1, issuerCodeTlv.getLength());
        assertEquals("01", issuerCodeTlv.getValue());
    }

    @Test
    public void test_tlv_parse_006() {
        // dgi
        String dgi = "8000FF00081122334455667788";
        Tlv tlv = Tlv.parse(dgi, Tlv.DGI);

        assertEquals("8000", tlv.getTag());
        assertEquals(8, tlv.getLength());
        assertEquals("1122334455667788", tlv.getValue());
    }

    @Test
    public void test_tlv_parse_007() {
        // 2byte tag
        Tlv tlv = Tlv.parse("9F110101");

        assertEquals("9F11", tlv.getTag());
        assertEquals(1, tlv.getLength());
        assertEquals("01", tlv.getValue());
    }

    @Test
    public void test_tlv_parse_008() {
        // 1byte tag
        Tlv tlv = Tlv.parse("880101");

        assertEquals("88", tlv.getTag());
        assertEquals(1, tlv.getLength());
        assertEquals("01", tlv.getValue());
    }


    @Test
    public void test_tlv_is_constructed_001() {
        // 1byte tag
        Tlv tlv = Tlv.parse("880101");
        assertFalse(tlv.isConstructed());
    }

    @Test
    public void test_tlv_is_constructed_002() {
        // 1byte tag
        Tlv tlv = Tlv.parse("61094F07A0000000031010");

        assertTrue(tlv.isConstructed());
        assertEquals("61", tlv.getTag());
        assertEquals(9, tlv.getLength());
        assertEquals("4F07A0000000031010", tlv.getValue());
    }

    @Test
    public void test_tlv_is_constructed_003() {
        String fci = "6F3B8407A0000008781010A530500B4B4C5343204372656469748701019F38035F2A025F2D046B6F656E9F1101019F12044B4C5343BF0C059F4D02150A";
        Tlv tlv = Tlv.parse(fci);

        assertTrue(tlv.isConstructed());
        assertEquals("6F", tlv.getTag());
        assertEquals(0x3b, tlv.getLength());
    }

    @Test
    public void test_tlv_to_bytes_001() {
        byte[] answer = { (byte)0x88, (byte)0x01, (byte)0x01 };
        Tlv tlv = Tlv.parse("880101");

        assertArrayEquals(answer, tlv.toBytes());
    }


    @Test
    public void test_tlv_parse_tag_001() {
        byte[] text = { (byte)0x88, (byte)0x01, (byte)0x01 };
        byte[] tag = Tlv.parseTag(text, 0);
        byte[] answer = {(byte)0x88};

        assertArrayEquals(answer, tag);
    }

    @Test
    public void test_tlv_find_001() {
        String resp = "6F20840E315041592E5359532E4444463031A50E5F2D046B6F656E9F110101880101";

        Tlv tlv = Tlv.parse(resp);
        Tlv tag88 = tlv.find("88");

        assertEquals("88", tag88.getTag());
        assertEquals(1, tag88.getLength());
        assertEquals("01", tag88.getValue());
    }

    @Test
    public void test_tlv_find_002() {
        String resp = "77319F2701809F360200019F2608CA800E798292C38D9F101A1110A00103220001DAC000000000000000FF00000000000000FF";
        Tlv tlv = Tlv.parse(resp);
        Tlv child;

        child = tlv.find("9f27"); // find cid
        assertEquals("9F27", child.getTag());
        assertEquals(1, child.getLength());
        assertEquals("80", child.getValue());

        child = tlv.find("9f36"); // find cid
        assertEquals("9F36", child.getTag());
        assertEquals(2, child.getLength());
        assertEquals("0001", child.getValue());

        child = tlv.find("9f26"); // find ac
        assertEquals("9F26", child.getTag());
        assertEquals(8, child.getLength());
        assertEquals("CA800E798292C38D", child.getValue());
    }

    @Test
    public void test_tlv_find_003() {
        String pseFci = "6F20840E315041592E5359532E4444463031A50E8801015F2D046B6F656E9F110101";
        Tlv tlv = Tlv.parse(pseFci);

        Tlv dfNameTlv = tlv.find("84");
        assertEquals("84", dfNameTlv.getTag());
        assertEquals(0x0E, dfNameTlv.getLength());
        assertEquals("315041592E5359532E4444463031", dfNameTlv.getValue());

        Tlv sfiTlv = tlv.find("88");
        assertEquals("88", sfiTlv.getTag());
        assertEquals(0x01, sfiTlv.getLength());
        assertEquals("01", sfiTlv.getValue());

        Tlv langPrefTlv = tlv.find("5F2D");
        assertEquals("5F2D", langPrefTlv.getTag());
        assertEquals(4, langPrefTlv.getLength());
        assertEquals("6B6F656E", langPrefTlv.getValue());

        Tlv issuerCodeTlv = tlv.find("9F11");
        assertEquals("9F11", issuerCodeTlv.getTag());
        assertEquals(1, issuerCodeTlv.getLength());
        assertEquals("01", issuerCodeTlv.getValue());
    }

    @Test
    public void test_tlv_find_004() {
        String dummy  = "2006200482021800";
        Tlv tlv = Tlv.parse(dummy);

        Tlv item = tlv.find("20");

        assertEquals("2006200482021800", item.toString());
    }

    @Test
    public void test_tlv_find_005() {
        String dummy  = "2006200482021800";
        Tlv tlv = Tlv.parse(dummy);

        Tlv item = tlv.find("82");

        assertEquals("82021800", item.toString());
    }

    @Test
    public void test_tlv_find_all_001() {
        String pseRecord = "703061164F07D410000001501050084E4557204B4C534387010161164F07D410000001101050084F4C44204B4C5343870102";

        Tlv tlv = Tlv.parse(pseRecord);
        List<Tlv> appTemplate =  tlv.findAll("61"); // application template

        assertEquals(2, appTemplate.size());
        Tlv app1 = appTemplate.get(0);
        Tlv app2 = appTemplate.get(1);

        assertEquals("61", app1.getTag());
        assertEquals(22, app1.getLength());
        assertEquals("4F07D410000001501050084E4557204B4C5343870101", app1.getValue());

        assertEquals("61", app2.getTag());
        assertEquals(22, app2.getLength());
        assertEquals("4F07D410000001101050084F4C44204B4C5343870102", app2.getValue());
    }




    @Test
    public void test_tlv_get_bytes_001() {
        byte[] aip = { (byte)0x82, (byte)0x02, (byte)0x18, (byte)0x00 };

        Tlv tlv = Tlv.parse("82021800");
        byte[] ret = tlv.toBytes();
        assertArrayEquals(aip, ret);

        byte[] t = {(byte)0x82};
        byte[] v = {(byte)0x18, (byte)0x00};
        tlv = new Tlv(t, v);
        ret = tlv.toBytes();
        assertArrayEquals(aip, ret);

        tlv = new Tlv("82", "1800");
        ret = tlv.toBytes();
        assertArrayEquals(aip, ret);
    }

    @Test
    public void test_tlv_get_bytes_002() {
        byte[] aip = { (byte)0x82, (byte)0x81, (byte)0x02, (byte)0x18, (byte)0x00 };


        byte[] t = {(byte)0x82};
        byte[] l = {(byte)0x81, (byte)0x02};
        byte[] v = {(byte)0x18, (byte)0x00};
        Tlv tlv = new Tlv(t, l, v);
        byte[] ret = tlv.toBytes();
        assertArrayEquals(aip, ret);

        tlv = Tlv.parse("8281021800");
        ret = tlv.toBytes();
        assertArrayEquals(aip, ret);
    }




    @Test
    public void test_tlv_gp_get_status() {
        Tlv tlv;
        tlv = new Tlv("20", "DF010100DF020100DF030100DF1104FFFFFFFFDF1204FFFFFFFFDF130400FFFFFF");

        Tlv child = tlv.getChild().get(0);
        assertEquals("DF01", child.getTag());
        assertEquals(1, child.getLength());
        assertEquals("00", child.getValue());

        tlv = new Tlv("20", "BF3481A8DF0112000000000000000000000000000038000000DF0212000000000000000000000000000000000000DF0312000000000000000000000000000000000000DF0412000000000000000000000000000000000000DF0512000000000000000000000000000000000000DF0612000000000000000000000000000000000000DF0712000000000000000000000000000000000000DF0812000000000000000000000000000000000000");
        child = tlv.find("DF01");
        assertEquals("DF01", child.getTag());
        assertEquals(18, child.getLength());
        assertEquals("000000000000000000000000000038000000", child.getValue());

    }


    @Test
    public void test_tlv_parse_byte_array() {
        Tlv tlv = Tlv.parse(null, 0, Tlv.EMV);
        assertNull(tlv);
    }

}

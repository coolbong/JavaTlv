import io.github.coolbong.util.Tlv;
import logger.ConsoleLogger;
import org.junit.Test;

import java.util.List;

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

        assertEquals(tlv.toString(), answer);
        assertEquals(tlv.getTag(), "84");
        assertEquals(tlv.getLength(), 14);
        assertEquals(tlv.getValue(), "315041592E5359532E4444463031");
        assertArrayEquals(tlv.getTagBytes(), tag);
        assertArrayEquals(tlv.getLengthBytes(), new byte[]{ (byte)0x0e });
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
        assertArrayEquals(tlv.getTagBytes(), tag);
        assertArrayEquals(tlv.getLengthBytes(), new byte[]{ (byte)0x0e });
        assertArrayEquals(tlv.getValueBytes(), value);
    }

    @Test
    public void test_tlv_constructor_003() {
        Tlv tlv = new Tlv("8000", "11223344", Tlv.DGI);

        assertEquals("8000", tlv.getTag());
        assertEquals(4, tlv.getLength());
        assertEquals("11223344", tlv.getValue());
    }

    @Test
    public void test_tlv_print_001() {
        Tlv tlv = new Tlv("84", "315041592E5359532E4444463031");
        tlv.print();
    }


    @Test
    public void test_tlv_parse_001() {
        String resp = "6F20840E315041592E5359532E4444463031A50E5F2D046B6F656E9F110101880101";

        Tlv tlv = Tlv.parse(resp);

        assertEquals(tlv.getTag(), "6F");
        assertEquals(tlv.getLength(), 32);
        assertEquals(tlv.getValue(), "840E315041592E5359532E4444463031A50E5F2D046B6F656E9F110101880101");
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
        assertArrayEquals(tlv.getLengthBytes(), new byte[]{(byte)0x81, (byte)0x02});
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
        //tlv.print();
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
        assertEquals(child.getTag(), "9F27");
        assertEquals(child.getLength(), 1);
        assertEquals(child.getValue(), "80");

        child = tlv.find("9f36"); // find cid
        assertEquals(child.getTag(), "9F36");
        assertEquals(child.getLength(), 2);
        assertEquals(child.getValue(), "0001");

        child = tlv.find("9f26"); // find ac
        assertEquals(child.getTag(), "9F26");
        assertEquals(child.getLength(), 8);
        assertEquals(child.getValue(), "CA800E798292C38D");
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
    public void test_tlv_log_001() {
        ConsoleLogger logger = new ConsoleLogger();

        Tlv tlv = Tlv.parse("8281021800");
        tlv.log(logger);
        //tlv.print();

        tlv = Tlv.parse("6F3B8407A0000008781010A530500B4B4C5343204372656469748701019F38035F2A025F2D046B6F656E9F1101019F12044B4C5343BF0C059F4D02150A");
        tlv.log(logger);
        //tlv.print();

        tlv = Tlv.parse("770E8202180094080801010018010200");
        tlv.log(logger);
        //tlv.print();

        tlv = Tlv.parse("703F57139490192619045993D28066011902275500000F5F2014515352422F4449574C20494C4D202020202020209F1F1031393032323030373535303030303030");
        tlv.log(logger);

        tlv = Tlv.parse("70765A0894901926190459935F3401015F25032307265F24032806305F280204109F0702FF008C1B9F02069F03069F1A0295055F2A029A039C019F37049F35019F34038D0991088A0295059F37048E10000000000000000002011E0302031F009F0E0500100000009F0F05B060FC98009F0D05B060BC8800");
        tlv.log(logger);

        tlv = Tlv.parse("700A5F300206019F08020001");
        tlv.log(logger);
    }


}

import io.github.coolbong.tlv.DolParser;
import io.github.coolbong.tlv.Tlv;
import io.github.coolbong.tlv.TlvLogger;
import logger.ConsoleLogger;
import logger.DummyLogger;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class DolTest {


    private TlvLogger logger;

    @Before
    public void setUp() {
        logger = new DummyLogger();
    }


    @Test
    public void test_dol_test_001() {
        // dol parser make tlv with default value '00'
        DolParser parser = new DolParser(logger);
        List<Tlv> tlvs = parser.parse("9F02069F03069F1A0295055F2A029A039C019F37049F35019F45029F3403");
        Tlv tlv = tlvs.get(0);

        assertEquals("9F0206000000000000", tlv.toString());
        assertEquals("9F02", tlv.getTag());
        assertEquals(6, tlv.getLength());
        assertEquals("000000000000", tlv.getValue());
    }

    @Test
    public void test_dol_test_002() {
        DolParser parser = new DolParser(logger);

        int length = parser.dolRelatedDataLength("9F02069F03069F1A0295055F2A029A039C019F37049F35019F45029F3403");

        assertEquals(35, length);

    }

    @Test
    public void test_dol_test_003() {
        // // dol parser make tlv with values
        DolParser parser = new DolParser(logger);

        List<Tlv> tlvs = parser.parse("9F02069F03069F1A0295055F2A029A039C019F37049F35019F34039B02",
                "00000000150000000000000004108000000000041021071200462C127E223F00000800");

        Tlv tlv;

        //9F02 06(6) 000000001500
        tlv = tlvs.get(0);
        assertEquals("9F02", tlv.getTag());
        assertEquals(6, tlv.getLength());
        assertEquals("000000001500", tlv.getValue());

        //9F03 06(6) 000000000000
        tlv = tlvs.get(1);
        assertEquals("9F03", tlv.getTag());
        assertEquals(6, tlv.getLength());
        assertEquals("000000000000", tlv.getValue());

        //9F1A 02(2) 0410
        tlv = tlvs.get(2);
        assertEquals("9F1A", tlv.getTag());
        assertEquals(2, tlv.getLength());
        assertEquals("0410", tlv.getValue());

        //95 05(5) 8000000000
        tlv = tlvs.get(3);
        assertEquals("95", tlv.getTag());
        assertEquals(5, tlv.getLength());
        assertEquals("8000000000", tlv.getValue());

        //5F2A 02(2) 0410
        tlv = tlvs.get(4);
        assertEquals("5F2A", tlv.getTag());
        assertEquals(2, tlv.getLength());
        assertEquals("0410", tlv.getValue());

        //9A 03(3) 210712
        tlv = tlvs.get(5);
        assertEquals("9A", tlv.getTag());
        assertEquals(3, tlv.getLength());
        assertEquals("210712", tlv.getValue());

        //9C 01(1) 00
        tlv = tlvs.get(6);
        assertEquals("9C", tlv.getTag());
        assertEquals(1, tlv.getLength());
        assertEquals("00", tlv.getValue());

        //9F37 04(4) 462C127E
        tlv = tlvs.get(7);
        assertEquals("9F37", tlv.getTag());
        assertEquals(4, tlv.getLength());
        assertEquals("462C127E", tlv.getValue());

        //9F35 01(1) 22
        tlv = tlvs.get(8);
        assertEquals("9F35", tlv.getTag());
        assertEquals(1, tlv.getLength());
        assertEquals("22", tlv.getValue());

        //9F34 03(3) 3F0000
        tlv = tlvs.get(9);
        assertEquals("9F34", tlv.getTag());
        assertEquals(3, tlv.getLength());
        assertEquals("3F0000", tlv.getValue());

        //9B 02(2) 0800
        tlv = tlvs.get(10);
        assertEquals("9B", tlv.getTag());
        assertEquals(2, tlv.getLength());
        assertEquals("0800", tlv.getValue());
    }


    @Test
    public void test_dol_test_004() {
        // multi bytes length
        DolParser parser = new DolParser(logger);
        List<Tlv> tlvs = parser.parse("828102", "1800");
        Tlv tlv = tlvs.get(0);

        assertEquals("8281021800", tlv.toString());
        assertEquals("82", tlv.getTag());
        assertEquals(2, tlv.getLength());
        assertEquals("1800", tlv.getValue());
    }


    @Test
    public void test_dol_test_005() {
        // multi bytes length
        DolParser parser = new DolParser(logger);
        int length = parser.dolRelatedDataLength("828102");

        assertEquals(2, length);
    }


    @Test
    public void test_dol_test_006() {
        DolParser parser = new DolParser(logger);
        // Invalid dol parse have to return empty list
        List<Tlv> tlvs = parser.parse("8");

        assertEquals(Collections.emptyList(), tlvs);
    }

    @Test
    public void test_dol_test_007() {
        DolParser parser = new DolParser(logger);
        // Invalid dol parse have to return empty list
        List<Tlv> tlvs = parser.parse("8", "9");

        assertEquals(Collections.emptyList(), tlvs);
    }

    @Test
    public void test_dol_test_008() {
        // multi bytes length
        DolParser parser = new DolParser(logger);
        int length = parser.dolRelatedDataLength("0");
        assertEquals(0, length);
    }

    @Test
    public void test_dol_test_009() {
        // multi bytes length
        DolParser parser = new DolParser();
        int length = parser.dolRelatedDataLength("");
        assertEquals(0, length);
    }


    @Test
    public void test_dol_test_010() {
        // multi bytes length
        DolParser parser = new DolParser();
        int length = parser.dolRelatedDataLength("8202");
        assertEquals(2, length);
    }



    @Test
    public void test_dol_test_011() {

        DolParser parser = new DolParser();
        List<Tlv> tlvs = parser.parse((byte [])null, (byte [])null);

        assertEquals(Collections.emptyList(), tlvs);
    }

    @Test
    public void test_dol_test_012() {

        DolParser parser = new DolParser();
        List<Tlv> tlvs = parser.parse(new byte[0], new byte[0]);

        assertEquals(Collections.emptyList(), tlvs);
    }


    @Test
    public void test_dol_test_013() {

        DolParser parser = new DolParser();
        List<Tlv> tlvs = parser.parse("8202");

        Tlv tlv = tlvs.get(0);

        assertEquals("82020000", tlv.toString());
        assertEquals("82", tlv.getTag());
        assertEquals(2, tlv.getLength());
        assertEquals("0000", tlv.getValue());
    }

}

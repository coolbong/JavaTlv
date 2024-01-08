import io.github.coolbong.tlv.DolParser;
import io.github.coolbong.tlv.Tlv;
import io.github.coolbong.tlv.TlvLogger;
import io.github.coolbong.tlv.TlvParser;
import logger.ConsoleLogger;
import logger.DummyLogger;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertArrayEquals;
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
        ArrayList<Tlv> tlvs = parser.parse("9F02069F03069F1A0295055F2A029A039C019F37049F35019F45029F3403");
        Tlv tlv = tlvs.get(0);
        //tlv.log(logger);

        assertEquals(tlv.toString(), "9F0206000000000000");
        assertEquals(tlv.getTag(), "9F02");
        assertEquals(tlv.getLength(), 6);
        assertEquals(tlv.getValue(), "000000000000");
    }

    @Test
    public void test_dol_test_002() {
        DolParser parser = new DolParser(logger);

        int length = parser.dolRelatedDataLength("9F02069F03069F1A0295055F2A029A039C019F37049F35019F45029F3403");

        assertEquals(35, length);

//        assertEquals(tlv.toString(), "9F0206000000000000");
//        assertEquals(tlv.getTag(), "9F02");
//        assertEquals(tlv.getLength(), 6);
//        assertEquals(tlv.getValue(), "000000000000");
    }


    @Test
    public void test_dol_test_003() {
        // // dol parser make tlv with values
        DolParser parser = new DolParser(logger);

        ArrayList<Tlv> tlvs = parser.parse("9F02069F03069F1A0295055F2A029A039C019F37049F35019F34039B02",
                "00000000150000000000000004108000000000041021071200462C127E223F00000800");

        Tlv tlv;

        //9F02 06(6) 000000001500
        tlv = tlvs.get(0);
        assertEquals(tlv.getTag(), "9F02");
        assertEquals(tlv.getLength(), 6);
        assertEquals(tlv.getValue(), "000000001500");

        //9F03 06(6) 000000000000
        tlv = tlvs.get(1);
        assertEquals(tlv.getTag(), "9F03");
        assertEquals(tlv.getLength(), 6);
        assertEquals(tlv.getValue(), "000000000000");

        //9F1A 02(2) 0410
        tlv = tlvs.get(2);
        assertEquals(tlv.getTag(), "9F1A");
        assertEquals(tlv.getLength(), 2);
        assertEquals(tlv.getValue(), "0410");

        //95 05(5) 8000000000
        tlv = tlvs.get(3);
        assertEquals(tlv.getTag(), "95");
        assertEquals(tlv.getLength(), 5);
        assertEquals(tlv.getValue(), "8000000000");

        //5F2A 02(2) 0410
        tlv = tlvs.get(4);
        assertEquals(tlv.getTag(), "5F2A");
        assertEquals(tlv.getLength(), 2);
        assertEquals(tlv.getValue(), "0410");

        //9A 03(3) 210712
        tlv = tlvs.get(5);
        assertEquals(tlv.getTag(), "9A");
        assertEquals(tlv.getLength(), 3);
        assertEquals(tlv.getValue(), "210712");

        //9C 01(1) 00
        tlv = tlvs.get(6);
        assertEquals(tlv.getTag(), "9C");
        assertEquals(tlv.getLength(), 1);
        assertEquals(tlv.getValue(), "00");

        //9F37 04(4) 462C127E
        tlv = tlvs.get(7);
        assertEquals(tlv.getTag(), "9F37");
        assertEquals(tlv.getLength(), 4);
        assertEquals(tlv.getValue(), "462C127E");

        //9F35 01(1) 22
        tlv = tlvs.get(8);
        assertEquals(tlv.getTag(), "9F35");
        assertEquals(tlv.getLength(), 1);
        assertEquals(tlv.getValue(), "22");

        //9F34 03(3) 3F0000
        tlv = tlvs.get(9);
        assertEquals(tlv.getTag(), "9F34");
        assertEquals(tlv.getLength(), 3);
        assertEquals(tlv.getValue(), "3F0000");

        //9B 02(2) 0800
        tlv = tlvs.get(10);
        assertEquals(tlv.getTag(), "9B");
        assertEquals(tlv.getLength(), 2);
        assertEquals(tlv.getValue(), "0800");
    }

}

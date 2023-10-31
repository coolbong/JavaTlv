import io.github.coolbong.tlv.DolParser;
import io.github.coolbong.tlv.Tlv;
import io.github.coolbong.tlv.TlvParser;
import logger.ConsoleLogger;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class DolTest {


    private ConsoleLogger logger;

    @Before
    public void setUp() {
        logger = new ConsoleLogger();
    }

    @Test
    public void test_dol_test_001() {
        //Dol.parse()
        DolParser parser = new DolParser(logger);
        ArrayList<Tlv> tlvs = parser.parse("9F02069F03069F1A0295055F2A029A039C019F37049F35019F45029F3403");
        Tlv tlv = tlvs.get(0);
        tlv.log(logger);

        assertEquals(tlv.toString(), "9F0206000000000000");
        assertEquals(tlv.getTag(), "9F02");
        assertEquals(tlv.getLength(), 6);
        assertEquals(tlv.getValue(), "000000000000");
    }
}

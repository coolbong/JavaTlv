import io.github.coolbong.tlv.Tlv;
import io.github.coolbong.tlv.TlvLogger;
import io.github.coolbong.tlv.TlvParser;
import logger.DummyLogger;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;

public class TlvNegativeTest {

    private TlvLogger logger;

    @Before
    public void setUp() {
        logger = new DummyLogger();
    }

    @Test
    public void tlv_negative_test_parse_001() {
        String wrongtlv = "880501";

        Tlv tlv = Tlv.parse(wrongtlv);
        assertNull(tlv);
    }

    @Test
    public void tlv_negative_test_parse_002() {
        String zerolengthvalue = "8800";
        Tlv tlv = Tlv.parse(zerolengthvalue);
        assertEquals(zerolengthvalue, tlv.toString());
    }


    @Test
    public void tlv_ne_parse_001() {
        String[] fcis = {
                "6F13840E325041592E5359532E4444463031A50100",
                "6F16840E325041592E5359532E4444463031A533BF0C0100",
                "6F46840E325041592E5359532E4444463031A53461164F07D410000001501050084E4557204B4C534387010161164F07D410000001401050084A5553544F554348870102BF0C0100",
                "6F46840E325041592E5359532E4444463031BF0C3061164F07D410000001501050084E4557204B4C534387010161164F07D410000001401050084A5553544F554348870102A50100",
                "6F47840E325041592E5359532E4444463031A533BF0C3061164F07D410000001501050084E4557204B4C534387010161164F07D410000001401050084A5553544F5543488701020000"
        };

        TlvParser parser = new TlvParser(logger);
        Tlv tlv = parser.parse("6F13840E325041592E5359532E4444463031A50100");
        tlv.log(logger);
    }

    @Test
    public void tlv_ne_parse_002() {
        TlvParser parser = new TlvParser(logger);
        Tlv tlv = parser.parse("6F16840E325041592E5359532E4444463031A533BF0C0100");
        tlv.log(logger);
    }

    @Test
    public void tlv_ne_parse_003() {
        TlvParser parser = new TlvParser(logger);
        Tlv tlv = parser.parse("6F46840E325041592E5359532E4444463031A53461164F07D410000001501050084E4557204B4C534387010161164F07D410000001401050084A5553544F554348870102BF0C0100");
        tlv.log(logger);
    }

    @Test
    public void tlv_ne_parse_004() {
        TlvParser parser = new TlvParser(logger);
        Tlv tlv = parser.parse("6F46840E325041592E5359532E4444463031BF0C3061164F07D410000001501050084E4557204B4C534387010161164F07D410000001401050084A5553544F554348870102A50100");
        tlv.log(logger);
    }

    @Test
    public void tlv_ne_parse_005() {
        TlvParser parser = new TlvParser(logger);
        Tlv tlv = parser.parse("6F47840E325041592E5359532E4444463031A533BF0C3061164F07D410000001501050084E4557204B4C534387010161164F07D410000001401050084A5553544F5543488701020000");
        tlv.log(logger);
    }
}

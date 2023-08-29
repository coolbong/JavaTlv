import io.github.coolbong.tlv.Tlv;
import io.github.coolbong.tlv.TlvParser;
import logger.ConsoleLogger;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.assertEquals;

public class DgiTest {


    private ConsoleLogger logger;

    @Before
    public void setUp() {
        logger = new ConsoleLogger();
    }


    @Test
    public void tlv_dgi_test_001() {

        TlvParser parser = new TlvParser(logger);
        Tlv tlv = parser.parse("010122702057125981786067381230D29125010000000000005F20094B4C53432043415244", Tlv.DGI);
        tlv.log(logger);

        assertEquals("0101", tlv.getTag());
        assertEquals(34, tlv.getLength());
        assertEquals("702057125981786067381230D29125010000000000005F20094B4C53432043415244", tlv.getValue());

        tlv = parser.parse(tlv.getValue());
        tlv.log(logger);
        assertEquals("70", tlv.getTag());
        assertEquals(32, tlv.getLength());
        assertEquals("57125981786067381230D29125010000000000005F20094B4C53432043415244", tlv.getValue());

    }
}

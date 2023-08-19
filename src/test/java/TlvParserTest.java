import io.github.coolbong.tlv.Tlv;
import io.github.coolbong.tlv.TlvParser;
import logger.ConsoleLogger;
import org.junit.Before;
import org.junit.Test;

public class TlvParserTest {

    private ConsoleLogger logger;

    @Before
    public void setUp() {
         logger = new ConsoleLogger();
    }


    @Test
    public void test_tlv_parse_001() {
        String resp = "6F3B8407A0000008781010A530500B4B4C5343204372656469748701019F38035F2A025F2D046B6F656E9F1101019F12044B4C5343BF0C059F4D02150A";
        TlvParser parser = new TlvParser(logger);
        Tlv tlv = parser.parse(resp);
        tlv.log(logger);
    }


    @Test
    public void test_dummy_bytes_skip_001() {
        String gpoResp = "77110000008202180094080801010018010200";
        TlvParser parser = new TlvParser(logger);

        Tlv tlv = parser.parse(gpoResp);
        tlv.log(logger);

    }
}

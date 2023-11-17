import io.github.coolbong.tlv.Tlv;
import io.github.coolbong.tlv.TlvParser;
import junit.framework.Assert;
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
    public void test_tlv_parser_002() {
        String ppse_resp = "6F52840E325041592E5359532E4444463031A540BF0C3D611E4F07D410000001501050104B4C5343204455414C20435245444954870101611B4F07D4100000014010500D4B4C5343204A5553544F554348870102";
        TlvParser parser = new TlvParser(logger);
        Tlv tlv = parser.parse(ppse_resp);
        tlv.log(logger);
    }

    @Test
    public void test_tlv_parser_003() {
        String pse_record = "705C61294F07D4100000015010500C4B4C534320505245504149449F120C4B4C53432050524550414944870101612F4F07D4100000011010500F4B4C5343205052505920414C4941539F120F4B4C5343205052505920414C494153870102";
        TlvParser parser = new TlvParser(logger);
        Tlv tlv = parser.parse(pse_record);
        tlv.log(logger);
    }


    @Test
    public void test_dummy_bytes_skip_001() {
        String gpoResp = "77110000008202180094080801010018010200";
        TlvParser parser = new TlvParser(logger);

        Tlv tlv = parser.parse(gpoResp);
        tlv.log(logger);
    }

    @Test
    public void test_tlv_parser_err_001() {
        String resp = "6F448407A0000008781010A539500B4B4C5343204352454449548701015F2D046B6F656E9F1101019F120E4E48204B4C534320435245444954BF0C0A39463444303231353041";
        TlvParser parser = new TlvParser(logger);
        Tlv tlv = parser.parse(resp);
        tlv.log(logger);
    }

    @Test
    public void test_tlv_parser_err_002() {
        String resp = "6F53840E325041592E5359532E4444463031A541BF0C1E611C4F07A0000008781010500E4B4C534320444920435245444954870101BF0C1D611B4F07D4100000014010500D4B4C5343204A5553544F554348870102";
        TlvParser parser = new TlvParser(logger);
        Tlv tlv = parser.parse(resp);
        tlv.log(logger);

        //Tlv tlv = Tlv.parse(resp);
        //tlv.print();
    }

    @Test
    public void test_tlv_parser_err_003() {
        String resp = "702057185981786067381230D29125010000000000005F20094B4C53432043415244";
        TlvParser parser = new TlvParser(logger);
        Tlv tlv = parser.parse(resp);
        tlv.log(logger);
    }

    @Test
    public void test_tlv_parser_err_004() {
        // invalid '6F' length
        String resp = "6F398407D4100000015010A52D50084E4557204B4C53439F38035F2A028701015F2D046B6F656E9F1101019F12044B4C5343BF0C059F4D02150A";
        TlvParser parser = new TlvParser(logger);
        Tlv tlv = parser.parse(resp);

        Assert.assertNull(tlv);
    }



    @Test
    public void test_tlv_filler_bytes_001() {
        String resp = "7081F857139409119700015643D49126012000014000000F0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
    }

}

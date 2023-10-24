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

    @Test
    public void tlv_dgi_test_pse_001() {

        TlvParser parser = new TlvParser(logger);
        Tlv tlv = parser.parse("010132703061164F07A000000878101050084E4557204B4C534387010161164F07D410000001101050084F4C44204B4C5343870102", Tlv.DGI);
        tlv.log(logger);

        assertEquals("0101", tlv.getTag());
        assertEquals(50, tlv.getLength());
        assertEquals("703061164F07A000000878101050084E4557204B4C534387010161164F07D410000001101050084F4C44204B4C5343870102", tlv.getValue());

        tlv = parser.parse(tlv.getValue());
        tlv.log(logger);
        assertEquals("70", tlv.getTag());
        assertEquals(48, tlv.getLength());
        assertEquals("61164F07A000000878101050084E4557204B4C534387010161164F07D410000001101050084F4C44204B4C5343870102", tlv.getValue());

        tlv.print();
    }

    @Test
    public void tlv_dgi_test_record_001() {
        TlvParser parser = new TlvParser(logger);
        Tlv tlv = parser.parse("704D57139409119700015643D25116012000014000000F5F201A5041524B204A414545554E2020202020202020202F20202020209F1F18323030303020202020202020203030313430303030303030");
        tlv.log(logger);
        //tlv.print();

        tlv = parser.parse("707A5F24032511305F25032011045A0894091197000156435F3401019F0702AB008C1B9F02069F03069F1A0295055F2A029A039C019F37049F35019F34038D0991088A0295059F37048E10000000000000000002011E0302031F009F0D05B060BC88009F0E0500100000009F0F05B060FC98005F280204109F4A0182");
        tlv.log(logger);

        tlv = parser.parse("700A5F300206019F08020001");
        tlv.log(logger);

        tlv = new Tlv("20", "8407A0000008781010A52750084E4557204B4C53438701015F2D046B6F656E9F1101019F12044B4C5343BF0C059F4D02150A910101E103C10101");
        tlv.log(logger);

        tlv = new Tlv("20", "8407A0000008781010A52D50084E4557204B4C53439F38035F2A028701015F2D046B6F656E9F1101019F12044B4C5343BF0C059F4D02150A910102E103C10102");
        tlv.log(logger);

        tlv = new Tlv("20", "8407D4100000011010A52750084F4C44204B4C53438701025F2D046B6F656E9F1101019F12044B4C5343BF0C059F4D02150A910101E103C10101");
        tlv.log(logger);

        tlv = new Tlv("20", "8407D4100000014010A55550084A5553544F5543489F3816DF10049F02069A039C019F21039F1A025F2A029F1C088701025F2D046B6F656E9F1101019F12044B4C5343BF0C1A9F4D02150ADF431200FFFFFFFFFFFFFFFFF11F00000012CC001F910102E103C10103");
        tlv.log(logger);

        //3000795F280204109F10200FA501000000000000000000000000000F0000000000000000000000000000009F4D02150A9F4F139F02065F2A029A039F52059F36029F2701CA01C104C108DE10C202F003C506FFFFFFFF0000C7020000C8140000000000000000000000000000000000000000D40180DF110400100000
        tlv = new Tlv("20", "5F280204109F10200FA501000000000000000000000000000F0000000000000000000000000000009F4D02150A9F4F139F02065F2A029A039F52059F36029F2701CA01C104C108DE10C202F003C506FFFFFFFF0000C7020000C8140000000000000000000000000000000000000000D40180DF110400100000");
        tlv.log(logger);




    }

}

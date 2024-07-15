import io.github.coolbong.tlv.Tlv;
import io.github.coolbong.tlv.TlvLogger;
import io.github.coolbong.tlv.TlvParser;
import logger.DummyLogger;
import org.junit.Before;
import org.junit.Test;


import static io.github.coolbong.tlv.Hex.toBytes;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class DgiTest {


    private TlvLogger logger;

    @Before
    public void setUp() {
        logger = new DummyLogger();
    }


    @Test
    public void tlv_dgi_test_001() {
        TlvParser parser = new TlvParser(logger);
        Tlv tlv = parser.parse("010122702057125981786067381230D29125010000000000005F20094B4C53432043415244", Tlv.DGI);
        //tlv.log(logger);

        assertEquals("0101", tlv.getTag());
        assertEquals(34, tlv.getLength());
        assertEquals("702057125981786067381230D29125010000000000005F20094B4C53432043415244", tlv.getValue());

        tlv = parser.parse(tlv.getValue());
        //tlv.log(logger);
        assertEquals("70", tlv.getTag());
        assertEquals(32, tlv.getLength());
        assertEquals("57125981786067381230D29125010000000000005F20094B4C53432043415244", tlv.getValue());
    }

    @Test
    public void tlv_dgi_test_002() {
        TlvParser parser = new TlvParser(logger);
        Tlv tlv = parser.parse("0201B67081B39081B046F84655ABEBF98E2054683B97C68580537E730EC535A2C1AF945435E2E8A1F8F58FA64E042C675FDF9F16A24D9B7B714F027211E479F8A860BC29C4F825EA5830B7556BA1D2D8B085DE52DAD12EF56B067BCD2EE925B0252812B216B603E4939453259C1E6906C178A9B126C45881B1BFE4809FD1093F612BE4F713B5664403B323DE035444C03040DA3885E531D9B30853F2CC56813D992AA9AA580EE5C282AAB39F465B5C4824D7B5AF7EF33ACF68", Tlv.DGI);
        //tlv.log(logger);

        assertEquals("0201", tlv.getTag());
        assertEquals(0xB6, tlv.getLength());

        tlv = parser.parse(tlv.getValue());
        assertEquals("70", tlv.getTag());
        assertEquals(0xB3, tlv.getLength());
    }

    @Test
    public void tlv_dgi_test_003() {
        TlvParser parser = new TlvParser(logger);
        Tlv tlv = parser.parse("02026670649F3201039224553FE6BE25C910D32A24FE9B421E6231FB4EF77418FC5544D3D67CF7035555F05340D8F38F01019F4701039F482A0ABF318E3218D4C7CE268130E7FA9568AE41FDFF30AAEA95CB58AED64CB47CADECA6C07F659669923D9D9F49039F3704", Tlv.DGI);

        assertEquals("0202", tlv.getTag());
        assertEquals(0x66, tlv.getLength());

        tlv = parser.parse(tlv.getValue());
        assertEquals("70", tlv.getTag());
        assertEquals(0x64, tlv.getLength());
    }

    @Test
    public void tlv_dgi_test_004() {
        TlvParser parser = new TlvParser(logger);
        Tlv tlv = parser.parse("0204B77081B49F2D81B04B7F2D09299377249E2074D1853D935C384AA52EE44538B5656E187A0D8EE3DD5EA87571EF5A3B3F6060E056C64C137B47D92861A1D73ABEC54280AD0C97A87ADC15363B184DB5ED80B70FFC90A536EF01D65893F911822CAA51C023A32760148DBB45F191BA8922DBF50C83C19A29BECE568C1BBADCF976C019E99EA1883957C329F78D164F0E0C84B96FABF8F7B160AAD4D8DBFFDA77B6875A243D311D6E3D0B144DA102620286E406C860293D3925", Tlv.DGI);

        assertEquals("0204", tlv.getTag());
        assertEquals(0xb7, tlv.getLength());

        tlv = parser.parse(tlv.getValue());
        assertEquals("70", tlv.getTag());
        assertEquals(0xB4, tlv.getLength());
    }

    @Test
    public void tlv_dgi_test_005() {
        TlvParser parser = new TlvParser(logger);
        Tlv tlv = parser.parse("02053570339F2E030100019F2F2A8404AB222A8CF31AF027335CAFF39F8263BE2B4CF99C11A929E128FAD5690AEF797D7771545A1D36F0E7", Tlv.DGI);

        assertEquals("0205", tlv.getTag());
        assertEquals(0x35, tlv.getLength());

        tlv = parser.parse(tlv.getValue());
        assertEquals("70", tlv.getTag());
        assertEquals(0x33, tlv.getLength());
    }

    @Test
    public void tlv_dgi_test_006() {
        TlvParser parser = new TlvParser(logger);
        Tlv tlv = parser.parse("03017E707C5F24032912315F25032001015A0859817860673812355F3401019F0702AB008C1B9F02069F03069F1A0295055F2A029A039C019F37049F35019F34038D0991088A0295059F37048E12000000000000000002011E03020341031F009F0D0500000000009F0E0500000000009F0F0500000000005F280204109F4A0182", Tlv.DGI);

        assertEquals("0301", tlv.getTag());
        assertEquals(0x7E, tlv.getLength());

        tlv = parser.parse(tlv.getValue());
        assertEquals("70", tlv.getTag());
        assertEquals(0x7c, tlv.getLength());
    }

    @Test
    public void tlv_dgi_test_pse_001() {

        TlvParser parser = new TlvParser(logger);
        Tlv tlv = parser.parse("010132703061164F07A000000878101050084E4557204B4C534387010161164F07D410000001101050084F4C44204B4C5343870102", Tlv.DGI);

        assertEquals("0101", tlv.getTag());
        assertEquals(50, tlv.getLength());
        assertEquals("703061164F07A000000878101050084E4557204B4C534387010161164F07D410000001101050084F4C44204B4C5343870102", tlv.getValue());

        tlv = parser.parse(tlv.getValue());
        assertEquals("70", tlv.getTag());
        assertEquals(48, tlv.getLength());
        assertEquals("61164F07A000000878101050084E4557204B4C534387010161164F07D410000001101050084F4C44204B4C5343870102", tlv.getValue());
    }


    @Test
    public void tlv_dgi_multibyte_length_test_0001() {
        TlvParser parser = new TlvParser(logger);
        Tlv tlv = parser.parse("0101FF0022702057125981786067381230D29125010000000000005F20094B4C53432043415244", Tlv.DGI);

        assertEquals("0101", tlv.getTag());
        assertEquals(34, tlv.getLength());
        assertEquals("702057125981786067381230D29125010000000000005F20094B4C53432043415244", tlv.getValue());
    }

    @Test
    public void tlv_dgi_test_negative_001() {
        TlvParser parser = new TlvParser(logger);
        Tlv tlv = parser.parse(null);
        assertNull(tlv);
    }

    @Test
    public void tlv_dgi_test_negative_002() {
        TlvParser parser = new TlvParser(logger);
        Tlv tlv = parser.parse(null, Tlv.EMV);
        assertNull(tlv);
    }

    @Test
    public void tlv_dgi_test_negative_003() {
        TlvParser parser = new TlvParser(logger);
        // null buffer
        Tlv tlv = parser.parse(null, 0, Tlv.EMV);
        assertNull(tlv);
    }

    @Test
    public void tlv_dgi_test_negative_004() {
        TlvParser parser = new TlvParser(logger);
        byte[] input = toBytes("82021900");
        // wrong offset
        Tlv tlv = parser.parse(input, 10, Tlv.EMV);
        assertNull(tlv);
    }

    @Test
    public void tlv_dgi_test_negative_005() {
        TlvParser parser = new TlvParser(logger);
        byte[] input = toBytes("82021900");
        // wrong encoding
        Tlv tlv = parser.parse(input, 0, 4);
        assertNull(tlv);
    }

    @Test
    public void tlv_dgi_test_no_logger_001() {
        TlvParser parser = new TlvParser();
        byte[] input = toBytes("82021900");
        Tlv tlv = parser.parse(input, 0, Tlv.EMV);

        assertEquals("82", tlv.getTag());
        assertEquals(2, tlv.getLength());
        assertEquals("1900", tlv.getValue());
    }

    @Test
    public void tlv_dgi_test_no_logger_002() {
        TlvParser parser = new TlvParser();
        // null buffer
        Tlv tlv = parser.parse(null, 0, Tlv.EMV);
        assertNull(tlv);
    }
}

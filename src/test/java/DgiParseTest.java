import io.github.coolbong.tlv.Tlv;
import io.github.coolbong.tlv.TlvLogger;
import io.github.coolbong.tlv.TlvParser;
import logger.ConsoleLogger;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class DgiParseTest {

    private TlvLogger logger;

    @Before
    public void setUp() {
        //logger = new DummyLogger();
        logger = new ConsoleLogger();
    }


    @Test
    public void tlv_ppse_fci_parse_test_001() {
        String fci = "6F45840E325041592E5359532E4444463031A533BF0C3061164F07A000000878101050084E4557204B4C534387010161164F07D410000001101050084F4C44204B4C5343870102";
        TlvParser parser = new TlvParser(logger);
        Tlv tlv = parser.parse(fci);
        tlv.log(logger);
        assertNotNull(tlv);
    }

    @Test
    public void tlv_dgi_parse_test_fci_001() {
        TlvParser parser;
        Tlv tlv;
        parser = new TlvParser(logger);
        tlv = parser.parse("91020EA50C8801015F2D02656E9F110101", Tlv.DGI);

        assertEquals("9102", tlv.getTag());
        assertEquals(0x0E, tlv.getLength());

        List<Tlv> tlvList = tlv.getChild();

        tlv = tlvList.get(0);
        assertEquals("A5", tlv.getTag());
        assertEquals(0x0C, tlv.getLength());

        tlvList = tlv.getChild();
        tlv = tlvList.get(0);
        assertEquals("88", tlv.getTag());
        assertEquals(0x01, tlv.getLength());
        assertEquals("01", tlv.getValue());

        tlv = tlvList.get(1);
        assertEquals("5F2D", tlv.getTag());
        assertEquals(0x02, tlv.getLength());
        assertEquals("656E", tlv.getValue());

        tlv = tlvList.get(2);
        assertEquals("9F11", tlv.getTag());
        assertEquals(0x01, tlv.getLength());
        assertEquals("01", tlv.getValue());
    }

    @Test
    public void tlv_dgi_parse_test_record() {
        TlvParser parser;
        Tlv tlv;
        parser = new TlvParser(logger);
        tlv = parser.parse("010122702057125981786067381230D29125010000000000005F20094B4C53432043415244", Tlv.DGI);
        Tlv child = parser.parse(tlv.getValue());

        assertEquals("70", child.getTag());
        assertEquals(0x20, child.getLength());

        parser = new TlvParser(logger);
        tlv = parser.parse("0201B67081B39081B046F84655ABEBF98E2054683B97C68580537E730EC535A2C1AF945435E2E8A1F8F58FA64E042C675FDF9F16A24D9B7B714F027211E479F8A860BC29C4F825EA5830B7556BA1D2D8B085DE52DAD12EF56B067BCD2EE925B0252812B216B603E4939453259C1E6906C178A9B126C45881B1BFE4809FD1093F612BE4F713B5664403B323DE035444C03040DA3885E531D9B30853F2CC56813D992AA9AA580EE5C282AAB39F465B5C4824D7B5AF7EF33ACF68", Tlv.DGI);
        parser.parse(tlv.getValue());

        parser = new TlvParser(logger);
        tlv = parser.parse("02026670649F3201039224553FE6BE25C910D32A24FE9B421E6231FB4EF77418FC5544D3D67CF7035555F05340D8F38F01019F4701039F482A0ABF318E3218D4C7CE268130E7FA9568AE41FDFF30AAEA95CB58AED64CB47CADECA6C07F659669923D9D9F49039F3704", Tlv.DGI);
        parser.parse(tlv.getValue());

        parser = new TlvParser(logger);
        tlv = parser.parse("0203B77081B49F4681B0881BDCCFA351C71F0883868A801F82A33F9288CD3F6B0886C38D1B9B3C26664104BBA89C245B0D6B7CC62786EF8F8BD7283126D33A575C9300A2815CEFCD926B0606C1F07ACCF515A87FD3E5641B7AE9611ECDE77F23483C0AD55A2F3C30432431DC92A6A30D5AAAC964B6043FA38A8E614AEE3A0E1A37CB8957A47E3F67530D41DBE7B2CA60EAE4B0FD1FA5ED662F8AA108F2B89A32F8E4C14A7A9632729187D33945341CDD2FE75C59F9AFF8912881", Tlv.DGI);
        parser.parse(tlv.getValue());

        parser = new TlvParser(logger);
        tlv = parser.parse("0204B77081B49F2D81B04B7F2D09299377249E2074D1853D935C384AA52EE44538B5656E187A0D8EE3DD5EA87571EF5A3B3F6060E056C64C137B47D92861A1D73ABEC54280AD0C97A87ADC15363B184DB5ED80B70FFC90A536EF01D65893F911822CAA51C023A32760148DBB45F191BA8922DBF50C83C19A29BECE568C1BBADCF976C019E99EA1883957C329F78D164F0E0C84B96FABF8F7B160AAD4D8DBFFDA77B6875A243D311D6E3D0B144DA102620286E406C860293D3925", Tlv.DGI);
        parser.parse(tlv.getValue());

        parser = new TlvParser(logger);
        tlv = parser.parse("02053570339F2E030100019F2F2A8404AB222A8CF31AF027335CAFF39F8263BE2B4CF99C11A929E128FAD5690AEF797D7771545A1D36F0E7", Tlv.DGI);
        parser.parse(tlv.getValue());

        parser = new TlvParser(logger);
        tlv = parser.parse("03017E707C5F24032912315F25032001015A0859817860673812355F3401019F0702AB008C1B9F02069F03069F1A0295055F2A029A039C019F37049F35019F34038D0991088A0295059F37048E12000000000000000002011E03020341031F009F0D0500000000009F0E0500000000009F0F0500000000005F280204109F4A0182", Tlv.DGI);
        parser.parse(tlv.getValue());

        parser = new TlvParser(logger);
        tlv = parser.parse("03020C700A5F300205019F08020001", Tlv.DGI);
        parser.parse(tlv.getValue());

        parser = new TlvParser(logger);
        tlv = parser.parse("1E010908020102FF01000181", Tlv.DGI);
        assertNotNull(tlv);

        parser = new TlvParser(logger);
        tlv = parser.parse("1E020908020102FF02000281", Tlv.DGI);
        assertNotNull(tlv);

        parser = new TlvParser(logger);
        tlv = parser.parse("1E030908020102FF03000381", Tlv.DGI);
        assertNotNull(tlv);

        parser = new TlvParser(logger);
        tlv = parser.parse("1E040908020102FF04000481", Tlv.DGI);
        assertNotNull(tlv);

        parser = new TlvParser(logger);
        tlv = parser.parse("1E050908020102FF05000581", Tlv.DGI);
        assertNotNull(tlv);

        parser = new TlvParser(logger);
        tlv = parser.parse("1E060908020102FF06000681", Tlv.DGI);
        assertNotNull(tlv);

        parser = new TlvParser(logger);
        tlv = parser.parse("1E070908020102FF07000781", Tlv.DGI);
        assertNotNull(tlv);

        parser = new TlvParser(logger);
        tlv = parser.parse("1E080908020102FF0800087F", Tlv.DGI);
        assertNotNull(tlv);
    }

    @Test
    public void tlv_dgi_parse_test_001() {
        TlvParser parser;
        Tlv tlv;

        parser = new TlvParser(logger);
        tlv = parser.parse("1D01 3C 8407D4100000011010 910102 A5 29 50 08 4E4557204B4C5343 9F38 02 8101 870101 5F2D046B6F656E 9F11 01 01 9F12044B4C5343 9F4D02150AE103C10101", Tlv.DGI);
        tlv = new Tlv("20", tlv.getValue());
        parser.parse(tlv.toString());

        parser = new TlvParser(logger);
        tlv = parser.parse("1D023C8407D4100000018000910102A52950084E4557204B4C53439F380281018701025F2D046B6F656E9F1101019F12044B4C53439F4D02150AE103C10102", Tlv.DGI);
        tlv = new Tlv("20", tlv.getValue());
        parser.parse(tlv.toString());

        parser = new TlvParser(logger);
        tlv = parser.parse("1D033C8407D4100000019000910102A52950084E4557204B4C53439F380281018701035F2D046B6F656E9F1101019F12044B4C53439F4D02150AE103C10103", Tlv.DGI);
        tlv = new Tlv("20", tlv.getValue());
        parser.parse(tlv.toString());

        parser = new TlvParser(logger);
        tlv = parser.parse("3F304BBF3048DF0106000000000000DF0206000000000000DF1118999999999999999999999999999999999999999999999999DF1218999999999999999999999999999999999999999999999999", Tlv.DGI);
        tlv = parser.parse(tlv.getValue());
        assertNotNull(tlv);

        parser = new TlvParser(logger);
        tlv = parser.parse("3F3121BF311EDF0102E00FDF0202E00FDF0302E00FDF0402E00FDF0502E00FDF0602E00F", Tlv.DGI);
        assertNotNull(tlv);

        parser = new TlvParser(logger);
        tlv = parser.parse("3F320FBF320CDF01030410C0DF02030840C0", Tlv.DGI);
        assertNotNull(tlv);

        parser = new TlvParser(logger);
        tlv = parser.parse("3F3481BF347EDF0112000000000000000000000000000000000000DF0212000000000000000000000000000000000000DF0312000000000000000000000000000000000000DF0412000000000000000000000000000000000000DF0512000000000000000000000000000000000000DF0612000000000000000000000000000000000000", Tlv.DGI);
        assertNotNull(tlv);

        parser = new TlvParser(logger);
        tlv = parser.parse("3F3524BF3521DF010100DF020100DF030100DF1104FFFFFFFFDF1204FFFFFFFFDF1304FFFFFFFF", Tlv.DGI);
        assertNotNull(tlv);

        parser = new TlvParser(logger);
        tlv = parser.parse("3F361BBF3618DF01010EDF02010EDF03010EDF04010EDF05010EDF06010E", Tlv.DGI);
        assertNotNull(tlv);

        parser = new TlvParser(logger);
        tlv = parser.parse("3F370FBF370CDF0101E0DF0201E0DF0301E0", Tlv.DGI);
        assertNotNull(tlv);

        parser = new TlvParser(logger);
        tlv = parser.parse("3F385DBF385ADF010C041008410004010978000481DF020C041008420004010978000481DF030C041008430004010978000481DF040C041008440004010978000481DF050C041008450004010978000481DF060C041008460004010978000481", Tlv.DGI);
        assertNotNull(tlv);

        parser = new TlvParser(logger);
        tlv = parser.parse("3F3B3FBF3B3CDF01079C2113A50C0000DF02079C2113A50C0000DF03079C2113A50C0000DF04079C2113A50C0000DF05079C2113A50C0000DF06079C2113A50C0000", Tlv.DGI);
        assertNotNull(tlv);

        parser = new TlvParser(logger);
        tlv = parser.parse("3F3C39BF3C36DF0106999999999999DF0206999999999999DF0306999999999999DF0406999999999999DF0506999999999999DF0606999999999999", Tlv.DGI);
        assertNotNull(tlv);

        parser = new TlvParser(logger);
        tlv = parser.parse("3F3D2DBF3D2ADF010404101F00DF020404112F00DF030404123F00DF040404134F00DF050404145F00DF060404156F00", Tlv.DGI);
        assertNotNull(tlv);

        parser = new TlvParser(logger);
        tlv = parser.parse("3F3E21BF3E1EDF01020100DF02020100DF03020100DF04020100DF05020100DF06020100", Tlv.DGI);
        assertNotNull(tlv);

        parser = new TlvParser(logger);
        tlv = parser.parse("3F3F45BF3F42DF010811111111FF1FFFFFDF020822222222FF2FFFFFDF030833333333FF3FFFFFDF040844444444FF4FFFFFDF050855555555FF5FFFFFDF060866666666FF6FFFFF", Tlv.DGI);
        assertNotNull(tlv);

        parser = new TlvParser(logger);
        tlv = parser.parse("3F416FBF416CDF010F19000C080101001001050018010200DF020F19000C080101001001050018010200DF030F19000C080101001001050018010200DF040F19000C080101001001050018010200DF050F19000C080101001001050018010200DF060F19000C080101001001050018010200", Tlv.DGI);
        assertNotNull(tlv);

        parser = new TlvParser(logger);
        tlv = parser.parse("8000300324E4EE56950455197FC145734EC3B1DB8A641D60A45C42CE1BCD5C4C415E7762156D0243D63ADD38477DD485836486", Tlv.DGI);
        assertNotNull(tlv);

        parser = new TlvParser(logger);
        tlv = parser.parse("90000928C8F1D09C4AB0B240", Tlv.DGI);
        assertNotNull(tlv);

        parser = new TlvParser(logger);
        tlv = parser.parse("8010081C8DE7D241786075", Tlv.DGI);
        assertNotNull(tlv);

        parser = new TlvParser(logger);
        tlv = parser.parse("9010020303", Tlv.DGI);
        assertNotNull(tlv);

        parser = new TlvParser(logger);
        tlv = parser.parse("30007B5F280204109F360200009F10200FA50C0000000000000000000000FFFF0F01000000000000FFFFFFFFFFFFFFFF9F4F139F02065F2A029A039F52059F36029F2701CA01C104FF08DE10C202F006C3029999C506FFFFFFFFFFFFC7020000C814FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF9F4D02150AD40180", Tlv.DGI);
        tlv = new Tlv("20", tlv.getValue());
        tlv = parser.parse(tlv.toString());
        assertNotNull(tlv);
    }
}

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
        //assertEquals("702057125981786067381230D29125010000000000005F20094B4C53432043415244", tlv.getValue());

        tlv = parser.parse(tlv.getValue());
        //tlv.log(logger);
        assertEquals("70", tlv.getTag());
        assertEquals(0xB3, tlv.getLength());
        //assertEquals("57125981786067381230D29125010000000000005F20094B4C53432043415244", tlv.getValue());
    }

    @Test
    public void tlv_dgi_test_003() {
        TlvParser parser = new TlvParser(logger);
        //Tlv tlv = parser.parse("0203B77081B49F4681B0881BDCCFA351C71F0883868A801F82A33F9288CD3F6B0886C38D1B9B3C26664104BBA89C245B0D6B7CC62786EF8F8BD7283126D33A575C9300A2815CEFCD926B0606C1F07ACCF515A87FD3E5641B7AE9611ECDE77F23483C0AD55A2F3C30432431DC92A6A30D5AAAC964B6043FA38A8E614AEE3A0E1A37CB8957A47E3F67530D41DBE7B2CA60EAE4B0FD1FA5ED662F8AA108F2B89A32F8E4C14A7A9632729187D33945341CDD2FE75C59F9AFF8912881", Tlv.DGI);
        Tlv tlv = parser.parse("02026670649F3201039224553FE6BE25C910D32A24FE9B421E6231FB4EF77418FC5544D3D67CF7035555F05340D8F38F01019F4701039F482A0ABF318E3218D4C7CE268130E7FA9568AE41FDFF30AAEA95CB58AED64CB47CADECA6C07F659669923D9D9F49039F3704", Tlv.DGI);
        //tlv.log(logger);

        //assertEquals("0201", tlv.getTag());
        //assertEquals(0xB6, tlv.getLength());
        //assertEquals("702057125981786067381230D29125010000000000005F20094B4C53432043415244", tlv.getValue());

        tlv = parser.parse(tlv.getValue());
        //tlv.log(logger);
        //assertEquals("70", tlv.getTag());
        //assertEquals(0xB3, tlv.getLength());
        //assertEquals("57125981786067381230D29125010000000000005F20094B4C53432043415244", tlv.getValue());
    }

    @Test
    public void tlv_dgi_test_004() {
        TlvParser parser = new TlvParser(logger);
        Tlv tlv = parser.parse("0204B77081B49F2D81B04B7F2D09299377249E2074D1853D935C384AA52EE44538B5656E187A0D8EE3DD5EA87571EF5A3B3F6060E056C64C137B47D92861A1D73ABEC54280AD0C97A87ADC15363B184DB5ED80B70FFC90A536EF01D65893F911822CAA51C023A32760148DBB45F191BA8922DBF50C83C19A29BECE568C1BBADCF976C019E99EA1883957C329F78D164F0E0C84B96FABF8F7B160AAD4D8DBFFDA77B6875A243D311D6E3D0B144DA102620286E406C860293D3925", Tlv.DGI);
        //tlv.log(logger);

        //assertEquals("0201", tlv.getTag());
        //assertEquals(0xB6, tlv.getLength());
        //assertEquals("702057125981786067381230D29125010000000000005F20094B4C53432043415244", tlv.getValue());

        tlv = parser.parse(tlv.getValue());
        //tlv.log(logger);
        //assertEquals("70", tlv.getTag());
        //assertEquals(0xB3, tlv.getLength());
        //assertEquals("57125981786067381230D29125010000000000005F20094B4C53432043415244", tlv.getValue());
    }

    @Test
    public void tlv_dgi_test_005() {
        TlvParser parser = new TlvParser(logger);
        Tlv tlv = parser.parse("02053570339F2E030100019F2F2A8404AB222A8CF31AF027335CAFF39F8263BE2B4CF99C11A929E128FAD5690AEF797D7771545A1D36F0E7", Tlv.DGI);
        //tlv.log(logger);

        //assertEquals("0201", tlv.getTag());
        //assertEquals(0xB6, tlv.getLength());
        //assertEquals("702057125981786067381230D29125010000000000005F20094B4C53432043415244", tlv.getValue());

        tlv = parser.parse(tlv.getValue());
        //tlv.log(logger);
        //assertEquals("70", tlv.getTag());
        //assertEquals(0xB3, tlv.getLength());
        //assertEquals("57125981786067381230D29125010000000000005F20094B4C53432043415244", tlv.getValue());
    }



    @Test
    public void tlv_dgi_test_006() {
        TlvParser parser = new TlvParser(logger);
        Tlv tlv = parser.parse("03017E707C5F24032912315F25032001015A0859817860673812355F3401019F0702AB008C1B9F02069F03069F1A0295055F2A029A039C019F37049F35019F34038D0991088A0295059F37048E12000000000000000002011E03020341031F009F0D0500000000009F0E0500000000009F0F0500000000005F280204109F4A0182", Tlv.DGI);
        tlv = parser.parse(tlv.getValue());
    }

    @Test
    public void tlv_dgi_test_007() {
        TlvParser parser = new TlvParser(logger);
        Tlv tlv = parser.parse("03020C700A5F300205019F08020001", Tlv.DGI);
        tlv = parser.parse(tlv.getValue());
    }

    @Test
    public void tlv_dgi_test_008() {
        Tlv tlv = Tlv.parse("1E010908020102FF01000181", Tlv.DGI);
        logger.debug("<dgi id=\"{}\" value=\"{}\" />", tlv.getTag(), tlv.getValue());
    }

    @Test
    public void tlv_dgi_test_009() {
        Tlv tlv = Tlv.parse("1E020908020102FF02000281", Tlv.DGI);
        logger.debug("<dgi id=\"{}\" value=\"{}\" />", tlv.getTag(), tlv.getValue());
    }

    @Test
    public void tlv_dgi_test_010() {
        TlvParser parser = new TlvParser(logger);
        Tlv tlv = parser.parse("1E030908020102FF03000381", Tlv.DGI);
    }

    @Test
    public void tlv_dgi_test_011() {
        TlvParser parser = new TlvParser(logger);
        Tlv tlv = parser.parse("1E040908020102FF04000481", Tlv.DGI);
    }

    @Test
    public void tlv_dgi_test_012() {
        TlvParser parser = new TlvParser(logger);
        Tlv tlv = parser.parse("1E050908020102FF05000581", Tlv.DGI);
    }

    @Test
    public void tlv_dgi_test_013() {
        TlvParser parser = new TlvParser(logger);
        Tlv tlv = parser.parse("1E060908020102FF06000681", Tlv.DGI);
    }

    @Test
    public void tlv_dgi_test_014() {
        TlvParser parser = new TlvParser(logger);
        Tlv tlv = parser.parse("1E070908020102FF07000781", Tlv.DGI);
    }

    @Test
    public void tlv_dgi_test_015() {
        TlvParser parser = new TlvParser(logger);
        Tlv tlv = parser.parse("1E080908020102FF0800087F", Tlv.DGI);
    }

    @Test
    public void tlv_dgi_test_016() {
        TlvParser parser = new TlvParser(logger);
        Tlv tlv = parser.parse("1D013A8407D4100000015010910102A52C50084E4557204B4C53439F380281018701015F2D046B6F656E9F1101019F12044B4C5343BF0C059F4D02150A", Tlv.DGI);
        //tlv = parser.parse(tlv.getValue());
        tlv = new Tlv("20", tlv.getValue());
        tlv = parser.parse(tlv.toString());
    }

    @Test
    public void tlv_dgi_test_017() {
        TlvParser parser = new TlvParser(logger);
        Tlv tlv = parser.parse("1D023A8407D4100000011010910102A52C50084F4C44204B4C53439F380281018701025F2D046B6F656E9F1101019F12044B4C5343BF0C059F4D02150A", Tlv.DGI);
        tlv = new Tlv("20", tlv.getValue());
        tlv = parser.parse(tlv.toString());
    }

    @Test
    public void tlv_dgi_test_018() {
        TlvParser parser = new TlvParser(logger);
        Tlv tlv = parser.parse("3F304BBF3048DF0106000000000000DF0206000000000000DF1118999999999999999999999999999999999999999999999999DF1218999999999999999999999999999999999999999999999999", Tlv.DGI);
        //tlv = new Tlv("20", tlv.getValue());
        tlv = parser.parse(tlv.getValue());
    }

    @Test
    public void tlv_dgi_test_019() {
        TlvParser parser = new TlvParser(logger);
        Tlv tlv = parser.parse("3F312BBF3128DF0102E00FDF0202E00FDF0302E00FDF0402E00FDF0502E00FDF0602E00FDF0702E00FDF0802E00F", Tlv.DGI);
        tlv = parser.parse(tlv.getValue());
    }

    @Test
    public void tlv_dgi_test_020() {
        TlvParser parser = new TlvParser(logger);
        Tlv tlv = parser.parse("3F320FBF320CDF01030410C0DF02030840C0", Tlv.DGI);
        tlv = parser.parse(tlv.getValue());
    }

    @Test
    public void tlv_dgi_test_021() {
        TlvParser parser = new TlvParser(logger);
        Tlv tlv = parser.parse("3F34ACBF3481A8DF0112000000000000000000000000000000000000DF0212000000000000000000000000000000000000DF0312000000000000000000000000000000000000DF0412000000000000000000000000000000000000DF0512000000000000000000000000000000000000DF0612000000000000000000000000000000000000DF0712000000000000000000000000000000000000DF0812000000000000000000000000000000000000", Tlv.DGI);
        tlv = parser.parse(tlv.getValue());
    }

    @Test
    public void tlv_dgi_test_022() {
        TlvParser parser = new TlvParser(logger);
        Tlv tlv = parser.parse("3F3524BF3521DF010100DF020100DF030100DF1104FFFFFFFFDF1204FFFFFFFFDF1304FFFFFFFF", Tlv.DGI);
        tlv = parser.parse(tlv.getValue());
    }

    @Test
    public void tlv_dgi_test_023() {
        TlvParser parser = new TlvParser(logger);
        Tlv tlv = parser.parse("3F3623BF3620DF01010EDF02010EDF03010EDF04010EDF05010EDF06010EDF07010EDF08010E", Tlv.DGI);
        tlv = parser.parse(tlv.getValue());
    }

    @Test
    public void tlv_dgi_test_024() {
        TlvParser parser = new TlvParser(logger);
        Tlv tlv = parser.parse("3F370FBF370CDF0101E0DF0201E0DF0301E0", Tlv.DGI);
        tlv = parser.parse(tlv.getValue());
    }

    @Test
    public void tlv_dgi_test_025() {
        TlvParser parser = new TlvParser(logger);
        Tlv tlv = parser.parse("3F387BBF3878DF010C041008410004010978000481DF020C041008420004010978000481DF030C041008430004010978000481DF040C041008440004010978000481DF050C041008450004010978000481DF060C041008460004010978000481DF070C041008470004010978000481DF080C041008480004010978000481", Tlv.DGI);
        tlv = parser.parse(tlv.getValue());
    }

    @Test
    public void tlv_dgi_test_026() {
        TlvParser parser = new TlvParser(logger);
        Tlv tlv = parser.parse("3F3B53BF3B50DF01079C2113A50C0000DF02079C2113A50C0000DF03079C2113A50C0000DF04079C2113A50C0000DF05079C2113A50C0000DF06079C2113A50C0000DF07079C2113A50C0000DF08079C2113A50C0000", Tlv.DGI);
        tlv = parser.parse(tlv.getValue());
    }

    @Test
    public void tlv_dgi_test_027() {
        TlvParser parser = new TlvParser(logger);
        Tlv tlv = parser.parse("3F3C4BBF3C48DF0106999999999999DF0206999999999999DF0306999999999999DF0406999999999999DF0506999999999999DF0606999999999999DF0706999999999999DF0806999999999999", Tlv.DGI);
        tlv = parser.parse(tlv.getValue());
    }

    @Test
    public void tlv_dgi_test_028() {
        TlvParser parser = new TlvParser(logger);
        Tlv tlv = parser.parse("3F3D3BBF3D38DF010404101F00DF020404112F00DF030404123F00DF040404134F00DF050404145F00DF060404156F00DF070404167F00DF080404178F00", Tlv.DGI);
        tlv = parser.parse(tlv.getValue());
    }

    @Test
    public void tlv_dgi_test_029() {
        TlvParser parser = new TlvParser(logger);
        Tlv tlv = parser.parse("3F3E2BBF3E28DF01020100DF02020100DF03020100DF04020100DF05020100DF06020100DF07020100DF08020100", Tlv.DGI);
        tlv = parser.parse(tlv.getValue());
    }

    @Test
    public void tlv_dgi_test_030() {
        TlvParser parser = new TlvParser(logger);
        Tlv tlv = parser.parse("3F3F5BBF3F58DF010811111111FF1FFFFFDF020822222222FF2FFFFFDF030833333333FF3FFFFFDF040844444444FF4FFFFFDF050855555555FF5FFFFFDF060866666666FF6FFFFFDF070877777777FF7FFFFFDF080888888888FF8FFFFF", Tlv.DGI);
        tlv = parser.parse(tlv.getValue());
    }

    @Test
    public void tlv_dgi_test_031() {
        TlvParser parser = new TlvParser(logger);
        Tlv tlv = parser.parse("3F4194BF418190DF010F19000C080101001001050018010200DF020F19000C080101001001050018010200DF030F19000C080101001001050018010200DF040F19000C080101001001050018010200DF050F19000C080101001001050018010200DF060F19000C080101001001050018010200DF070F19000C080101001001050018010200DF080F19000C080101001001050018010200", Tlv.DGI);
        tlv = parser.parse(tlv.getValue());
    }

    @Test
    public void tlv_dgi_test_032() {
        TlvParser parser = new TlvParser(logger);
        Tlv tlv = parser.parse("8000300324E4EE56950455197FC145734EC3B1DB8A641D60A45C42CE1BCD5C4C415E7762156D0243D63ADD38477DD485836486", Tlv.DGI);
        //logger.debug("{} {} {}", tlv.getTag(), tlv.getLength(), tlv.getValue());
    }

    @Test
    public void tlv_dgi_test_033() {
        TlvParser parser = new TlvParser(logger);
        Tlv tlv = parser.parse("90000928C8F1D09C4AB0B240", Tlv.DGI);

    }

    @Test
    public void tlv_dgi_test_034() {
        TlvParser parser = new TlvParser(logger);
        Tlv tlv = parser.parse("8010081C8DE7D241786075", Tlv.DGI);
        logger.debug("<dgi id=\"{}\" value=\"{}\" />", tlv.getTag(), tlv.getValue());
    }

    @Test
    public void tlv_dgi_test_035() {
        TlvParser parser = new TlvParser(logger);
        Tlv tlv = parser.parse("9010020303", Tlv.DGI);
        logger.debug("<dgi id=\"{}\" value=\"{}\" />", tlv.getTag(), tlv.getValue());
    }

    @Test
    public void tlv_dgi_test_036() {
        TlvParser parser = new TlvParser(logger);
        Tlv tlv = parser.parse("820160E3EBBE67E2D70986B81F6C34ADAEB63B8D94F7132877465EE04D2EDFFB199264E053EC9817E4902087A4E8D31D793046762E141C2565C821000C160D0978A8AB4A21470BFEB225645A66EBF5C622793DA82BF3FD64C1BC96F79B86BE6049ADD9", Tlv.DGI);
        logger.debug("<dgi id=\"{}\" value=\"{}\" />", tlv.getTag(), tlv.getValue());
    }

    @Test
    public void tlv_dgi_test_037() {
        TlvParser parser = new TlvParser(logger);
        Tlv tlv = parser.parse("8202602643BF086B58F93BEDFCF4E9CF8B40BBA890B798B596DFB62DEAC1275C901A991349DF4BA3DD90E3B83123B7AFDCDE998E1ECB2C68687927DD3F55D4D9A7A620978DAC10F816F7B21633ED340DFDE494D4A44795DF642E94F79B86BE6049ADD9", Tlv.DGI);
        logger.debug("<dgi id=\"{}\" value=\"{}\" />", tlv.getTag(), tlv.getValue());
    }

    @Test
    public void tlv_dgi_test_038() {
        TlvParser parser = new TlvParser(logger);
        Tlv tlv = parser.parse("820360861E329C37F93531BC8F8B4179276A74FA49E427811F013421816CF61FF88A7EFD5A1CDB9572CD00F7DA476E28231FF6A9334F578962D0747AB772DBCFAAC1D86B3E2D7EE0B81BA1D2EFD647E9810C9682AA15ADE3512052F79B86BE6049ADD9", Tlv.DGI);
        logger.debug("<dgi id=\"{}\" value=\"{}\" />", tlv.getTag(), tlv.getValue());
    }

    @Test
    public void tlv_dgi_test_039() {
        TlvParser parser = new TlvParser(logger);
        Tlv tlv = parser.parse("820460A08F1789517F3737650833480DF957A97B61E44BBC3B05DB553D3C14F51482F8EAB99C11C8F738B1F4E59D97DD098A822B7413F06E3DFC3ADB10C21911E6ABEF4A15045B2ACBCB9F706C98FE7A275E6A57EC31B1423FD14BF79B86BE6049ADD9", Tlv.DGI);
        logger.debug("<dgi id=\"{}\" value=\"{}\" />", tlv.getTag(), tlv.getValue());
    }

    @Test
    public void tlv_dgi_test_040() {
        TlvParser parser = new TlvParser(logger);
        Tlv tlv = parser.parse("820560496AF567F4B811B67A031DE585BACAA480AD2B0C733EA590B6D2C4799BD9850FC78210124CC9D8A87392A269A298CBEAC65A6CFA65F1610F6A4C24FBC54189A2AA8EDB9668643D8A7327FE7FD5CBAE85C3FD66E7709980EDF79B86BE6049ADD9", Tlv.DGI);
        logger.debug("<dgi id=\"{}\" value=\"{}\" />", tlv.getTag(), tlv.getValue());
    }

    @Test
    public void tlv_dgi_test_041() {
        TlvParser parser = new TlvParser(logger);
        Tlv tlv = parser.parse("8301607D309D49CD0FBD4EF83A089D4BF2AD71F1CE50A6C6782EF3AA905639D8A9727904C56DABDF85B0AD6F7D5893F466C22AAC1C34CA364C329AF4CF106CDABC9F0AC142A8E26B8C92861141075433BC9DFED428B5FD7B1EAEC4F79B86BE6049ADD9", Tlv.DGI);
        logger.debug("<dgi id=\"{}\" value=\"{}\" />", tlv.getTag(), tlv.getValue());
    }

    @Test
    public void tlv_dgi_test_042() {
        TlvParser parser = new TlvParser(logger);
        Tlv tlv = parser.parse("8302600F629565EAC7E156B15952DE2E3919FC21EC6E9C4D6863AC741229AA2BDE513559DD6506C5B9608790121ECEE9FDB6E2A250B3F9BE6851D944962BABBDBD1CB148B4C2F1E88E3AB2CD67D042CA34EE6449B2FA3EFE5C0E5BF79B86BE6049ADD9", Tlv.DGI);
        logger.debug("<dgi id=\"{}\" value=\"{}\" />", tlv.getTag(), tlv.getValue());
    }

    @Test
    public void tlv_dgi_test_043() {
        TlvParser parser = new TlvParser(logger);
        Tlv tlv = parser.parse("830360DD56D67CB480AF268DAC106E6C6A7F60FC9C24B4D5F5E7376A131FFE6459C19204F97BC0648A32A0A7C7B6BCAE35468DE855A69AC79EF111992635825FA49E76A3030F57239C30F35D97DFBD771B9D4467E426CEE28519D8F79B86BE6049ADD9", Tlv.DGI);
        logger.debug("<dgi id=\"{}\" value=\"{}\" />", tlv.getTag(), tlv.getValue());
    }

    @Test
    public void tlv_dgi_test_044() {
        TlvParser parser = new TlvParser(logger);
        Tlv tlv = parser.parse("8304600CC131A7804D70CB924295DBB38002C6CD29D10E7D8A9B16EE8D2C39E231226177B1E7DE1635C4FCEC9272104EE78E338A49B817EECE8A55D9E13B57A265CCC5D592EB93CE9D1ECDF3F04F9F53141FAB514526F143DB5B97F79B86BE6049ADD9", Tlv.DGI);
        logger.debug("<dgi id=\"{}\" value=\"{}\" />", tlv.getTag(), tlv.getValue());
    }

    @Test
    public void tlv_dgi_test_045() {
        TlvParser parser = new TlvParser(logger);
        Tlv tlv = parser.parse("830560E6B08C9702E22C9088509065F06784447356CF5F8A23FACAA51B42188C3736E08447410070ECB0376F57D33B6C3865AD6436C2009F1EB775DF469374B4B4C658B9B69EF90DC256E6265508AD56C2A880F309F2B254349463F79B86BE6049ADD9", Tlv.DGI);
        logger.debug("<dgi id=\"{}\" value=\"{}\" />", tlv.getTag(), tlv.getValue());
    }

    @Test
    public void tlv_dgi_test_046() {
        TlvParser parser = new TlvParser(logger);
        Tlv tlv = parser.parse("30007B5F280204109F360200009F10200FA50C0000000000000000000000FFFF0F01000000000000FFFFFFFFFFFFFFFF9F4F139F02065F2A029A039F52059F36029F2701CA01C104FF08DE10C202F008C3029999C506FFFFFFFFFFFFC7020000C814FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF9F4D02150AD40180", Tlv.DGI);
        //tlv = parser.parse(tlv.getValue());
        tlv = new Tlv("20", tlv.getValue());
        tlv = parser.parse(tlv.toString());
    }

    @Test
    public void tlv_dgi_test_047() {
        TlvParser parser = new TlvParser(logger);
        //Tlv tlv = parser.parse("9102 35 A533BF0C3061164F07D410000001501050084E4557204B4C534387010161164F07D410000001101050084F4C44204B4C5343870102", Tlv.DGI);
        Tlv tlv = new Tlv("6F", "A533BF0C3061164F07D410000001501050084E4557204B4C534387010161164F07D410000001101050084F4C44204B4C5343870102");
        tlv = parser.parse(tlv.toString());
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

import io.github.coolbong.tlv.Tlv;
import org.junit.Test;

import static io.github.coolbong.tlv.Hex.toBytes;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;

public class TlvBuildTest {

//    class TlvBuilder {
//
//    }

    @Test
    public void test_tlv_build_001() {
        //dgi for pse fci
        Tlv tlv1 = new Tlv("88", "01");
        Tlv tlv2 = new Tlv("5F2D", "6B6F656E");
        Tlv tlv3 = new Tlv("9F11", "01");

        Tlv tlv4 = new Tlv("A5", tlv1.toString()+tlv2.toString()+tlv3.toString());
        Tlv tlv = new Tlv("9102", tlv4.toString(), Tlv.DGI);

        assertEquals("910210A50E8801015F2D046B6F656E9F110101", tlv.toString());
    }

    @Test
    public void test_tlv_build_002() {
        // pse record example
        String aid = "A0000000031010";
        String label = "56495341435245444954";

        Tlv tlv1 = new Tlv("4F", aid);
        Tlv tlv2 = new Tlv("50", label);
        Tlv tlv3 = new Tlv("81", "01");

        Tlv tlv4 = new Tlv("61", tlv1.toString()+tlv2.toString()+tlv3.toString());
        Tlv tlv = new Tlv("70", tlv4.toString());

        assertEquals("701A61184F07A0000000031010500A56495341435245444954810101", tlv.toString());
    }

    @Test
    public void test_tlv_buidl_003() {
        String tag = "70";
        String value = "57139409119700015643D49126012000014000000F0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";

        Tlv tlv = new Tlv(tag, value);
        assertEquals("7081F857139409119700015643D49126012000014000000F0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000", tlv.toString());
    }

    @Test
    public void test_tlv_buidl_004() {
        // build invalid length tlv
        //6F398407D4100000015010A52D50084E4557204B4C53439F38035F2A028701015F2D046B6F656E9F1101019F12044B4C5343BF0C059F4D02150A
        byte[] tag = toBytes("6F");
        byte[] len = { (byte)0x39 };
        byte[] value = toBytes("8407D4100000015010A52D50084E4557204B4C53439F38035F2A028701015F2D046B6F656E9F1101019F12044B4C5343BF0C059F4D02150A");

        Tlv tlv = new Tlv(tag, len, value);
        assertEquals("6F398407D4100000015010A52D50084E4557204B4C53439F38035F2A028701015F2D046B6F656E9F1101019F12044B4C5343BF0C059F4D02150A", tlv.toString());
    }

    @Test
    public void test_tlv_builder_001() {

        // TODO new Builder: simple, easy

        Tlv tlv1 = new Tlv("DF01", "999999999999");
        //Tlv tlv2 = new Tlv("DF02", "999999999999");
        //Tlv tlv3 = new Tlv("DF03", "999999999999");
        //Tlv tlv = new Tlv("3F3C", tlv1.toString() + tlv2.toString() + tlv3.toString(), Tlv.DGI);
        Tlv tlv = new Tlv("3F3C", tlv1.toString(), Tlv.DGI);
        tlv.print();
    }


}

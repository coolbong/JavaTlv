import io.github.coolbong.tlv.Tlv;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

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
    public void test_tlv_builder_001() {


    }
}

import io.github.coolbong.util.Tlv;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;

public class TlvNegativeTest {


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

}

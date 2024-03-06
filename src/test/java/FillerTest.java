import io.github.coolbong.tlv.Tlv;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FillerTest {

    @Test
    public void test_filler_001() {
        String input = "770C000082020880940408010300";
        Tlv tlv = Tlv.parse(input);

        assertEquals(tlv.getTag(), "77");
        assertEquals(tlv.getLength(), 0x0c);
        assertEquals(tlv.getValue(), "000082020880940408010300");
    }


    @Test
    public void test_filler_002() {
        String input = "00000082021800";
        Tlv tlv = Tlv.parse(input);

        assertEquals(tlv.getTag(), "00");
        assertEquals(tlv.getLength(), 3);
        assertEquals(tlv.getValue(), "000000");
    }
}

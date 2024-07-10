import io.github.coolbong.tlv.Tlv;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class FillerTest {

    @Test
    public void test_filler_001() {
        String input = "770C000082020880940408010300";
        Tlv tlv = Tlv.parse(input);

        assertEquals("77", tlv.getTag());
        assertEquals(0x0c, tlv.getLength());
        assertEquals("000082020880940408010300", tlv.getValue());
    }


    @Test
    public void test_filler_002() {
        String input = "00000082021800";
        Tlv tlv = Tlv.parse(input);

        assertEquals("00", tlv.getTag());
        assertEquals(3, tlv.getLength());
        assertEquals("000000", tlv.getValue());
    }



    @Test
    public void test_filler_get_size_001() {
        String input ="7706000082021800";
        Tlv tlv = Tlv.parse(input);

        List<Tlv> child = tlv.getChild();

        // this is filler
        Tlv item = child.get(0);

        assertEquals(2, item.getLength());
        assertEquals(2, item.getSize());
        assertEquals("0000", item.getValue());
        assertEquals("0000", item.toString());
    }
}

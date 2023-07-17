import io.github.coolbong.util.Hex;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ToLvTest {

    @Test
    public void test_to_lv001() {
        String input = "112233";
        String answer  = "03112233";

        String ret = Hex.toLv(input);

        assertEquals(answer, ret);
    }
}

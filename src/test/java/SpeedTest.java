import io.github.coolbong.util.Hex;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Stopwatch;

public class SpeedTest {


    @Rule
    public SimpleStopwatch stopwatch = new SimpleStopwatch();

    @Test
    public void speed_test_001() {

        byte[] arr = {
                (byte) 0x40, (byte) 0x41, (byte) 0x42, (byte) 0x43, (byte) 0x44, (byte) 0x45, (byte) 0x46, (byte) 0x47,
                (byte) 0x48, (byte) 0x49, (byte) 0x4a, (byte) 0x4b, (byte) 0x4c, (byte) 0x4d, (byte) 0x4e, (byte) 0x4f
        };
        for (int i=0; i<1000; i++){
            Hex.toHex(arr);
        }
    }

    @Test
    public void speed_test_002() {

        byte[] arr = {
                (byte) 0x40, (byte) 0x41, (byte) 0x42, (byte) 0x43, (byte) 0x44, (byte) 0x45, (byte) 0x46, (byte) 0x47,
                (byte) 0x48, (byte) 0x49, (byte) 0x4a, (byte) 0x4b, (byte) 0x4c, (byte) 0x4d, (byte) 0x4e, (byte) 0x4f
        };
        for (int i=0; i<1000; i++){
            Hex.toHex(arr);
        }
    }
}

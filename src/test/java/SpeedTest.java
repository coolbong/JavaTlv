import io.github.coolbong.tlv.Hex;
import io.github.coolbong.tlv.Tlv;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.time.Duration;
import java.time.Instant;

public class SpeedTest {

    @Rule
    public SimpleStopwatch stopwatch = new SimpleStopwatch();

    @Before
    public void start() {
        System.currentTimeMillis();
    }

    @After
    public void end() {
        System.currentTimeMillis();
    }

/*
    @Test
    public void speed_test_000() {
        //System.out.println("Start time");
    }

    @Test
    public void speed_test_001() {

        byte[] arr = {
                (byte) 0x40, (byte) 0x41, (byte) 0x42, (byte) 0x43, (byte) 0x44, (byte) 0x45, (byte) 0x46, (byte) 0x47,
                (byte) 0x48, (byte) 0x49, (byte) 0x4a, (byte) 0x4b, (byte) 0x4c, (byte) 0x4d, (byte) 0x4e, (byte) 0x4f
        };
        //long start = System.currentTimeMillis();
        Instant beforeTime = Instant.now();  // 코드 실행 전에 시간 받아오기
        for (int i=0; i<100000; i++){
            Hex.toHex(arr);
        }
        Instant afterTime = Instant.now();
        long diffTime = Duration.between(beforeTime, afterTime).toMillis(); // 두 개의 실행 시간
        //System.out.println("실행 시간(ms): " + diffTime);
        //long duration = System.currentTimeMillis() - start;
        //System.out.println("speed_test_001: " + duration + "ms");
    }

    @Test
    public void speed_test_002() {
        byte[] arr = {
                (byte) 0x40, (byte) 0x41, (byte) 0x42, (byte) 0x43, (byte) 0x44, (byte) 0x45, (byte) 0x46, (byte) 0x47,
                (byte) 0x48, (byte) 0x49, (byte) 0x4a, (byte) 0x4b, (byte) 0x4c, (byte) 0x4d, (byte) 0x4e, (byte) 0x4f
        };
        //long start = System.currentTimeMillis();
        Instant beforeTime = Instant.now();  // 코드 실행 전에 시간 받아오기
        for (int i=0; i<100000; i++){
            Hex.toHex(arr);
        }
        Instant afterTime = Instant.now();
        long diffTime = Duration.between(beforeTime, afterTime).toMillis(); // 두 개의 실행 시간
        //System.out.println("실행 시간(ms): " + diffTime);
        //long duration = System.currentTimeMillis() - start;
        //System.out.println("speed_test_001: " + duration + "ms");
    }

    @Test
    public void speed_test_003() {
        byte[] arr = {
                (byte) 0x40, (byte) 0x41, (byte) 0x42, (byte) 0x43, (byte) 0x44, (byte) 0x45, (byte) 0x46, (byte) 0x47,
                (byte) 0x48, (byte) 0x49, (byte) 0x4a, (byte) 0x4b, (byte) 0x4c, (byte) 0x4d, (byte) 0x4e, (byte) 0x4f
        };
        //long start = System.currentTimeMillis();
        Instant beforeTime = Instant.now();  // 코드 실행 전에 시간 받아오기
        for (int i=0; i<100000; i++){
            Hex.toHex(arr);
        }
        Instant afterTime = Instant.now();
        long diffTime = Duration.between(beforeTime, afterTime).toMillis(); // 두 개의 실행 시간
        //System.out.println("실행 시간(ms): " + diffTime);
        //long duration = System.currentTimeMillis() - start;
        //System.out.println("speed_test_001: " + duration + "ms");
    }

    @Test
    public void speed_test_004() {
        byte[] arr = {
                (byte) 0x40, (byte) 0x41, (byte) 0x42, (byte) 0x43, (byte) 0x44, (byte) 0x45, (byte) 0x46, (byte) 0x47,
                (byte) 0x48, (byte) 0x49, (byte) 0x4a, (byte) 0x4b, (byte) 0x4c, (byte) 0x4d, (byte) 0x4e, (byte) 0x4f
        };
        //long start = System.currentTimeMillis();
        Instant beforeTime = Instant.now();  // 코드 실행 전에 시간 받아오기
        for (int i=0; i<100000; i++){
            Hex.toHexOld(arr);
        }
        Instant afterTime = Instant.now();
        long diffTime = Duration.between(beforeTime, afterTime).toMillis(); // 두 개의 실행 시간
        //System.out.println("실행 시간(ms): " + diffTime);
        //long duration = System.currentTimeMillis() - start;
        //System.out.println("speed_test_001: " + duration + "ms");
    }

    @Test
    public void speed_test_005() {
        byte[] arr = {
                (byte) 0x40, (byte) 0x41, (byte) 0x42, (byte) 0x43, (byte) 0x44, (byte) 0x45, (byte) 0x46, (byte) 0x47,
                (byte) 0x48, (byte) 0x49, (byte) 0x4a, (byte) 0x4b, (byte) 0x4c, (byte) 0x4d, (byte) 0x4e, (byte) 0x4f
        };
        //long start = System.currentTimeMillis();
        Instant beforeTime = Instant.now();  // 코드 실행 전에 시간 받아오기
        for (int i=0; i<100000; i++){
            Hex.toHex3(arr);
        }
        Instant afterTime = Instant.now();
        long diffTime = Duration.between(beforeTime, afterTime).toMillis(); // 두 개의 실행 시간
        //System.out.println("실행 시간(ms): " + diffTime);
        //long duration = System.currentTimeMillis() - start;
        //System.out.println("speed_test_001: " + duration + "ms");
    }

    @Test
    public void speed_test_006() {
        byte[] arr = {
                (byte) 0x40, (byte) 0x41, (byte) 0x42, (byte) 0x43, (byte) 0x44, (byte) 0x45, (byte) 0x46, (byte) 0x47,
                (byte) 0x48, (byte) 0x49, (byte) 0x4a, (byte) 0x4b, (byte) 0x4c, (byte) 0x4d, (byte) 0x4e, (byte) 0x4f
        };
        //long start = System.currentTimeMillis();
        Instant beforeTime = Instant.now();  // 코드 실행 전에 시간 받아오기
        for (int i=0; i<100000; i++){
            Hex.toHex4(arr);
        }
        Instant afterTime = Instant.now();
        long diffTime = Duration.between(beforeTime, afterTime).toMillis(); // 두 개의 실행 시간
        //System.out.println("실행 시간(ms): " + diffTime);
        //long duration = System.currentTimeMillis() - start;
        //System.out.println("speed_test_001: " + duration + "ms");
    }

    @Test
    public void speed_tlv_parse_001() {
        String resp = "6F20840E315041592E5359532E4444463031A50E5F2D046B6F656E9F110101880101";
        Instant beforeTime = Instant.now();  // 코드 실행 전에 시간 받아오기
        for (int i=0; i<10000; i++) {
            Tlv tlv = Tlv.parse(resp);
        }
        Instant afterTime = Instant.now();
        long diffTime = Duration.between(beforeTime, afterTime).toMillis(); // 두 개의 실행 시간
        //System.out.println("실행 시간(ms): " + diffTime);
    }
*/

}

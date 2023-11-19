package logger;

import io.github.coolbong.tlv.TlvLogger;

public class ConsoleLogger implements TlvLogger {
    @Override
    public void debug(String format, Object... args) {
        System.out.println(MessageFormatter.arrayFormat(format, args).getMessage());
    }

    @Override
    public void warn(String format, Object... args) {
        System.out.println(MessageFormatter.arrayFormat(format, args).getMessage());
    }

    @Override
    public void error(String format, Object... args) {
        System.out.println(MessageFormatter.arrayFormat(format, args).getMessage());
    }
}

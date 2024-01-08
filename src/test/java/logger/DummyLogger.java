package logger;

import io.github.coolbong.tlv.TlvLogger;

public class DummyLogger implements TlvLogger {
    @Override
    public void debug(String format, Object... args) {

    }

    @Override
    public void warn(String format, Object... args) {

    }

    @Override
    public void error(String format, Object... args) {

    }
}

package io.github.coolbong.tlv;


public interface TlvLogger {
    void debug(String format, Object... args);

    void warn(String format, Object... args);

    void error(String format, Object... args);
}

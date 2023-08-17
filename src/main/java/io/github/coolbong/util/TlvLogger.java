package io.github.coolbong.util;




public interface TlvLogger {
    void debug(String format, Object... args);
    void error(String format, Object... args);
}

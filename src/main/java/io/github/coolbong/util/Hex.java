package io.github.coolbong.util;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;

public class Hex {

    private static final char[] hexArray = "0123456789ABCDEF".toCharArray();

    public static String strip(String s) {
        return s.replaceAll("\\s", "");
    }

    public static byte[] toBytes(short s) {
        return ByteBuffer.allocate(2).putShort(s).array();
    }

    public static byte[] toBytes(String s) {
        return decodeHex(s.replaceAll("\\s", "").toCharArray());
    }

    public static void toBytes(String s, byte[] bArray, int offset) {
        decodeHex(s.replaceAll("\\s", "").toCharArray(), bArray, offset);
    }

    private static byte[] decodeHex(char[] data) {
        int len = data.length;
        if ((len & 1) != 0) {
            return null;
        } else {
            byte[] out = new byte[len >> 1];
            int i = 0;

            for(int j = 0; j < len; ++i) {
                int f = toDigit(data[j]) << 4;
                ++j;
                f |= toDigit(data[j]);
                ++j;
                out[i] = (byte)(f & 255);
            }

            return out;
        }
    }

    private static void decodeHex(char[] data, byte[] buffer, int offset) {
        int len = data.length;
        if ((len & 1) == 0) {
            if (buffer != null && buffer.length >= len >> 1) {
                int i = 0;

                for(int j = 0; j < len; ++i) {
                    int f = toDigit(data[j]) << 4;
                    ++j;
                    f |= toDigit(data[j]);
                    ++j;
                    buffer[i + offset] = (byte)(f & 255);
                }

            }
        }
    }

    private static int toDigit(char ch) {
        int digit = Character.digit(ch, 16);
        return digit;
    }


    public static String toAscii(byte[] buf) {
        try {
            return new String(buf, StandardCharsets.UTF_8);
        } catch (Exception e) {
            return "";
        }
    }
    public static String toAscii(byte[] buf, int offset, int length) {
        try {
            return new String(buf, offset, length, StandardCharsets.UTF_8);
        } catch (Exception e) {
            return "";
        }
    }

    public static String toAscii(String hex) {
        byte[] buf = toBytes(hex);
        return toAscii(buf);
    }

    public static String toHex(byte b) {
        char[] hexChars = new char[2];
        int val = b & 0xff;
        hexChars[0] = hexArray[val >>> 4];
        hexChars[1] = hexArray[val & 15];
        return new String(hexChars);
    }

    public static String toHex(short s) {
        byte[] bArray = new byte[2];
        bArray[1] = (byte)(s & 0xff);
        bArray[0] = (byte)((s >> 8) & 0xff);
        return toHex(bArray);
    }

    public static String toHex(int i) {
        byte[] bArray = new byte[4];
        bArray[3] = (byte)(i & 0xff);
        bArray[2] = (byte)((i >> 8) & 0xff);
        bArray[1] = (byte)((i >> 16) & 0xff);
        bArray[0] = (byte)((i >> 24) & 0xff);
        return toHex(bArray);
    }

    public static String toHex(byte[] buffer, int offset, int length) {
        char[] hexChars = new char[length * 2];

        for(int j = 0; j < length; ++j) {
            int v = buffer[j + offset] & 255;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 15];
        }
        return new String(hexChars);
    }

    public static String toHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];

        for(int j = 0; j < bytes.length; ++j) {
            int v = bytes[j] & 255;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 15];
        }

        return new String(hexChars);
    }

    public static String u1(byte[] data, int offset) {
        return toHex(data[offset]);
    }

    public static String u2(byte[] data, int offset) {
        return toHex(data[offset]) + toHex(data[offset + 1]);
    }

    public static String un(byte[] data, int offset, int length) {
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<length; i++)
            sb.append(toHex(data[offset+i]));

        return sb.toString();
    }

    public static short setShort(byte[] bArray, int bOff, int sValue) {
        bArray[bOff + 1] = (byte)((sValue & 0xff));
        bArray[bOff] = (byte)((sValue >> 8) & 0xff);
        return (short)(bOff + 2);
    }

    public static short getShort(byte[] bArray, int bOff) {
        return (short)(((bArray[bOff] & 0xff) << 8) | (bArray[bOff + 1] & 0xff));
    }

    public static int toInt(byte[] bArray) {
        return ByteBuffer.wrap(bArray).getInt();
    }

    public static int toInt(String hex) {
        return Integer.parseInt(hex, 16);
    }

    public static byte[] slice(byte[] buf, int offset, int length) {
        return Arrays.copyOfRange(buf, offset, offset + length);
    }

    public static String toLv(String data) {

        byte[] value = Hex.toBytes(data);
        int length = value.length;
        byte[] bLen = null;

        if (length < 128) {
            bLen = new byte[1];
            bLen[0] = (byte)length;
        } else if (length < 256) {
            bLen = new byte[2];
            bLen[0] = (byte)0x81;
            bLen[1] = (byte)length;
        } else {
            bLen = new byte[3];
            bLen[0] = (byte)0x82;
            bLen[1] = (byte)(length >>> 8);
            bLen[2] = (byte)length;
        }

        return toHex(bLen) + data;
    }

}

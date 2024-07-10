package io.github.coolbong.tlv;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Hex {

    private Hex() {
    }

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
            return new byte[0];
            //return null;
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
        if ((len & 1) == 0 && buffer != null && buffer.length >= len >> 1) {
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


    private static int toDigit(char ch) {
        return Character.digit(ch, 16);
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

    public static String toHexOld(byte[] buffer, int offset, int length) {
        char[] hexChars = new char[length * 2];

        for(int j = 0; j < length; ++j) {
            int v = buffer[j + offset] & 255;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 15];
        }
        return new String(hexChars);
    }

    public static String toHex(byte[] buffer, int offset, int length) {
        char[] dst   = new char[length * 2];

        for (int si = offset, di = 0; si < offset + length; si++) {
            byte b = buffer[si];
            dst[di++] = hexArray[(b & 0xf0) >>> 4];
            dst[di++] = hexArray[(b & 0x0f)];
        }

        return new String(dst);
    }

    public static String toHexOld(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];

        int length = bytes.length;
        for(int j = 0; j < length; ++j) {
            int v = bytes[j] & 255;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 15];
        }

        return new String(hexChars);
    }

    public static String toHex(byte[] bytes) {
        int length = bytes.length;
        char[] dst   = new char[length * 2];

        for (int si = 0, di = 0; si < length; si++) {
            byte b = bytes[si];
            dst[di++] = hexArray[(b & 0xf0) >>> 4];
            dst[di++] = hexArray[(b & 0x0f)];
        }

        return new String(dst);
    }

    public static String toHex3(byte[] arr) {
        StringBuilder sb = new StringBuilder();
        for (byte b : arr) {
            sb.append(String.format("%02X", b));
        }
        return sb.toString();
    }
    private static final char[] BYTE2HEX=(
            "000102030405060708090A0B0C0D0E0F"+
                    "101112131415161718191A1B1C1D1E1F"+
                    "202122232425262728292A2B2C2D2E2F"+
                    "303132333435363738393A3B3C3D3E3F"+
                    "404142434445464748494A4B4C4D4E4F"+
                    "505152535455565758595A5B5C5D5E5F"+
                    "606162636465666768696A6B6C6D6E6F"+
                    "707172737475767778797A7B7C7D7E7F"+
                    "808182838485868788898A8B8C8D8E8F"+
                    "909192939495969798999A9B9C9D9E9F"+
                    "A0A1A2A3A4A5A6A7A8A9AAABACADAEAF"+
                    "B0B1B2B3B4B5B6B7B8B9BABBBCBDBEBF"+
                    "C0C1C2C3C4C5C6C7C8C9CACBCCCDCECF"+
                    "D0D1D2D3D4D5D6D7D8D9DADBDCDDDEDF"+
                    "E0E1E2E3E4E5E6E7E8E9EAEBECEDEEEF"+
                    "F0F1F2F3F4F5F6F7F8F9FAFBFCFDFEFF").toCharArray();

    public static String toHex4(byte[] arr) {
        int length = arr.length;
        char[] chars = new char[length <<1];
        int index;
        int idx = 0;
        int offset = 0;
        while (offset < length) {
            index =(arr[offset++] & 0xFF)<<1;
            chars[idx++]=BYTE2HEX[index++];
            chars[idx++]=BYTE2HEX[index];
        }
        return new String(chars);
    }

    public static String asciiToHex(String ascii) {
        byte[] arr = ascii.getBytes(StandardCharsets.UTF_8);
        return toHex(arr);
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
        bArray[bOff + 1] = (byte)(sValue & 0xff);
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
        byte[] bLen;

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


    public static String toVariable(String hex) {
        byte[] arr = toBytes(hex);


//        ByteBuffer buffer = ByteBuffer.wrap(arr);
//        String var = Stream.generate(buffer::get)
//                .map(b -> "0x" + toHex(b))
//                .collect(Collectors.joining(", "));
//        return var;

        StringBuilder sb = new StringBuilder();
        for (byte b : arr) {
            sb.append(String.format("(byte)0x%02x, ", b));
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.deleteCharAt(sb.length() - 1);

        return sb.toString();

    }

}

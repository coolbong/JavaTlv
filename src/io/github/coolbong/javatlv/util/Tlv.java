package io.github.coolbong.javatlv.util;

import java.util.ArrayList;

/**
 * Tlv data
 */

public class Tlv {

    private byte[] bTag;
    private byte[] bLen;
    private byte[] bValue;
    private int length;

    private ArrayList<Tlv> child;


    public Tlv(String tag, String value) {
        this(Hex.toBytes(tag), null, Hex.toBytes(value));
    }

    public Tlv(byte[] bTag, byte[] bValue) {
        this(bTag, null, bValue);
    }

    public Tlv(byte[] tag, byte[] len,  byte[] value) {
        this.bTag = tag;
        this.bValue = value;
        this.length = value.length;
        this.child = new ArrayList<Tlv>();

        if (len == null) {
            if (length > 127) {
                this.bLen = new byte[2];
                this.bLen[0] = (byte)0x81;
                this.bLen[1] = (byte)length;
            } else {
                this.bLen = new byte[1];
                this.bLen[0] = (byte)length;
            }
        } else {
            this.bLen = len;
        }

        if (isConstructed()) {
            int offset = 0;
            while (offset < length) {
                Tlv child = Tlv.parse(value, offset);
                offset += child.getSize();
                this.child.add(child);
            }
        }
    }

    public String getbTag() {
        return Hex.toHex(bTag);
    }

    public int getLength() {
        return length;
    }

    public String value() {
        return Hex.toHex(bValue);
    }

    public Tlv find(String tag) {
        return null;
    }

    public boolean isConstructed() {
        return ((this.bTag[0] & 0x20) == 0x20);
    }

    public int getEncodedLength() {
        return bLen.length;
    }

    public int getSize() {
        return bTag.length + getEncodedLength() + bValue.length;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Hex.toHex(bTag)).append(" ");
        sb.append(Hex.toHex(bLen)).append(" ");
        sb.append(Hex.toHex(bValue));
        return sb.toString();
    }

    public void print() {
        print(0);
    }

    public void print(int indent) {
        StringBuilder tab = new StringBuilder();
        for (int i=0; i<indent; i++)
            tab.append("\t");

        StringBuilder sb = new StringBuilder();
        sb.append(tab);
        sb.append(Hex.toHex(bTag)).append(" "); // tag
        sb.append(Hex.toHex(bLen)).append("(").append(length).append(")"); // length


        if (child.size() != 0) {
            System.out.println(sb.toString());
            child.forEach(tlv -> tlv.print(indent + 1));
        } else {
            sb.append(' ');
            sb.append(Hex.toHex(bValue)); // value
            System.out.println(sb.toString());
        }
    }


    public static Tlv parse(String data) {
        return parse(Hex.toBytes(data));
    }

    public static Tlv parse(byte[] buf) {
        return parse(buf, 0);
    }

    public static Tlv parse(byte[] buf, int offset) {
        byte[] bTag = parseTag(buf, offset);

        offset += bTag.length;

        int length = 0;
        byte[] bLen;
        int number_of_bytes = 0;
        if ((buf[offset] & 0x80) == 0x80) {
            number_of_bytes = buf[offset] & 0x7F + 1;
            length = Hex.toInt(Hex.toHex(buf, offset, number_of_bytes));
            bLen = Hex.slice(buf, offset, number_of_bytes);
        } else {
            number_of_bytes = 1;
            length = buf[offset];
            bLen = new byte[1];
            bLen[0] =  buf[offset];
        }
        offset += number_of_bytes;

        if ((offset + length) > buf.length) {
            System.out.println("Invalid Data");
        }

        byte[] bValue = Hex.slice(buf, offset, length);
        return new Tlv(bTag, bLen, bValue);
    }

    public static byte[] parseTag(byte[] buf, int offset) {
        int length = 1;
        int pos = offset;
        if ((buf[pos++] & 0x1f) == 0x1f) { // subsequent byte
            do {
                length ++;
            } while ((buf[pos++] & 0x80) == 0x80);
        }

        return Hex.slice(buf, offset, length);
    }



}

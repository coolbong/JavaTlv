package io.github.coolbong.tlv;


import java.util.ArrayList;
import java.util.List;

import static io.github.coolbong.tlv.Hex.toHex;

public class Tlv {

    public static final int EMV = 0;
    public static final int DGI = 1;


    byte[] bTag;
    byte[] bLen;
    byte[] bValue;
    int length;

    int encoding;

    ArrayList<Tlv> child;

    public Tlv() {
        this.bTag = null;
        this.bValue = null;
        this.length = 0;
        this.child = new ArrayList<>();
        this.encoding = EMV;
    }

    public Tlv(String tag, String value) {
        this(Hex.toBytes(tag), null, Hex.toBytes(value), EMV);
    }

    public Tlv(String tag, String value, int encoding) {
        this(Hex.toBytes(tag), null, Hex.toBytes(value), encoding);
    }

    public Tlv(byte[] bTag, byte[] bValue) {
        this(bTag, null, bValue, EMV);
    }

    public Tlv(byte[] bTag, byte[] bValue, int encoding) {
        this(bTag, null, bValue, encoding);
    }

    public Tlv(byte[] tag, byte[] len,  byte[] value) {
        this(tag, len, value, EMV);
    }

    public Tlv(byte[] tag, byte[] len,  byte[] value, int encoding) {
        this.bTag = tag.clone();
        this.bValue = value.clone();
        this.length = value.length;
        this.child = new ArrayList<>();
        this.encoding = encoding;

        if (len == null) {
            if (encoding == DGI) {
                // 3 bytes with the first byte set to 'FF' followed by 2 bytes '0000' to 'FFFE'(0 to 65534)
                if (length > 254) {
                    this.bLen = new byte[3];
                    this.bLen[0] = (byte)0xff;
                    this.bLen[1] = (byte)((length >> 8) & 0xff);
                    this.bLen[2] = (byte)length;
                } else {  //  1 byte in binary format if the length of data is from '00' to 'FE' (0 to 254 bytes)
                    this.bLen = new byte[1];
                    this.bLen[0] = (byte)length;
                }
            } else {
                if (length > 127) {
                    this.bLen = new byte[2];
                    this.bLen[0] = (byte)0x81;
                    this.bLen[1] = (byte)length;
                } else {
                    this.bLen = new byte[1];
                    this.bLen[0] = (byte)length;
                }
            }
        } else {
            this.bLen = len.clone();
        }

        if (isConstructed()) {
            int offset = 0;
            while (offset < length) {
                Tlv child = Tlv.parse(value, offset);
                if (child == null) {
                    break;
                }
                offset += child.getSize();
                this.child.add(child);
            }
        }
    }

    /**
     * Return the value of tag field of the Tlv.
     *
     * @return string value of the tag
     */
    public String getTag() {
        return toHex(bTag);
    }

    /**
     * Return the value of tag field of the Tlv.
     *
     * @return byte array value of the tag
     */
    public byte[] getTagBytes() {
        return bTag;
    }

    /**
     * Return the value of length field of the Tlv.
     *
     * @return the length of the Tlv.
     */
    public int getLength() {
        return length;
    }

    /**
     * Return the value of length field of the TLV.
     *
     * @return byte array of the length field of the TLV.
     */
    public byte[] getLengthBytes() {
        return bLen;
    }

    /**
     * Return the value of the value field of the TLV.
     *
     * @return the value field of the TLV.
     */
    public String getValue() {
        return toHex(bValue);
    }

    /**
     * Return the value of the value field of the TLV.
     *
     * @return byte array of the value field of the TLV.
     */
    public byte[] getValueBytes() {
        return bValue;
    }

    /**
     * find tlv object.
     * @param tag string value of the tag
     * @return tlv object or null
     */
    public Tlv find(String tag) {
        final String targetTag = Hex.strip(tag).toUpperCase();

        Tlv targetTlv = null;
        for (Tlv tlv : child) {
            if (tlv.getTag().equals(targetTag)) {
                targetTlv = tlv;
                break;
            }
            if (tlv.isConstructed()) {
                targetTlv = tlv.find(targetTag);
                if (targetTlv != null) {
                    break;
                }
            }
        }

        return targetTlv;
    }

    /**
     * find tlv object.
     *
     * @param tag string value of the tag
     * @return the tlv list, or empty list
     */
    public List<Tlv> findAll(String tag) {
        final String targetTag = Hex.strip(tag).toUpperCase();

        ArrayList<Tlv> tlvs = new ArrayList<>();

        for (Tlv tlv : child) {
            if (tlv.getTag().equals(targetTag)) {
                tlvs.add(tlv);
            }
            if (tlv.isConstructed()) {
                List<Tlv> ret = tlv.findAll(targetTag);
                tlvs.addAll(ret);
            }
        }
        return tlvs;
    }

    public List<Tlv> getChild() {
        return this.child;
    }

    /**
     * true if the Tlv object is constructed
     * @return true if the Tlv object is constructed and false otherwise
     */
    public boolean isConstructed() {
        return ((this.encoding == Tlv.EMV) && ((this.bTag[0] & 0x20) == 0x20));
    }

    public int getEncodedLength() {
        return bLen.length;
    }

    public int getSize() {
        return bTag.length + getEncodedLength() + bValue.length;
    }

    @Override
    public String toString() {
        return String.format("%s%s%s", toHex(bTag), toHex(bLen), toHex(bValue));
    }

    public byte[] toBytes() {
        int length = bTag.length + bLen.length + bValue.length;
        int offset = 0;
        byte[] dst = new byte[length];
        System.arraycopy(bTag, 0, dst, offset, bTag.length);
        offset += bTag.length;
        System.arraycopy(bLen, 0, dst, offset, bLen.length);
        offset += bLen.length;
        System.arraycopy(bValue, 0, dst, offset, bValue.length);
        return dst;
    }

    public void print() {
        print(0);
    }

    public void print(int indent) {
        StringBuilder tab = new StringBuilder();
        for (int i=0; i<indent; i++) {
            tab.append(space);
        }

        StringBuilder sb = new StringBuilder();
        sb.append(tab);
        sb.append(String.format("%s %s(%d)", toHex(bTag), toHex(bLen), length));

        if (!child.isEmpty()) {
            System.out.println(sb);
            child.forEach(tlv -> tlv.print(indent + 1));
        } else {
            sb.append(' ');
            sb.append(toHex(bValue)); // value
            System.out.println(sb);
        }
    }

    public void log(TlvLogger TlvLogger) {
        log(0, TlvLogger);
    }

    public static final String space = "    ";
    //public static final String space = "\t";

    public void log(int indent, TlvLogger logger) {
        StringBuilder tab = new StringBuilder();
        for (int i=0; i<indent; i++) {
            tab.append(space);
        }

        if (!child.isEmpty()) {
            logger.debug("{}{} {}({})", tab, String.format("%-4s",toHex(bTag)), toHex(bLen), String.format("%2d", length));
            child.forEach(tlv -> tlv.log(indent + 1, logger));
        } else {
            logger.debug("{}{} {}({}) {}", tab, String.format("%-4s",toHex(bTag)), toHex(bLen), String.format("%2d", length), toHex(bValue));
        }
    }


    public static Tlv parse(String data) {
        return parse(Hex.toBytes(data), 0, EMV);
    }

    public static Tlv parse(String data, int encoding) {
        return parse(Hex.toBytes(data), 0, encoding);
    }

    public static Tlv parse(byte[] buf, int offset) {
        return parse(buf, offset, EMV);
    }

    public static Tlv parse(byte[] buf, int offset, int encoding) {
        if (buf == null) {
            return null;
        }
        // if (buf.length < offset) // prevent array out of bound exception

        // skip dummy byte (zero byte)
        while(buf[offset] == 0) {
            offset++;
        }

        byte[] bTag;
        bTag = parseTag(buf, offset, encoding);

        offset += bTag.length;

        int length;
        byte[] bLen;
        int number_of_bytes;
        if (encoding == Tlv.EMV) {
            if ((buf[offset] & 0x80) == 0x80) {
                number_of_bytes = (buf[offset] & 0x7F) + 1;
                length = Hex.toInt(toHex(buf, offset + 1, number_of_bytes - 1));
                bLen = Hex.slice(buf, offset, number_of_bytes);
            } else {
                number_of_bytes = 1;
                length = buf[offset];
                bLen = new byte[1];
                bLen[0] = buf[offset];
            }

        } else {
            if (buf[offset] == (byte)0xff) { // 3 byte length
                number_of_bytes = 3;
                length = Hex.getShort(buf, offset +1);
                bLen = Hex.slice(buf, offset, 3); // ff 00 12
            } else { // 1 byte length
                number_of_bytes = 1;
                length = buf[offset] & 0xff;
                bLen = new byte[1];
                bLen[0] = buf[offset];
            }
        }
        offset += number_of_bytes;

        if ((offset + length) > buf.length) {
            System.out.println("Invalid Data");
            return null;
        }

        byte[] bValue = Hex.slice(buf, offset, length);
        return new Tlv(bTag, bLen, bValue, encoding);
    }



    public static byte[] parseTag(byte[] buf, int offset) {
        return parseTag(buf, offset, EMV);
    }

    public static byte[] parseTag(byte[] buf, int offset, int encoding) {
        int length = 1;
        int pos = offset;
        if (encoding == EMV) {
            if ((buf[pos++] & 0x1f) == 0x1f) { // subsequent byte
                do {
                    length++;
                } while ((buf[pos++] & 0x80) == 0x80);
            }
        } else {
            length = 2; // dgi tag length 2 byte
        }

        return Hex.slice(buf, offset, length);
    }


}
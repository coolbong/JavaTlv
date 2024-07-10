package io.github.coolbong.tlv;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static io.github.coolbong.tlv.Hex.toBytes;
import static io.github.coolbong.tlv.Hex.toHex;

public class DolParser {

    private final TlvLogger logger;
    public DolParser() {
        logger = new TlvLogger() {
            @Override
            public void debug(String format, Object... args) {
                // nothing to do
            }

            @Override
            public void warn(String format, Object... args) {
                // nothing to do
            }

            @Override
            public void error(String format, Object... args) {
                // nothing to do
            }
        };
    }

    public DolParser(TlvLogger logger) {
        this.logger = logger;
    }

    public List<Tlv> parse(String dol) {
        return parse(toBytes(dol), null);
    }

    public List<Tlv> parse(String dol, String data) {
        return parse(toBytes(dol), toBytes(data));
    }

    public int dolRelatedDataLength(String dol) {
        byte[] dolBuf = toBytes(dol);

        int offset = 0;

        if (dolBuf.length == 0) { // error check
            logger.error("Invalid argument: DOL buffer is null or length == 0");
            return 0;
        }

        logger.debug("start tlv parse ----------------------------------------------------------------------------");
        logger.debug("buffer offset: {} length: {}", offset, dolBuf.length);

        int ret = 0;
        while (offset < dolBuf.length) {
            int lengthOfTag = getTagLength(dolBuf, offset);
            logger.debug("[tag] offset[{}] length[{}]", String.format("%2d", offset), String.format("%2d", lengthOfTag));

            offset += lengthOfTag;

            int length;
            int numberOfBytes;
            if ((dolBuf[offset] & 0x80) == 0x80) {
                numberOfBytes = (dolBuf[offset] & 0x7F) + 1;
                length = Hex.toInt(toHex(dolBuf, offset + 1, numberOfBytes - 1));
            } else {
                numberOfBytes = 1;
                length = dolBuf[offset];
            }

            logger.debug("[len] offset[{}] length[{}]", String.format("%2d", offset), String.format("%2d", length));
            offset += numberOfBytes;
            ret += length;
        }
        return ret;
    }

    public List<Tlv> parse(byte[] dolBuf, byte[] dolRelatedDataBuf) {
        ArrayList<Tlv> tlvs = new ArrayList<>();

        int offset = 0;
        int valueOffset = 0;

        if (dolBuf == null || dolBuf.length == 0) { // error check
            logger.error("Invalid argument: DOL buffer is null or length == 0");
            return Collections.emptyList();
        }

        if (dolRelatedDataBuf == null) { // error check
            logger.warn("DOL related data is null: tlv value set to be 0");
            dolRelatedDataBuf = new byte[0];
        }

        logger.debug("start tlv parse ----------------------------------------------------------------------------");
        logger.debug("buffer offset: {} length: {}", offset, dolBuf.length);

        while (offset < dolBuf.length) {

            byte[] bTag;
            bTag = parseTag(dolBuf, offset);
            logger.debug("[tag] offset[{}] length[{}] Tag:[{}] ", String.format("%2d", offset), String.format("%2d", bTag.length), toHex(bTag));

            offset += bTag.length;

            int length;
            byte[] bLen;
            int numberOfBytes;
            if ((dolBuf[offset] & 0x80) == 0x80) {
                numberOfBytes = (dolBuf[offset] & 0x7F) + 1;
                length = Hex.toInt(toHex(dolBuf, offset + 1, numberOfBytes - 1));
                bLen = Hex.slice(dolBuf, offset, numberOfBytes);
            } else {
                numberOfBytes = 1;
                length = dolBuf[offset];
                bLen = new byte[1];
                bLen[0] = dolBuf[offset];
            }

            logger.debug("[len] offset[{}] length[{}] Len:[{}]({})", String.format("%2d", offset), String.format("%2d", bLen.length), toHex(bLen), length);
            offset += numberOfBytes;


            byte[] bValue;
            if ((valueOffset + length) > dolRelatedDataBuf.length) {
                bValue = new byte[length];
            } else {
                bValue = Hex.slice(dolRelatedDataBuf, valueOffset, length);
            }
            logger.debug("[val] offset[{}] length[{}] Val:[{}]", String.format("%2d", offset), String.format("%2d", bValue.length), toHex(bValue));
            valueOffset += length;

            Tlv tlv = new Tlv();
            tlv.bTag = bTag;
            tlv.bLen = bLen;
            tlv.length = length;
            tlv.bValue = bValue; // remove?
            tlv.encoding = Tlv.EMV;

            tlvs.add(tlv);
        }

        return tlvs;
    }

    public static byte[] parseTag(byte[] buf, int offset) {
        int length = 1;
        int pos = offset;
        if ((buf[pos++] & 0x1f) == 0x1f) { // subsequent byte
            do {
                length++;
            } while ((buf[pos++] & 0x80) == 0x80);
        }
        return Hex.slice(buf, offset, length);
    }

    public static int getTagLength(byte[] buf, int offset) {
        int length = 1;
        int pos = offset;
        if ((buf[pos++] & 0x1f) == 0x1f) { // subsequent byte
            do {
                length++;
            } while ((buf[pos++] & 0x80) == 0x80);
        }
        return length;
    }


}

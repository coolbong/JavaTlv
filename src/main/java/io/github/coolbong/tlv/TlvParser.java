package io.github.coolbong.tlv;

import static io.github.coolbong.tlv.Hex.*;

public class TlvParser {
    private final TlvLogger logger;

    public TlvParser() {
        logger = new TlvLogger() {
            @Override
            public void debug(String format, Object... args) {
                //System.out.println();
            }

            @Override
            public void warn(String format, Object... args) {
                //System.out.println();
            }

            @Override
            public void error(String format, Object... args) {
                //System.out.println();
            }
        };
    }

    public TlvParser(TlvLogger logger) {
        this.logger = logger;
    }


    public byte[] parseTag(byte[] buf, int offset, int encoding) {
        int length = 1;
        int pos = offset;

        if (encoding == Tlv.DGI) {
            length = 2;
        } else {
            if ((buf[pos++] & 0x1f) == 0x1f) { // subsequent byte
                do {
                    length++;
                } while ((buf[pos++] & 0x80) == 0x80);
            }
        }

        return Hex.slice(buf, offset, length);
    }
    // TODO  more wrapper api

    public Tlv parse(String hex) {
        if (hex == null) {
            return null;
        }
        byte[] arr = toBytes(hex);
        return parse(arr, 0, Tlv.EMV);
    }

    public Tlv parse(String hex, int encoding) {
        if (hex == null) {
            return null;
        }
        byte[] arr = toBytes(hex);
        return parse(arr, 0, encoding);
    }

    public Tlv parse(byte[] buf, int offset, int encoding) {
        if (buf == null) { // error check
            logger.error("Invalid argument: buffer is null");
            return null;
        }

        if (buf.length <= offset) { // prevent array out of bound exception
            logger.error("Invalid argument: offset: {}", offset);
            return null;
        }

        if ((encoding != Tlv.EMV) && (encoding != Tlv.DGI)) {
            logger.error("Invalid argument: encoding: {}", encoding);
            return null;
        }

        logger.debug("start tlv parse ----------------------------------------------------------------------------");
        logger.debug("buffer offset: {} length: {} encoding: {}", offset, buf.length, (encoding == Tlv.EMV) ? "emv" : "dgi");

        // skip dummy byte (zero byte)
        int skipCount = 0;
        while (buf[offset] == 0) {
            offset++;
            skipCount++;

            if (buf.length <= offset) {
                logger.warn("Warning: offset is bigger than buffer length: offset: {}", offset);
                break;//return null;
            }
        }

        if (skipCount != 0) {
            //logger.debug("zero dummy data: build Filler bytes offset: {} length: {}", (offset - skipCount), skipCount);
            logger.debug("[Fil] offset[{}] length[{}]", String.format("%2d", (offset - skipCount)), String.format("%2d", skipCount));
            return new Filler(skipCount);
        }

        byte[] bTag;
        bTag = parseTag(buf, offset, encoding);
        boolean isConstructed = ((encoding == Tlv.EMV) && ((bTag[0] & 0x20) == 0x20));
        logger.debug("[tag] offset[{}] length[{}] Tag:[{}] is constructed: {}", String.format("%2d", offset), String.format("%2d", bTag.length), toHex(bTag), isConstructed);

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
                length = Hex.getShort(buf, offset + 1);
                bLen = Hex.slice(buf, offset, 3); // ff 00 12
            } else { // 1 byte length
                number_of_bytes = 1;
                length = buf[offset] & 0xff;
                bLen = new byte[1];
                bLen[0] = buf[offset];
            }
        }
        logger.debug("[len] offset[{}] length[{}] Len:[{}]({})", String.format("%2d", offset), String.format("%2d", bLen.length), toHex(bLen), length);
        offset += number_of_bytes;

        if ((offset + length) > buf.length) {
            logger.error("Invalid Data: value info offset: {} length: {}, (offset + length)[{}] >  buf.length[{}]", offset, length, (offset + length), buf.length);
            logger.error("Invalid Data: Tag: {} length: {}", toHex(bTag), toHex(bLen));
            return null;
        }

        byte[] bValue = Hex.slice(buf, offset, length);
        logger.debug("[val] offset[{}] length[{}] Val:[{}]", String.format("%2d", offset), String.format("%2d", bValue.length), toHex(bValue));
        //return new Tlv(bTag, bLen, bValue, encoding);

        if (isConstructed) {
            Tlv tlv = new Tlv();
            tlv.bTag = bTag;
            tlv.bLen = bLen;
            tlv.length = length;
            tlv.bValue = bValue; // remove?
            tlv.encoding = encoding;

            if (length < 2) {
                return tlv; // invalid tlv
            }

            int bufLength = (offset + length);
            while (offset < bufLength) {
                Tlv child = parse(buf, offset, encoding);
                if (child == null) {
                    //TODO throw exception ?
                    logger.error("parsed child is null: invalid data: " + toHex(bValue));
                    break;
                }
                offset += child.getSize();
                tlv.child.add(child);
            }
            return tlv;
        } else {
            //logger.debug("</dgi>");
            return new Tlv(bTag, bLen, bValue, encoding);
        }
    }
}

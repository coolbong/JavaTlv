package io.github.coolbong.tlv;

import java.util.ArrayList;

import static io.github.coolbong.tlv.Hex.toHex;

public class Filler extends Tlv{

    public Filler(int len) {

        this.bTag = new byte[1];
        this.bValue = new byte[len];
        this.bLen = new byte[0];
        this.length = len;
        this.child = new ArrayList<>();
        this.encoding = EMV;
    }

    public int getSize() {
        return length;
    }

    public String toString() {
        return toHex(bValue);
    }
}

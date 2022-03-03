package io.github.coolbong.javatlv;

import io.github.coolbong.javatlv.util.Tlv;

public class Main {

    public void test() {
        String resp = "6F20840E315041592E5359532E4444463031A50E8801015F2D046B6F656E9F110101";
        Tlv tlv = Tlv.parse(resp);
        System.out.println(tlv);

        assert tlv.getbTag() == "6F";
        assert tlv.getLength() == 0x20;

        // find dedicated file name
        Tlv df_name = tlv.find("84");

        // find FCI Proprietary Template
        Tlv fci_prop_template = tlv.find("A5");
        tlv.print();
    }


    public static void main(String[] args) {
        System.out.println("Hello World");

        Main main = new Main();
        main.test();
    }
}

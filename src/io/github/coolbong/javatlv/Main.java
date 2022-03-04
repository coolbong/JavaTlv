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

    public void ppse_parse_test() {
        String resp = "6F52840E325041592E5359532E4444463031A540BF0C3D611E4F07D410000001501050104B4C5343204455414C20435245444954870101611B4F07D4100000014010500D4B4C5343204A5553544F554348870102";
        Tlv tlv = Tlv.parse(resp);
        tlv.print();
    }

    public void build_ppse() {
        Tlv df_name = new Tlv("84", "325041592E5359532E4444463031");

        Tlv adf_name = new Tlv("4F", "A0000000031010"); // aid for visa
        Tlv app_label = new Tlv("50", "5649534120435245444954"); // application label
        Tlv app_priority = new Tlv("87", "01"); //  Application Priority Indicator

        Tlv dir_entry = new Tlv("61", adf_name.toString() + app_label.toString() + app_priority.toString());
        dir_entry.print();

        Tlv issuer_discretionary_data = new Tlv("BF0C", dir_entry.toString());
        issuer_discretionary_data.print();

        Tlv fci_proprietary_template = new Tlv("A5", issuer_discretionary_data.toString());
        fci_proprietary_template.print();

        Tlv fci = new Tlv("6F", df_name.toString() + fci_proprietary_template.toString());
        fci.print();


    }

    public static void main(String[] args) {
        System.out.println("Hello World");

        Main main = new Main();
        main.test();
        main.ppse_parse_test();
        main.build_ppse();
    }
}

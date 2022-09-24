package io.github.coolbong.javatlv;

import io.github.coolbong.javatlv.util.Tlv;

import java.util.ArrayList;

public class Main {

    public void test() {
        String resp = "6F20840E315041592E5359532E4444463031A50E8801015F2D046B6F656E9F110101";
        Tlv tlv = Tlv.parse(resp);
        System.out.println(tlv);

        assert tlv.getTag() == "6F";
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

        Tlv df_name_tlv = tlv.find("84");
        df_name_tlv.print();

        ArrayList<Tlv> tlvs = tlv.findAll("61");
        tlvs.forEach(ret -> ret.print());

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

    public void parse_gac() {
        String resp = "7781E09F2701809F360200019F4B81B020F9D296CC1A4CEF89E094B0703CA553C9E8BC416F31DE3E03977621269A098F1644D61498602A0467337D4C19C44CC4B8D595F338380A6DFE33CF0719FBA951F7FC46A03E061DB7F72438E18DDE79619337EDC41B5016131F421C27E9AABF9AED9CE3D4873361E0415AC97380127C8AD767B3108B69991B6BECD2AE9FD2F734DAEF5FD11EF8586B17E8B60AC2CBBD7FD66C9AE00ED4B683EE1E0563A718069A90E2709EE9F22EE0BCE540E0543B558D9F10200FA501A800F0000000000000000000000F010000000000000000000000000000";
        Tlv tlv = Tlv.parse(resp);
        tlv.print();
    }

    public void parse_record() {
        Tlv tlv = null;
        tlv = Tlv.parse("704D57139409119700015643D49126012000014000000F5F201A5041524B204A414545554E2020202020202020202F20202020209F1F183230303030202020202020202030303134303030303030309000");
        tlv.print();
        tlv = Tlv.parse("70675F24034912315F25032011045A0894091197000156435F3401019F0702AB008C1D9F02069F03069F1A0295055F2A029A039C019F37049F35019F34039B028E0A00000000000000001F009F0D0500000000009F0E0500000000009F0F0500000000005F280204109000");
        tlv.print();
        tlv = Tlv.parse("700A5F300206019F080200019000");
        tlv.print();
    }

    public static void main(String[] args) {
        System.out.println("Hello World");

        Main main = new Main();
        //main.test();
        //main.ppse_parse_test();
        //main.build_ppse();
        //main.parse_gac();
        main.parse_record();
    }
}

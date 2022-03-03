# JavaTlv

Java tlv parser

[ISO7816 part 4 BER-TLV](https://cardwerk.com/iso7816-4-annex-d-use-of-basic-encoding-rules-asn-1/)



## Example for parse FCI

```java


    String resp = "6F20840E315041592E5359532E4444463031A50E8801015F2D046B6F656E9F110101";
    Tlv tlv = Tlv.parse(resp);

    assert tlv.getTag() == "6F";
    assert tlv.getLength() == 0x20;

    // find dedicated file name
    Tlv df_name = tlv.find("84");

    // find FCI Proprietary Template
    Tlv fci_prop_template = tlv.find("A5");


```

package ua.lviv.iot;

public class Helmet extends Good {
    private String sizeOfHead;

    public Helmet(final GoodType goodType, final GoodName name, final int price, final String material,
                  final double weight, final String manufucturer,
                  final String color, final int amount, final String sizeOfHead) {
        super(goodType, name, price, material, weight, manufucturer, color, amount);
        setSizeOfHead(sizeOfHead);
    }

    public String getHeaders() {
        return super.getHeaders() + "," + "sizeOfHead";
    }

    public String toCVS() {
        return super.toCVS() + "," + getSizeOfHead();
    }

    public String getSizeOfHead() {
        return sizeOfHead;
    }

    public final void setSizeOfHead(final String sizeOfHead) {
        this.sizeOfHead = sizeOfHead;
    }

}

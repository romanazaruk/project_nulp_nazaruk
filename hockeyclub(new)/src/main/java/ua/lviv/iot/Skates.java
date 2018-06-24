package ua.lviv.iot;

public class Skates extends Good {
    private String sizeOfLeg;

    public Skates(final GoodType goodType, final GoodName name, final int price, final String material,
                  final double weight, final String manufucturer,
                  final String color, final int amount, final String sizeOfLeg) {

        super(goodType, name, price, material, weight, manufucturer, color, amount);
        setSizeOfLeg(sizeOfLeg);
    }

    public String getHeaders() {
        return super.getHeaders() + "," + "sizeOfLeg";
    }

    public String toCVS() {
        return super.toCVS() + "," + getSizeOfLeg();
    }

    public final String getSizeOfLeg() {
        return sizeOfLeg;
    }

    public final void setSizeOfLeg(final String sizeOfLeg) {
        this.sizeOfLeg = sizeOfLeg;
    }

}

public class Stick extends Good {
    private double length;

    public Stick(final GoodType goodType, final GoodName name, final int price, final String material,
                 final double weight, final String manufucturer,
                 final String color, final int amount, final double length) {

        super(goodType, name, price, material, weight, manufucturer, color, amount);
        setLength(length);
    }

    public String getHeaders() {
        return super.getHeaders() + "," + "length";
    }

    public String toCVS() {
        return super.toCVS() + "," + getLength();
    }

    public final double getLength() {
        return length;
    }

    public final void setLength(final double length) {
        this.length = length;
    }

}

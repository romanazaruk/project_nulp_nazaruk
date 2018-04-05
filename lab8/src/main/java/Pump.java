public class Pump extends Good {

    private double diametr;

    public Pump(final GoodType goodType, final GoodName name, final int price, final String material,
                final double weight, final String manufucturer,
                final String color, final int amount, final double diametr) {

        super(goodType, name, price, material, weight, manufucturer, color, amount);
        setDiametr(diametr);
    }

    public String getHeaders() {
        return super.getHeaders() + "," + "diametr";
    }

    public String toCVS() {
        return super.toCVS() + "," + getDiametr();
    }

    public final double getDiametr() {
        return diametr;
    }

    public final void setDiametr(final double diametr) {
        this.diametr = diametr;
    }
}

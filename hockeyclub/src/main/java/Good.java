public class Good {

    private GoodType goodType;
    private GoodName name;
    private int price;
    private String material;
    private double weight;
    private String manufacturer;
    private String color;
    private int id;
    private int amount;

    public Good(GoodType goodType, GoodName name, int price, String material, double weight, String manufucturer, String color, int amount) {

    }
    public Good (final int id) {
        this.id = id;
    }


    public Good(final GoodType goodType, final GoodName name, final int price, final String material,
                final double weight, final String manufacturer,
                final String color, final int amount,final int  newId) {

        setGoodType(goodType);
        setName(name);
        setPrice(price);
        setMaterial(material);
        setWeight(weight);
        setManufacturer(manufacturer);
        setColor(color);
        setAmount(amount);
        this.id = newId;

    }

    public Good(){}

    public String getHeaders() {
        return "name" + "," + "weight" + "," + "color" + "," + "price";
    }

    public String toCVS() {
        return getName() + "," + getWeight() + "," + getColor() + "," + getPrice();
    }


    public final void setGoodType(final GoodType goodType) {
        this.goodType = goodType;
    }

    public Good(final GoodName name, final int price) {
        this.name = name;
        this.price = price;
    }

    public final GoodName getName() {
        return name;

    }

    public final void setName(final GoodName name) {
        this.name = name;

    }

    public final void setType(final GoodType type) {
        this.goodType = type;
    }

    public final GoodType getType() {
        return this.goodType;
    }

    public final int getPrice() {
        return price;
    }

    public final void setPrice(final int price) {
        this.price = price;
    }

    public final String getMaterial() {
        return material;
    }

    public final void setMaterial(final String material) {
        this.material = material;
    }

    public final double getWeight() {
        return weight;
    }

    public final void setWeight(final double weight) {
        this.weight = weight;
    }

    public final String getManufacturer() {
        return manufacturer;
    }

    public final void setManufacturer(final String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public final String getColor() {
        return color;
    }

    public final void setColor(final String color) {
        this.color = color;
    }

    public final int getAmount() {
        return amount;
    }

    public final void setAmount(final int amount) {
        this.amount = amount;
    }

    public final String toString() {

        return goodType.name() + " " + getName() + " " + getPrice() + " " + getMaterial() + " " + getWeight()
                + " " + getManufacturer() + " " + getColor() + " " + getAmount();


    }

    public final int getId() {
        return this.id;
    }

    public final void setId(final int newId) {
        this.id = newId;
    }
}

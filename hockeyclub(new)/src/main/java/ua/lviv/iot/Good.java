package ua.lviv.iot;

import javax.persistence.*;

@Entity
public class Good {

    private GoodType goodType;
    private GoodName name;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "price")
    private int price;

    @Column(name = "material")
    private String material;

    @Column(name = "weight")
    private double weight;

    @Column(name = "manufacturer")
    private String manufacturer;

    @Column(name = "color")
    private String color;

    @Column(name = "amount")
    private int amount;

    public Good(GoodType goodType, GoodName name, int price, String material, double weight, String manufucturer, String color, int amount) {

    }

    public Good( int id) {
        this.id = id;
    }


    public Good( GoodType goodType,  GoodName name,  int price,  String material,
                 double weight,  String manufacturer,
                 String color,  int amount,  int newId) {

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

    public Good() {
    }

    public String getHeaders() {
        return "name" + "," + "weight" + "," + "color" + "," + "price";
    }

    public String toCVS() {
        return getName() + "," + getWeight() + "," + getColor() + "," + getPrice();
    }


    public  void setGoodType( GoodType goodType) {
        this.goodType = goodType;
    }

    public Good( GoodName name,  int price) {
        this.name = name;
        this.price = price;
    }

    public GoodName getName() {
        return name;

    }

    public  void setName( GoodName name) {
        this.name = name;

    }

    public  void setType( GoodType type) {
        this.goodType = type;
    }

    public  GoodType getType() {
        return this.goodType;
    }

    public  int getPrice() {
        return price;
    }

    public  void setPrice( int price) {
        this.price = price;
    }

    public  String getMaterial() {
        return material;
    }

    public  void setMaterial( String material) {
        this.material = material;
    }

    public  double getWeight() {
        return weight;
    }

    public  void setWeight( double weight) {
        this.weight = weight;
    }

    public  String getManufacturer() {
        return manufacturer;
    }

    public  void setManufacturer( String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public  String getColor() {
        return color;
    }

    public  void setColor( String color) {
        this.color = color;
    }

    public  int getAmount() {
        return amount;
    }

    public  void setAmount( int amount) {
        this.amount = amount;
    }

    public  String toString() {

        return goodType.name() + " " + getName() + " " + getPrice() + " " + getMaterial() + " " + getWeight()
                + " " + getManufacturer() + " " + getColor() + " " + getAmount();


    }

    public  int getId() {
        return this.id;
    }

    public  void setId(int newId) {
        this.id = newId;
    }
}

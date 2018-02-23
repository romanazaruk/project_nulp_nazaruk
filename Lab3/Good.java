package ua.lviv.iot.hockeyclub;

public class Good {
	
	
	private GoodType goodType ;
	private GoodName name;
	private int price;
	private String material;
	private double weight;
	private String manufacturer;
	private String color;
	private int amount;
	
	
	 public Good() {
	      
	    }


	    public Good(GoodType goodType, int price, String material, double weight, String manufacturer, String color, int amount) {
	     
	    	
	     this.goodType = goodType;	
	     this.price = price;
	     this.material = material;
	     this.weight = weight;
	     this.manufacturer = manufacturer;
	     this.color = color;
	     this.amount = amount;
	     
	    }
	    
	    public Good (GoodName name, int price) {
	    	this.name = name;
	    	this.price = price;
	    }
	    
	    
	   		    	
	    public GoodName getName() {

	        return name;

	    }



	    public void setName(GoodName name) {

	        this.name = name;

	    }
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getMaterial() {
		return material;
	}
	public void setMaterial(String material) {
		this.material = material;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
}

package ua.lviv.iot.hockeyclub;

public class Stick extends Good {
	private double length;	
	
public Stick () {
		
	}

	
	public Stick(GoodType goodType, int price , String material, double weight, String manfucturer, String color , int amount , double length) {
	 
		super(goodType, price, material, weight, manfucturer, color, amount);
		this.length = length;
	}

	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}

}

package ua.lviv.iot.hockeyclub;

public class Pump extends Good {

	private double diametr;

	public Pump() {

	}

	public Pump(GoodType goodType, GoodName name, int price, String material, double weight, String manfucturer, String color,
			int amount, double diametr) {

		super(goodType, name, price, material, weight, manfucturer, color, amount);

		this.diametr = diametr;

	}

	public double getDiametr() {
		return diametr;
	}

	public void setDiametr(double diametr) {
		this.diametr = diametr;
	}
}

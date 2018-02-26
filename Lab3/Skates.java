package ua.lviv.iot.hockeyclub;

public class Skates extends Good {
	private String sizeOfLeg;

	public Skates() {

	}

	public Skates(GoodType goodType, GoodName name, int price, String material, double weight, String manfucturer,
			String color, int amount, String sizeOfLeg) {

		super(goodType, name, price, material, weight, manfucturer, color, amount);

		this.sizeOfLeg = sizeOfLeg;
	}

	public String getSizeOfLeg() {
		return sizeOfLeg;
	}

	public void setSizeOfLeg(String sizeOfLeg) {
		this.sizeOfLeg = sizeOfLeg;
	}

}

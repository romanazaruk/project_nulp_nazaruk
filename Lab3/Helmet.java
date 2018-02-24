package ua.lviv.iot.hockeyclub;

public class Helmet extends  Good {
	private String sizeOfHead;

	public Helmet () {
		
	}
	
	public Helmet(GoodType goodType, GoodName name, int price , String material, double weight, String manfucturer, String color , int amount,String sizeOfHead) {
		
		super(goodType, name, price, material, weight, manfucturer, color, amount);
		
		this.sizeOfHead = sizeOfHead;
	
	 }
	
	public String getSizeOfHead() {
		return sizeOfHead;
	}

	public void setSizeOfHead(String sizeOfHead) {
		this.sizeOfHead = sizeOfHead;
	}

}

package ua.lviv.iot.hockeyclub;

import java.util.List;

public class Main {

	public static void main(String[] args) {

		HockeyClub hockeyClub = new HockeyClub("Boston", 20);

		hockeyClub.addGood(new Pump(GoodType.FORANY, GoodName.PUMP, 50, "metal", 2, "Liza", "black", 20, 15));
		hockeyClub.addGood(new Stick(GoodType.FORANY, GoodName.STICK, 1500, "metal", 2, "Vasia", "black", 15, 25.5));
		hockeyClub.addGood(new Helmet(GoodType.FORANY, GoodName.HELMET, 70, "metal", 2.5, "Oksana", "black", 20, "S"));
		hockeyClub.addGood(new Skates(GoodType.FORANY, GoodName.SKATES, 90, "metal", 2, "Alex", "black", 20, "M"));

		List<Good> goods = hockeyClub.findGoodByPrice(80);
		goods = hockeyClub.sortGoodByPrice(goods);

		goods.forEach(good -> System.out.println(good));  
	}

}

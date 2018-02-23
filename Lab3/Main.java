package ua.lviv.iot.hockeyclub;

public class Main {

	public static void main(String[] args) {

		HockeyClub hockeyClub = new HockeyClub("Boston", 20);

		Good Pump = new Pump(GoodType.FORANY, 50, "metal", 2, "Vasia", "black", 20, 15);

		Good Stick = new Stick(GoodType.FORANY, 50, "metal", 2, "Vasia", "black", 15, 25.5);

		Good Helmet = new Helmet(GoodType.FORANY, 50, "metal", 2.5, "Vasia", "black", 20, "S");

		Good Skates = new Skates(GoodType.FORANY, 50, "metal", 2, "Vasia", "black", 20, "M");

		hockeyClub.addGood(Pump);
		hockeyClub.addGood(Stick);
		hockeyClub.addGood(Helmet);
		hockeyClub.addGood(Skates);

		hockeyClub.sortGoodByPrice(hockeyClub.getGoodList());

		for (Good good : hockeyClub.getGoodList()) {

			System.out.println(good.getName() + " " + good.getPrice());

		}

	}

}

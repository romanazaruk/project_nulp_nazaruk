package ua.lviv.iot.hockeyclub;

import java.util.*;

public class HockeyClub {
	private String name = "no name";
	private int amountOfPlayers;
	private List<Good> goodList;

	public HockeyClub(String name, int amountOfPlayers) {
		this.name = name;
		this.amountOfPlayers = amountOfPlayers;
		this.goodList = new LinkedList<>();

	}

	public void addGood(Good addGood) {
		goodList.add(addGood);
	}

	public List<Good> sortGoodByPrice(List<Good> list) {
		list.sort((Good o1, Good o2) -> o1.getPrice() - o2.getPrice());
		return list;

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAmountOfPlayers() {
		return amountOfPlayers;
	}

	public void setAmountOfPlayers(int amountOfPlayers) {
		this.amountOfPlayers = amountOfPlayers;
	}

	public List<Good> getGoodList() {
		return goodList;
	}

	public void setGoodList(List<Good> goodList) {
		this.goodList = goodList;
	}

	public List<Good> findGoodByPrice(int price) {
		List<Good> result = new LinkedList<>();
		for (Good goodList : goodList) {
			if (goodList.getPrice() <= price) {
				result.add(goodList);
			}
		}

		return result;
	}


}

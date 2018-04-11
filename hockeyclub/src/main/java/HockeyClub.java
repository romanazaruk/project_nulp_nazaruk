import java.util.*;

public class HockeyClub {
    private String name = "no name";
    private int amountOfPlayers;
    private List<Good> goodList;

    public HockeyClub(final String name, final int amountOfPlayers) {
        setName(name);
        setAmountOfPlayers(amountOfPlayers);
        this.goodList = new LinkedList<>();

    }

    @Override
    public String toString() {
        return "HockeyClub{" +
                "name='" + name + '\'' +
                ", amountOfPlayers=" + amountOfPlayers +
                ", goodList=" + goodList +
                '}';
    }

    public final void addGood(final Good addGood) {
        goodList.add(addGood);
    }

    public final List<Good> sortGoodByPrice(final List<Good> list) {
        list.sort((Good o1, Good o2) -> o1.getPrice() - o2.getPrice());
        return list;

    }

    /*public final String getName() {
        return name;
    }*/

    public final void setName(final String name) {
        this.name = name;
    }

    /*public final int getAmountOfPlayers() {
        return amountOfPlayers;
    }*/

    public final void setAmountOfPlayers(final int amountOfPlayers) {
        this.amountOfPlayers = amountOfPlayers;
    }

    public final List<Good> getGoodList() {
        return goodList;
    }

    public final void setGoodList(final List<Good> goodList) {
        this.goodList = goodList;
    }

    public final List<Good> findGoodByPrice(final int price) {
        List<Good> result = new LinkedList<>();
        for (Good goodList : goodList) {
            if (goodList.getPrice() <= price) {
                result.add(goodList);
            }
        }

        return result;
    }
}

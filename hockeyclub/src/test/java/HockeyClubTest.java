import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;



public class HockeyClubTest {
    private HockeyClub hockeyClub = new HockeyClub("Barsuk", 5);

    @BeforeEach
    void setUp() {

        hockeyClub = new HockeyClub("Boston", 20);

        hockeyClub.addGood(new Pump(GoodType.FORANY, GoodName.PUMP, 50, "metal", 2,
                "Liza", "black", 20, 15));
        hockeyClub.addGood(new Stick(GoodType.FORANY, GoodName.STICK, 1500, "metal", 2,
                "Vasia", "black", 15, 25.5));
        hockeyClub.addGood(new Helmet(GoodType.FORANY, GoodName.HELMET, 70, "metal", 2.5,
                "Oksana", "black", 20, "S"));
        hockeyClub.addGood(new Skates(GoodType.FORANY, GoodName.SKATES, 25, "metal", 2,
                "Alex", "black", 20, "M"));
    }

    @Test
    public final void testToString() {
        assertFalse(hockeyClub.toString().contains("@"));
    }

    @Test
    void sortGoodByPrice() {
        List<Good> list = new LinkedList<>();
        list.add(new Good(GoodName.HELMET, 70));
        list.add(new Good(GoodName.PUMP, 50));
        list.add(new Good(GoodName.SKATES, 25));

        hockeyClub.sortGoodByPrice(list);
        assertThat(list.get(0).getPrice(), is(25));
        assertThat(list.get(1).getPrice(), is(50));
        assertThat(list.get(2).getPrice(), is(70));
    }

    @Test
    void findGoodByPrice() {
        List<Good> list = new LinkedList<>();
        hockeyClub.addGood(new Good(GoodName.HELMET, 70));
        hockeyClub.addGood(new Good(GoodName.PUMP, 15));
        hockeyClub.addGood(new Good(GoodName.SKATES, 25));

        assertThat(GoodName.PUMP, is(hockeyClub.findGoodByPrice(20).get(0).getName()));
    }

    @Test
    public void testWriter() {
        Writer writer = new Writer();
        try {
            writer.writeToFile(hockeyClub.getGoodList());
        } catch (Exception e) {
            assertFalse(false);
        }
    }


}

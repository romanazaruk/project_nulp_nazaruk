package ua.lviv.iot;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class Writer {
    public void writeToFile(final List<Good> goodsList) throws IOException {
        try (PrintWriter writer = new PrintWriter("file.csv")) {

            goodsList.forEach((Good x) -> {
                writer.println(x.getHeaders());
                writer.println(x.toCVS());
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}







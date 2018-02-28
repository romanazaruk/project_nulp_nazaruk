package laba4;

import java.io.IOException;
import java.util.*;


public class Main {
    public static void main(String[] args) {
        StringProcessor stringProcessor = new StringProcessor();
        try {
            String input = stringProcessor.readInputText();
            String result = stringProcessor.processText(input);
            stringProcessor.showResult(result);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}


class StringProcessor {
    private int numberOfTimes;

    public String readInputText() throws IOException {
        Scanner input = new Scanner(System.in);
        StringBuilder text = new StringBuilder();

        String paragraph;
        while (!(paragraph = input.nextLine()).equals("")) {
            text = text.append(paragraph+" ");
        }
        System.out.println("Enter number of times: ");
        numberOfTimes = input.nextInt();
        if (text.toString().equals("")) throw new IOException("Please, enter text");
        return text.toString();

    }

    public String processText(String inputText) {
        String result = "";
        String regex = "([\\.,!&])+";
        inputText = inputText.replaceAll(regex, "");
        ArrayList<String> tempList = new ArrayList<>(Arrays.asList(inputText.split(" ")));
        Map<String, Integer> hashMap = new HashMap<>();

        for (String aTempList : tempList) {
            int value;
            try {
                value = hashMap.get(aTempList);
            } catch (NullPointerException e) {
                value = 0;
            }
            hashMap.put(aTempList, value + 1);
        }


        for (String word : hashMap.keySet()) {
            if (hashMap.get(word) > numberOfTimes) result = result.concat(word + " ");
        }
        
        return result;
    }

    public void showResult(String resultText) {
        System.out.println("Words that are repeated more than " + numberOfTimes + " times: " + resultText);

    }

}

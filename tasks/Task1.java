package animals.tasks;

import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

public class Task1 {

    private final static List<String> positiveConfirmations = List.of(
            "y",
            "yes",
            "yeah",
            "yep",
            "sure",
            "right",
            "affirmative",
            "correct",
            "indeed",
            "you bet",
            "exactly",
            "you said it"

    );
    private final static List<String> negativeConfirmation = List.of(
            "n",
            "no",
            "no way",
            "nah",
            "nope",
            "negative",
            "I don't think so",
            "yeah no"
    );
    private final static List<String> clarificationQuestions = List.of(
            "I'm not sure I caught you: was it yes or no?",
            "Funny, I still don't understand, is it yes or no?",
            "Oh, it's too complicated for me: just tell me yes or no.",
            "Could you please simply say yes or no?",
            "Oh, no, don't try to confuse me: say yes or no."
    );
    private final static List<String> goodByeList = List.of(
            "Goodbye",
            "See you never",
            "Bye",
            "See yaa",
            "Catch you later",
            "Have a good day"
    );


    public static void main(String[] args) {
        LocalTime now = LocalTime.now();
        int time = now.getHour();
        String greeting;

//        Greetings decider
        if (time >= 5 && time < 12) {
            greeting = "Good Morning!";
        } else if (time >= 12 && time < 18) {
            greeting = "Good Afternoon!";
        } else {
            greeting = "Good Evening!";
        }
        System.out.println(greeting + " \n\nEnter an Animal:");

//        Taking inputs
        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine().toLowerCase();
        String[] inputArray = input.split(" ");
        StringBuilder animalName = new StringBuilder();
        String article;

        if (inputArray[0].equals("the")) {
            article = assignAnArticle(inputArray[1]);
            for (int i = 1; i< inputArray.length ; i++) {
                animalName.append(inputArray[i]).append(" ");

            }
        }
        else if (inputArray[0].equals("a") || inputArray[0].equals("an")) {
            article = inputArray[0];
            for (int i = 1; i< inputArray.length ; i++) {
                animalName.append(inputArray[i]).append(" ");

            }
        }
        else {
            article = assignAnArticle(inputArray[0]);
            for (int i = 0; i < inputArray.length; i++) {
                animalName.append(inputArray[i]).append(" ");
            }
        }

        System.out.println("Is it " + article + " " + animalName.toString().trim() + "?");

//            Taking confirmation from User
        while (sc.hasNextLine()) {
            String confirmation = sc.nextLine().toLowerCase().trim();
            if(confirmation.contains(".") || confirmation.contains("!")){
                char[] confirmationArray = confirmation.toCharArray();
                int punctCount = 0;
                for (char c : confirmationArray) {
                    if (c == '.' || c == '!') {
                        punctCount++;
                    }  //do nothing

                }
                if(punctCount < 2){
                    confirmation = confirmation.replaceAll("\\.|\\!","");
                }
                //do nothing

            }

            if (positiveConfirmations.contains(confirmation)) {
                System.out.println("You answered: Yes");
                break;
            } else if (negativeConfirmation.contains(confirmation)) {
                System.out.println("You answered: No");
                break;
            } else {
                int randomIndex = (int) ((Math.random() * (clarificationQuestions.size())) + 0);
                System.out.println(clarificationQuestions.get(randomIndex));
            }
        }
//        Generating random goodbye text for user
        int randomIndex = (int) ((Math.random() * (goodByeList.size())) + 0);
        System.out.println("\n" + goodByeList.get(randomIndex) + "!");
        sc.close();

    }
    //    Assigning article for animals
    private static String assignAnArticle (String animal){
        char alphabet = animal.charAt(0);
        if (alphabet == 'a' || alphabet == 'e' || alphabet == 'i' || alphabet == 'o' || alphabet == 'u') {
            return "an";
        } else {
            return "a";
        }
    }
}

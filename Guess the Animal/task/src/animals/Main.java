package animals;

import java.time.LocalTime;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
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

//        Taking inputs
        System.out.println(greeting + "\n");
        System.out.println("Enter the first Animal:");
        String[] firstAnimalArray = sc.nextLine().toLowerCase().trim().split(" ");
        System.out.println("Enter the Second Animal:");
        String[] secondAnimalArray = sc.nextLine().toLowerCase().trim().split(" ");

        Animal firstAnimalObj = getPropertiesForAnimal(firstAnimalArray);
        Animal secondAnimalObj = getPropertiesForAnimal(secondAnimalArray);

        System.out.println("Specify a fact that distinguishes " + firstAnimalObj.getArticle() + " " + firstAnimalObj.getName() + " from " + secondAnimalObj.getArticle() + " " + secondAnimalObj.getName() + ".\nThe sentence should be of the format: 'It can/has/is ...'.");

        //Taking input for animal property
        String statementGiven = "";
        while (sc.hasNext()) {
            statementGiven = sc.nextLine().trim();
            String[] statementArray = statementGiven.split(" ");
            Boolean isCorrectFormat = verifyStatement(statementArray);

            if (isCorrectFormat) {
                break;
            } else {
                System.out.println("The examples of a statement:\n" +
                        " - It can fly\n" +
                        " - It has horn\n" +
                        " - It is a mammal");
                System.out.println("Specify a fact that distinguishes " + firstAnimalObj.getArticle() + " " + firstAnimalObj.getName() + " from " + secondAnimalObj.getArticle() + " " + secondAnimalObj.getName() + ".\nThe sentence should be of the format: 'It can/has/is ...'.");

            }
        }


        System.out.println("Is it correct for " + secondAnimalObj.getArticle() + " " + secondAnimalObj.getName() + "?");
        String responseText = sc.nextLine().toLowerCase().trim();

//            Based on the user response print something
        String[] statement = statementGiven.split(" ");
        StringBuilder tempString = new StringBuilder();
        StringBuilder tempString1 = new StringBuilder();
        if (responseText.equals("yes")) {
            //statement is true for animal 2

            tempString.append("The ")
                    .append(firstAnimalObj.getName())
                    .append(" ").append(statement[1].contains("has") ? "does" : statement[1])
                    .append(statement[1].contains("can") ? "'t " : statement[1].contains("has") ? "n't have ": "n't ");
            sentenceMaker(statement, tempString);

            System.out.println("I learned the following facts about animals:\n-"
                    + tempString.toString().trim());

            tempString1.append("The ")
                    .append(secondAnimalObj.getName())
                    .append(" ").append(statement[1])
                    .append(" ");
        } else {
            //statement is true for animal 2

            tempString.append("The ")
                    .append(firstAnimalObj.getName())
                    .append(" ").append(statement[1]).append(" ");
            sentenceMaker(statement, tempString);

            System.out.println("I learned the following facts about animals:\n-"
                    + tempString.toString().trim());

            tempString1.append("The ")
                    .append(secondAnimalObj.getName())
                    .append(" ").append(statement[1].contains("has") ? "does" : statement[1])
                    .append(statement[1].contains("can") ? "'t " : statement[1].contains("has") ? "n't have ": "n't ");
            sentenceMaker(statement, tempString);
        }
        printStatement(statement, tempString1);


    }

    private static void printStatement(String[] statement, StringBuilder tempString1) {
        sentenceMaker(statement, tempString1);
        System.out.print("-"
                + tempString1.toString().trim() + "\n");


//            Question Generator
        StringBuilder tempString2 = new StringBuilder();

        if (statement[1].equals("has")) {
            tempString2.append("Does").append(" ").append(statement[0].toLowerCase(Locale.ROOT)).append(" ").append("have").append(" ");
            appendStringArray(tempString2, statement);
        } else {
            String firstWord = statement[1].substring(0, 1).toUpperCase() + statement[1].substring(1);
            tempString2.append(firstWord).append(" ").append(statement[0].toLowerCase(Locale.ROOT)).append(" ");
            appendStringArray(tempString2, statement);
        }

        System.out.println("I can distinguish these animals by asking the question:\n-" + tempString2.toString().trim());
    }

    private static void sentenceMaker(String[] statement, StringBuilder tempString1) {
        for (int i = 2; i < statement.length - 1; i++) {
            tempString1.append(statement[i]).append(" ");
        }
//        tempString1.append(statement[statement.length-1].contains(".") ? statement[statement.length-1] : statement[statement.length-1] + ".");
        tempString1.append(statement[statement.length - 1].contains("?") ?
                statement[statement.length - 1].replace("?", ".") :
                statement[statement.length - 1].contains(".") ?
                        statement[statement.length - 1] : statement[statement.length - 1] + ".");
    }

    private static void appendStringArray(StringBuilder tempString, String[] statement) {
        for (int i = 2; i < statement.length - 1; i++) {
            tempString.append(statement[i]).append(" ");
        }
        tempString.append((statement[statement.length - 1]).replace(".", "?"));
//        tempString.append("");
    }

    public static Boolean verifyStatement(String[] statement) {
        if (statement.length > 1) {
            return (statement[0].compareTo("It") == 0) && (statement[1].contains("can") || statement[1].contains("has") || statement[1].contains("is"));

        } else return false;

    }

    public static Animal getPropertiesForAnimal(String[] inputArray) {
        Animal animal = new Animal();
        String article;
        StringBuilder animalContainer = new StringBuilder();
        if (inputArray[0].equals("the")) {
            article = assignAnArticle(inputArray[1]);
            for (int i = 1; i < inputArray.length; i++) {
                animalContainer.append(inputArray[i]).append(" ");

            }
        } else if (inputArray[0].equals("a") || inputArray[0].equals("an")) {
            article = inputArray[0];
            for (int i = 1; i < inputArray.length; i++) {
                animalContainer.append(inputArray[i]).append(" ");

            }
        } else {
            article = assignAnArticle(inputArray[0]);
            for (String s : inputArray) {
                animalContainer.append(s).append(" ");
            }
        }
        animal.setArticle(article);
        animal.setName(animalContainer.toString().trim());
        return animal;
    }

    private static String assignAnArticle(String animal) {
        char alphabet = animal.charAt(0);
        if (alphabet == 'a' || alphabet == 'e' || alphabet == 'i' || alphabet == 'o' || alphabet == 'u') {
            return "an";
        } else {
            return "a";
        }
    }
}

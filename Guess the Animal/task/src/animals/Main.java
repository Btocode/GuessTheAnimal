package animals;

import java.time.LocalTime;
import java.util.Locale;
import java.util.Scanner;

public class Main {
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
        System.out.println(greeting + "\n");
        System.out.println("Enter the first Animal:");
        String[] firstAnimalArray = sc.nextLine().toLowerCase().split(" ");
        System.out.println("Enter the Second Animal:");
        String[] secondAnimalArray = sc.nextLine().toLowerCase().split(" ");

        Animal firstAnimalObj = getPropertiesForAnimal(firstAnimalArray);
        Animal secondAnimalObj = getPropertiesForAnimal(secondAnimalArray);

        System.out.println("Specify a fact that distinguishes "+ firstAnimalObj.getArticle() + " " +firstAnimalObj.getName()+ " from " + secondAnimalObj.getArticle() + " " +secondAnimalObj.getName()+".\nThe sentence should be of the format: 'It can/has/is ...'.");

        //Taking input for animal property
        String[] statement;
        while(sc.hasNext()){
            statement = sc.nextLine().split(" ");
            Boolean isCorrectFormat = verifyStatement(statement);

            if(isCorrectFormat){
                break;
            }
            else{
                System.out.println("The examples of a statement:\n" +
                        " - It can fly\n" +
                        " - It has horn\n" +
                        " - It is a mammal");
            }
        }


        System.out.println("Is it correct for " + secondAnimalObj.getArticle() + " " + secondAnimalObj.getName() + "?");
        String responseText = sc.nextLine().toLowerCase().trim();

//            Based on the user response print something
        if(responseText.equals("yes")){
            System.out.println("The " + firstAnimalObj.getName() + " "+ );
        }
        else{
            StringBuilder tempString = new StringBuilder();
            for (int i = 2; i < statement.length; i++) {
                tempString.append(statement[i]).append(" ");
            }
            System.out.println();
        }


    }

    public static Boolean verifyStatement(String[] statement){
        if(statement.length > 1){
            return (statement[0].compareTo("It") == 0) && statement[1].contains("can") || statement[1].contains("has") || statement[1].contains("is");
        }
        else return false;

    }
    public static Animal getPropertiesForAnimal(String[] inputArray){
        Animal animal = new Animal();
        String article;
        StringBuilder animalContainer = new StringBuilder();
        if (inputArray[0].equals("the")) {
            article = assignAnArticle(inputArray[1]);
            for (int i = 1; i< inputArray.length ; i++) {
                animalContainer.append(inputArray[i]).append(" ");

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

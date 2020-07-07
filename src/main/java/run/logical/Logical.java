package run.logical;

import com.github.javafaker.Faker;

import java.io.*;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Logical {

    public static final Faker FAKER = new Faker();
    public static final Scanner INPUTVALUE=  new Scanner(System.in);

    public void chooseOption() {

        int amountflights =0;
        boolean erroramountflights=true;
        boolean exitvalue=true;

        do {
            System.out.println("Menu Reservation Airtickets");
            for(EnumMenu enumMenu:EnumMenu.values()){
                System.out.println(enumMenu.getMenuInt() + " :: "   + enumMenu.getMenuString());
            }

            int checkOption= INPUTVALUE.nextInt();
            INPUTVALUE.nextLine();
            switch (checkOption) {
                case 1:
                    System.out.println("1. Create tickets-insert value(e.g 52 or 1000)");
                    Logical saveFligh = new Logical();
                    do {
                        try {
                            amountflights = INPUTVALUE.nextInt();
                            erroramountflights = false;
                            saveFligh.saveTxt(amountflights);
                        } catch (InputMismatchException ime) {
                            System.out.println("Insert value is not number");
                            INPUTVALUE.nextLine();
                        }
                    } while (erroramountflights);
                case 2:
                    showAllFlight();
                    break;

                case 3:

                    break;
                case 4:

                    break;
                case 5:

                    break;
                case 6:
                    exitvalue=false;
                    break;
                default:
                    //do not have moore choose
            }
        } while (exitvalue);
                System.out.println("Goodbye");
        INPUTVALUE.close();
            }
    /////////222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222

    private void showAllFlight(){
        ArrayList<String> stringArrayListReadflights = new ArrayList<>();
        try
                (
                    var fileReader = new FileReader("list_flights.txt");
                    var reader = new BufferedReader(fileReader)
                ){
            String readflights="";
            while ((readflights = reader.readLine()) != null){
                readflights  = readflights.replace(";", " ");
                stringArrayListReadflights.add(readflights);
            }
        }catch (IOException ioe){
            ioe.printStackTrace();
        }

        for (String flightsArrays: stringArrayListReadflights) {
            System.out.println(flightsArrays);

        }
    }
    ////11111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111

    private String generateflys() {
        String countryFrom = FAKER.address().country();
        String countryTo = FAKER.address().country();
     return countryFrom + ";" + countryTo;
    }


    private void saveTxt(int sumAllFligh) {
        int countNumber = 0;


        String fileName = "list_flights.txt";
        try (
                var fileWriter = new FileWriter(fileName);
                var writer = new BufferedWriter(fileWriter)
        ) {
            while (countNumber < sumAllFligh) {
                writer.write(addAllRecord());
                writer.newLine();
                countNumber++;
            }
        } catch (IOException ioe) {
            System.err.println("Bład apisu pliku");
            ioe.printStackTrace();
        }

        System.out.println("Gotowe:");
    }

    private String addAllRecord() {
        return generateflys()
                        + ";"
                        + createDate(LocalDate.of(2020, 9, 1), LocalDate.of(2021, 11, 21))
                        + ";"
                        + createMoney(223.07, 3000.00);
    }


    private String createDate(LocalDate min, LocalDate max) {
        Random random = new Random();
        int minDay = (int) min.toEpochDay();
        int maxDay = (int) max.toEpochDay();
        long randomDay = minDay + random.nextInt(maxDay - minDay);
        LocalDate randomBirthDate = LocalDate.ofEpochDay(randomDay);
        LocalTime timeHour =LocalTime.of(random.nextInt(24), random.nextInt(60));
        LocalDateTime fromDateAndTime = LocalDateTime.of(randomBirthDate, timeHour);
        String fromDateAndTimeString = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(fromDateAndTime);

        return fromDateAndTimeString;
    }

    private String createMoney(double moneyMin, double moneyMax) {
        DecimalFormat df2 = new DecimalFormat("0.0");
        double rand = new Random().nextDouble();
        String result = df2.format(moneyMin + (rand * (moneyMax - moneyMin))).replace(",", ".") + "0";
        return result;
    }


}









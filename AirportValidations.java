/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package funciones_aeropuerto.model.validations;

/**
 *
 * @author Usuario
 */
import java.time.LocalTime;
import java.time.Duration;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public class AirportValidations {

    public static boolean isNumeric(String str) {

        for (int i = 1; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }

        return true;

    }
    public static boolean isAlphabetic(String str) {

        for (int i = 1; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i))) {
                return false;
            }
        }

        return true;

    }

    
    //FUNCTION 3
    public static boolean checkFlightCode(String code) {

        if (code.length() > 7) { //IATA MAX 6 characters ICAO MAX 7
            return false; //
        }

        String part1 = code.substring(0, 2);
        String part2 = code.substring(2);

        if (!isNumeric(part2)) {
            return false;
        }

        if (!isAlphabetic(part1)) {
            return false;
        }

        return true;

    }

    //this aplies to spanish passports
    public static boolean checkPassportNumber(String passport) {

        if (passport == null) {
            return false;
        }

        if (!passport.matches("^[A-Z]{2}[0-9]+$") || passport.length() > 9) {
            return false;
        } else {
            return true;
        }
    }
    /**
     * @param time La hora
     * @return True or false
     */
    public static boolean checkHour(String time) {
        try {
            LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm"));
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static int calculateLayoverMinutes(String departure, String arrival) {

        if (!checkHour(departure) || !checkHour(arrival)) {
            return -1;
        }

        LocalTime timeDeparture = LocalTime.parse(departure);
        LocalTime timeArrival = LocalTime.parse(arrival);

        if (timeArrival.isBefore(timeDeparture)) {
            return -1;
        }

        return (int) ChronoUnit.MINUTES.between(timeDeparture, timeArrival);
    }

    public static boolean checkSeatCode(String seat) {
        if (seat == null) {
            return false;
        }

        
        return seat.matches("^[1-9][0-9]{0,2}[A-HJ-NP-Z]$");
    }

}

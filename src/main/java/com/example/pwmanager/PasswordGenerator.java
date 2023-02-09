package com.example.pwmanager;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class PasswordGenerator {
    public static void main(String[] args) throws Exception {

        System.out.print(generatePassword());
    }

    public static String generatePassword() {
        System.out.println("Hello, World! Welcome to the Password Generator!");
        System.out.println(
                "\n Enter 0 below if you would like a Password (Example: fnoh90j34fmNDM09W)" +
                        "\n Enter 1 below if you would like a Passphrase (Example: metaxylem-earflap-crutch)\n");

        Scanner passwordOrPassphraseScanner = new Scanner(System.in);
        if (passwordOrPassphraseScanner.nextInt() == 1) {
            return generatePassPhrase();

        }

        String[] capitalLetters = {
                "A",
                "B",
                "C",
                "D",
                "E",
                "F",
                "G",
                "H",
                "I",
                "J",
                "K",
                "L",
                "M",
                "N",
                "O",
                "P",
                "Q",
                "R",
                "S",
                "T",
                "U",
                "V",
                "W",
                "X",
                "Y",
                "Z"
        };

        String[] lowerCaseLetters = {
                "a",
                "b",
                "c",
                "d",
                "e",
                "f",
                "g",
                "h",
                "i",
                "j",
                "k",
                "l",
                "m",
                "n",
                "o",
                "p",
                "q",
                "r",
                "s",
                "t",
                "u",
                "v",
                "w",
                "x",
                "y",
                "z"
        };

        String[] specialCharacters = {
                "!",
                "@",
                "?",
                "#",
                "$",
                "%",
                "^",
                "&",
                "*",
                "(",
                ")"
        };

        String[] numbers = {
                "0",
                "1",
                "2",
                "3",
                "4",
                "5",
                "6",
                "7",
                "8",
                "9"
        };


        System.out.println
                (
                        "\nDo you have a preference for how long you would like your password to be? "
                                +
                                "Enter 1 for yes, Enter 2 for no\n"
                );

        // create a new scanner for asking/collecting length preference
        Scanner preferenceForLengthScanner = new Scanner(System.in);
        // Save input to newLength
        Integer preferenceForLength = preferenceForLengthScanner.nextInt();
        // save default length for passwords
        int passwordLength = 25;


        // check for wrong answers, let user know what is acceptable to enter, ask again
        if ((preferenceForLength != 1 && preferenceForLength != 2)) {
            while (preferenceForLength.getClass().toString() != "Integer" || (preferenceForLength != 1 && preferenceForLength != 2)) {
                System.out.println("Please enter either 1 or 2, nothing else");
                preferenceForLength = preferenceForLengthScanner.nextInt();
            }
        }

        if (preferenceForLength == 1) {
            // if user does want a custom length, we ask them how long they want it and save the desired length to our password length variable
            System.out.println("\nHow long would you like your password to be? Enter Integer Length Below:\n");
            // we check if its at least 16 characters, per minimum strong pw requirements
            // https://www.lmgsecurity.com/how-long-should-your-password-be-a-technical-guide-to-a-safe-password-length-policy/#:~:text=Unless%20strong%20Multifactor%20Authentication%20(MFA,characters%20or%20greater%20whenever%20possible.
            // create scanner to collect desired custom length from user if requested

            Scanner userDesiredLengthScanner = new Scanner(System.in);
            Integer userDesiredLengthInteger = userDesiredLengthScanner.nextInt();

            // require desired password length to be more than 16 characters long
            // if not keep prompting them to enter it
            // TODO: 1/14/23 Add check if user enters invalid characters (letters etc,
            //  anything other than a number 16-100) if over 100, print for them to chill lol

            if (userDesiredLengthInteger < 16) {
                while (userDesiredLengthInteger < 16) {
                    System.out.println("\nALERT: \n\nIt is recommended that your password be at least 16 characters long. \nPenetration testers can crack passwords that are 8 characters long or less in 8 - 15 hours. \nHowever, with a 16 character password, it would take trillions of years. 🤯 \nPretty large difference there, huh? \nTry to enter a new password length that is at least 16 characters or higher, \n... for your own safety 🙂\n");
                    System.out.println("\nHow long would you like your password to be? Enter Integer Length Below:\n");
                    userDesiredLengthInteger = userDesiredLengthScanner.nextInt();
                }
            }

            passwordLength = userDesiredLengthInteger;
        }



        // declare variable to store responses as true/false
        ArrayList<Boolean> passwordCharacterPreferences = new ArrayList<Boolean>();

        // check special character preference

        System.out.println("\nWould you like to include special characters? Enter true or false.\n");
        Scanner preferenceForSpecialCharactersScanner = new Scanner(System.in);
        Boolean preferenceForSpecialCharacters = preferenceForSpecialCharactersScanner.nextBoolean();
        //todo Add checks
        passwordCharacterPreferences.add(preferenceForSpecialCharacters);

        // check numbers preference

        System.out.println("\nWould you like to include numbers? Enter true or false.\n");
        Scanner preferenceForNumbers = new Scanner(System.in);
        passwordCharacterPreferences.add(preferenceForNumbers.nextBoolean());

        // check capital letter preference

        System.out.println("\nWould you like to include capital letters? Enter true or false.\n");
        Scanner preferenceForCapitalLetters = new Scanner(System.in);
        passwordCharacterPreferences.add(preferenceForCapitalLetters.nextBoolean());

        // now that we have the responses in the array lets create the password based on them and the length


        // create new included letters array
        // if only one item in the array is true,
        // use that corresponding array only
        // if all are false, give only lowercase letters
        // if two or more are true,
        // add their items to the array and join as one
        // loop through that array and randomly choose characters and put them in a result string.
        // return the result string


        ArrayList<String> includedLetters = new ArrayList<String>();

        for (int i = 0; i < lowerCaseLetters.length; i++) {
            includedLetters.add(lowerCaseLetters[i]);
        }

        StringBuilder res = new StringBuilder();

        int count = 0;

        for (int i = 0; i < passwordCharacterPreferences.size(); i++) {
            if (passwordCharacterPreferences.get(i)) {
                count = count + 1;
            }
        }

        if (passwordCharacterPreferences.get(0)) {
            for (int i = 0; i < specialCharacters.length; i++) {
                includedLetters.add(specialCharacters[i]);
            }
        }
        if (passwordCharacterPreferences.get(1)) {
            for (int i = 0; i < numbers.length; i++) {
                includedLetters.add(numbers[i]);
            }
        }
        if (passwordCharacterPreferences.get(2)) {
            for (int i = 0; i < capitalLetters.length; i++) {
                includedLetters.add(capitalLetters[i]);
            }
        }


        for (int i = 0; i < passwordLength; i++) {
            res.append(includedLetters.get((int) Math.floor(Math.random() * includedLetters.size())));
        }

        // will generate randomized string of lowercase letters as default
        return "\nHere is your password: \n\n" + res.toString();





    }

    public static String generatePassPhrase() {
        int passphraseLength = 3;
        System.out.println("\nDo you have a preference for how many words are in the passphrase?\n");
        System.out.println("Enter true if you would like to specify a number of words to include \n" +
                "Enter false if you do not mind the length (will default to a length of 3)\n");
        Scanner lengthPreferenceInquiry = new Scanner(System.in);

        if (lengthPreferenceInquiry.nextBoolean()) {
            System.out.println("\nPlease enter your preferred length below (Example: 5) \n");
            Scanner lengthPreference = new Scanner(System.in);
            passphraseLength = lengthPreference.nextInt();
        }

        System.out.println("\n Generating your passphrase...\n");

        StringBuilder res = new StringBuilder();
        try {
            for (int i = 0; i < passphraseLength; i++) {
                URL u = new URL("https://random-word-api.herokuapp.com/word");
                HttpURLConnection hr = (HttpURLConnection) u.openConnection();

                if (hr.getResponseCode() == 200) {

                    InputStream im = hr.getInputStream();

                    BufferedReader br = new BufferedReader(new InputStreamReader(im));
                    String line = br.readLine();

                    res.append("-").append(line.substring(2, line.length() - 2));

                }

            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return "Here is your passphrase: " + res.substring(1);
    }


}

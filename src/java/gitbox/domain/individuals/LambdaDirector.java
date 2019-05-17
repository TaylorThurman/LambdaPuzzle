package gitbox.domain.individuals;

import show.domain.StringCipher;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public class LambdaDirector {

    // Your options for cipher algorithms - find the right one
    public static List<String> cipherTypes = Arrays.asList(
            "AES", "BES", "CES", "DES"
    );

    // The alphanumeric list for the caesar cipher
    public static List<String> alphabet = Arrays.asList(
            "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
            "a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
            "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
            "u", "v", "w", "x", "y", "z", "A", "B", "C", "D",
            "E", "F", "G", "H", "I", "J", "K", "L", "M", "N",
            "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X",
            "Y", "Z", " ", "!"
    );


    // remove Mr. MeeSeeks phrase from the string
    public static Function<String, String> removeMeeSeek = string -> {

        String firstHalf = string.substring(0, string.indexOf('_'));
        String secondHalf = string.substring(string.lastIndexOf('_') + 1, string.length());
        String newString = firstHalf + secondHalf;

        return newString;
    };

    // decrypt with StringCipher
    public static BiFunction<String, StringCipher, String> decrypt = (string, cipher) -> {
        return cipher.decrypt(string, cipherTypes.get(3));
    };

    // A basic caesar cipher. Figure out which index it was switched to.
    // Use alphabet array list above to decrypt
    public static Function<String, String> caesarCipher = string -> {

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < string.length(); i++) {
            int indexOfOldLetter = alphabet.indexOf(Character.toString(string.charAt(i)));
            int indexOfNewLetter;
            if (indexOfOldLetter > 2) {
                indexOfNewLetter = indexOfOldLetter - 3;
            } else {
                int tempIndex = indexOfOldLetter - 3;
                indexOfNewLetter = alphabet.size() + tempIndex;
            }
            sb.append(alphabet.get(indexOfNewLetter));
        }

        return sb.toString();
    };

    // for each word replace the first set of numbers with the character
    public static Function<String, String> revertNumberBeginning = string -> {

        String[] splitString = string.split(" ");
        StringBuilder finalString = new StringBuilder();

        for (int i = 0; i < splitString.length; i++) {
            String s = splitString[i];
            String temp = s.replace(s.charAt(0), s.charAt(s.length()-1)) ;
            temp = temp.substring(0, temp.length()-1);
            finalString.append(temp);
            if (i < splitString.length-1) {
                finalString.append(" ");
            }
        }

        return finalString.toString();
    };

    // turn every number into a character
    public static Function<String, String> listOfStringNumbers = string -> {

        String periodsToSpaces = string.replace(".", " ");
        String[] splitString = periodsToSpaces.split(" ");
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < splitString.length; i++) {
            String temp = splitString[i];
            int tempInt = Integer.parseInt(temp);
            sb.append(Character.toString((char) tempInt));
        }

        return sb.toString();
    };

    // reverse the numbers in the string
    public static Function<String, String> individualNumbers = string -> {

        String periodsToSpaces = string.replace(".", " ");
        String[] splitString = periodsToSpaces.split(" ");
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < splitString.length; i++) {
            StringBuilder temp = new StringBuilder(splitString[i]);
            sb.append(temp.reverse());
            if (i < splitString.length - 1) {
                sb.append(".");
            }
        }

        return sb.toString();
    };

    // reverse the string
    public static Function<String, String> reverseString = string -> {

        StringBuilder sb = new StringBuilder(string);

        return sb.reverse().toString();
    };

    /** NEED A HINT? CHECKOUT gitbox.include.show.Hints **/

}

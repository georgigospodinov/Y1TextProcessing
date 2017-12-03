package Utility;

import CharacterLevelNgrams.CharacterGramInfo;

import java.util.Comparator;
import java.util.Scanner;

public interface ChangeTheNameOfThisInterface {

    char SLASH = 92;

    //defines the way to compare two n-grams
    Comparator<CharacterGramInfo> comparator = ( o1, o2 ) -> {

        /*
        * the values here (-1, 1, 0) are taken from Oracle's documentation on Comparator.compare ()
        * that method returns -1 if the first element is less than the second
        * 1 if it's greater than it, or 0 if the elements are equal
        *
        * I have chosen to put switch the places of 1 and -1 so that the resulting order is descending
        */
        if ( o1.getOccurrences () < o2.getOccurrences () )
            return 1;

        else if ( o1.getOccurrences () > o2.getOccurrences () )
            return -1;

        else return 0;
    };

    static int defineN () {

        Scanner in = new Scanner ( System.in );
        String number = null;
        int sizeOfNGram = 0;

        while ( number == null ) {

            System.out.println ( "Enter size of grams (N of n-grams) to search for:" );
            number = in.next ();

            try {
                sizeOfNGram = Integer.parseInt ( number );
            }
            catch ( NumberFormatException e ) {
                System.out.println ( "Please enter a valid number." );
                number = null;//keeps up the while loop
            }

        }

        return sizeOfNGram;

    }

}

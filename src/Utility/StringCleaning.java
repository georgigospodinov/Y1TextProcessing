package Utility;

public class StringCleaning {

    private static boolean isLetter ( char c ) {

        return c >= 'a' && c <= 'z';

    }
    private static boolean isCapitalLetter ( char c ) {

        return c >= 'A' && c <= 'Z';

    }
    private static char capitalToLower ( char c ) {

        if ( isCapitalLetter ( c ) )
            return (char) (c + ('a'-'A'));

        else return c;

    }

    //takes a string and converts it to lower case letters and spaces
    public static String removeNonLetters ( String line ) {

        int l = line.length ();
        StringBuilder sb = new StringBuilder ( line );

        for ( int i = 0; i < l; i++ ) {

            //converts capital letters to lowercase letters
            sb.setCharAt ( i, capitalToLower ( sb.charAt ( i ) ) );

            if ( !isLetter ( sb.charAt ( i ) ) ) {
                if ( i > 0 && sb.charAt ( i - 1 ) != ' ' && sb.charAt ( i-1 ) != '-' )
                    sb.setCharAt ( i, ' ' );

                else sb.setCharAt ( i, '-' );//so that there are no consecutive spaces
            }

        }

        for ( int i = 0; i < sb.length (); i++ )
            if ( sb.charAt ( i ) == '-' ) {
                sb.deleteCharAt ( i );//delete character and
                i--;//decrease i otherwise a character will be skipped
            }

        //remove last char if it's a space but there was a letter before it and was not detected by the loop above
        l = sb.length ()-1;
        if ( l > -1 )
        if ( sb.charAt ( l ) == ' ' )
            sb.deleteCharAt ( l );

        return sb.toString ();

    }

}

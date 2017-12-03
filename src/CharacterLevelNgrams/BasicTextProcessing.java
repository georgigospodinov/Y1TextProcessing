package CharacterLevelNgrams;

import Utility.*;
import java.util.ArrayList;
import java.util.Collections;

public class BasicTextProcessing {

    private static int sizeOfNGram = 3;
    private static ArrayList<CharacterGramInfo> characterGramInfos;

    //these three methods are just for counting N-grams
    private static String[] generateNgramsOutOf ( String word ) {

        int l = word.length ();
        l -= (sizeOfNGram - 2);//this way l-1 is the last index of a char in word that can start a new n-gram
        //example in report
        String[] ngrams = new String[l];

        int i;
        l--;// l-1 (mentioned above), excluded from loop and added after it
        //the n-grams that have sizeOfNGram letters
        for ( i = 0; i < l; i++ )
            ngrams[i] = word.substring ( i, i + sizeOfNGram );
        //the last one that ends with an underscore
        ngrams[i] = word.substring ( i, i + sizeOfNGram - 1 ) + "_";

        return ngrams;

    }
    //looks for ngram in characterGramInfos
    private static int getIndexOf ( String ngram ) {

        int s = characterGramInfos.size ();

        int i;
        for ( i = 0; i < s; i++ )
            if ( characterGramInfos.get ( i ).getNgram ().equals ( ngram ) )
                break;

        return i;

    }
    //goes through the characterGramInfos arrayList and counts occurrences
    private static void iterateNgrams ( String[] ngrams ) {

        for ( String gram : ngrams ) {

            //skip null grams
            if ( gram == null )
                continue;

            int index = getIndexOf ( gram );

            if ( index == characterGramInfos.size () ) {

                CharacterGramInfo characterGramInfo = new CharacterGramInfo ( gram );
                characterGramInfos.add ( characterGramInfo );

            }

            characterGramInfos.get ( index ).countOccurrence ();

        }

    }

    public static void main ( String[] args ) {

        characterGramInfos = new ArrayList<> ();

        boolean empty = InputFile.open ();
        if ( !empty ) return;//if file was empty

        sizeOfNGram = ChangeTheNameOfThisInterface.defineN ();

        String line = InputFile.getNextLine ();
        System.out.println ( "Counting " + sizeOfNGram + "-grams..." );
        while ( line != null ) {

            String[] words = line.split ( " " );
            for ( String word : words )
            //count the n-grams in from it
                if ( word.length () > sizeOfNGram - 2 ) {
                    String[] Ngrams = generateNgramsOutOf ( word );
                    iterateNgrams ( Ngrams );
                }

            line = InputFile.getNextLine ();
        }

        System.out.println ( characterGramInfos.size () + " different " + sizeOfNGram + "-grams were found." );
        InputFile.close ();
        System.out.println ( "Sorting the " + sizeOfNGram + "-grams..." );
        Collections.sort ( characterGramInfos, ChangeTheNameOfThisInterface.comparator );

        OutputFile.open ( sizeOfNGram, "character" );

        System.out.println ( "Writing to output file..." );
        OutputFile.write ( sizeOfNGram + "-gram, count\n" );
        for ( CharacterGramInfo characterGramInfo : characterGramInfos )
            OutputFile.write ( characterGramInfo.toString () );

        OutputFile.close ();

    }
}

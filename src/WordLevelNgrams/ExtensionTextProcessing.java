package WordLevelNgrams;

import Utility.*;

import java.util.ArrayList;
import java.util.Collections;

public class ExtensionTextProcessing {

    private static int sizeOfNGram = 3;
    private static ArrayList<WordGramInfo> wordGramInfos;
    private static ArrayList<String> currentNgram;

    //adds words to currentNgram, returns the index of the last word read in inputWords
    private static void addWordsToCurrentNgram ( String[] inputWords, int startFrom ) {

        for ( int i = startFrom; i < inputWords.length; i++ ) {

            if ( currentNgram.size () < sizeOfNGram )
                currentNgram.add ( inputWords[i] );

            else break;//enough words have been read to form a WordGramInfo

        }

    }

    //adds currentNgram to wordGramInfos
    private static void addCurrentNgram () {

        //if the whole line has been read but a n-gram was not formed
        //cleans any half-entered ngrams
        if ( currentNgram.size () != sizeOfNGram ) {
            cleanCurrentNgram ();
            return;
        }

        String[] grams = new String[sizeOfNGram];
        for ( int i = 0; i < sizeOfNGram; i++ )
            grams[i] = currentNgram.get ( i );

        WordGramInfo wgi = new WordGramInfo ( grams );
//        System.out.println (wgi.toString ());

        int i, l = wordGramInfos.size ();
        for ( i = 0; i < l; i++ )//look for wgi in the array list
            if ( wordGramInfos.get ( i ).getNgram ().equals ( wgi.getNgram () ) ) {
                wordGramInfos.get ( i ).countOccurrence ();
                //System.out.println ( wordGramInfos.get ( i ).toString () );
                break;//if it's found, count an occurrence and stop searching
            }

        if ( i == l ) {//if loop has ended due to i == l rather than the break

            wgi.countOccurrence ();
            wordGramInfos.add ( wgi );
        }

        //forgets the currentNgram and is ready to remember a new one
        cleanCurrentNgram ();

    }

    private static void cleanCurrentNgram () {
        currentNgram = new ArrayList<> ();
    }

    public static void main ( String[] args ) {

        wordGramInfos = new ArrayList<> ();
        cleanCurrentNgram ();
        boolean empty = InputFile.open ();
        if ( !empty ) return;//if file was empty

        sizeOfNGram = ChangeTheNameOfThisInterface.defineN ();

        String line = InputFile.getNextLine ();
        int lineCounter = 1;
        System.out.println ( "Counting " + sizeOfNGram + "-grams..." );
        System.out.println ( "[This may take time]" );
        while ( line != null ) {

            //skip empty lines
            if ( line.equals ( "" ) ) {
                line = InputFile.getNextLine ();
                lineCounter ++;
                continue;
            }

            //to tell the user that it's not stuck
            if ( lineCounter%1000 == 0 )
                System.out.println ("Past line: " + lineCounter);

            String[] wordsInCurrentLine = line.split ( " " );
            int l = wordsInCurrentLine.length;
            //skip lines with less than sizeOfNGram words
            if ( l < sizeOfNGram ) {
                line = InputFile.getNextLine ();
                lineCounter ++;
                continue;
            }

            for ( int i = 0; i < l - 1; i++ ) {

                addWordsToCurrentNgram ( wordsInCurrentLine, i );
                addCurrentNgram ();

            }

            cleanCurrentNgram ();
            line = InputFile.getNextLine ();
            lineCounter ++;

        }

        System.out.println ( wordGramInfos.size () + " different " + sizeOfNGram + "-grams were found." );
        InputFile.close ();

        System.out.println ( "Sorting the " + sizeOfNGram + "-grams..." );
        Collections.sort ( wordGramInfos, ChangeTheNameOfThisInterface.comparator );

        OutputFile.open ( sizeOfNGram, "word" );

        System.out.println ( "Writing to output file..." );
        OutputFile.write ( sizeOfNGram + "-gram, count\n" );
        for ( WordGramInfo wgi : wordGramInfos )
                OutputFile.write ( wgi.toString () );

        OutputFile.close ();

    }
}

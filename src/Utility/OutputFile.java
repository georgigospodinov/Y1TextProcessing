package Utility;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class OutputFile {

    private static final String OUTPUT_FOLDER = "NgramCounts" + ChangeTheNameOfThisInterface.SLASH;

    private static BufferedWriter outputFile;

    private static String defineOutputFileName ( String input, int sizeOfNgrams, String level ) {

        int l = input.length ();

        //first find the name of the file after all (if any) directory slashes '\'
        int last_slash_index = 0;
        for ( int i = 0; i < l; i++ )
            if ( input.charAt ( i ) == ChangeTheNameOfThisInterface.SLASH )
                last_slash_index = i;

        //then define the name of the output file
        String output = OUTPUT_FOLDER + level + "-level_" + sizeOfNgrams + "-grams_in_";
        //the last four digits of the input are ".txt"
        output += input.substring ( last_slash_index + 1, l - 4 );
        output += ".csv";
        return output;

    }

    public static void open ( int sizeOfNgrams, String level ) {

        System.out.println ("Opening output file...");
        String outputFileName = defineOutputFileName ( InputFile.getInputFileName (), sizeOfNgrams, level );

        try {
            outputFile = new BufferedWriter ( new FileWriter ( outputFileName ) );
            System.out.println ( "Output file successfully opened." );
        }
        catch ( IOException e ) {
            System.out.println ( "Failed to open output file." );
            System.out.println ( "File name: " + outputFileName );
        }

    }

    public static void write ( String data ) {

        try {
            outputFile.write ( data );
        }
        catch ( IOException e ) {
            System.out.println ( "Failed to write to output file." );
        }

    }

    public static void close () {

        System.out.println ("Closing output file...");
        try {
            outputFile.close ();
        }
        catch ( IOException e ) {
            System.out.println ( "Failed to close output file." );
        }

    }

}

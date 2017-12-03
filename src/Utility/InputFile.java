package Utility;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class InputFile {

    private static final String INPUT_FOLDER = "InputFiles" + ChangeTheNameOfThisInterface.SLASH;

    private static BufferedReader inputFile;
    private static int lineCounter = 0;
    private static String inputFileName;

    public static boolean open () {

        System.out.println ( "Opening input file..." );
        Scanner in = new Scanner ( System.in );

        System.out.println ( "Enter the name of the input file (without the \".txt\" extension)." );
        System.out.println ( "The file should be in the folder: " + INPUT_FOLDER );

        while ( inputFileName == null ) {

            inputFileName = in.nextLine ();

            try {
                inputFileName.length ();
            }
            catch ( NullPointerException e ) {
                System.out.println ( "Please, make sure you enter a valid file name." );
                continue;
            }

            String name = INPUT_FOLDER + inputFileName + ".txt";

            try {
                inputFile = new BufferedReader ( new FileReader ( name ) );
                System.out.println ( "Input file successfully opened." );
                inputFileName = name;

                System.out.println ( "Checking file contents..." );
                String firstLine = getNextLine ();
                if ( firstLine == null ) {
                    System.out.println ( "Input file is empty. Program terminates." );
                    InputFile.close ();
                    return false;
                }
                else {
                    System.out.println ( "File has contents to read." );
                    System.out.println ( "Checking if it can be closed later..." );
                    close ();
                    inputFile = new BufferedReader ( new FileReader ( name ) );
                    System.out.println ( "Input file fully operable." );
                    return true;
                }
            }
            catch ( FileNotFoundException e ) {
                System.out.println ( "Could not open input file." );
                System.out.println ( "File name: " + inputFileName );
                System.out.println ( "Please, try again." );
                inputFileName = null;//so that the loop continues if the try was unsuccessful
            }

        }

        //the try in the while stops the method
        //see the return statements in the try block
        return false;

    }

    public static void close () {
        System.out.println ( "Closing input file..." );
        try {
            inputFile.close ();
        }
        catch ( IOException e ) {
            System.out.println ( "Failed to close input file." );
        }
    }

    public static String getNextLine () {

        String line;
        try {
            line = inputFile.readLine ();
            lineCounter++;
        }
        catch ( IOException e ) {
            System.out.println ( "Could not read a line from input file." );
            System.out.println ( "Last line read: " + lineCounter );
            System.out.println ( "Stack trace: " );
            e.printStackTrace ();
            line = null;//the null value stops any loops / fails any if-s
        }

        if ( line != null )//this way returned line is always clean
            line = StringCleaning.removeNonLetters ( line );

        return line;
    }

    public static String getInputFileName () {
        return inputFileName;
    }
}

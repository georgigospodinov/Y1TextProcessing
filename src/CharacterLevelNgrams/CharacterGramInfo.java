package CharacterLevelNgrams;

public class CharacterGramInfo {

    //it's called firstWord because in WordGramInfo I need it separate from the rest
    //more info in that class
    protected String firstWord;
    private int occurrences = 0;

    public void countOccurrence () {
        occurrences ++;
    }

    public CharacterGramInfo ( String firstWord ) {
        this.firstWord = firstWord;
    }

    public String getNgram () {
        return firstWord;
    }
    public int getOccurrences () {
        return occurrences;
    }

    @Override
    public String toString () {
        return firstWord + ", " + occurrences + "\n";
    }
}

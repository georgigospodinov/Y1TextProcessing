package WordLevelNgrams;

import CharacterLevelNgrams.CharacterGramInfo;

//inherits CharacterGramInfo so that I don't need to write all the same methods again
// and so that the comparator in Utility.ChangeTheNameOfThisInterface can take instances of this class
// otherwise a new comparator would be needed
public class WordGramInfo extends CharacterGramInfo {

    private int size;
    private String[] gram;//has all the words but the first one

    public WordGramInfo ( String[] gram ) {

        super(gram[0]);
        size = gram.length;
        this.gram = new String[size-1];

        for ( int i = 1; i < size; i++ )
            this.gram[i - 1] = gram[i];

    }

    @Override
    public String getNgram () {
        String string = firstWord;
        for ( String g : gram ) string += " " + g;

        return string;
    }

    @Override
    public String toString () {
        return this.getNgram () + ", " + this.getOccurrences() + "\n";
    }
}

package nassermohamedit.machines;

import java.util.Iterator;

/**
 * @author nasser
 */
public class CharArrayIterator implements Iterator<Character> {

    private final char[] chars;

    private final int length;

    private int curr;

    public CharArrayIterator(char[] chars) {
        this.chars = chars;
        length = chars.length;
        curr = 0;
    }

    @Override
    public boolean hasNext() {
        return curr < length;
    }

    @Override
    public Character next() {
        return chars[curr++];
    }
}

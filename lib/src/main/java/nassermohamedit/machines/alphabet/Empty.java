package nassermohamedit.machines.alphabet;

import nassermohamedit.machines.CharacterMatcher;
import nassermohamedit.machines.OutOfAlphabetException;

import java.util.Collections;
import java.util.Iterator;

/**
 * @author nasser
 */
final class Empty implements Alphabet {

    Empty() {}

    @Override
    public char[] toArray() {
        return new char[0];
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean contains(char c) {
        return false;
    }

    @Override
    public boolean contains(char... c) {
        return false;
    }

    @Override
    public Iterator<Character> iterator() {
        return Collections.emptyIterator();
    }

    @Override
    public CharacterMatcher single(char c) {
        throw new OutOfAlphabetException(c);
    }

    @Override
    public CharacterMatcher any(char... c) {
        throw new OutOfAlphabetException(c[0]);
    }

    @Override
    public CharacterMatcher any() {
        return c -> { throw new OutOfAlphabetException(c); };
    }

    @Override
    public CharacterMatcher range(char min, char max) {
        throw new OutOfAlphabetException(min);
    }

}

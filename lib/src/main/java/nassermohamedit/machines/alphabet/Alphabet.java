package nassermohamedit.machines.alphabet;


import java.util.Iterator;

import nassermohamedit.machines.CharArrayIterator;
import nassermohamedit.machines.CharacterMatcher;
import nassermohamedit.machines.OutOfAlphabetException;

public sealed interface Alphabet extends Iterable<Character>
        permits Empty, Range, Set, Singleton, Universe {

    Alphabet NULL = new Empty();

    Alphabet UNIVERSE = new Universe();

    char[] toArray();

    int size();

    boolean contains(char c);

    default boolean contains(char... chars) {
        for(char c: chars) {
            if (!contains(c))
                return false;
        }
        return true;
    }

    default Iterator<Character> iterator() {
        return new CharArrayIterator(toArray());
    }

    default CharacterMatcher single(char ch) {
        checkCharacters(ch);
        return c -> c == ch;
    }

    default CharacterMatcher any(char... chars) {
        checkCharacters(chars);
        return c -> {
            for (char ch : chars) {
                if (ch == c)
                    return true;
            }
            return false;
        };
    }

    default CharacterMatcher any() {
        return this::contains;
    }

    default CharacterMatcher complement(char... chars) {
        checkCharacters(chars);
        return c -> {
            for (char ch : chars)
                if (ch == c)
                    return false;
            if (!this.contains(c)) throw new OutOfAlphabetException(c);
            return true;
        };
    }

    default CharacterMatcher range(char min, char max) {
        if (min > max)
            throw new IllegalArgumentException();
        int n = max - min + 1;
        for (int i = 0; i < n; i++) {
            checkCharacter((char) (min + i));
        }
        return c -> c >= min && c <= max;
    }

    private void checkCharacter(char c) {
        if (!contains(c))
            throw new OutOfAlphabetException(c);
    }

    private void checkCharacters(char... chars) {
        for (char c : chars)
            checkCharacter(c);
    }
}

package nassermohamedit.machines.alphabet;

import nassermohamedit.machines.CharacterMatcher;

/**
 * @author nasser
 */
final class Universe implements Alphabet {

    Universe() {}

    @Override
    public char[] toArray() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int size() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean contains(char c) {
        return true;
    }

    @Override
    public CharacterMatcher range(char min, char max) {
        if (min > max) throw new IllegalArgumentException();
        return c -> c >= min && c <= max;
    }
}

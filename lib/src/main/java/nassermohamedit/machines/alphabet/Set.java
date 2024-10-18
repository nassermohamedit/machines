package nassermohamedit.machines.alphabet;

/**
 * @author nasser
 */
final class Set implements Alphabet {

    private final char[] chars;

    public Set(char... chars) {
        this.chars = chars.clone();

    }

    @Override
    public char[] toArray() {
        return chars;
    }

    @Override
    public int size() {
        return chars.length;
    }

    @Override
    public boolean contains(char c) {
        for (char cc : chars) {
            if (cc == c)
                return true;
        }
        return false;
    }
}

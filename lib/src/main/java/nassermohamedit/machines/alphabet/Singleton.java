package nassermohamedit.machines.alphabet;

/**
 * @author nasser
 */
record Singleton(char c) implements Alphabet {

    @Override
    public char[] toArray() {
        return new char[] {c};
    }

    @Override
    public int size() {
        return 1;
    }

    @Override
    public boolean contains(char c) {
        return this.c == c;
    }
}

package nassermohamedit.machines.alphabet;

import nassermohamedit.machines.CharacterMatcher;

/**
 * @author nasser
 */
public record Range(int min, int max) implements Alphabet {

    @Override
    public char[] toArray() {
        int size = max - min + 1;
        char[] chars = new char[size];
        for (int i = 0; i < size; i++) {
            chars[i] = (char) (min + i);
        }
        return chars;
    }

    @Override
    public int size() {
        return max - min + 1;
    }

    @Override
    public boolean contains(char c) {
        return c >= min && c <= max;
    }

    @Override
    public CharacterMatcher complement(char... chars) {
        int len = chars.length;
        if (len == 1) {
            char ch = chars[0];
            if (ch == min)
                return c -> c > ch && c <= max;
            else if (ch == max)
                return c -> c >= min && c < max;
            return c -> c >= min && c <= max && c != ch;
        }
        return Alphabet.super.complement(chars);
    }

    @Override
    public CharacterMatcher range(char minc, char maxc) {
        if (minc > maxc || minc < min || maxc > max)
            throw new IllegalArgumentException();
        return c -> c >= minc && c <= maxc;
    }

    public static Range az() {
        return new Range('a', 'z');
    }

    public static Range digits() {
        return new Range('0', '9');
    }
}

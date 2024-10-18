package nassermohamedit.machines;

/**
 * @author nasser
 */
public class OutOfAlphabetException extends RuntimeException {

    public OutOfAlphabetException(char c) {
        super(String.format("character is out of alphabet: %c", c));
    }
}

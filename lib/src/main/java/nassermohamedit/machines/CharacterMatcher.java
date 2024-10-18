package nassermohamedit.machines;

/**
 * @author nasser
 */
@FunctionalInterface
public interface CharacterMatcher {

    boolean match(char c);
}

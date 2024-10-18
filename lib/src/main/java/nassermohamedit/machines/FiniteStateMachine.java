package nassermohamedit.machines;

import nassermohamedit.machines.alphabet.Alphabet;

/**
 * @author nasser
 */
public interface FiniteStateMachine {

    Alphabet alphabet();

    /**
     * @return the number of states in this machine
     */
    int states();

    /**
     * @param curr the current state
     * @param c the input character
     * @return the next state
     */
    int transition(int curr, char c);

    /**
     * Tests if the input string moves the machine from start state to an
     * accept state. start can be any state.
     * @param start The starting state
     * @param input The input string
     * @return true if the machine ends at an accept state after taking input
     */
    boolean accepts(int start, String input);

    /**
     * @param input The input string to be tested
     * @return true if this machine accepts the input string
     */
    default boolean accepts(String input) {
        return accepts(0, input);
    }

    boolean isAccepting(int state);
}

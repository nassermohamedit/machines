package nassermohamedit.machines;

import nassermohamedit.machines.alphabet.Alphabet;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * Deterministic Finite Automaton
 * @author nasser
 */
public class DFA implements FiniteStateMachine {

    // The set of possible input characters
    private final Alphabet alphabet;

    // number of states in this DFA
    private final int states;

     // accept[s] == boolean iff s is an accept state
    private final boolean[] accept;

    private final List<? extends List<Transition>> transitions;

    private boolean built = false;

    private DFA(int states, Alphabet alpha) {
        alphabet = alpha;
        this.states = states;
        accept = new boolean[states];
        transitions = Stream.generate(() -> new ArrayList<Transition>()).limit(states).toList();
    }

    public static DFABuilder builder(int states, Alphabet alpha) {
        return new DFABuilder(states, alpha);
    }


    private void transition(int curr, CharacterMatcher cm, int next) {
        checkIfBuilt();
        Objects.checkIndex(curr, states);
        Objects.checkIndex(next, states);
        List<Transition> ts = transitions.get(curr);
        ts.add(new Transition(cm, next));
    }

    private void acceptingState(int state, boolean isAccept) {
        checkIfBuilt();
        accept[state] = isAccept;
    }

    private void checkIfBuilt() {
        if (built)
            throw new IllegalStateException();
    }

    private void checkInputChar(char in) {
        if (!alphabet.contains(in))
            throw new IllegalArgumentException();
    }

    @Override
    public boolean isAccepting(int state) {
        Objects.checkIndex(state, states);
        return accept[state];
    }


    @Override
    public int transition(int currentState, char in) {
        if (!built)
            throw new IllegalStateException();
        checkInputChar(in);
        Objects.checkIndex(currentState, states);
        List<Transition> ts = transitions.get(currentState);
        if (ts == null || ts.isEmpty())
            return -1;
        var it = ts.listIterator(ts.size());
        while (it.hasPrevious()) {
            var t = it.previous();
            if (t.matcher().match(in))
                return t.nextState();
        }
        return -1;
    }

    @Override
    public int states() {
        return states;
    }

    @Override
    public Alphabet alphabet() {
        return alphabet;
    }

    @Override
    public boolean accepts(int initialState, String input) {
        if (!built) throw new IllegalStateException();
        int state = initialState;
        for (int i = 0; i < input.length(); ++i) {
            state = transition(state, input.charAt(i));
            if (state == -1)
                return false;
        }
        return isAccepting(state);
    }

    @Override
    public boolean accepts(String input) {
        if (!built)
            throw new IllegalStateException();
        return accepts(0, input);
    }

    private record Transition(CharacterMatcher matcher, int nextState) {}

    public static class DFABuilder {

        private final DFA instance;

        private DFABuilder(int states, Alphabet alphabet) {
            instance = new DFA(states, alphabet);
        }

        public DFABuilder transition(int curr, CharacterMatcher m, int next) {
            instance.transition(curr, m, next);
            return this;
        }

        public DFABuilder transition(int curr, char c, int next) {
            instance.transition(curr, instance.alphabet.single(c), next);
            return this;
        }

        public DFABuilder setAcceptState(int state, boolean accept) {
            instance.acceptingState(state, accept);
            return this;
        }

        public DFA build() {
            instance.built = true;
            return instance;
        }
    }
}

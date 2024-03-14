public class DSAHashEntry {
    public enum State {
        ACTIVE, DELETED
    }

    private String key;
    private Object value;
    private State state;

    public DSAHashEntry(String key, Object value) {
        this.key = key;
        this.value = value;
        this.state = State.ACTIVE;
    }

    public String getKey() {
        return key;
    }

    public Object getValue() {
        return value;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}

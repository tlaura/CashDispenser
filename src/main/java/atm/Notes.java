package atm;

public enum Notes {
    NOTE_50(50), NOTE_20(20);

    private final int value;

    Notes(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

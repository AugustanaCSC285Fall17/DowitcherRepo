package edu.augustana.csc285.game.datamodel.effect;

public enum EffectOperation {
    PLUS,
    MINUS,
    TIMES;
	// change should always be positive, original should always be non negative
    int applyEffect(int original, int change) {
        switch (this) {
            case PLUS:
                return original + change;
            case MINUS:
            	int temp = original - change;
            	if (temp<0) {
            		temp = 0;
            	}
                return temp;
            case TIMES:
                return original*change;
            default:
                throw new AssertionError("Unknown effect " + this);
        }
    }
	public String toString() {
		return this.name().toLowerCase();
	}

}
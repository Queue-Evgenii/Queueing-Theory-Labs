package ua.nure.tmo_lab_1_2_fx.models.sequences;

import java.security.InvalidParameterException;
import java.util.Random;

public class RandomSequence extends Sequence {
    public RandomSequence(int size) throws InvalidParameterException {
        super(size);

        this.generateFloats();
    }
    private void generateFloats() {
        Random rand = new Random();
        for (int i = 0; i < this.sequence.length; ++i) {
            this.sequence[i] = rand.nextFloat();
        }
    }
    public float[] getSequence() {
        return sequence.clone();
    }
    public int getLength() {
        return sequence.length;
    }
}

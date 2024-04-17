package ua.nure.tmo_lab_1_2_fx.models.sequences;

import java.security.InvalidParameterException;

public class ZSequence extends Sequence {
    private final float lambda;
    public ZSequence(float lambda, float[] randomSequence) throws InvalidParameterException {
        super(randomSequence.length);

        if (lambda <= 0) throw new InvalidParameterException();
        this.lambda = lambda;

        for (int i = 0; i < this.sequence.length; ++i) {
            this.sequence[i] = (float)((-1 / lambda) * (Math.log(randomSequence[i])));
        }
    }
    public float[] getSequence() {
        return sequence.clone();
    }
    public float getLambda() {
        return lambda;
    }
}

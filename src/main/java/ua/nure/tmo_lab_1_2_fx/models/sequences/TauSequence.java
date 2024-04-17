package ua.nure.tmo_lab_1_2_fx.models.sequences;


import ua.nure.tmo_lab_1_2_fx.tools.SequenceHelpers;

import java.security.InvalidParameterException;

public class TauSequence extends Sequence {
    private float tau;
    public TauSequence(float T1, float T2, int intervalCount, float[] tSequence) throws InvalidParameterException {
        super(intervalCount);
        if (intervalCount <= 0 || T1 <= 0 || T2 <= 0) throw new InvalidParameterException();
        this.tau = SequenceHelpers.calculateTau(T1, T2, intervalCount);

        float end, start;

        int i = 0;
        while (i < intervalCount) {
            start = T1 + i * tau;
            end = start + tau;
            this.sequence[i] = SequenceHelpers.countNumbersOnInterval(start, end, tSequence);
            ++i;
        }
    }

    public TauSequence(TauSequence sequence1, TauSequence sequence2) throws InvalidParameterException {
        super(sequence1.getLength());
        if (sequence1.getLength() != sequence2.getLength() || sequence1.tau != sequence2.tau) throw new InvalidParameterException();
        this.tau = sequence1.tau;

        for (int i = 0; i < this.sequence.length; ++i) {
            this.sequence[i] = sequence1.sequence[i] + sequence2.sequence[i];
        }
    }

    public float getTau() {
        return tau;
    }

    public float[] getSequence() {
        return sequence.clone();
    }

    public int getLength() {
        return sequence.length;
    }
}

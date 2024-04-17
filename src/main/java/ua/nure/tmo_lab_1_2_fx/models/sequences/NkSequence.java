package ua.nure.tmo_lab_1_2_fx.models.sequences;


import ua.nure.tmo_lab_1_2_fx.tools.SequenceHelpers;

public class NkSequence extends Sequence {
    public NkSequence(float[] tauSequence, int intervalCount) {
        super(SequenceHelpers.getMaxValue(tauSequence));
        int i = 0;
        while (i < this.sequence.length) {
            this.sequence[i] = SequenceHelpers.countNumbersEqual(i, tauSequence, intervalCount);
            ++i;
        }
    }

    public float[] getSequence() {
        return sequence.clone();
    }
    public int getLength() {
        return sequence.length;
    }
}

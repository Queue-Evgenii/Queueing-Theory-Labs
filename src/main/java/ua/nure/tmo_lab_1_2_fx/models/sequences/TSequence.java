package ua.nure.tmo_lab_1_2_fx.models.sequences;

public class TSequence extends Sequence {
    public TSequence(float[] zSequence, float T1, float T2) {
        super(1);
        float[] temp = new float[zSequence.length];

        for (int i = 0; i < zSequence.length; ++i) {
            temp[i] = T1;
        }

        int k = -1;
        do {
            if (k >= zSequence.length) break;
            ++k;

            for (int i = 0; i < k; i++) {
                temp[k] += zSequence[i];
            }

        } while (temp[k] <= T2);

        this.sequence = new float[k];
        System.arraycopy(temp, 0, this.sequence, 0, k);
    }

    public float[] getSequence() {
        return sequence.clone();
    }
    public int getLength() {
        return sequence.length;
    }
}

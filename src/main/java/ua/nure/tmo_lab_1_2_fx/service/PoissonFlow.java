package ua.nure.tmo_lab_1_2_fx.service;

import ua.nure.tmo_lab_1_2_fx.models.sequences.RandomSequence;
import ua.nure.tmo_lab_1_2_fx.models.sequences.TSequence;
import ua.nure.tmo_lab_1_2_fx.models.sequences.ZSequence;

public class PoissonFlow {
    private final float lambda;
    private final int T1;
    private final int T2;
    private final int intervalCount;
    protected RandomSequence sequence;
    protected ZSequence zSequence;
    protected TSequence tSequence;

    public PoissonFlow(float lambda, int studentNumber, int T1, int T2, int intervalCount) {
        this.lambda = lambda;
        this.T1 = T1 + studentNumber;
        this.T2 = T2 + studentNumber;
        this.intervalCount = intervalCount;

        this.createFlow((int)(this.lambda * 8));
    }
    public PoissonFlow(float lambda, int studentNumber, int T1, int T2) {
        this.lambda = lambda;
        this.T1 = T1 + studentNumber;
        this.T2 = T2 + studentNumber;
        this.intervalCount = (T2 - T1) * 5;

        this.createFlow(this.intervalCount);
    }

    private void createFlow(int size) {
        sequence = new RandomSequence(size);
        zSequence = new ZSequence(this.lambda, this.sequence.getSequence());
        tSequence = new TSequence(this.zSequence.getSequence(), this.T1, this.T2);
    }

    public float[] getInitialSequence() {
        return sequence.getSequence();
    }
    public float[] getZSequence() {
        return zSequence.getSequence();
    }
    public float[] getTSequence() {
        return tSequence.getSequence();
    }

    public int getT2() {
        return T2;
    }
    public int getT1() {
        return T1;
    }
    public float getLambda() {
        return lambda;
    }
    public int getIntervalCount() {
        return intervalCount;
    }
    public int getLength() {
        return sequence.getLength();
    }
}

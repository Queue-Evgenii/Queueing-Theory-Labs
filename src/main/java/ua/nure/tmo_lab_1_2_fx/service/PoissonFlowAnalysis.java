package ua.nure.tmo_lab_1_2_fx.service;

import ua.nure.tmo_lab_1_2_fx.models.sequences.NkSequence;
import ua.nure.tmo_lab_1_2_fx.models.sequences.TauSequence;

public class PoissonFlowAnalysis {
    protected final TauSequence tauSequence;
    protected final NkSequence nkSequence;
    private final float modelLambda;
    public PoissonFlowAnalysis(PoissonFlow flow) {
        this.tauSequence = new TauSequence(flow.getT1(), flow.getT2(), flow.getIntervalCount(), flow.getTSequence());
        this.nkSequence = new NkSequence(tauSequence.getSequence(), flow.getIntervalCount());
        this.modelLambda = this.calcModelValue();
    }
    public PoissonFlowAnalysis(TauSequence tauSequence, int intervalCount) {
        this.tauSequence = tauSequence;
        this.nkSequence = new NkSequence(tauSequence.getSequence(), intervalCount);
        this.modelLambda = this.calcModelValue();
    }
    private float calcModelValue() {
        int size = nkSequence.getLength();
        float[] sequence = nkSequence.getSequence();

        int M = getSum(sequence, size);
        int sum = 0;

        int i = 0;
        while (i < size) {
            sum += (i * sequence[i]);
            ++i;
        }

        return ((sum / (float)M) / this.tauSequence.getTau());
    }
    private int getSum(float[] sequence, int size) {
        int sum = 0;
        int i = 0;
        while (i < size) {
            sum += sequence[i];
            ++i;
        }
        return sum;
    }

    public float getModelLambda() {
        return this.modelLambda;
    }
    public float getTau() {
        return this.tauSequence.getTau();
    }
    public float[] getTauSequence() {
        return tauSequence.getSequence();
    }
    public TauSequence getTauSequenceObject() {
        return tauSequence;
    }
    public float[] getNkSequence() {
        return nkSequence.getSequence();
    }
}

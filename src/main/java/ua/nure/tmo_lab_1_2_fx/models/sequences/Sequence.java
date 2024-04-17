package ua.nure.tmo_lab_1_2_fx.models.sequences;

import java.security.InvalidParameterException;
import java.text.DecimalFormat;

public class Sequence {
    protected float[] sequence;
    DecimalFormat formatter = new DecimalFormat();
    public Sequence(int size) throws InvalidParameterException {
        if (size <= 0) throw new InvalidParameterException();
        this.sequence = new float[size];
        formatter.setMaximumFractionDigits(4);
    }

    public void printSingle(int index) throws InvalidParameterException {
        if (index < 0 || index >= sequence.length) throw new InvalidParameterException();
        System.out.print(formatter.format(sequence[index]));
    }
    public float getSingle(int index) throws InvalidParameterException {
        if (index < 0 || index >= sequence.length) throw new InvalidParameterException();
        return sequence[index];
    }
}

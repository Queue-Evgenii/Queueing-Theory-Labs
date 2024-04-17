package ua.nure.tmo_lab_1_2_fx.tools;

import java.security.InvalidParameterException;

public class SequenceHelpers {
    public static float calculateTau(float T1, float T2, int intervalCount) throws InvalidParameterException {
        if (intervalCount == 0) throw new InvalidParameterException();
        return (T2 - T1) / (float)intervalCount;
    }
    public static int countNumbersOnInterval(float start, float end, float[] sequence) throws InvalidParameterException {
        if (start > end) throw new InvalidParameterException();
        int count = 0;

        int i = 0;
        while (i < sequence.length) {
            if (sequence[i] >= start && sequence[i] < end) ++count;
            ++i;
        }

        return count;
    }
    public static int getMaxValue(float[] sequence) {
        float max = sequence[0];

        int i = 0;
        while (i < sequence.length) {
            if (max < sequence[i]) max = sequence[i];
            ++i;
        }

        return (int)max;
    }
    public static float countNumbersEqual(int value, float[] sequence, int size) {
        int count = 0;

        int i = 0;
        while (i < size) {
            if (sequence[i] == value) ++count;
            ++i;
        }

        return (float)count;
    }

}

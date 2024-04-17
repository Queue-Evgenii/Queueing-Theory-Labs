package ua.nure.tmo_lab_1_2_fx.tools;

public class GeneralHelpers {
    public static int calculateLambda(int groupNumber, int studentNumber, int value1, int value2) {
        return ((value1 * groupNumber) / (studentNumber + value2));
    }
    public static float calculateLambda(int groupNumber, int studentNumber, int servicesNumber) {
        return ((float)(10 * groupNumber) / (float)(studentNumber * servicesNumber));
    }
    public static float calculateLambdaAlt(int groupNumber, int studentNumber, int servicesNumber) {
        return ((float)(15 * groupNumber) / (float)(studentNumber * servicesNumber));
    }
    public static float calculateMu(int groupNumber, int studentNumber, int servicesNumber) {
        return 3 * ((groupNumber + (float)(studentNumber / 4)) / ((float)studentNumber * servicesNumber));
    }
    public static float calculateAverageX(float[] sequence) {
        float xAverage = 0;
        for (int i = 0; i < sequence.length; ++i) {
            xAverage += sequence[i];
        }
        xAverage /= sequence.length;
        return xAverage;
    }
    public static float calculateDispersion(float[] sequence) {
        float result = 0;
        float xAverage = calculateAverageX(sequence);

        for (int i = 0; i < sequence.length; ++i) {
            result += (float)Math.pow(sequence[i] - xAverage, 2);
        }
        result /= (sequence.length - 1);

        return result;
    }

    public static float systemOverload(float lambda, float mu) {
        return lambda / mu;
    }

    public static double factorial(int number) {
        if (number <= 1) return 1;
        return number * factorial(number - 1);
    }
}
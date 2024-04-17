package ua.nure.tmo_lab_1_2_fx.service;

import ua.nure.tmo_lab_1_2_fx.tools.GeneralHelpers;

public class ErlangSystem {

    float[] Pk;

    float lambda;
    float mu;
    float ro;
    float averageOverload;
    int servicesNumber;

    public ErlangSystem(int groupNumber, int studentNumber, int servicesNumber) {
        this.servicesNumber = servicesNumber;
        lambda = GeneralHelpers.calculateLambda(groupNumber, studentNumber, servicesNumber);
        mu = GeneralHelpers.calculateMu(groupNumber, studentNumber, servicesNumber);
        ro = GeneralHelpers.systemOverload(lambda, mu);

        createSequence(servicesNumber);
    }
    public ErlangSystem(float lambda, float mu) {
        this.servicesNumber = 0;
        this.lambda = lambda;
        this.mu = mu;
        ro = GeneralHelpers.systemOverload(lambda, mu);

        createSequence(servicesNumber);
    }
    public ErlangSystem(float lambda, float averageServiceTime, boolean isByTime) {
        this.servicesNumber = 0;
        this.lambda = lambda;
        this.mu = 1.0f / averageServiceTime;
        this.ro = GeneralHelpers.systemOverload(lambda, mu);

        createSequence(servicesNumber);
    }

    private void createSequence(int servicesNumber) {
        Pk = new float[servicesNumber];

        float sum = getSystemSum(servicesNumber);
        for (int k = 0; k < servicesNumber; k++) {
            Pk[k] = (float)(Math.pow(ro, k) / GeneralHelpers.factorial(k)) / sum;
            averageOverload += (k * Pk[k]);
        }
    }

    public float getSystemSum(int size) {
        float initValue = 0;
        for (int i = 0; i <= size; i++) {
            initValue += (float)(Math.pow(ro, i) / GeneralHelpers.factorial(i));
        }
        return initValue;
    }

    public float getLambda() {
        return lambda;
    }
    public float getMu() {
        return mu;
    }
    public float getRo() {
        return ro;
    }
    public float getAverageOverload() {
        return averageOverload;
    }
    public float getPk(int k) {
        if (k >= Pk.length) return 0;
        return Pk[k];
    }
    public int getServicesNumber() {
        return servicesNumber;
    }
}

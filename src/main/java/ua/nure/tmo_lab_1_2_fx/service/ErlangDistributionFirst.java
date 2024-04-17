package ua.nure.tmo_lab_1_2_fx.service;

import ua.nure.tmo_lab_1_2_fx.tools.GeneralHelpers;

public class ErlangDistributionFirst extends ErlangDistribution {
    protected float averageOverload;
    public float getAverageOverload() {
        return averageOverload;
    }

    public ErlangDistributionFirst(int groupNumber, int studentNumber, int servicesNumber) {
        this.servicesNumber = servicesNumber;
        this.flowIntensity = GeneralHelpers.calculateFlowIntensity(groupNumber, studentNumber, servicesNumber);
        this.serviceIntensity = GeneralHelpers.calculateServiceIntensity(groupNumber, studentNumber, servicesNumber);
        this.systemOverload = GeneralHelpers.systemOverload(this.flowIntensity, this.serviceIntensity);

        createSequence(servicesNumber);
    }
    public ErlangDistributionFirst(float flowIntensity, float serviceIntensity) {
        this.servicesNumber = 0;
        this.flowIntensity = flowIntensity;
        this.serviceIntensity = serviceIntensity;
        this.systemOverload = GeneralHelpers.systemOverload(flowIntensity, serviceIntensity);

        createSequence(servicesNumber);
    }
    public ErlangDistributionFirst(float flowIntensity, float averageServiceTime, boolean isByTime) {
        this.servicesNumber = 0;
        this.flowIntensity = flowIntensity;
        this.serviceIntensity = 1.0f / averageServiceTime;
        this.systemOverload = GeneralHelpers.systemOverload(flowIntensity, this.serviceIntensity);

        createSequence(this.servicesNumber);
    }

    private void createSequence(int servicesNumber) {
        this.Pk = new float[servicesNumber];

        float sum = getSystemSum(servicesNumber);
        for (int k = 0; k < servicesNumber; k++) {
            this.Pk[k] = (float)(Math.pow(this.systemOverload, k) / GeneralHelpers.factorial(k)) / sum;
            this.averageOverload += (k * this.Pk[k]);
        }
    }

    public float getSystemSum(int size) {
        float initValue = 0;
        for (int i = 0; i <= size; i++) {
            initValue += (float)(Math.pow(this.systemOverload, i) / GeneralHelpers.factorial(i));
        }
        return initValue;
    }


}

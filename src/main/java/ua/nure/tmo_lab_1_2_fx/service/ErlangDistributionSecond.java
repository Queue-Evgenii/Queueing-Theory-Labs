package ua.nure.tmo_lab_1_2_fx.service;

import ua.nure.tmo_lab_1_2_fx.tools.GeneralHelpers;

public class ErlangDistributionSecond extends ErlangDistribution {
    public ErlangDistributionSecond(int groupNumber, int studentNumber, int servicesNumber) {
        this.servicesNumber = servicesNumber;
        this.flowIntensity = GeneralHelpers.calculateFlowIntensityAlt(groupNumber, studentNumber, servicesNumber);
        this.serviceIntensity = GeneralHelpers.calculateServiceIntensityAlt(groupNumber, studentNumber, servicesNumber);
        this.systemOverload = GeneralHelpers.systemOverload(this.flowIntensity, this.serviceIntensity);

        this.P0 = createP0();
        createSequence(servicesNumber);
    }
    private float createP0() {
        float denominator = (float)(Math.pow(systemOverload, servicesNumber + 1) /
                            (GeneralHelpers.factorial(servicesNumber) * (servicesNumber - systemOverload)));
        for (int i = 0; i < servicesNumber; ++i) {
            denominator += (float)(Math.pow(systemOverload, i) / GeneralHelpers.factorial(i));
        }
        return 1 / denominator;
    }
    private void createSequence(int servicesNumber) {
        this.Pk = new float[servicesNumber * 2];
        for (int i = 0; i < this.Pk.length; ++i) {
            if (i < servicesNumber) {
                this.Pk[i] = this.P0 * (float)(Math.pow(systemOverload, i) / GeneralHelpers.factorial(i));
                continue;
            }
            this.Pk[i] = this.P0 * (float)(Math.pow(systemOverload, i) /
                                    (GeneralHelpers.factorial(servicesNumber) * Math.pow(servicesNumber, i - servicesNumber)));
        }
    }

    public float getQueueProbability() {
        return this.P0 * (float)(Math.pow(systemOverload, servicesNumber + 1) /
                            (GeneralHelpers.factorial(servicesNumber) * (servicesNumber - systemOverload)));
    }
    public float getAllNodesBusyProbability() {
        return this.P0 * (float)(Math.pow(systemOverload, servicesNumber) /
                                (GeneralHelpers.factorial(servicesNumber - 1) * (servicesNumber - systemOverload)));
    }
    private float getReusableExpression(float value) {
        return (float)((Math.pow(systemOverload, servicesNumber + 1) * value) /
                (GeneralHelpers.factorial(servicesNumber - 1) * Math.pow(servicesNumber - systemOverload, 2)));
    }
    public float getAverageRequestsNumber() {
        float temp = 0;
        for (int i = 0; i < servicesNumber - 1; ++i) {
            temp += (float)(Math.pow(systemOverload, i) / GeneralHelpers.factorial(i));
        }
        temp *= systemOverload;
        return this.P0 * (temp + getReusableExpression((servicesNumber + 1 - systemOverload)));
    }
    public float getAverageQueueLength() {
        return this.P0 * getReusableExpression(1);
    }
    public float getAverageFreeNodesLength() {
        return servicesNumber - systemOverload;
    }
    public float getAverageBusyNodesLength() {
        return systemOverload;
    }
    public float getAverageWaitingTime() {
        return this.P0 * (float)(Math.pow(systemOverload, servicesNumber) /
                                (
                                        serviceIntensity *
                                        GeneralHelpers.factorial(servicesNumber - 1) *
                                        Math.pow(servicesNumber - systemOverload, 2)
                                ));
    }
    public float getAverageTimeInSystem() {
        return getAverageWaitingTime() + (1 / serviceIntensity);
    }
}

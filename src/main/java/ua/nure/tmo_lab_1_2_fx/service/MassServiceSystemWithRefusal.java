package ua.nure.tmo_lab_1_2_fx.service;

import ua.nure.tmo_lab_1_2_fx.tools.MassServiceSystemHelper;

import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Map;

public class MassServiceSystemWithRefusal {
    PoissonFlow flow;
    int servicesNumber;
    float averageServiceTime;
    float[][] overload;
    float[] waitingTimes;
    HashMap<Float, Integer> serviceNodeOverload;
    int refusalNumber = 0;
    public MassServiceSystemWithRefusal(PoissonFlow flow, int servicesNumber, float averageServiceTime, int defaultOverloadSize) throws InvalidParameterException {
        if (servicesNumber <= 0 || averageServiceTime <= 0) throw new InvalidParameterException();
        this.flow = flow;
        this.servicesNumber = servicesNumber;
        this.averageServiceTime = averageServiceTime;
        this.overload = new float[flow.tSequence.getLength()][servicesNumber];
        if (defaultOverloadSize > 0) {
            for (int i = 0; i < defaultOverloadSize; ++i) {
                this.overload[0][i] = MassServiceSystemHelper.generateXi(averageServiceTime, (float)Math.random());
            }
        }

        modeling();
    }

    private void modeling() {
        waitingTimes = new float[flow.tSequence.getLength()];
        serviceNodeOverload = new HashMap<>();
        float value;
        boolean isInserted;

        for (int i = 0; i < flow.tSequence.getLength() - 1; ++i) {
            waitingTimes[i] = MassServiceSystemHelper.generateXi(averageServiceTime, flow.sequence.getSingle(i));
            value = flow.tSequence.getSingle(i) + waitingTimes[i];
            isInserted = false;
            for (int j = 0; j < servicesNumber; ++j) {
                if (overload[i][j] > flow.tSequence.getSingle(i)) {
                    overload[i + 1][j] = overload[i][j];
                } else {
                    if (!isInserted) {
                        overload[i + 1][j] = value;
                        serviceNodeOverload.put(value, j + 1);
                        isInserted = true;
                    } else {
                        overload[i + 1][j] = 0;
                    }
                }
            }

            if (!isInserted) {
                ++refusalNumber;
                serviceNodeOverload.put(value, -1);
                waitingTimes[i] = -1;
            }
        }

        for (int i = 0; i < flow.tSequence.getLength(); ++i) {
            System.out.printf("%6.4f => ", flow.tSequence.getSingle(i));
            for (int j = 0; j < servicesNumber; ++j) {
                System.out.printf("%6.4f\t", overload[i][j]);
            }
            System.out.println();
        }
    }

    public PoissonFlow getFlow() {
        return flow;
    }

    public float[] getWaitingTimes() {
        return waitingTimes.clone();
    }

    public Map<Float, Integer> getServiceNodeOverload() {
        return serviceNodeOverload;
    }
}

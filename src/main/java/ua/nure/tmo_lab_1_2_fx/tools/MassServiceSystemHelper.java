package ua.nure.tmo_lab_1_2_fx.tools;

public class MassServiceSystemHelper {

    public static float generateXi(float averageServiceTime, float randomRFromFlow) {
        return (float)((-1.0f) * averageServiceTime * Math.log(randomRFromFlow));
    }
}

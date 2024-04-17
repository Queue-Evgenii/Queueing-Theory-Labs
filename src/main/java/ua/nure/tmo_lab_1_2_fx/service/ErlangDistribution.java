package ua.nure.tmo_lab_1_2_fx.service;

public class ErlangDistribution {
    /**
     * System probability of k state
     */
    protected float[] Pk;

    /**
     * System probability of zero state
     */
    float P0;

    protected float flowIntensity;
    protected float serviceIntensity;
    protected float systemOverload;
    protected int servicesNumber;

    public float getPk(int k) {
        if (k >= this.Pk.length) return 0;
        return this.Pk[k];
    }
    public float getP0() {
        return P0;
    }
    public int getPkLength() { return Pk.length; }
    public float getFlowIntensity() {
        return flowIntensity;
    }
    public float getServiceIntensity() {
        return serviceIntensity;
    }
    public float getSystemOverload() {
        return systemOverload;
    }
    public int getServicesNumber() {
        return servicesNumber;
    }
}

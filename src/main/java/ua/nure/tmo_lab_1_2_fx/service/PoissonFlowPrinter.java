package ua.nure.tmo_lab_1_2_fx.service;

public class PoissonFlowPrinter {
    PoissonFlow flow;
    boolean isTruncated;
    public PoissonFlowPrinter(PoissonFlow flow, boolean isTruncated) {
        this.flow = flow;
        this.isTruncated = isTruncated;
    }

    public void printLambda() {
        System.out.println("Lambda = " + flow.getLambda());
    }
    public void printAllSequences() {
        int length = isTruncated ? flow.tSequence.getLength() + 1 : flow.getLength();

        System.out.println("r\t\tz\t\tt");
        for (int i = 0; i < length; ++i) {
            flow.sequence.printSingle(i);
            System.out.print("\t");
            flow.zSequence.printSingle(i);
            System.out.print("\t");
            try {
                flow.tSequence.printSingle(i+1);
            } catch (Exception e) {
                System.out.print("-");
            }
            System.out.print("\n");
        }
    }
}

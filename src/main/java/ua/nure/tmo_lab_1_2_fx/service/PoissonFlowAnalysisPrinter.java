package ua.nure.tmo_lab_1_2_fx.service;

public class PoissonFlowAnalysisPrinter {
    PoissonFlowAnalysis flowAnalysis;
    public PoissonFlowAnalysisPrinter(PoissonFlowAnalysis flowAnalysis) {
        this.flowAnalysis = flowAnalysis;
    }

    public void printTau() {
        System.out.println("Tau = " + this.flowAnalysis.getTau());
    }

    public void printTauSequence() {
        System.out.println("i\tx(tau)");
        for (int i = 0; i < flowAnalysis.tauSequence.getLength(); ++i) {
            System.out.print(i +"\t\t");
            flowAnalysis.tauSequence.printSingle(i);
            System.out.print("\n");
        }
    }

    public void printNkSequence() {
        System.out.println("x(tau)\tn");
        for (int i = 0; i < flowAnalysis.nkSequence.getLength(); ++i) {
            System.out.print(i +"\t\t");
            flowAnalysis.nkSequence.printSingle(i);
            System.out.print("\n");
        }
    }

    public void printModelLambda() {
        System.out.println("Model = " + flowAnalysis.getModelLambda());
    }
}

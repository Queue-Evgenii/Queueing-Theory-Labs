package ua.nure.tmo_lab_1_2_fx;

import javafx.application.Application;
import javafx.stage.Stage;
import ua.nure.tmo_lab_1_2_fx.controls.ErlangChart;
import ua.nure.tmo_lab_1_2_fx.controls.FlowChart;
import ua.nure.tmo_lab_1_2_fx.controls.MassServiceSystemWithRefusalXSL;
import ua.nure.tmo_lab_1_2_fx.models.sequences.TauSequence;
import ua.nure.tmo_lab_1_2_fx.service.*;
import ua.nure.tmo_lab_1_2_fx.tools.GeneralHelpers;

import java.io.IOException;
import java.text.DecimalFormat;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        lab5();
    }

    public static void main(String[] args) {
        launch();
    }

    private final int groupNumber = 2;
    private final int studentNumber = 7;
    private final int T1 = 1;
    private final int T2 = 5;
    private final int intervalCount = 25;
    private final int servicesNumberForThirstLab = 6;
    private final int servicesNumberForFourthLab = 5;
    private final float averageServiceTime = 95;
    private final int defaultOverloadSize = 2;

    public void lab1() {
        PoissonFlow flow = new PoissonFlow(
                GeneralHelpers.calculateLambda(groupNumber, studentNumber, 20, 3),
                studentNumber,
                T1,
                T2,
                intervalCount
        );
        PoissonFlowAnalysis flowAnalysis = new PoissonFlowAnalysis(flow);

        PoissonFlowPrinter flowPrinter = new PoissonFlowPrinter(flow, false);
        flowPrinter.printLambda();
        flowPrinter.printAllSequences();

        PoissonFlowAnalysisPrinter poissonFlowAnalysisPrinter = new PoissonFlowAnalysisPrinter(flowAnalysis);
        poissonFlowAnalysisPrinter.printTau();
        poissonFlowAnalysisPrinter.printTauSequence();
        poissonFlowAnalysisPrinter.printNkSequence();
        poissonFlowAnalysisPrinter.printModelLambda();
    }
    public void lab2() {
        PoissonFlow flow1 =  new PoissonFlow(
                GeneralHelpers.calculateLambda(groupNumber, studentNumber, 28, 3),
                studentNumber,
                T1,
                T2,
                intervalCount
        );
        PoissonFlow flow2 =  new PoissonFlow(
                GeneralHelpers.calculateLambda(groupNumber, studentNumber, 15, 2),
                studentNumber,
                T1,
                T2,
                intervalCount
        );
        System.out.println("Lambda 1: " + flow1.getLambda());
        System.out.println("Lambda 2: " + flow2.getLambda());

        PoissonFlowAnalysis tauFlow1Analysis = new PoissonFlowAnalysis(flow1);
        PoissonFlowAnalysis tauFlow2Analysis = new PoissonFlowAnalysis(flow2);

        TauSequence tauFlow1 = tauFlow1Analysis.getTauSequenceObject();
        TauSequence tauFlow2 = tauFlow2Analysis.getTauSequenceObject();
        TauSequence summaryTauFlow = new TauSequence(tauFlow1, tauFlow2);

        PoissonFlowAnalysis summaryTauFlowAnalysis = new PoissonFlowAnalysis(summaryTauFlow, intervalCount);


        System.out.println("\nN\tx1(tau)\tx2(tau)\tx(tau)");
        for (int i = 1; i < tauFlow1.getLength(); ++i) {
            System.out.print(i+"\t");
            tauFlow1.printSingle(i);
            System.out.print("\t\t");
            tauFlow2.printSingle(i);
            System.out.print("\t\t");
            summaryTauFlow.printSingle(i);
            System.out.print("\n");
        }

        DecimalFormat formatter = new DecimalFormat();
        formatter.setMaximumFractionDigits(4);

        System.out.println("Flow 1: " + tauFlow1Analysis.getModelLambda());
        System.out.println("Flow 2: " + formatter.format(tauFlow2Analysis.getModelLambda()));
        System.out.println("Flow summary: " + summaryTauFlowAnalysis.getModelLambda());

        System.out.println("Average x:" + GeneralHelpers.calculateAverageX(summaryTauFlowAnalysis.getTauSequence()));
        System.out.println("Dispersion:" + GeneralHelpers.calculateDispersion(summaryTauFlowAnalysis.getTauSequence()));

        FlowChart obj1 = new FlowChart(tauFlow1.getSequence(), "Flow 1");
        FlowChart obj2 = new FlowChart(tauFlow2.getSequence(), "Flow 2");
        FlowChart ob2j3 = new FlowChart(summaryTauFlow.getSequence(), "Summary flow");
    }
    public void lab3() {
        ErlangSystem sys = new ErlangSystem(groupNumber, studentNumber, servicesNumberForThirstLab);


        double PN = Math.pow(sys.getRo(), sys.getServicesNumber()) / (GeneralHelpers.factorial(sys.getServicesNumber()) * sys.getSystemSum(sys.getServicesNumber()));

        System.out.println("Лямбда = " + sys.getLambda() + "; Мю = " + sys.getMu() + "; Ро = " + sys.getRo());
        System.out.println("Ймовірність відмови (PN): " + PN);
        System.out.println("Середня кількість зайнятих вузлів: " + sys.getAverageOverload());
        System.out.println("Середня кількість вільних вузлів: " + (sys.getServicesNumber() - sys.getAverageOverload()));
        System.out.println("Відносна пропускна здатність: " + (1 - PN));
        System.out.println("Абсолютна пропускна здатність: " + (1 - PN) * sys.getLambda());
        System.out.println("Коефіцієнт зайнятості вузлів: " + sys.getAverageOverload() / sys.getServicesNumber());

        ErlangChart chart = new ErlangChart(sys);
    }
    public void lab4() {
        PoissonFlow flow = new PoissonFlow(
                GeneralHelpers.calculateLambdaAlt(groupNumber, studentNumber, servicesNumberForFourthLab),
                studentNumber,
                1,
                201
        );

        PoissonFlowPrinter flowPrinter = new PoissonFlowPrinter(flow, true);
        flowPrinter.printLambda();
        flowPrinter.printAllSequences();

        System.out.println(flow.getTSequence().length);
        MassServiceSystemWithRefusal massServiceSystem = new MassServiceSystemWithRefusal(flow, servicesNumberForFourthLab, averageServiceTime, defaultOverloadSize);

        ErlangSystem sys = new ErlangSystem(GeneralHelpers.calculateLambdaAlt(groupNumber, studentNumber, servicesNumberForFourthLab), averageServiceTime, true);
        float val1 = (float)Math.pow(sys.getRo(), servicesNumberForFourthLab) / (float)GeneralHelpers.factorial(servicesNumberForFourthLab);
        float val2 = sys.getSystemSum(servicesNumberForFourthLab);
        float PN = (val1 / val2);

        System.out.println("Лямбда = " + sys.getLambda() + "; Мю = " + sys.getMu() + "; Ро = " + sys.getRo());
        System.out.println(val1 + " " + val2);
        System.out.println("Ймовірність відмови (PN): " + PN);


        MassServiceSystemWithRefusalXSL test = new MassServiceSystemWithRefusalXSL(massServiceSystem);
    }
    public void lab5() {

    }
}
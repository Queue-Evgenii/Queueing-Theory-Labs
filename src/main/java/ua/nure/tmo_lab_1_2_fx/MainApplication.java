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
    private final int servicesNumberForFourthNFifthLab = 5;
    private final float averageServiceTime = 95;
    private final int defaultOverloadSize = 2;

    public void lab1() {
        PoissonFlow flow = new PoissonFlow(
                GeneralHelpers.calculateFlowIntensity(groupNumber, studentNumber, 20, 3),
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
                GeneralHelpers.calculateFlowIntensity(groupNumber, studentNumber, 28, 3),
                studentNumber,
                T1,
                T2,
                intervalCount
        );
        PoissonFlow flow2 =  new PoissonFlow(
                GeneralHelpers.calculateFlowIntensity(groupNumber, studentNumber, 15, 2),
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
        ErlangDistributionFirst sys = new ErlangDistributionFirst(groupNumber, studentNumber, servicesNumberForThirstLab);


        double PN = Math.pow(sys.getSystemOverload(), sys.getServicesNumber()) /
                    (GeneralHelpers.factorial(sys.getServicesNumber()) * sys.getSystemSum(sys.getServicesNumber()));

        System.out.println("Лямбда = " + sys.getFlowIntensity() + "; Мю = " + sys.getServiceIntensity() + "; Ро = " + sys.getSystemOverload());
        System.out.println("Ймовірність відмови (PN): " + PN);
        System.out.println("Середня кількість зайнятих вузлів: " + sys.getAverageOverload());
        System.out.println("Середня кількість вільних вузлів: " + (sys.getServicesNumber() - sys.getAverageOverload()));
        System.out.println("Відносна пропускна здатність: " + (1 - PN));
        System.out.println("Абсолютна пропускна здатність: " + (1 - PN) * sys.getFlowIntensity());
        System.out.println("Коефіцієнт зайнятості вузлів: " + sys.getAverageOverload() / sys.getServicesNumber());

        ErlangChart chart = new ErlangChart(sys);
    }
    public void lab4() {
        PoissonFlow flow = new PoissonFlow(
                GeneralHelpers.calculateFlowIntensityAlt(groupNumber, studentNumber, servicesNumberForFourthNFifthLab),
                studentNumber,
                1,
                201
        );

        PoissonFlowPrinter flowPrinter = new PoissonFlowPrinter(flow, true);
        flowPrinter.printLambda();
        flowPrinter.printAllSequences();

        System.out.println(flow.getTSequence().length);
        MassServiceSystemWithRefusal massServiceSystem = new MassServiceSystemWithRefusal(flow, servicesNumberForFourthNFifthLab, averageServiceTime, defaultOverloadSize);

        ErlangDistributionFirst sys = new ErlangDistributionFirst(GeneralHelpers.calculateFlowIntensityAlt(groupNumber, studentNumber, servicesNumberForFourthNFifthLab), averageServiceTime, true);
        float val1 = (float)Math.pow(sys.getSystemOverload(), servicesNumberForFourthNFifthLab) / (float)GeneralHelpers.factorial(servicesNumberForFourthNFifthLab);
        float val2 = sys.getSystemSum(servicesNumberForFourthNFifthLab);
        float PN = (val1 / val2);

        System.out.println("Лямбда = " + sys.getFlowIntensity() + "; Мю = " + sys.getServiceIntensity() + "; Ро = " + sys.getSystemOverload());
        System.out.println(val1 + " " + val2);
        System.out.println("Ймовірність відмови (PN): " + PN);


        MassServiceSystemWithRefusalXSL test = new MassServiceSystemWithRefusalXSL(massServiceSystem);
    }
    public void lab5() {
        ErlangDistributionSecond sys = new ErlangDistributionSecond(groupNumber, studentNumber, servicesNumberForFourthNFifthLab);

        System.out.println("Інтенсивність потоку (лямбда) = " + sys.getFlowIntensity()
                        + "; інтенсивність обслуговування = " + sys.getServiceIntensity()
                        + "; навантаження системи = " + sys.getSystemOverload()
                        + "; ймовірність Р0 = " + sys.getP0());
        System.out.println("Ймовірність наявності черги (Pk) = " + sys.getQueueProbability());
        System.out.println("Ймовірність зайнятості всіх вузлів = " + sys.getAllNodesBusyProbability());
        System.out.println("Середня кількість вимог в системі = " + sys.getAverageRequestsNumber());
        System.out.println("Середня довжина черги = " + sys.getAverageQueueLength());
        System.out.println("Середня кількість вільних вузлів = " + sys.getAverageFreeNodesLength());
        System.out.println("Середня кількість зайнятих вузлів = " + sys.getAverageBusyNodesLength());
        System.out.println("Середній час очікування = " + sys.getAverageWaitingTime());
        System.out.println("Загальний час перебування вимог у черзі, що надійшли за одиницю часу (теж саме що середня довжина) = " + sys.getAverageQueueLength());
        System.out.println("Середній час перебування вимог у системі = " + sys.getAverageTimeInSystem());
        System.out.println("Сумарний час, що проводять всі вимоги в системі, що надійшли за одиницю часу = " + (sys.getAverageQueueLength() + sys.getSystemOverload()));

        ErlangChart chart = new ErlangChart(sys);
    }
}
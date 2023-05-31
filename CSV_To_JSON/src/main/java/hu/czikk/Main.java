package hu.czikk;

import hu.czikk.input.InputProcess;
import hu.czikk.output.OutputProcess;
import org.apache.commons.lang3.time.StopWatch;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;


public class Main {
    private static final File sitesCSV = new File("src/main/resources/InputFiles_ver3/sites82c.csv");
    private static final File linksCSV = new File("src/main/resources/InputFiles_ver3/links.csv");
    private static final File ocResCSV = new File("src/main/resources/InputFiles_ver3/gen05K82c/gen05K82c_ocres.csv");
    private static final File omsDescCSV = new File("src/main/resources/InputFiles_ver3/gen05K82c/gen05K82c_omsdesc.csv");
    private static final File omsResCSV = new File("src/main/resources/InputFiles_ver3/gen05K82c/gen05K82c_omsres.csv");
    private static final File sitesDescCSV = new File("src/main/resources/InputFiles_ver3/gen05K82c/gen05K82c_nodes.csv");
    private static final File outputFileJSON = new File("src/main/resources/OutputFiles_ver3/gen05K82c_anonim.json");
    private static final File outputFileJSON_perf = new File("src/main/resources/OutputFiles_ver3/gen05K82c_anonim_perf.json");

    public static void main(String[] args) throws IOException, IncorrectFileNameException {
        StopWatch inputWatch = new StopWatch();
        InputProcess inpPro = new InputProcess();
        OutputProcess outPro = new OutputProcess();
        Scanner sc = new Scanner(System.in);
        long inputTime;
        System.out.println("Enter number for InputProcess type \n\n");
        System.out.println("[0.] to start regular InputProcess \n");
        System.out.println("[1.] to start performance InputProcess \n\n");
        int inputNum = sc.nextInt();
        switch (inputNum) {
            case 0 -> {
                System.out.println("Start stopwatch for InputProcess runtime measurement! \n");
                inputWatch.start();
                inpPro.inputProcess(sitesCSV, linksCSV, ocResCSV, omsDescCSV, omsResCSV, sitesDescCSV);
                System.out.println("Stop stopwatch for InputProcess runtime measurement! \n");
                inputWatch.stop();
                System.out.println("InputProcess time elapsed(ms): " + inputWatch.getTime() + "\n");
                inputTime = inputWatch.getTime();
                System.out.println("Reset stopwatch for OutputProcess runtime measurement! \n");
                inputWatch.reset();
                System.out.println("Start stopwatch for OutputProcess runtime measurement!\n");
                inputWatch.start();
                outPro.outputProcess(inpPro, outputFileJSON);
                System.out.println("Stop stopwatch for OutputProcess runtime measurement!\n");
                inputWatch.stop();
                System.out.println("OutputProcess time elapsed(ms): " + inputWatch.getTime() + "\n");
                System.out.println("JoinedProcesses time elapsed(ms): " + (inputTime + inputWatch.getTime()) + "\n");
            }
            case 1 -> {
                System.out.println("Start stopwatch for InputProcess runtime measurement!" + "\n");
                inputWatch.start();
                inpPro.inputProcessPerformanceTest_SITES_LINKS(ocResCSV, omsDescCSV, omsResCSV, sitesDescCSV);
                System.out.println("Stop stopwatch for InputProcess runtime measurement! \n");
                inputWatch.stop();
                System.out.println("InputProcess time elapsed(ms): " + inputWatch.getTime() + "\n");
                inputTime = inputWatch.getTime();
                System.out.println("Reset stopwatch for OutputProcess runtime measurement! \n");
                inputWatch.reset();
                System.out.println("Start stopwatch for OutputProcess runtime measurement!\n");
                inputWatch.start();
                outPro.outputProcess(inpPro, outputFileJSON_perf);
                System.out.println("Stop stopwatch for OutputProcess runtime measurement!\n");
                inputWatch.stop();
                System.out.println("OutputProcess time elapsed(ms): " + inputWatch.getTime() + "\n");
                System.out.println("JoinedProcesses time elapsed(ms): " + (inputTime + inputWatch.getTime()) + "\n");
            }
            default -> System.out.println("Wrong number input!");
        }
    }
}


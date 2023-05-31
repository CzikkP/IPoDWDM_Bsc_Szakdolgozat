package hu.czikk.input;

import com.fasterxml.jackson.core.JsonProcessingException;
import hu.czikk.IncorrectFileNameException;
import hu.czikk.input.data.*;
import hu.czikk.input.handle.CSV_Reader;
import lombok.Data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Data
public class InputProcess {

    private List<Site> sites = new ArrayList<>();
    private List<Link> links = new ArrayList<>();
    private List<OC> OC_arr = new ArrayList<>();
    private List<OMS_Desc> OMS_Desc_arr = new ArrayList<>();
    private List<OMS_Res> OMS_Res_arr = new ArrayList<>();
    private List<Site_Desc> sites_Desc_arr = new ArrayList<>();

    private static final File sitesCSV_inputTest = new File("src/main/resources/OutputFiles_ver3/inputTests_gen05K82c/sites82c_output_test.json");
    private static final File linksCSV_inputTest = new File("src/main/resources/OutputFiles_ver3/inputTests_gen05K82c/links_output_test.json");
    private static final File ocResCSV_inputTest = new File("src/main/resources/OutputFiles_ver3/inputTests_gen05K82c/oc_output_test.json");
    private static final File omsDescCSV_inputTest = new File("src/main/resources/OutputFiles_ver3/inputTests_gen05K82c/oms_desc_output_test.json");
    private static final File omsResCSV_inputTest = new File("src/main/resources/OutputFiles_ver3/inputTests_gen05K82c/oms_res_output_test.json");
    private static final File sitesDescCSV_inputTest = new File("src/main/resources/OutputFiles_ver3/inputTests_gen05K82c/site_desc_output_test.json");

    public void inputProcess(File sitesCSV, File linksCSV, File ocResCSV, File omsDescCSV, File omsResCSV, File sitesDescCSV) throws IOException, IncorrectFileNameException {

        CSV_Reader<Site> siteRead = new CSV_Reader<>(Site.class, sitesCSV);
        CSV_Reader<Link> linkRead = new CSV_Reader<>(Link.class, linksCSV);
        CSV_Reader<OC> ocRead = new CSV_Reader<>(OC.class, ocResCSV);
        CSV_Reader<OMS_Desc> omsDescRead = new CSV_Reader<>(OMS_Desc.class, omsDescCSV);
        CSV_Reader<OMS_Res> omsResRead = new CSV_Reader<>(OMS_Res.class, omsResCSV);
        CSV_Reader<Site_Desc> siteDescRead = new CSV_Reader<>(Site_Desc.class, sitesDescCSV);
        //SITES READ
        sites = siteRead.inputRead();

        //LINKS READ
        links = linkRead.inputRead();

        //OC READ
        OC_arr = ocRead.inputRead();

        //OMS_Desc READ
        OMS_Desc_arr = omsDescRead.inputRead();

        //OMS_Res READ
        OMS_Res_arr = omsResRead.inputRead();

        //Site_Desc READ
        sites_Desc_arr = siteDescRead.inputRead();

        //SITES TEST
        inputTest(sitesCSV_inputTest, siteRead, sites);

        //LINKS TEST
        inputTest(linksCSV_inputTest, linkRead, links);

        //OC TEST
        inputTest(ocResCSV_inputTest, ocRead, OC_arr);

        //OMS_Desc TEST
        inputTest(omsDescCSV_inputTest, omsDescRead, OMS_Desc_arr);

        //OMS_Res TEST
        inputTest(omsResCSV_inputTest, omsResRead, OMS_Res_arr);

        //Site_Desc TEST
        inputTest(sitesDescCSV_inputTest, siteDescRead, sites_Desc_arr);
    }

    public void inputProcessPerformanceTest_SITES_LINKS(File ocResCSV, File omsDescCSV, File omsResCSV, File sitesDescCSV) throws IOException, IncorrectFileNameException {
        File sitesCSV_PerformanceTest = new File("src\\main\\resources\\InputFiles_ver3\\PerformanceTest\\10x\\sites82c_perf.csv");
        File linksCSV_PerformanceTest = new File("src\\main\\resources\\InputFiles_ver3\\PerformanceTest\\10x\\links_perf.csv");
        CSV_Reader<Site> siteReadPerf = new CSV_Reader<>(Site.class, sitesCSV_PerformanceTest);
        CSV_Reader<Link> linkReadPerf = new CSV_Reader<>(Link.class, linksCSV_PerformanceTest);
        CSV_Reader<OC> ocRead = new CSV_Reader<>(OC.class, ocResCSV);
        CSV_Reader<OMS_Desc> omsDescRead = new CSV_Reader<>(OMS_Desc.class, omsDescCSV);
        CSV_Reader<OMS_Res> omsResRead = new CSV_Reader<>(OMS_Res.class, omsResCSV);
        CSV_Reader<Site_Desc> siteDescRead = new CSV_Reader<>(Site_Desc.class, sitesDescCSV);

        //SITES READ
        sites = siteReadPerf.inputRead();

        //LINKS READ
        links = linkReadPerf.inputRead();

        //OC READ
        OC_arr = ocRead.inputRead();

        //OMS_Desc READ
        OMS_Desc_arr = omsDescRead.inputRead();

        //OMS_Res READ
        OMS_Res_arr = omsResRead.inputRead();

        //Site_Desc READ
        sites_Desc_arr = siteDescRead.inputRead();
    }

    private void inputTest(File outputFile, CSV_Reader csvReader, List array) throws IncorrectFileNameException {
        try {
            Files.writeString(Path.of(outputFile.getPath()), csvReader.testInputRead(array));
        } catch (IOException e) {
            throw new IncorrectFileNameException("INPUT_READ_TEST_EXCEPTION =>> Incorrect filename or path to file: " + outputFile, e);
        }

    }
}

package hu.czikk.output;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import hu.czikk.IncorrectFileNameException;
import hu.czikk.input.InputProcess;
import hu.czikk.input.data.*;
import hu.czikk.output.data.link.LinkFeatures;
import hu.czikk.output.data.site.SiteFeatures;
import hu.czikk.output.data.FeatureOutput;
import hu.czikk.output.handle.LinkOutput;
import hu.czikk.output.handle.SiteOutput;
import lombok.Data;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Data
public class OutputProcess {
    private List<SiteFeatures> allSiteFeatures = new ArrayList<>();
    private List<LinkFeatures> allLinkFeatures = new ArrayList<>();
    private FeatureOutput output = new FeatureOutput();
    private List<Object> featuresItemsList = new ArrayList<>();

    public void outputProcess(InputProcess inputProcess, File outputFile) throws JsonProcessingException, IncorrectFileNameException {

        //LINKS PROCESS FOR OUTPUT JSON STRUCTURE
        LinkOutput.linkProcessForOutput(inputProcess, allLinkFeatures);

        //SITES PROCESS FOR OUTPUT JSON STRUCTURE
        SiteOutput.siteProcessForOutput(inputProcess, allLinkFeatures, allSiteFeatures);

        //AGGREGATE LINKS AND SITES INTO FEATURE_OUTPUT OBJECT
        featuresItemsList.addAll(allSiteFeatures);
        featuresItemsList.addAll(allLinkFeatures);

        //SET FINAL ATTRIBUTES FOR FEATURE_OUTPUT OBJECT
        output.setFeatures(featuresItemsList);
        output.setAllSystems(inputProcess.getOC_arr());

        //GENERATE JSON OUTPUT FROM FEATURE_OUTPUT OBJECT
        writeFeaturesToJson(output, outputFile);
    }

    private void writeFeaturesToJson(FeatureOutput output, File outputFile) throws JsonProcessingException, IncorrectFileNameException {
        ObjectMapper jsonOutputMapper = new ObjectMapper();
        jsonOutputMapper.enable(SerializationFeature.INDENT_OUTPUT);
        jsonOutputMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        String jsonOutputString = jsonOutputMapper.writeValueAsString(output);

        try {
            Files.writeString(Path.of(outputFile.getPath()), jsonOutputString);
        } catch (IOException e) {
            throw new IncorrectFileNameException("OUTPUT_WRITE_ERROR =>> No such directory: " + outputFile, e);
        }
    }

    public void outputProcess_UnitTests(InputProcess inputProcess) {
        LinkOutput.linkProcessForOutput(inputProcess, allLinkFeatures);
        SiteOutput.siteProcessForOutput(inputProcess, allLinkFeatures, allSiteFeatures);
        output.setAllSystems(inputProcess.getOC_arr());
    }
}

import hu.czikk.IncorrectFileNameException;
import hu.czikk.Main;
import hu.czikk.input.InputProcess;
import hu.czikk.input.data.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InputProcessTest {
    private static final File sitesCSV = new File("src/main/resources/InputFiles_ver3/sites82c.csv");
    private static final File linksCSV = new File("src/main/resources/InputFiles_ver3/links.csv");
    private static final File ocResCSV = new File("src/main/resources/InputFiles_ver3/gen05K82c/gen05K82c_ocres.csv");
    private static final File omsDescCSV = new File("src/main/resources/InputFiles_ver3/gen05K82c/gen05K82c_omsdesc.csv");
    private static final File omsResCSV = new File("src/main/resources/InputFiles_ver3/gen05K82c/gen05K82c_omsres.csv");
    private static final File sitesDescCSV = new File("src/main/resources/InputFiles_ver3/gen05K82c/gen05K82c_nodes.csv");

    private static final InputProcess inputProcessTest = new InputProcess();

    @BeforeEach
    void initTests() throws IOException, IncorrectFileNameException {
        inputProcessTest.inputProcess(sitesCSV, linksCSV, ocResCSV, omsDescCSV, omsResCSV, sitesDescCSV);
    }

    @Nested
    class InputProcessTest_Sites {
        private int getSiteIndex(String siteID) {
            int index = 0;
            while (index < inputProcessTest.getSites().size()) {
                if (inputProcessTest.getSites().get(index).getId().equals(siteID)) {
                    return index;
                }
                index++;
            }
            return -1;
        }

        @Test
        void inputProcessTest_Site_1() {
            String currentSiteID = "S0191X";
            int currentSiteIndex = getSiteIndex(currentSiteID);
            if (currentSiteIndex != -1) {
                Site site1 = inputProcessTest.getSites().get(currentSiteIndex);
                assertEquals("S0191X", site1.getId());
                assertEquals("Site 0191", site1.getName());
                assertEquals(19.074223, site1.getLongitude());
                assertEquals(47.474288, site1.getLatitude());
                assertEquals(1, site1.getPrimer());
                assertEquals(1, site1.getIsCore());
                assertEquals(1, site1.getIsOverlaySite());
                assertNull(site1.getIsOla());
                assertNull(site1.getIsUnderlay_2());
                assertNull(site1.getIsUnderlay_3());
                assertEquals(1, site1.getIsOverlaySync());
                assertEquals(1600, site1.getBitrate());
                assertEquals(200, site1.getPZone());
            } else {
                fail("SITE_ID [" + currentSiteID + "] NOT FOUND IN THE SEARCH ARRAY");
            }
        }

        @Test
        void inputProcessTest_Site_2() {
            String currentSiteID = "S0236X";
            int currentSiteIndex = getSiteIndex(currentSiteID);
            if (currentSiteIndex != -1) {
                Site site2 = inputProcessTest.getSites().get(currentSiteIndex);
                assertEquals("S0236X", site2.getId());
                assertEquals("Site 0236", site2.getName());
                assertEquals(19.082695, site2.getLongitude());
                assertEquals(47.539905, site2.getLatitude());
                assertEquals(1, site2.getPrimer());
                assertNull(site2.getIsCore());
                assertNull(site2.getIsOverlaySite());
                assertNull(site2.getIsOla());
                assertEquals(1, site2.getIsUnderlay_2());
                assertNull(site2.getIsUnderlay_3());
                assertNull(site2.getIsOverlaySync());
                assertEquals(800, site2.getBitrate());
                assertEquals(200, site2.getPZone());
            } else {
                fail("SITE_ID [" + currentSiteID + "] NOT FOUND IN THE SEARCH ARRAY");
            }
        }

        @Test
        void inputProcessTest_Site_3() {
            String currentSiteID = "S0337X";
            int currentSiteIndex = getSiteIndex(currentSiteID);
            if (currentSiteIndex != -1) {
                Site site3 = inputProcessTest.getSites().get(currentSiteIndex);
                assertEquals("S0337X", site3.getId());
                assertEquals("Site 0337", site3.getName());
                assertEquals(21.728395, site3.getLongitude());
                assertEquals(47.963964, site3.getLatitude());
                assertEquals(42, site3.getPrimer());
                assertNull(site3.getIsCore());
                assertNull(site3.getIsOverlaySite());
                assertEquals(1, site3.getIsOla());
                assertEquals(1, site3.getIsUnderlay_2());
                assertNull(site3.getIsUnderlay_3());
                assertEquals(1, site3.getIsOverlaySync());
                assertEquals(800, site3.getBitrate());
                assertEquals(200, site3.getPZone());
            } else {
                fail("SITE_ID [" + currentSiteID + "] NOT FOUND IN THE SEARCH ARRAY");
            }
        }
    }

    @Nested
    class InputProcessTest_Links {
        private int getLinkIndex(String linkID) {
            int index = 0;

            while (index < inputProcessTest.getLinks().size()) {
                if (inputProcessTest.getLinks().get(index).getId().equals(linkID)) {
                    return index;
                }
                index++;
            }
            return -1;
        }

        @Test
        void inputProcessTest_Link_1() {
            String currentLinkID = "L0001X";
            int currentLinkIndex = getLinkIndex(currentLinkID);
            if (currentLinkIndex != -1) {
                Link link1 = inputProcessTest.getLinks().get(currentLinkIndex);
                assertEquals("L0001X", link1.getId());
                assertEquals("S0136X", link1.getSite1_ID());
                assertEquals("S0161X", link1.getSite2_ID());
                assertEquals("Link 0001", link1.getName());
                assertEquals(27.073, link1.getLength());
                assertEquals(0, link1.getLeased());
            } else {
                fail("LINK_ID [" + currentLinkID + "] NOT FOUND IN THE SEARCH ARRAY");
            }
        }

        @Test
        void inputProcessTest_Link_2() {
            String currentLinkID = "L0048X";
            int currentLinkIndex = getLinkIndex(currentLinkID);
            if (currentLinkIndex != -1) {
                Link link2 = inputProcessTest.getLinks().get(currentLinkIndex);
                assertEquals("L0048X", link2.getId());
                assertEquals("S0349X", link2.getSite1_ID());
                assertEquals("S0354X", link2.getSite2_ID());
                assertEquals("Link 0048", link2.getName());
                assertEquals(20.933, link2.getLength());
                assertEquals(0, link2.getLeased());
            } else {
                fail("LINK_ID [" + currentLinkID + "] NOT FOUND IN THE SEARCH ARRAY");
            }
        }

        @Test
        void inputProcessTest_Link_3() {
            String currentLinkID = "L0057X";
            int currentLinkIndex = getLinkIndex(currentLinkID);
            if (currentLinkIndex != -1) {
                Link link3 = inputProcessTest.getLinks().get(currentLinkIndex);
                assertEquals("L0057X", link3.getId());
                assertEquals("S0109X", link3.getSite1_ID());
                assertEquals("S0108X", link3.getSite2_ID());
                assertEquals("Link 0057", link3.getName());
                assertEquals(51.048, link3.getLength());
                assertEquals(1, link3.getLeased());
            } else {
                fail("LINK_ID [" + currentLinkID + "] NOT FOUND IN THE SEARCH ARRAY");
            }
        }
    }

    @Nested
    class InputProcessTest_OMS_Desc {
        private int getOMS_Desc_Index(Integer omsID) {
            int index = 0;

            while (index < inputProcessTest.getOMS_Desc_arr().size()) {
                if (inputProcessTest.getOMS_Desc_arr().get(index).getId().equals(omsID)) {
                    return index;
                }
                index++;
            }
            return -1;
        }

        //OMS_DESC
        @Test
        void inputProcessTest_OMS_Desc_1() {
            int currentOMSDescID = 0;
            int currentOMSDescIndex = getOMS_Desc_Index(currentOMSDescID);
            if (currentOMSDescIndex != -1) {
                OMS_Desc oms1 = inputProcessTest.getOMS_Desc_arr().get(currentOMSDescIndex);
                assertEquals(0, oms1.getId());
                assertEquals("Site 0191-Site 0193 OMS1", oms1.getName());
                assertEquals("OL", oms1.getLayer());
                assertEquals("S0191X", oms1.getSite1_ID());
                assertEquals("S0193X", oms1.getSite2_ID());
                assertEquals(1, oms1.getHops());
                assertEquals(8.895, oms1.getLength());
                assertEquals(48.895, oms1.getCost());
            } else {
                fail("OMS_ID [" + currentOMSDescID + "] NOT FOUND IN THE SEARCH ARRAY");
            }
        }

        @Test
        void inputProcessTest_OMS_Desc_2() {
            int currentOMSDescID = 105;
            int currentOMSDescIndex = getOMS_Desc_Index(currentOMSDescID);
            if (currentOMSDescIndex != -1) {
                OMS_Desc oms2 = inputProcessTest.getOMS_Desc_arr().get(currentOMSDescIndex);
                assertEquals(105, oms2.getId());
                assertEquals("Site 0174-Site 0257 OMS1", oms2.getName());
                assertEquals("UL", oms2.getLayer());
                assertEquals("S0174X", oms2.getSite1_ID());
                assertEquals("S0257X", oms2.getSite2_ID());
                assertEquals(2, oms2.getHops());
                assertEquals(17.808, oms2.getLength());
                assertEquals(102.574, oms2.getCost());
            } else {
                fail("OMS_ID [" + currentOMSDescID + "] NOT FOUND IN THE SEARCH ARRAY");
            }
        }

        @Test
        void inputProcessTest_OMS_Desc_3() {
            int currentOMSDescID = 500;
            int currentOMSDescIndex = getOMS_Desc_Index(currentOMSDescID);
            if (currentOMSDescIndex != -1) {
                OMS_Desc oms3 = inputProcessTest.getOMS_Desc_arr().get(currentOMSDescIndex);
                assertEquals(500, oms3.getId());
                assertEquals("Site 0328-Site 0339 OMS1", oms3.getName());
                assertEquals("UL", oms3.getLayer());
                assertEquals("S0328X", oms3.getSite1_ID());
                assertEquals("S0339X", oms3.getSite2_ID());
                assertEquals(2, oms3.getHops());
                assertEquals(27.290, oms3.getLength());
                assertEquals(107.290, oms3.getCost());
            } else {
                fail("OMS_ID [" + currentOMSDescID + "] NOT FOUND IN THE SEARCH ARRAY");
            }
        }
    }

    @Nested
    class InputProcessTest_OMS_Res {
        private List<Integer> getOMS_Res_Indexes(Integer omsID) {
            int index = 0;
            List<Integer> omsID_Indexes = new ArrayList<>();
            while (index < inputProcessTest.getOMS_Res_arr().size()) {
                if (inputProcessTest.getOMS_Res_arr().get(index).getOmsID().equals(omsID)) {
                    omsID_Indexes.add(index);
                }
                index++;
            }
            return omsID_Indexes;
        }

        @Test
        void inputProcessTest_OMS_Res() {
            int currentOMSResIndex = 0;
            int currentOMSID = 6;
            List<String> linkIDs = List.of(new String[]{"L0030X", "L0056X", "L0077X", "L0529X", "L0097X"});
            List<String> linkDirs = List.of(new String[]{"BW", "FW", "BW", "FW", "BW"});
            List<Integer> omsID_Indexes = getOMS_Res_Indexes(currentOMSID);

            if (omsID_Indexes.size() != 0) {
                while (currentOMSResIndex < omsID_Indexes.size()) {
                    OMS_Res currentOMS = inputProcessTest.getOMS_Res_arr().get(omsID_Indexes.get(currentOMSResIndex));
                    assertEquals(6, currentOMS.getOmsID());
                    int seq = currentOMSResIndex + 1;
                    assertEquals(seq, currentOMS.getSequence());
                    assertEquals(linkIDs.get(currentOMSResIndex), currentOMS.getLinkID());
                    assertEquals(linkDirs.get(currentOMSResIndex), currentOMS.getLinkDirection());
                    currentOMSResIndex++;
                }
            } else {
                fail("OMS_ID [" + currentOMSID + "] NOT FOUND IN THE SEARCH ARRAY");
            }
        }
    }


    @Nested
    class InputProcessTest_OCs {
        private List<Integer> getOC_Indexes(String ocName) {
            int index = 0;
            List<Integer> ocName_Indexes = new ArrayList<>();
            while (index < inputProcessTest.getOC_arr().size()) {
                if (inputProcessTest.getOC_arr().get(index).getOcName().equals(ocName)) {
                    ocName_Indexes.add(index);
                }
                index++;
            }
            return ocName_Indexes;
        }

        @Test
        void inputProcessTest_OC_1() {
            int currentOC_Index = 0;
            String currentOC_Name = "Site 0112-Site 0022 OC1";
            List<Integer> omsIDs = List.of(new Integer[]{208, 324, 118, 596});
            List<String> omsNames = List.of(new String[]{"Site 0092-Site 0112 OMS1", "Site 0092-Site 0102 OMS1", "Site 0102-Site 0082 OMS1", "Site 0022-Site 0082 OMS1"});
            List<String> omsDirs = List.of(new String[]{"BW", "FW", "FW", "BW"});
            List<Integer> ocIndexes = getOC_Indexes(currentOC_Name);
            if (ocIndexes.size() != 0) {
                while (currentOC_Index < ocIndexes.size()) {
                    OC currentOC = inputProcessTest.getOC_arr().get(ocIndexes.get(currentOC_Index));
                    assertEquals("S0112X", currentOC.getSrcID());
                    assertEquals("S0022X", currentOC.getDstID());
                    assertEquals("Site 0112", currentOC.getSrcName());
                    assertEquals("Site 0022", currentOC.getDstName());
                    assertEquals("Site 0112-Site 0022 OC1", currentOC.getOcName());
                    int hop = currentOC_Index + 1;
                    assertEquals(hop, currentOC.getHop());
                    assertEquals(omsIDs.get(currentOC_Index), currentOC.getOmsID());
                    assertEquals(omsNames.get(currentOC_Index), currentOC.getOmsName());
                    assertEquals(omsDirs.get(currentOC_Index), currentOC.getOmsDirection());
                    assertEquals(1, currentOC.getUlSystem());
                    assertEquals(1, currentOC.getUlSubSystem());
                    currentOC_Index++;
                }
            } else {
                fail("OC_NAME [" + currentOC_Name + "] NOT FOUND IN THE SEARCH ARRAY");
            }
        }

        @Test
        void inputProcessTest_OC_2() {
            int currentOC_Index = 0;
            String currentOC_Name = "Site 0347-Site 0191 OC1";
            List<Integer> omsIDs = List.of(new Integer[]{245, 538, 311, 10});
            List<String> omsNames = List.of(new String[]{"Site 0330-Site 0347 OMS1", "Site 0330-Site 0308 OMS1", "Site 0275-Site 0308 OMS1", "Site 0275-Site 0191 OMS1"});
            List<String> omsDirs = List.of(new String[]{"BW", "FW", "BW", "FW"});
            List<Integer> ocIndexes = getOC_Indexes(currentOC_Name);
            if (ocIndexes.size() != 0) {
                while (currentOC_Index < ocIndexes.size()) {
                    OC currentOC = inputProcessTest.getOC_arr().get(ocIndexes.get(currentOC_Index));
                    assertEquals("S0347X", currentOC.getSrcID());
                    assertEquals("S0191X", currentOC.getDstID());
                    assertEquals("Site 0347", currentOC.getSrcName());
                    assertEquals("Site 0191", currentOC.getDstName());
                    assertEquals("Site 0347-Site 0191 OC1", currentOC.getOcName());
                    int hop = currentOC_Index + 1;
                    assertEquals(hop, currentOC.getHop());
                    assertEquals(omsIDs.get(currentOC_Index), currentOC.getOmsID());
                    assertEquals(omsNames.get(currentOC_Index), currentOC.getOmsName());
                    assertEquals(omsDirs.get(currentOC_Index), currentOC.getOmsDirection());
                    assertEquals(1, currentOC.getUlSystem());
                    assertEquals(8, currentOC.getUlSubSystem());
                    currentOC_Index++;
                }
            } else {
                fail("OC_NAME [" + currentOC_Name + "] NOT FOUND IN THE SEARCH ARRAY");
            }
        }
    }

    @Nested
    class InputProcessTest_Site_Desc {
        private int getSite_Desc_Index(String siteID) {
            int index = 0;
            while (index < inputProcessTest.getSites_Desc_arr().size()) {
                if (inputProcessTest.getSites_Desc_arr().get(index).getId().equals(siteID)) {
                    return index;
                }
                index++;
            }
            return -1;
        }

        @Test
        void inputProcessTest_Site_Desc_1() {
            String currentSiteID = "S0191X";
            int currentSiteIndex = getSite_Desc_Index(currentSiteID);
            if (currentSiteIndex != -1) {
                Site_Desc site1 = inputProcessTest.getSites_Desc_arr().get(currentSiteIndex);
                assertEquals("S0191X", site1.getId());
                assertEquals("Site 0191", site1.getName());
                assertEquals(0, site1.getUlSystem());
                assertEquals(0, site1.getUlSubSystem());
                assertEquals(5, site1.getOlDegree());
                assertEquals(3, site1.getUlDegree());

                assertEquals("Site 0209", site1.getOcCore_A());
                assertEquals("Site 0191", site1.getOcOverlay_A());
                assertEquals(1, site1.getOcHops_A());
                assertEquals(6.075, site1.getOcLength_A());

                assertEquals("Site 0191", site1.getOcCore_B());
                assertEquals("Site 0191", site1.getOcOverlay_B());
                assertEquals(0, site1.getOcHops_B());
                assertEquals(0.000, site1.getOcLength_B());

                assertEquals("", site1.getSyncParent_A());
                assertNull(site1.getSyncHops_A());
                assertNull(site1.getSyncLength_A());

                assertEquals("", site1.getSyncParent_B());
                assertNull(site1.getSyncHops_B());
                assertNull(site1.getSyncLength_B());
            } else {
                fail("SITE_ID [" + currentSiteID + "] NOT FOUND IN THE SEARCH ARRAY");
            }
        }

        @Test
        void inputProcessTest_Site_Desc_2() {
            String currentSiteID = "S0076X";
            int currentSiteIndex = getSite_Desc_Index(currentSiteID);
            if (currentSiteIndex != -1) {
                Site_Desc site2 = inputProcessTest.getSites_Desc_arr().get(currentSiteIndex);
                assertEquals("S0076X", site2.getId());
                assertEquals("Site 0076", site2.getName());
                assertEquals(1, site2.getUlSystem());
                assertEquals(9, site2.getUlSubSystem());
                assertEquals(0, site2.getOlDegree());
                assertEquals(2, site2.getUlDegree());

                assertEquals("Site 0066", site2.getOcCore_A());
                assertEquals("Site 0066", site2.getOcOverlay_A());
                assertEquals(10, site2.getOcHops_A());
                assertEquals(389.788, site2.getOcLength_A());

                assertEquals("Site 0193", site2.getOcCore_B());
                assertEquals("Site 0193", site2.getOcOverlay_B());
                assertEquals(6, site2.getOcHops_B());
                assertEquals(285.101, site2.getOcLength_B());

                assertEquals("S0077X", site2.getSyncParent_A());
                assertEquals(1, site2.getSyncHops_A());
                assertEquals(16.921, site2.getSyncLength_A());
                assertEquals("S0062X", site2.getSyncParent_B());
                assertEquals(6, site2.getSyncHops_B());
                assertEquals(190.558, site2.getSyncLength_B());
            } else {
                fail("SITE_ID [" + currentSiteID + "] NOT FOUND IN THE SEARCH ARRAY");
            }
        }

        @Test
        void inputProcessTest_Site_Desc_3() {
            String currentSiteID = "S0020X";
            int currentSiteIndex = getSite_Desc_Index(currentSiteID);
            if (currentSiteIndex != -1) {
                Site_Desc site3 = inputProcessTest.getSites_Desc_arr().get(currentSiteIndex);
                assertEquals("S0020X", site3.getId());
                assertEquals("Site 0020", site3.getName());
                assertEquals(1, site3.getUlSystem());
                assertEquals(16, site3.getUlSubSystem());
                assertEquals(0, site3.getOlDegree());
                assertEquals(2, site3.getUlDegree());

                assertEquals("Site 0022", site3.getOcCore_A());
                assertEquals("Site 0022", site3.getOcOverlay_A());
                assertEquals(1, site3.getOcHops_A());
                assertEquals(35.129, site3.getOcLength_A());

                assertEquals("Site 0258", site3.getOcCore_B());
                assertEquals("Site 0258", site3.getOcOverlay_B());
                assertEquals(10, site3.getOcHops_B());
                assertEquals(486.251, site3.getOcLength_B());

                assertEquals("S0022X", site3.getSyncParent_A());
                assertEquals(1, site3.getSyncHops_A());
                assertEquals(35.129, site3.getSyncLength_A());
                assertEquals("S0044X", site3.getSyncParent_B());
                assertEquals(10, site3.getSyncHops_B());
                assertEquals(486.251, site3.getSyncLength_B());
            } else {
                fail("SITE_ID [" + currentSiteID + "] NOT FOUND IN THE SEARCH ARRAY");
            }
        }
    }
}

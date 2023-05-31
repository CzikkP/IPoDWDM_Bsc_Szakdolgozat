import hu.czikk.IncorrectFileNameException;
import hu.czikk.input.InputProcess;
import hu.czikk.output.OutputProcess;
import hu.czikk.output.data.link.LinkFeatures;
import hu.czikk.output.data.site.SiteFeatures;
import org.hamcrest.collection.IsMapContaining;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class OutputProcessTest {
    private static final File sitesCSV = new File("src/main/resources/InputFiles_ver3/sites82c.csv");
    private static final File linksCSV = new File("src/main/resources/InputFiles_ver3/links.csv");
    private static final File ocResCSV = new File("src/main/resources/InputFiles_ver3/gen05K82c/gen05K82c_ocres.csv");
    private static final File omsDescCSV = new File("src/main/resources/InputFiles_ver3/gen05K82c/gen05K82c_omsdesc.csv");
    private static final File omsResCSV = new File("src/main/resources/InputFiles_ver3/gen05K82c/gen05K82c_omsres.csv");
    private static final File sitesDescCSV = new File("src/main/resources/InputFiles_ver3/gen05K82c/gen05K82c_nodes.csv");

    private static final InputProcess inputProcessTest = new InputProcess();
    private static final OutputProcess outputProcessTest = new OutputProcess();

    @BeforeEach
    void initTests() throws IOException, IncorrectFileNameException {
        inputProcessTest.inputProcess(sitesCSV, linksCSV, ocResCSV, omsDescCSV, omsResCSV, sitesDescCSV);
        outputProcessTest.outputProcess_UnitTests(inputProcessTest);
    }

    @Nested
    class OutputProcessTest_Sites {
        private int getSiteIndex(String siteName) {
            int index = 0;
            while (index < outputProcessTest.getAllSiteFeatures().size()) {
                if (outputProcessTest.getAllSiteFeatures().get(index).getProperties().getName().equals(siteName)) {
                    return index;
                }
                index++;
            }
            return -1;
        }

        @Test
        void outputProcessTest_Site_1() {
            String currentSiteName = "Site 0191";
            int currentSiteIndex = getSiteIndex(currentSiteName);
            if (currentSiteIndex != -1) {
                SiteFeatures site1 = outputProcessTest.getAllSiteFeatures().get(currentSiteIndex);
                Map<String, List<Integer>> ulSystemsTest = outputProcessTest.getAllSiteFeatures().get(currentSiteIndex).getProperties().getULSystems();
                List<String> aggrTraffic = new ArrayList<>();
                aggrTraffic.addAll(site1.getProperties().getOlTraffic1());
                aggrTraffic.addAll(site1.getProperties().getOlTraffic2());
                aggrTraffic.addAll(site1.getProperties().getUlTraffic1());
                aggrTraffic.addAll(site1.getProperties().getUlTraffic2());
                assertEquals(19.074223, site1.getGeometry().getCoordinates().get(0));
                assertEquals(47.474288, site1.getGeometry().getCoordinates().get(1));
                assertEquals("Point", site1.getGeometry().getType());
                assertThat(ulSystemsTest, IsMapContaining.hasEntry("0", List.of(0)));
                assertThat(ulSystemsTest, IsMapContaining.hasEntry("1", List.of(3, 8, 10, 17, 22, 24, 28, 40)));
                assertThat(ulSystemsTest, IsMapContaining.hasEntry("2", List.of(34, 38)));
                assertThat(ulSystemsTest, IsMapContaining.hasEntry("3", List.of(13, 14)));
                assertEquals(true, site1.getProperties().getBpNode());
                assertEquals(true, site1.getProperties().getExistingEr());
                assertEquals(0, site1.getProperties().getHopNumber());
                assertEquals(0, site1.getProperties().getId());
                assertEquals("Site 0191", site1.getProperties().getName());
                assertEquals(4, site1.getProperties().getUlRole());
                assertEquals(4, site1.getProperties().getOlRole());
                assertThat(aggrTraffic, containsInAnyOrder("0_211", "210_211", "13_210"));
            } else {
                fail("SITE_ID [" + currentSiteName + "] NOT FOUND IN THE SEARCH ARRAY");
            }
        }

        @Test
        void outputProcessTest_Site_2() {
            String currentSiteName = "Site 0113";
            int currentSiteIndex = getSiteIndex(currentSiteName);
            if (currentSiteIndex != -1) {
                SiteFeatures site2 = outputProcessTest.getAllSiteFeatures().get(currentSiteIndex);
                List<String> aggrTraffic = new ArrayList<>();
                aggrTraffic.addAll(site2.getProperties().getOlTraffic1());
                aggrTraffic.addAll(site2.getProperties().getOlTraffic2());
                aggrTraffic.addAll(site2.getProperties().getUlTraffic1());
                aggrTraffic.addAll(site2.getProperties().getUlTraffic2());
                assertEquals(18.818819, site2.getGeometry().getCoordinates().get(0));
                assertEquals(47.099099, site2.getGeometry().getCoordinates().get(1));
                assertEquals("Point", site2.getGeometry().getType());
                assertNull(site2.getProperties().getULSystem());
                assertNull(site2.getProperties().getULSubsystem());
                assertEquals(false, site2.getProperties().getBpNode());
                assertEquals(false, site2.getProperties().getExistingEr());
                assertEquals(0, site2.getProperties().getHopNumber());
                assertEquals(174, site2.getProperties().getId());
                assertEquals("Site 0113", site2.getProperties().getName());
                assertEquals(0, site2.getProperties().getUlRole());
                assertNull(site2.getProperties().getOlRole());
                assertTrue(aggrTraffic.isEmpty());
            } else {
                fail("SITE_ID [" + currentSiteName + "] NOT FOUND IN THE SEARCH ARRAY");
            }
        }
    }

    @Nested
    class OutputProcessTest_Links {
        private int getLinkIndex(String linkName) {
            int index = 0;
            while (index < outputProcessTest.getAllLinkFeatures().size()) {
                if (outputProcessTest.getAllLinkFeatures().get(index).getProperties().getName().equals(linkName)) {
                    return index;
                }
                index++;
            }
            return -1;
        }

        @Test
        void outputProcessTest_Link_1() {
            String currentLinkName = "L0144X";
            int currentLinkIndex = getLinkIndex(currentLinkName);
            if (currentLinkIndex != -1) {
                LinkFeatures link1 = outputProcessTest.getAllLinkFeatures().get(currentLinkIndex);
                assertEquals(19.179179, link1.getGeometry().getCoordinates().get(0).get(0));
                assertEquals(47.547136, link1.getGeometry().getCoordinates().get(0).get(1));
                assertEquals(19.152486, link1.getGeometry().getCoordinates().get(1).get(0));
                assertEquals(47.515927, link1.getGeometry().getCoordinates().get(1).get(1));
                assertEquals("LineString", link1.getGeometry().getType());
                assertEquals(1, link1.getProperties().getULReservation());
                assertEquals(false, link1.getProperties().getOL());
                assertTrue(link1.getProperties().getULSystems().isEmpty());
                assertEquals(0, link1.getProperties().getCapacity());
                assertEquals("Site 0249", link1.getProperties().getEndpoint0());
                assertEquals("Site 0252", link1.getProperties().getEndpoint1());
                assertEquals(244, link1.getProperties().getFromId());
                assertEquals(355, link1.getProperties().getToId());
                assertEquals("244_355", link1.getProperties().getLabel());
                assertEquals("L0144X", link1.getProperties().getName());
                assertEquals(2.542, link1.getProperties().getLength());
                assertEquals(0, link1.getProperties().getType());
            } else {
                fail("SITE_ID [" + currentLinkName + "] NOT FOUND IN THE SEARCH ARRAY");
            }
        }

        @Test
        void outputProcessTest_Link_2() {
            String currentLinkName = "L0517X";
            int currentLinkIndex = getLinkIndex(currentLinkName);
            if (currentLinkIndex != -1) {
                LinkFeatures link2 = outputProcessTest.getAllLinkFeatures().get(currentLinkIndex);
                assertEquals(19.486153, link2.getGeometry().getCoordinates().get(0).get(0));
                assertEquals(47.099099, link2.getGeometry().getCoordinates().get(0).get(1));
                assertEquals(19.272606, link2.getGeometry().getCoordinates().get(1).get(0));
                assertEquals(47.171171, link2.getGeometry().getCoordinates().get(1).get(1));
                assertEquals("LineString", link2.getGeometry().getType());
                assertThat(link2.getProperties().getULSystems(), IsMapContaining.hasEntry("0", List.of(0)));
                assertEquals(2, link2.getProperties().getCapacity());
                assertEquals("Site 0116", link2.getProperties().getEndpoint0());
                assertEquals("Site 0123", link2.getProperties().getEndpoint1());
                assertEquals(221, link2.getProperties().getFromId());
                assertEquals(34, link2.getProperties().getToId());
                assertEquals("221_34", link2.getProperties().getLabel());
                assertEquals("L0517X", link2.getProperties().getName());
                assertEquals(24.59, link2.getProperties().getLength());
                assertEquals(0, link2.getProperties().getType());
            } else {
                fail("SITE_ID [" + currentLinkName + "] NOT FOUND IN THE SEARCH ARRAY");
            }
        }
    }

    @Nested
    class OutputProcessTest_ULSystems {
        @Test
        void outputProcessTest_ULSystems() {
            Map<String, List<Integer>> ulSystemsTest = outputProcessTest.getOutput().getSystems();
            assertThat(ulSystemsTest, IsMapContaining.hasEntry("0", List.of(0)));
            assertThat(ulSystemsTest, IsMapContaining.hasEntry("1", List.of(1, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 15, 16, 17, 18, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 35, 36, 37, 39, 40, 41, 42)));
            assertThat(ulSystemsTest, IsMapContaining.hasEntry("2", List.of(2, 34, 38)));
            assertThat(ulSystemsTest, IsMapContaining.hasEntry("3", List.of(13, 14)));
            assertThat(ulSystemsTest, IsMapContaining.hasEntry("4", List.of(19)));
            assertThat(ulSystemsTest, IsMapContaining.hasEntry("5", List.of(20)));
        }
    }
}

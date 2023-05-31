package hu.czikk.output.handle;

import hu.czikk.input.InputProcess;
import hu.czikk.input.data.Link;
import hu.czikk.input.data.OC;
import hu.czikk.input.data.OMS_Res;
import hu.czikk.input.data.Site;
import hu.czikk.output.data.link.LinkFeatures;
import hu.czikk.output.data.link.LinkGeometry;
import hu.czikk.output.data.link.LinkProperties;
import lombok.Data;

import java.util.*;
import java.util.stream.Collectors;

@Data
public class LinkOutput {

    public static void linkProcessForOutput(InputProcess inputProcess, List<LinkFeatures> featuresItemLinks) {
        for (Link currentLink : inputProcess.getLinks()) {
            LinkProperties linkProps = new LinkProperties();
            List<List<Double>> linkCoords = new ArrayList<>();
            String site1_ID = currentLink.getSite1_ID();
            String site2_ID = currentLink.getSite2_ID();
            linkCoords.add(getSiteCoordsByID(site1_ID, inputProcess.getSites()));
            linkCoords.add(getSiteCoordsByID(site2_ID, inputProcess.getSites()));
            setLinkPropsValues(currentLink, linkProps, site1_ID, site2_ID, inputProcess);
            featuresItemLinks.add(new LinkFeatures(new LinkGeometry(linkCoords, "LineString"), linkProps, "Feature"));
        }
    }

    private static void setLinkPropsValues(Link currentLink, LinkProperties p, String site1_ID, String site2_ID, InputProcess inputProcess) {
        p.setName(currentLink.getId());
        p.setLength(currentLink.getLength());
        p.setType(currentLink.getLeased());
        List<Site> sites = inputProcess.getSites();
        p.setEndpoint0(getSiteNameByID(site1_ID, sites));
        p.setEndpoint1(getSiteNameByID(site2_ID, sites));
        p.setFromId(getSiteIndexByID(site1_ID, sites));
        p.setToId(getSiteIndexByID(site2_ID, sites));
        p.setLabel(p.getFromId().toString() + "_" + p.getToId().toString());
        p.setULSystems(getULsystemsByLinkName(p.getName(), inputProcess));
        p.setOL(getLinkIsOL(currentLink, inputProcess.getSites()));
        Integer omsCount = getOMSCountByLinkID(currentLink.getId(), inputProcess.getOMS_Res_arr());
        p.setCapacity(omsCount); //n * OMS_sub_carriers(384)
        if (p.getULSystems().isEmpty() && p.getOL()) {
            p.setULReservation(0);
        } else {
            p.setULReservation(1);
        }
    }

    private static Map<String, List<Integer>> getULsystemsByLinkName(String linkName, InputProcess inputProcess) {
        //UL_SYSTEMS SEARCH BY LINK_NAME
        List<Integer> oms_IDs = new ArrayList<>();
        Map<String, List<Integer>> ulSystemsMap = new HashMap<>();

        for (OMS_Res oms_res : inputProcess.getOMS_Res_arr()) {
            if (oms_res.getLinkID().equals(linkName)) {
                oms_IDs.add(oms_res.getOmsID());
            }
        }

        //CONVERT OMS_IDS LIST TO MAP
        for (Integer oms_id : oms_IDs) {
            for (OC oc : inputProcess.getOC_arr()) {
                if (oc.getOmsID().equals(oms_id)) {
                    ulSystemsMap.computeIfAbsent(oc.getUlSystem().toString(), i -> new ArrayList<>())
                            .addAll(Arrays.asList(oc.getUlSubSystem()));
                }
            }
        }

        //DELETE DUPLICATES IN MAP
        for (String name : ulSystemsMap.keySet()) {
            List<Integer> withDupes = ulSystemsMap.get(name);
            List<Integer> withoutDupes = withDupes.stream()
                    .distinct()
                    .collect(Collectors.toList());
            ulSystemsMap.put(name, withoutDupes);
        }
        return ulSystemsMap;
    }

    private static String getSiteNameByID(String siteID, List<Site> sites) {
        int index = 0;
        while (index < sites.size()) {
            if (sites.get(index).getId().equals(siteID)) {
                return sites.get(index).getName();
            }
            index++;
        }
        return null;
    }

    private static Integer getSiteIndexByID(String siteID, List<Site> sites) {
        int index = 0;
        while (index < sites.size()) {
            if (sites.get(index).getId().equals(siteID)) {
                return index;
            }
            index++;
        }
        return null;
    }

    private static List<Double> getSiteCoordsByID(String siteID, List<Site> sites) {
        int index = 0;
        while (index < sites.size()) {
            if (sites.get(index).getId().equals(siteID)) {
                List<Double> coords = new ArrayList<>();
                coords.add(sites.get(index).getLongitude());
                coords.add(sites.get(index).getLatitude());
                return coords;
            }
            index++;
        }
        return null;
    }

    private static Integer getOMSCountByLinkID(String linkID, List<OMS_Res> oms_res) {
        int counter = 0;
        for (OMS_Res omsRe : oms_res)
            if (omsRe.getLinkID().equals(linkID))
                counter++;
        return counter;
    }

    private static Boolean getLinkIsOL(Link currentLink, List<Site> sites) {
        for (Site site : sites)
            if ((site.getId().equals(currentLink.getSite1_ID()) || (site.getId().equals(currentLink.getSite2_ID())))
                    && (site.getIsOverlaySite() != null || site.getIsCore() != null)) {
                return true;
            }
        return false;
    }
}

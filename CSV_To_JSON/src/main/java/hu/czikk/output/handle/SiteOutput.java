package hu.czikk.output.handle;

import hu.czikk.input.InputProcess;
import hu.czikk.input.data.*;
import hu.czikk.output.data.link.LinkFeatures;
import hu.czikk.output.data.site.SiteFeatures;
import hu.czikk.output.data.site.SiteGeometry;
import hu.czikk.output.data.site.SiteProperties;
import lombok.Data;

import java.util.*;
import java.util.stream.Collectors;

@Data
public class SiteOutput {

    public static void siteProcessForOutput(InputProcess inputProcess, List<LinkFeatures> allLinkFeatures, List<SiteFeatures> featuresItemsSites) {
        for (int i = 0; i < inputProcess.getSites().size(); i++) {
            SiteProperties siteProps = new SiteProperties();
            List<Double> siteCoords = new ArrayList<>();
            Site currentSite = inputProcess.getSites().get(i);
            siteCoords.add(0, currentSite.getLongitude());
            siteCoords.add(1, currentSite.getLatitude());
            setSitePropsValues(i, currentSite, siteProps, inputProcess, allLinkFeatures);
            featuresItemsSites.add(new SiteFeatures(new SiteGeometry(siteCoords, "Point"), siteProps, "Feature"));
        }
    }

    private static void setSitePropsValues(int currentSiteIndex, Site currentSite, SiteProperties p, InputProcess inputProcess, List<LinkFeatures> allLinkFeatures) {
        List<Integer> tempIntArray = new ArrayList<>();
        List<List<String>> allTraffic = new ArrayList<>();

        p.setName(currentSite.getName());
        p.setId(currentSiteIndex);
        p.setUlRole(getSiteUnderlayRole(currentSite));
        if (p.getUlRole() == 3 || p.getUlRole() == 4) {
            p.setOlRole(getSiteOverlayRole(currentSite));
            p.setULSystems(getULsystemsBySiteName(p.getName(), inputProcess, allLinkFeatures));
        }
        if (p.getUlRole() != 4) {
            p.setULAmplifiers(tempIntArray);
        }
        if (p.getUlRole() == 1) {
            List<Integer> tempSys_SubSys = getSys_SubSys_OCarr(p.getName(), inputProcess.getOC_arr());
            if (tempSys_SubSys.isEmpty()) {
                System.out.println(p);
            }
            p.setULSystem(tempSys_SubSys.get(0));
            p.setULSubsystem(tempSys_SubSys.get(1));
        }

        p.setExistingEr(currentSite.getPZone() != null && currentSite.getBitrate() != null);
        p.setHopNumber(getHopNumber(currentSite, inputProcess.getSites_Desc_arr()));
        p.setBpNode(currentSite.getPrimer().equals(1));

        getAllTraffic(p.getName(), allTraffic, inputProcess, allLinkFeatures);
        p.setUlTraffic1(allTraffic.get(0));
        p.setUlTraffic2(allTraffic.get(1));
        p.setOlTraffic1(allTraffic.get(2));
        p.setOlTraffic2(allTraffic.get(3));

        p.setWlTraffic1(tempIntArray);
        p.setWlTraffic2(tempIntArray);
    }

    private static Map<String, List<Integer>> getULsystemsBySiteName(String siteName, InputProcess inputProcess, List<LinkFeatures> allLinkFeatures) {
        Map<String, List<Integer>> ulSystemsMap = new HashMap<>();

        for (OC oc : inputProcess.getOC_arr()) {
            if (oc.getSrcName().equals(siteName) || oc.getDstName().equals(siteName)) {
                ulSystemsMap.computeIfAbsent(oc.getUlSystem().toString(), i -> new ArrayList<>())
                        .addAll(Arrays.asList(oc.getUlSubSystem()));
            }
        }

        for (String name : ulSystemsMap.keySet()) {
            List<Integer> withDupes = ulSystemsMap.get(name);
            List<Integer> withoutDupes = withDupes.stream()
                    .distinct()
                    .collect(Collectors.toList());
            ulSystemsMap.put(name, withoutDupes);
        }
        return ulSystemsMap;
    }

    static void getAllTraffic(String siteName, List<List<String>> allTraffic, InputProcess inputProcess, List<LinkFeatures> allLinkFeatures) {
        int index = 0;
        int traffic = 1;
        List<OC> OC_arr = inputProcess.getOC_arr();
        List<OMS_Desc> OMS_Desc_arr = inputProcess.getOMS_Desc_arr();
        List<OMS_Res> OMS_Res_arr = inputProcess.getOMS_Res_arr();

        List<String> ul1 = new ArrayList<>();
        List<String> ul2 = new ArrayList<>();
        List<String> ol1 = new ArrayList<>();
        List<String> ol2 = new ArrayList<>();

        allTraffic.add(0, ul1);
        allTraffic.add(1, ul2);
        allTraffic.add(2, ol1);
        allTraffic.add(3, ol2);

        String tmpOcName = OC_arr.get(0).getOcName();

        while (index < OC_arr.size()) {
            String currentOcName = OC_arr.get(index).getOcName();

            if (traffic == 2) {
                if (!currentOcName.equals(tmpOcName)) {
                    traffic = 1;
                }
            } else {
                if (!currentOcName.equals(tmpOcName)) {
                    traffic = 2;
                }
            }
            if (OC_arr.get(index).getSrcName().equals(siteName)) {
                Integer omsID = getOMSidByOMS_name(OC_arr.get(index).getOmsName(), OMS_Desc_arr);
                String layer = getLayerByOMS_id(OC_arr.get(index).getOmsID(), OMS_Desc_arr);

                if (layer.equals("UL") || layer.equals("UB")) {
                    if (traffic == 1) {
                        ul1 = getLabelArrayByOMS_id(omsID, OMS_Res_arr, allLinkFeatures);
                        if (allTraffic.size() == 0)
                            allTraffic.add(0, ul1);
                        else
                            allTraffic.get(0).addAll(ul1);
                    }
                    if (traffic == 2) {
                        ul2 = getLabelArrayByOMS_id(omsID, OMS_Res_arr, allLinkFeatures);
                        if (allTraffic.size() == 0)
                            allTraffic.add(1, ul2);
                        else
                            allTraffic.get(1).addAll(ul2);
                    }
                }
                if (layer.equals("OL")) {
                    if (traffic == 1) {

                        ol1 = getLabelArrayByOMS_id(omsID, OMS_Res_arr, allLinkFeatures);
                        if (allTraffic.size() == 0)
                            allTraffic.add(2, ol1);
                        else
                            allTraffic.get(2).addAll(ol1);
                    }
                    if (traffic == 2) {

                        ol2 = getLabelArrayByOMS_id(omsID, OMS_Res_arr, allLinkFeatures);
                        if (allTraffic.size() == 0)
                            allTraffic.add(3, ol2);
                        else
                            allTraffic.get(3).addAll(ol2);
                    }
                }
            }
            index++;
            tmpOcName = OC_arr.get(index - 1).getOcName();
        }
    }

    private static Integer getSiteUnderlayRole(Site s) {
        if (s.getIsOverlaySite() != null) {
            if (s.getIsCore() != null) {
                return 4;
            }
            return 3;
        }
        if (s.getIsUnderlay_2() != null || s.getIsUnderlay_3() != null) return 1;
        return 0;
    }

    private static Integer getSiteOverlayRole(Site s) {
        if (s.getIsOverlaySite() != null) {
            if (s.getIsCore() != null) {
                return 4;
            }
            return 3;
        }
        return 0;
    }

    private static List<Integer> getSys_SubSys_OCarr(String id, List<OC> OC_arr) {
        int index = 0;
        List<Integer> tmp = new ArrayList<>();
        while (index < OC_arr.size()) {
            if (OC_arr.get(index).getSrcName().equals(id)) {
                tmp.add(OC_arr.get(index).getUlSystem());
                tmp.add(OC_arr.get(index).getUlSubSystem());
                return tmp;
            }
            index++;
        }
        return tmp;
    }

    private static List<Integer> getSys_SubSys_SiteDescArr(String id, List<Site_Desc> site_Desc_arr) {
        int index = 0;
        List<Integer> tmp = new ArrayList<>();
        while (index < site_Desc_arr.size()) {
            if (site_Desc_arr.get(index).getId().equals(id)) {
                tmp.add(site_Desc_arr.get(index).getUlSystem());
                tmp.add(site_Desc_arr.get(index).getUlSubSystem());
                return tmp;
            }
            index++;
        }
        return tmp;
    }

    private static String getLayerByOMS_id(Integer id, List<OMS_Desc> OMS_Desc_arr) {
        int index = 0;
        while (index < OMS_Desc_arr.size()) {
            if (OMS_Desc_arr.get(index).getId().equals(id)) {
                return OMS_Desc_arr.get(index).getLayer();
            }
            index++;
        }
        return "";
    }

    private static Integer getOMSidByOMS_name(String omsName, List<OMS_Desc> OMS_Desc_arr) {
        int index = 0;
        while (index < OMS_Desc_arr.size()) {
            if (OMS_Desc_arr.get(index).getName().equals(omsName)) {
                return OMS_Desc_arr.get(index).getId();
            }
            index++;
        }
        return 0;
    }

    private static List<String> getLabelArrayByOMS_id(Integer id, List<OMS_Res> OMS_Res_arr, List<LinkFeatures> allLinkFeatures) {
        List<String> omsLinks = new ArrayList<>();
        List<String> labels = new ArrayList<>();

        for (OMS_Res oms_res : OMS_Res_arr) {
            if (oms_res.getOmsID().equals(id)) {
                omsLinks.add(oms_res.getLinkID());
            }
        }

        for (String omsLink : omsLinks) {
            for (LinkFeatures allLinkFeature : allLinkFeatures) {
                if (allLinkFeature.getProperties().getName().equals(omsLink)) {
                    labels.add(allLinkFeature.getProperties().getLabel());
                }
            }
        }
        return labels;
    }

    private static Integer getHopNumber(Site currentSite, List<Site_Desc> sites_Desc_arr) {
        for (Site_Desc siteDesc : sites_Desc_arr) {
            if (siteDesc.getId().equals(currentSite.getId())) {
                if (siteDesc.getSyncHops_A() != null) {
                    if (siteDesc.getSyncHops_B() != null) {
                        if (!Objects.equals(siteDesc.getSyncHops_A(), siteDesc.getSyncHops_B())) {
                            return Integer.min(siteDesc.getSyncHops_A(), siteDesc.getSyncHops_B());
                        }
                        return siteDesc.getSyncHops_B(); //CAN return with either sync_hop
                    }
                    return siteDesc.getSyncHops_A(); //syncB is null
                }
            }
        }
        return 0;
    }
}

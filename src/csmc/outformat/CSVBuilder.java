package csmc.outformat;

import csmc.metrics.CKMetric;
import csmc.metrics.MOODMetric;

import java.util.List;

/**
 * Builds csv representation of CK metrics
 */
public class CSVBuilder {
    public static String fromCKMetrics(List<CKMetric> metrics) {
        StringBuilder sb = new StringBuilder();
        sb.append("ClassName,WMC,DIT,NOC,CBO,RFC,LCOM\n");
        metrics.forEach(metric -> {
            sb.append(metric.getClassName()); sb.append(",");
            sb.append(metric.getWmc()); sb.append(",");
            sb.append(metric.getDit()); sb.append(",");
            sb.append(metric.getNoc()); sb.append(",");
            sb.append(metric.getCbo()); sb.append(",");
            sb.append(metric.getRfc()); sb.append(",");
            sb.append(metric.getLcom()); sb.append("\n");
        });
        return sb.toString();
    }

    public static String fromMOODMetric(MOODMetric metric) {
        return "AHF,MHF,AIF,MIF,CF,PF\n" +
                metric.getAhf() + "," +
                metric.getMhf() + "," +
                metric.getAif() + "," +
                metric.getMif() + "," +
                metric.getCf() + "," +
                metric.getPf() + "\n";
    }

}

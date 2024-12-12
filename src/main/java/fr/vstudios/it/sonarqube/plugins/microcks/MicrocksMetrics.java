package fr.vstudios.it.sonarqube.plugins.microcks;


import org.sonar.api.measures.CoreMetrics;
import org.sonar.api.measures.Metric;
import org.sonar.api.measures.Metrics;

import java.util.List;

import static java.util.Arrays.asList;

public class MicrocksMetrics implements Metrics {

    public static final Metric<String> API_AVG_CONFORMANCE = new Metric.Builder("microks_api_avg_conformance", "Microcks API Conformance Index", Metric.ValueType.RATING)
            .setDescription("Microcks API average conformance measurement")
            .setDirection(Metric.DIRECTION_BETTER)
            .setQualitative(true)
            .setDomain(CoreMetrics.DOMAIN_GENERAL)
            .create();

    public static final Metric<String> API_LOWEST_CONFORMANCE = new Metric.Builder("microks_api_lowest_conformance", "Microcks API Conformance Index", Metric.ValueType.RATING)
            .setDescription("Microcks API lowest conformance measurement")
            .setDirection(Metric.DIRECTION_BETTER)
            .setQualitative(true)
            .setDomain(CoreMetrics.DOMAIN_GENERAL)
            .create();

    public static final Metric<Integer> MOCK_COUNT = new Metric.Builder("microks_mock_count", "Number of Microcks Mocks", Metric.ValueType.INT)
            .setDescription("Number of Microcks mocking services")
            .setDirection(Metric.DIRECTION_BETTER)
            .setQualitative(false)
            .setDomain(CoreMetrics.DOMAIN_GENERAL)
            .create();

    @Override
    public List<Metric> getMetrics() {
        return asList(API_AVG_CONFORMANCE, API_LOWEST_CONFORMANCE, MOCK_COUNT);
    }
}

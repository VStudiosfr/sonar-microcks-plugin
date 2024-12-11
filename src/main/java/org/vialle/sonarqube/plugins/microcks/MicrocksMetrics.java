package org.vialle.sonarqube.plugins.microcks;


import org.sonar.api.measures.CoreMetrics;
import org.sonar.api.measures.Metric;
import org.sonar.api.measures.Metrics;

import java.util.List;

import static java.util.Arrays.asList;

public class MicrocksMetrics implements Metrics {

    public static final Metric<Integer> API_CONFORMANCE = new Metric.Builder("microks_api_conformance", "Microcks API Conformance Index", Metric.ValueType.RATING)
            .setDescription("Microcks API Conformance measurement")
            .setDirection(Metric.DIRECTION_BETTER)
            .setQualitative(true)
            .setDomain(CoreMetrics.DOMAIN_RELEASABILITY)
            .create();

    public static final Metric<Integer> MOCK_COUNT = new Metric.Builder("microks_mock_count", "Number of Microcks Mocks", Metric.ValueType.INT)
            .setDescription("Number of Microcks mock services")
            .setDirection(Metric.DIRECTION_BETTER)
            .setQualitative(false)
            .setDomain(CoreMetrics.DOMAIN_RELEASABILITY)
            .create();

    @Override
    public List<Metric> getMetrics() {
        return asList(API_CONFORMANCE, MOCK_COUNT);
    }
}

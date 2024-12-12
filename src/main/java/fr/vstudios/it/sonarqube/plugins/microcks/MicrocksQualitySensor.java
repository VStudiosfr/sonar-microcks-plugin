package fr.vstudios.it.sonarqube.plugins.microcks;


import org.sonar.api.batch.sensor.SensorContext;
import org.sonar.api.batch.sensor.SensorDescriptor;
import org.sonar.api.config.Configuration;
import org.sonar.api.scanner.sensor.ProjectSensor;
import org.sonar.api.utils.log.Logger;
import org.sonar.api.utils.log.Loggers;

import java.util.ArrayList;
import java.util.List;

public class MicrocksQualitySensor implements ProjectSensor {

    private static final Logger LOGGER = Loggers.get(MicrocksQualitySensor.class);

    @Override
    public void describe(SensorDescriptor descriptor) {
        descriptor.name("Microcks Quality Fetcher");
    }

    @Override
    public void execute(SensorContext context) {

        Configuration config = context.config();
        String microcksUrl = config.get("microcks.base.url").orElse(null);
        String idpUrl = config.get("microcks.idp.url").orElse(null);
        String clientId = config.get("microcks.idp.clientid").orElse(null);
        String clientSecret = config.get("microcks.idp.secret").orElse(null);
        String scope = config.get("microcks.idp.scope").orElse(null);

        String artifactName = context.project().key();

        try {
            /*
            MicrocksApiClient fetcher = new MicrocksApiClient(microcksUrl,
                    idpUrl,
                    clientId,
                    clientSecret,
                    scope);
            */
            //List<String> servicesList = fetcher.fetchServicesBasedOnProjectName(artifactName);
            //String apiQuality = fetcher.fetchApiConformanceIndex(servicesList);

            //TODO tmp Mock
            List<String> servicesList = new ArrayList<>();
            String apiAvgQuality = "C";
            String apiLowestQuality = "F";

            int servicesCount = servicesList.size();
            LOGGER.info("Microcks Mock count for artifact {}: {}", artifactName, servicesCount);

            context.<String>newMeasure().forMetric(MicrocksMetrics.API_AVG_CONFORMANCE).withValue(apiAvgQuality).save();
            context.<String>newMeasure().forMetric(MicrocksMetrics.API_LOWEST_CONFORMANCE).withValue(apiLowestQuality).save();
            context.<Integer>newMeasure().forMetric(MicrocksMetrics.MOCK_COUNT).withValue(servicesCount).save();

        } catch (Exception e) {
            LOGGER.error("Error while fetching data from Microcks: ", e);
        }
    }

}

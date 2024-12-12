package fr.vstudios.it.sonarqube.plugins.microcks;


import org.sonar.api.Properties;
import org.sonar.api.Property;
import org.sonar.api.batch.sensor.Sensor;
import org.sonar.api.batch.sensor.SensorContext;
import org.sonar.api.batch.sensor.SensorDescriptor;
import org.sonar.api.config.Configuration;
import org.sonar.api.utils.log.Logger;
import org.sonar.api.utils.log.Loggers;

import java.util.List;


@Properties({
        @Property(
                key = "sonar.microcks.url",
                name = "Microcks URL",
                description = "Base URL of the Microcks instance",
                defaultValue = "http://localhost:8081"
        ),
        @Property(
                key = "sonar.microcks.token",
                name = "Microcks Access Token",
                description = "Authentication token for Microcks API",
                type = org.sonar.api.PropertyType.PASSWORD
        )
})
public class MicrocksQualitySensor implements Sensor {
    private static final Logger LOGGER = Loggers.get(MicrocksQualitySensor.class);


    @Override
    public void describe(SensorDescriptor descriptor) {
        descriptor.name("Microcks Quality Fetcher");
    }

    @Override
    public void execute(SensorContext context) {
        Configuration config = context.config();
        String microcksBaseUrl = config.get("microcks.base.url").orElse(null);
        String artifactName = context.project().key();

        if (microcksBaseUrl == null || artifactName == null) {
            LOGGER.warn("Microcks base URL or artifact name is not configured.");
            return;
        }

        try {

            MicrocksApiClient fetcher = new MicrocksApiClient(microcksBaseUrl);

            List<String> servicesList = fetcher.fetchServicesBasedOnProjectName(artifactName);
            String apiQuality = fetcher.fetchApiConformanceIndex(servicesList);

            int servicesCount = servicesList.size();
            LOGGER.info("Mock count for artifact {}: {}", artifactName, servicesCount);
            LOGGER.info("API Quality for artifact {}: {}", artifactName, apiQuality);

            //context.newMeasure().forMetric(MicrocksMetrics.API_CONFORMANCE).withValue(apiQuality).save();
            //context.newMeasure().forMetric(MicrocksMetrics.MOCK_COUNT).withValue(servicesCount).save();
        } catch (Exception e) {
            LOGGER.error("Error while fetching data from Microcks: ", e);
        }
    }


}

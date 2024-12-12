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
                key = "microcks.base.url",
                name = "Microcks URL",
                description = "Base URL of the Microcks instance.",
                defaultValue = ""
        ),
        @Property(
                key = "microcks.idp.url",
                name = "Microcks IdP URL",
                description = "URL of the OAuth2 IdentityProvider used to be authenticated with Microcks instance " +
                        "with Client Credentials Grant.",
                defaultValue = ""
        ),
        @Property(
                key = "microcks.idp.clientid",
                name = "Microcks IdP URL",
                description = "Microcks IdP Client ID.",
                defaultValue = ""
        ),
        @Property(
                key = "microcks.idp.secret",
                name = "Microcks Access Token",
                description = "Authentication token for Microcks API.",
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
        String microcksUrl = config.get("microcks.base.url").orElse(null);
        String idpUrl = config.get("microcks.idp.url").orElse(null);
        String clientId = config.get("microcks.idp.clientid").orElse(null);
        String clientSecret = config.get("microcks.idp.secret").orElse(null);
        String scope = config.get("microcks.idp.scope").orElse(null);

        String artifactName = context.project().key();

        try {
            MicrocksApiClient fetcher = new MicrocksApiClient(microcksUrl,
                    idpUrl,
                    clientId,
                    clientSecret,
                    scope);

            List<String> servicesList = fetcher.fetchServicesBasedOnProjectName(artifactName);
            String apiQuality = fetcher.fetchApiConformanceIndex(servicesList);

            int servicesCount = servicesList.size();
            LOGGER.info("Mock count for artifact {}: {}", artifactName, servicesCount);
            LOGGER.info("API Quality for artifact {}: {}", artifactName, apiQuality);

            //context.newMeasure().forMetric(MicrocksMetrics.API_AVG_CONFORMANCE).withValue(apiQuality).save();
            //context.newMeasure().forMetric(MicrocksMetrics.API_LOWEST_CONFORMANCE).withValue(apiQuality).save();
            //context.newMeasure().forMetric(MicrocksMetrics.MOCK_COUNT).withValue(servicesCount).save();

        } catch (Exception e) {
            LOGGER.error("Error while fetching data from Microcks: ", e);
        }
    }

}

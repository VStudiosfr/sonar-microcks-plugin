package fr.vstudios.it.sonarqube.plugins.microcks;

import junit.framework.TestCase;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Integration test, you need a Mockserver :)
 */
public class MicrocksApiClientTest extends TestCase {

    public static final String ARTEFACT_TEST_KEY = "fiware-ngs";
    private final static String MICROCKS_URL = "http://localhost:38080";
    private final static String IDP_URL = "http://localhost:18080/realms/microcks";
    private final static String IDP_CLIENTID = "sonarqube";
    private final static String IDP_SECRET = "kaFut69QihR6qXI0apDeJZyK1jlOfhwx";
    private final static String IDP_SCOPE = "any";

    MicrocksApiClient apiClient;

    public void setUp() throws Exception {
        apiClient = new MicrocksApiClient(MICROCKS_URL,
                IDP_URL,
                IDP_CLIENTID,
                IDP_SECRET,
                IDP_SCOPE);
    }

    public void testFetchServicesBasedOnProjectName() throws IOException, ExecutionException, InterruptedException {
        List<String> servicesList = apiClient.fetchServicesBasedOnProjectName(ARTEFACT_TEST_KEY);
        assertNotNull(servicesList);
    }

    public void testFetchApiConformanceIndex() throws IOException, ExecutionException, InterruptedException {
        List<String> servicesList = apiClient.fetchServicesBasedOnProjectName(ARTEFACT_TEST_KEY);
        apiClient.fetchApiConformanceIndex(servicesList);
    }

    public void testGetAccessToken() throws IOException, ExecutionException, InterruptedException {
        String accessToken = apiClient.getAccessToken();
        assertNotNull(accessToken);
    }
}
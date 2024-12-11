package org.vialle.sonarqube.plugins.microcks;

import junit.framework.TestCase;

import java.io.IOException;
import java.util.List;

/**
 * Integration test, you need a Mockserver :)
 */
public class MicrocksApiClientTest extends TestCase {

    public static final String ARTEFACT_TEST_KEY = "fiware-ngs";
    private final static String BASE_URL = "http://localhost:38080";
    MicrocksApiClient apiClient;

    public void setUp() throws Exception {
        apiClient = new MicrocksApiClient(BASE_URL);
    }

    public void testFetchServicesBasedOnProjectName() throws IOException {
        List<String> servicesList = apiClient.fetchServicesBasedOnProjectName(ARTEFACT_TEST_KEY);
        assertNotNull(servicesList);
    }

    public void testFetchApiConformanceIndex() throws IOException {
        List<String> servicesList = apiClient.fetchServicesBasedOnProjectName(ARTEFACT_TEST_KEY);
        apiClient.fetchApiConformanceIndex(servicesList);

    }
}
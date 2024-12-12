package fr.vstudios.it.sonarqube.plugins.microcks;

import com.squareup.okhttp.*;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MicrocksApiClient {

    private final OkHttpClient httpClient = new OkHttpClient();
    private String baseUrl;
    private String clientId;
    private String clientSecret;

    /**
     * Constructor
     *
     * @param url Microcks instance base url
     */
    public MicrocksApiClient(String url) {
         this.baseUrl = url;
    }

    /**
     * Search all the services that are linked with the label "artefact" and the project name declared in SonarQube
     *
     * @param project name of the artefact declared in Sonarqube
     * @return the list of services in Microcks
     * @throws IOException
     */
    public List<String> fetchServicesBasedOnProjectName(String project) throws IOException {

        //Build query
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("artefact", project);
        JSONObject jsonQueryMap = new JSONObject(queryMap);

        //build request
        HttpUrl.Builder urlBuilder = HttpUrl.parse(baseUrl + "/service/search").newBuilder();
        urlBuilder.addQueryParameter("queryMap", jsonQueryMap.toString());

        String url = urlBuilder.build().toString();

        Request request = new Request.Builder().url(url).build();
        Call call = httpClient.newCall(request);
        Response response = call.execute();

        response.body().string();

        return null;
    }


    public String fetchApiConformanceIndex(List<String> servicesList) throws IOException {
        return "A";
    }


}

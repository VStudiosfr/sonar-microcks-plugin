package fr.vstudios.it.sonarqube.plugins.microcks;

import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.builder.api.DefaultApi20;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.oauth.OAuth20Service;
import okhttp3.*;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class MicrocksApiClient {

    private final String microcksUrl;

    private final OAuth20Service oAuth20Service;
    private final OkHttpClient httpClient = new OkHttpClient();

    /**
     * Constructor
     *
     * @param microcksUrl Microcks instance base url
     */
    public MicrocksApiClient(String microcksUrl,
                             String idpUrl,
                             String clientId,
                             String clientSecret,
                             String scope) {
        this.microcksUrl = microcksUrl;

        this.oAuth20Service = new ServiceBuilder(clientId)
                .apiSecret(clientSecret)
                .defaultScope(scope) // replace with desired scope
                .build(new DefaultApi20() {
                    @Override
                    public String getAccessTokenEndpoint() {
                        return idpUrl + "/token";
                    }

                    @Override
                    protected String getAuthorizationBaseUrl() {
                        throw new UnsupportedOperationException(
                                "This API doesn't support a Base URL.");
                    }
                });
    }

    /**
     * Search all the services that are linked with the label "artefact" and the project name declared in SonarQube
     *
     * @param project name of the artefact declared in Sonarqube
     * @return the list of services in Microcks
     * @throws IOException
     */
    public List<String> fetchServicesBasedOnProjectName(String project) throws IOException, ExecutionException, InterruptedException {

        //Build query
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("artefact", project);
        JSONObject jsonQueryMap = new JSONObject(queryMap);

        //build request
        HttpUrl.Builder urlBuilder = HttpUrl.parse(microcksUrl + "/service/search").newBuilder();
        urlBuilder.addQueryParameter("queryMap", jsonQueryMap.toString());

        String url = urlBuilder.build().toString();
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization", "Bearer " + getAccessToken())
                .build();
        Call call = httpClient.newCall(request);
        try {
            Response response = call.execute();

            //TODO pas top
            assert response.body() != null;
            String content = response.body().string();

            //TODO n'importe quoi
            return null;
        } catch (IOException e) {
            //TODO pas top
            e.printStackTrace();
            return null;
        }

    }


    public String fetchApiConformanceIndex(List<String> servicesList) throws IOException {
        return "A";
    }

    public String getAccessToken() throws IOException, ExecutionException, InterruptedException {
        final OAuth2AccessToken accessToken = this.oAuth20Service.getAccessTokenClientCredentialsGrant();
        return accessToken.getRawResponse();
    }


}

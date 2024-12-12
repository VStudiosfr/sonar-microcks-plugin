package fr.vstudios.it.sonarqube.plugins.microcks;



import okhttp3.*;

import java.io.IOException;

public class Oauth2Client {

    // Préparation du client OkHttp
    OkHttpClient client = new OkHttpClient();

    private String identityProviderUrl;
    private String realm;
    private String clientId;
    private String clientSecret;


    public String getAccessToken() {

        // Construction du corps de la requête
        RequestBody formBody = new FormBody.Builder()
                .add("grant_type", "client_credentials")
                .add("client_id", clientId)
                .add("client_secret", clientSecret)
                .build();

        // Création de la requête
        Request request = new Request.Builder()
                .url(identityProviderUrl + "/realms/" + realm + "/protocol/openid-connect/token")
                .post(formBody)
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .build();

        // Exécution de la requête
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Erreur lors de la récupération du token: " + response);
            }

            // Parsing de la réponse
            String responseBody = response.body().string();
            // Vous pouvez utiliser une bibliothèque comme Gson pour parser le JSON
            // Ici, un exemple simple d'extraction du token
            return extractAccessTokenFromResponse(responseBody);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String extractAccessTokenFromResponse(String responseBody) {
        // Implémentation simple d'extraction du token
        // Dans un vrai projet, utilisez un parser JSON comme Gson
        int startIndex = responseBody.indexOf("\"access_token\":\"") + 16;
        int endIndex = responseBody.indexOf("\"", startIndex);
        return responseBody.substring(startIndex, endIndex);
    }


}
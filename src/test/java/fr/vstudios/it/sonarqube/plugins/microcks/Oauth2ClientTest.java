package fr.vstudios.it.sonarqube.plugins.microcks;

import junit.framework.TestCase;


public class Oauth2ClientTest extends TestCase {

    private Oauth2Client oauth2Client;


    public void setUp() throws Exception {
        super.setUp();
        String idpUrl = "https://votre-serveur-keycloak.com";
        String realm = "votre-realm";
        String clientId = "votre-client-id";
        String clientSecret = "votre-client-secret";

        this.oauth2Client = new Oauth2Client(idpUrl, realm, clientId, clientSecret);
    }

    public void testGetAccessToken() {


        String accessToken = getAccessToken(keycloakUrl, realm, clientId, clientSecret);
        System.out.println("Token d'accès : " + accessToken);
    }
}
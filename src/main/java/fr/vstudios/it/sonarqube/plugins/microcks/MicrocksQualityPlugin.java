package fr.vstudios.it.sonarqube.plugins.microcks;

import org.sonar.api.Plugin;
import org.sonar.api.Properties;
import org.sonar.api.Property;


@Properties({
        @Property(
                key = "microcks.base.url",
                name = "Microcks URL",
                description = "Base URL of the Microcks instance.",
                defaultValue = "",
                global = true,
                project = false,
                module = false
        ),
        @Property(
                key = "microcks.idp.url",
                name = "Microcks IdP URL",
                description = "URL of the OAuth2 IdentityProvider used to be authenticated with Microcks instance" +
                        "with Client Credentials Grant.",
                global = true,
                project = false,
                module = false
        ),
        @Property(
                key = "microcks.idp.clientid",
                name = "Microcks IdP Client ID",
                description = "Microcks OAuth2 IdentityProvider Client ID.",
                global = true,
                project = false,
                module = false
        ),
        @Property(
                key = "microcks.idp.scope",
                name = "Microcks IdP Scope",
                description = "Microcks OAuth2 IdentityProvider Scope.",
                defaultValue = "any"
        ),
        @Property(
                key = "microcks.idp.secret",
                name = "Microcks IdP Client Secret",
                description = "Microcks OAuth2 IdentityProvider Client Secret.",
                type = org.sonar.api.PropertyType.PASSWORD
        )
})
public class MicrocksQualityPlugin implements Plugin {

    @Override
    public void define(Context context) {

        // Ajouter les métriques personnalisées
        context.addExtensions(MicrocksMetrics.class, MicrocksQualitySensor.class);
    }
}
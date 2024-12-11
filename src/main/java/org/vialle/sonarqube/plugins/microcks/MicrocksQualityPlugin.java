package org.vialle.sonarqube.plugins.microcks;

import org.sonar.api.Plugin;
import org.sonar.api.measures.Metric;


public class MicrocksQualityPlugin implements Plugin {

    @Override
    public void define(Context context) {
        // Enregistrer le sensor personnalisé
        context.addExtension(MicrocksQualitySensor.class);

        // Ajouter les métriques personnalisées
        context.addExtension(
                new Metric.Builder("microcks_api_quality", "API Quality Score", Metric.ValueType.PERCENT)
                        .setDescription("Score de qualité global des APIs")
                        .setDirection(Metric.DIRECTION_BETTER)
                        .setQualitative(false)
                        .setDomain("API")
                        .create()
        );
    }
}
package fr.vstudios.it.sonarqube.plugins.microcks;

import org.sonar.api.Plugin;

import static org.sonar.api.measures.CoreMetrics.getMetrics;


public class MicrocksQualityPlugin implements Plugin {

    @Override
    public void define(Context context) {
        // Enregistrer le sensor personnalisé
        context.addExtension(MicrocksQualitySensor.class);

        // Ajouter les métriques personnalisées
        context.addExtensions(getMetrics());
    }
}
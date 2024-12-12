package fr.vstudios.it.sonarqube.plugins.microcks;

import org.sonar.api.ce.posttask.PostProjectAnalysisTask;
import org.sonar.api.utils.log.Logger;
import org.sonar.api.utils.log.Loggers;

public class MicrocksQualityPostTask implements PostProjectAnalysisTask {

    private static final Logger LOGGER = Loggers.get(MicrocksQualityPostTask.class);

    @Override
    public void finished(PostProjectAnalysisTask.ProjectAnalysis analysis) {
        LOGGER.info("Analysis completed for project: {}", analysis.getProject().getKey());
    }
}

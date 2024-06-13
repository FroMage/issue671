package rest;

import org.jboss.resteasy.reactive.RestQuery;

import io.quarkiverse.renarde.Controller;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;
import jakarta.inject.Inject;
import jakarta.ws.rs.Path;
import util.ScheduleAI;
import util.ScheduleAI.AITalk;

public class Application extends Controller {
    
    @CheckedTemplate
    public static class Templates {
        public static native TemplateInstance ai(String results);
    }

    @Inject
    ScheduleAI ai;

    @Path("/ai")
    public TemplateInstance ai(@RestQuery String topics){
    	StringBuilder results = new StringBuilder();
    	if(topics != null && !topics.isBlank()) {
    		results.append("Results: ");
    		for (AITalk aiTalk : ai.findTalks(topics)) { // CCE EXCEPTION HERE
        		results.append(aiTalk.id);
        		results.append(", ");
			}
    	}
    	return Templates.ai(results.toString());
    }
}
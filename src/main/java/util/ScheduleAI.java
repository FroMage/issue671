package util;

import java.util.List;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import io.quarkiverse.langchain4j.RegisterAiService;

@RegisterAiService( 
		retrievalAugmentor = ScheduleDocumentRetreiver.class
)
public interface ScheduleAI {

    @SystemMessage("You are a computer science conference organiser") 
    @UserMessage("""
    			I want to find the talks from the conference program that match my interests and constraints.
                Give me the list of talks that match my interests and constraints: {topics}
            """)
    List<AITalk> findTalks(String topics);
    
    public static class AITalk {
    	public String title;
    	public String id;
    }
}
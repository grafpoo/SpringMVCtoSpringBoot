package liveproject.webreport.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import liveproject.webreport.match.Match;
import liveproject.webreport.match.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class MatchLoader implements
        ApplicationListener<ContextRefreshedEvent> {

    @Value("${local.loadfile}")
    private String loadfileResourceName;

    private MatchRepository repository;
    private ApplicationContext appContext;

    @Autowired
    public MatchLoader(MatchRepository repository, ApplicationContext ctx) {
        this.repository = repository;
        this.appContext = ctx;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        Resource resource =
                appContext.getResource(loadfileResourceName);
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Match.Result.class, new MatchResultDeserializer());
        objectMapper.registerModule(module);
        try {
            List<Match> list = objectMapper.readValue(resource.getInputStream(), new TypeReference<List<Match>>() {
            });
            //        list.stream().map(m -> repository.save(m));
            for (Match m : list) {
                if (repository.findByHomeTeamAndAwayTeamAndDate(m.getHomeTeam(), m.getAwayTeam(), m.getDate()).isEmpty())
                    repository.save(m);
            }
        } catch (IOException ioe) {
            throw new RuntimeException("Error initializing database: ", ioe);
        }
 //        log.info("Repo size: "+repository.count());
    }
}

package io.github.aveuiller.bug_reproduction.bean_post_process;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.web.WebAppConfiguration;

@WebAppConfiguration
@ComponentScan(basePackages = {"io.github.aveuiller.bug_reproduction.bean_post_process"})
public class InheritedOnlyConfig {
    @Bean("postProcessedSpringBean")
    public PostProcessedSpringBean postProcessedSpringBean() {
        return new InheritedPostProcessSpringBean();
    }
}

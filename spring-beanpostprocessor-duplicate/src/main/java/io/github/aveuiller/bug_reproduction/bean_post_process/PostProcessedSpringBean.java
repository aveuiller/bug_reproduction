package io.github.aveuiller.bug_reproduction.bean_post_process;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Original Bean is forced as component, so always resolved
 */
@Component("postProcessedSpringBean")
public class PostProcessedSpringBean implements BeanPostProcessor {
    @Autowired
    protected StatefulComponent statefulComponent;

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("Original Method called");
        int myElement = 1;
        statefulComponent.addInt(myElement);
        return myElement;
    }

    public List<Integer> getInitList() {
        return statefulComponent.initList;
    }
}

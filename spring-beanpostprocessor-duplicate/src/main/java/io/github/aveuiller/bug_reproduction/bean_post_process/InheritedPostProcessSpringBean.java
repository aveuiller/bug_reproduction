package io.github.aveuiller.bug_reproduction.bean_post_process;

import org.springframework.beans.BeansException;
import org.springframework.stereotype.Component;

// @Component // You will have 2 initialization by uncommenting this one.
public class InheritedPostProcessSpringBean extends PostProcessedSpringBean {
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("Overridden Method called");
        int myElement = 2;
        statefulComponent.addInt(myElement);
        return myElement;
    }
}

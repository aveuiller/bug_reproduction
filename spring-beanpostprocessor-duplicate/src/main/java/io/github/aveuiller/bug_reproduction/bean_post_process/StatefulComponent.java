package io.github.aveuiller.bug_reproduction.bean_post_process;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StatefulComponent {
    protected List<Integer> initList = new ArrayList<>();

    public void addInt(int i) {
        initList.add(i);
    }

}

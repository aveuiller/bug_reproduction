package io.github.aveuiller.bug_reproduction.bean_post_process;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {InheritedOnlyConfig.class})
public class PostProcessTestCase {

    @Autowired
    @Qualifier("postProcessedSpringBean")
    PostProcessedSpringBean bean;

    @Test
    public void inheritedOnlyCalledTestCase() {
        Assert.assertNotNull(bean);
        Assert.assertTrue(bean instanceof InheritedPostProcessSpringBean);

        List<Integer> initList = bean.getInitList();
        Assert.assertArrayEquals(new Integer[]{2}, initList.toArray(new Integer[0]));
    }
}

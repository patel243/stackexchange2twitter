package org.tweet.meta.component;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.stackexchange.util.TwitterTag;
import org.tweet.spring.TwitterConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TwitterConfig.class })
public class TwitterMaxRtScoreExistsIntegrationTest {

    @Autowired
    private MaxRtRetriever maxRtRetriever;

    // API

    @Test
    public final void whenRetrievingMaxRtScoresForTag_thenFound() {
        for (final TwitterTag tag : TwitterTag.values()) {
            assertNotNull("No maxrt for tag " + tag, maxRtRetriever.maxrtRaw(tag.name()));
        }
    }
}
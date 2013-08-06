package org.tweet.meta.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.classification.spring.ClassificationConfig;
import org.common.spring.CommonPersistenceJPAConfig;
import org.common.spring.CommonServiceConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.keyval.spring.KeyValPersistenceJPAConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.tweet.meta.spring.TwitterMetaConfig;
import org.tweet.meta.spring.TwitterMetaPersistenceJPAConfig;
import org.tweet.spring.TwitterConfig;
import org.tweet.spring.TwitterLiveConfig;
import org.tweet.spring.util.SpringProfileUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {// @formatter:off
    KeyValPersistenceJPAConfig.class, 
    
    CommonPersistenceJPAConfig.class, 
    CommonServiceConfig.class, 
    
    ClassificationConfig.class,
    
    TwitterConfig.class, 
    TwitterLiveConfig.class,
    TwitterMetaPersistenceJPAConfig.class, 
        
    TwitterMetaConfig.class 
}) // @formatter:on
@ActiveProfiles(SpringProfileUtil.LIVE)
public final class UserInteractionServiceLiveTest {

    @Autowired
    private UserInteractionLiveService userInteractionService;

    // tests

    @Test
    public final void whenContextIsBootstrapped_thenNoException() {
        assertNotNull(userInteractionService);
    }

    // no

    @Test
    public final void whenTestingIfUser1ShouldBeInteractedWith_thenNo() {
        assertFalse(userInteractionService.isUserWorthInteractingWith("johnhike"));
    }

    @Test
    public final void whenTestingIfUser2ShouldBeInteractedWith_thenNo() {
        // no retweets
        assertFalse(userInteractionService.isUserWorthInteractingWith("Moz"));
    }

    // yes

    @Test
    public final void whenTestingIfUser1ShouldBeInteractedWith_thenYes() {
        assertTrue(userInteractionService.isUserWorthInteractingWith("russmiles")); // for lisp
    }

    @Test
    public final void whenTestingIfUser2ShouldBeInteractedWith_thenYes() {
        assertTrue(userInteractionService.isUserWorthInteractingWith("petrikainulaine"));
    }

    @Test
    public final void whenTestingIfUser3ShouldBeInteractedWith_thenYes() {
        assertTrue(userInteractionService.isUserWorthInteractingWith("SpringSource"));
    }

    @Test
    public final void whenTestingIfUser4ShouldBeInteractedWith_thenYes() {
        assertTrue(userInteractionService.isUserWorthInteractingWith("skillsmatter"));
    }

    //

}

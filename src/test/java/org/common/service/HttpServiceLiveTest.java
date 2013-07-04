package org.common.service;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.common.spring.CommonContextConfig;
import org.gplus.spring.GplusContextConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { CommonContextConfig.class, GplusContextConfig.class })
public class HttpServiceLiveTest {

    @Autowired
    private HttpService httpService;

    // tests

    @Test
    public final void whenContextIsBootstrapped_thenNoException() {
        //
    }

    @Test
    public final void whenShortenedUriIsUnshortednedBySingleLevelScenario1_thenResultIsCorrect() throws ClientProtocolException, IOException {
        final String unshortenedUrl = httpService.expandSingleLevel("http://t.co/wCD5WnAFGi");
        System.out.println(unshortenedUrl);
        assertNotNull(unshortenedUrl);
        assertThat(unshortenedUrl, not(containsString("t.co")));
    }

    @Test
    public final void whenStandardUriIsUnshortednedBySingleLevelScenario1_thenResultIsCorrect() throws ClientProtocolException, IOException {
        final String url = "http://www.yahoo.com";
        final String unshortenedUrl = httpService.expandSingleLevel(url);
        System.out.println(unshortenedUrl);
        assertNotNull(unshortenedUrl);
        assertThat(unshortenedUrl, equalTo(url));
    }

    @Test
    public final void whenShortenedUriIsUnshortednedScenario1_thenResultIsCorrect() throws ClientProtocolException, IOException {
        final String unshortenedUrl = httpService.expand("http://t.co/wCD5WnAFGi");
        System.out.println(unshortenedUrl);
        assertNotNull(unshortenedUrl);
        assertFalse(httpService.isKnownShortenedUrl(unshortenedUrl));
    }

    @Test
    public final void givenUrlIsInvalid_whenExpanding_thenNoExceptions() throws IOException {
        httpService.expand("http://www.marketwatch.com/enf/rss.asp?guid={3B615536-E289-11E2-ACAD-002128040CF6}");
    }

    @Test
    public final void givenUrlUnshortened_whenVerifyingIfUrlIsHomepage_thenResultIsCorrect() throws ClientProtocolException, IOException {
        final String candidateUrl = httpService.expand("http://bit.ly/N7vAX");
        System.out.println(candidateUrl);
        assertTrue(httpService.isHomepageUrl(candidateUrl));
    }

    @Test
    public final void givenUrlUnshortened_whenVerifyingIfUrlIsHomepageScenario1_thenResultIsCorrect() throws ClientProtocolException, IOException {
        assertTrue(httpService.isHomepageUrl("http://www.google.com"));
    }

    @Test
    public final void givenUrlUnshortened_whenVerifyingIfUrlIsHomepageScenario2_thenResultIsCorrect() throws ClientProtocolException, IOException {
        assertTrue(httpService.isHomepageUrl("http://www.blog.something.org"));
    }

    @Test
    public final void givenUrlUnshortened_whenVerifyingIfUrlIsHomepageScenario3_thenResultIsCorrect() throws ClientProtocolException, IOException {
        assertTrue(httpService.isHomepageUrl("http://www.yahoo.com/"));
    }

    // util

}

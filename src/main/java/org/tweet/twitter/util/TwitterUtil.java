package org.tweet.twitter.util;

import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.classification.util.ClassificationSettings;
import org.common.service.LinkService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

public final class TwitterUtil {
    final static Logger logger = LoggerFactory.getLogger(TwitterUtil.class);

    public final static Splitter splitter = Splitter.on(' ').omitEmptyStrings().trimResults(); // if this would include more chars, then recreating the tweet would not be exact
    public final static Joiner joiner = Joiner.on(' ');

    final static List<String> bannedContainsKeywords = Lists.newArrayList(// @formatter:off
        "buy", "discount", 
        "freelance", "job", "consulting", "hire", "hiring", "careers", 
        // "need", // for now, still in the maybe pile  
        "football", "exclusive",
        "dumb", 
        "gift", "highheels",
        "djmix", "housemusic",
        "escort", "escorts", "xxx", "porn", "fuck", "boobs", "breastfeeding", 
        "islamic", "islam", "muslim", "muslims", "pakistan", "egypt", "syria", "jewish", "jew",
        "followback"
    );// @formatter:on
    final static List<String> bannedContainsKeywordsMaybe = Lists.newArrayList(// @formatter:off
        // "buy", // was here, I'm sufficiently convinced that it's not good 
        "#deal", "#deals", // new - including this with the hashcode here - all of them should be validly rejected - if they are - move to the bannedContainsKeywords
        "need", // gathering some more data for need
        "wife",
        "killed",
        "trial", // Amazing #SEO tool will help you achieve top #google positions with no effort. 7days trial available!http://bit.ly/1cAnMcc
        "dumb", "dumber", 
        "dance", 
        "cheep", // trying it out
        "lucky", 
        "fpl", // fantasy player league
        "deals", "deal", 
        "win", "promo", // new
        "snake", // python snake...yes, it happened
        "kurd", "kurds", "afganistan", "palestinians", // other political stuff
        "$3.99", "$2.99", "$1.99", "$0.99", 
        "thugs" // new
    );// @formatter:on

    /**
     * These are special cases that are OK <br/>
     * - <b>ex</b>: `killed it` is a special case for `killed` that is OK
     */
    final static List<String> acceptedContainsKeywordsOverrides = Lists.newArrayList(// @formatter:off
        "killed it", 
        "win-win", "win/win"
    );// @formatter:on
    final static List<String> bannedStartsWithExprs = Lists.newArrayList(// @formatter:off
        "photo: "
    );// @formatter:on
    final static List<String> bannedExpressions = Lists.newArrayList(// @formatter:off
        "web developer", "web developers", 
        "application engineer", "application engineers", 
        "python developer", "java developer", "php developer", "clojure developer", "c# developer", "c++ developer", 
        "backend developer", "back end developer", "frontend developer", "front end developer", "fullstack developer", "full stack developer", 
        "on strike", 
        "for sale", 
        "win a ", "to win", "win one", // win
        "i need", "we need", "need help", "need someone", 
        "music video"
    ); // @formatter:on
    final static List<String> bannedExpressionsMaybe = Lists.newArrayList(// @formatter:off
        "rt if" 
    ); // @formatter:on

    final static List<String> bannedRegExes = Lists.newArrayList(// @formatter:off
        "Get (.)* on Amazon.*", // Get 42% off Secrets of the #JavaScript Ninja on Amazon http://amzn.to/12kkaUn @jeresig
        "I'm broadcasting .* on .*",  // I'm broadcasting #LIVE on #HangWith for #iPhone! Come Hang w/souljaboy! http://bit.ly/hangwsocial
        "Follow us on (Linkedin|Twitter|G+) .*", // Follow us on Linkedin - http://linkd.in/V4Fxa5  #Android #iOS #PS3 #Xbox360 #Apps #GameDev #IDRTG #Video #Game #Developer
        ".*R(T|t)[ .!@\\-].*R(T|t)([ .!@\\-]|\\Z).*", // 2 RTs
        ".*(?i)FREE[ .!@\\-].*R(T|t)([ .!@\\-]|\\Z).*",  // Free ... RT
        ".*(f|F)ollow (&|and|AND) R(T|t).*", // Follow & RT
        ".*R(T|t) .* (f|F)ollow(ed)? .*", // RT this if you want me to follow you
        ".*\\d(\\d)?% (o|O)ff.*", // 97% Off
        "(?i).*follow @.*"
    ); // @formatter:on

    final static List<String> bannedRegExesMaybe = Lists.newArrayList(// @formatter:off
        // 
    ); // @formatter:on

    final static List<String> bannedTwitterUsers = Lists.newArrayList(// @formatter:off
        "blogginginside", // in German - https://twitter.com/blogginginside
        "ulohjobs",  // jobs
        "heyyouapp"// crap
        // , 
        // "BigDataExpo", "CloudExpo" //temporary
    ); // @formatter:on

    private TwitterUtil() {
        throw new AssertionError();
    }

    // API

    /**
     * Verifies that: <br/>
     * - the text has <b>no link</b> <br/>
     * - the text has the <b>correct length</b> <br/>
     */
    public static boolean isTweetTextWithoutLinkValid(final String text) {
        final int linkNoInTweet = new LinkService().extractUrls(text).size();
        if (linkNoInTweet > 1) {
            return false;
        }

        return text.length() <= 122;
    }

    /**
     * Verifies that: <br/>
     * - the text has the <b>correct length</b> <br/>
     * - <b>note</b>: there is something about shortened urls - 20 to 18 characters - experimenting with 142 (from 140)
     */
    public static boolean isTweetTextWithLinkValid(final String fullTweet) {
        return fullTweet.length() <= 142;
    }

    /**
     * - <b>local</b> <br/>
     */
    public static boolean isUserBannedFromRetweeting(final String username) {
        return bannedTwitterUsers.contains(username);
    }

    // retweet logic

    public static String extractTweetFromRt(final String fullTweet) {
        // \A - anchor - matches before start of text block
        final String resultWhenRtIsStart = fullTweet.replaceAll("\\ART @[a-zA-Z0-9_]+ ?[:-] ?", "");
        if (!resultWhenRtIsStart.equals(fullTweet)) {
            return resultWhenRtIsStart;
        }

        final String resultWhenRtIsEnd = fullTweet.replaceAll(" ?[:-]? ?RT @[a-zA-Z0-9_]+ ?\\Z", "");
        if (!resultWhenRtIsEnd.equals(fullTweet)) {
            return resultWhenRtIsEnd;
        }

        return resultWhenRtIsStart;
    }

    /**
     * - note: only for a very special case of tweet - the retweet mention (which I am not sure really happens without the added `RT @`)
     */
    public static String extractOriginalUserFromRt(final String textOfRetweet) {
        final Pattern pattern = Pattern.compile("@[a-zA-Z0-9_]*");
        final Matcher matcher = pattern.matcher(textOfRetweet);
        if (matcher.find()) {
            final String user = matcher.group(0);
            return user.substring(1);
        }

        return null;
    }

    // utils

    static int countWordsToHash(final Iterable<String> tokens, final List<String> lowerCaseWordsToHash) {
        int countWordsToHash = 0;
        for (final String token : tokens) {
            if (lowerCaseWordsToHash.contains(token.toLowerCase())) {
                countWordsToHash++;
            }
        }

        return countWordsToHash;
    }

    /**
     * - <b>local</b> <br/>
     * Tweet can be banned by: <br/>
     * - expression (multiple words) <br/>
     * - single word - contains <br/>
     * - single word - starts with <br/>
     * - regular expression - matches <br/>
    */
    public static boolean isTweetBanned(final String originalTweet) {
        // by expression
        final String textLowerCase = originalTweet.toLowerCase();

        for (final String bannedExpressionMaybe : bannedExpressionsMaybe) {
            if (textLowerCase.contains(bannedExpressionMaybe)) {
                logger.error("1 - Rejecting the following tweet because a token matches the maybe banned expression={}; tweet=\n{}", bannedExpressionMaybe, originalTweet);
                return true;
            }
        }

        for (final String bannedExpression : bannedExpressions) {
            if (textLowerCase.contains(bannedExpression)) {
                logger.debug("Rejecting the following tweet because a token matches the banned expression={}; tweet=\n{}", bannedExpression, originalTweet);
                return true;
            }
        }

        final List<String> tweetTokens = Lists.newArrayList(Splitter.on(CharMatcher.anyOf(ClassificationSettings.TWEET_TOKENIZER + "#")).split(originalTweet));

        // by contains keyword - maybe
        if (isRejectedByContainsKeywordMaybe(tweetTokens, originalTweet)) {
            return true;
        }

        // by contains keyword
        for (final String tweetToken : tweetTokens) {
            if (TwitterUtil.bannedContainsKeywords.contains(tweetToken.toLowerCase())) {
                logger.debug("Rejecting the following tweet because a token matches one of the banned keywords: token= {}; tweet= \n{}", tweetToken, originalTweet);
                return true;
            }
        }

        // by starts with keyword
        for (final String bannedStartsWith : bannedStartsWithExprs) {
            if (originalTweet.startsWith(bannedStartsWith)) {
                logger.debug("Rejecting the following tweet because it starts with= {}; tweet= \n{}", bannedStartsWith, originalTweet);
                return true;
            }
        }

        // by regex
        if (isRejectedByBannedRegexExpressions(originalTweet)) {
            return true;
        }

        return false;
    }

    static boolean isRejectedByContainsKeywordMaybe(final List<String> tweetTokens, final String originalTweet) {
        for (final String tweetToken : tweetTokens) {
            if (TwitterUtil.bannedContainsKeywordsMaybe.contains(tweetToken.toLowerCase())) {
                // first - check if there are any overrides
                if (overrideFoundForContainsKeywords(originalTweet)) {
                    continue;
                }

                // try catch to at least get the stack
                try {
                    throw new IllegalStateException("I need the full stack - maybe keywords rejection");
                } catch (final Exception ex) {
                    logger.error("2 - Rejecting the following tweet because a token matches one of the banned maybe keywords: token= " + tweetToken + "; tweet= \n" + originalTweet);
                    logger.debug("Rejecting the following tweet because a token matches one of the banned maybe keywords (go to debug for the whole stack): token= " + tweetToken + "; tweet= \n" + originalTweet, ex);
                }
                return true;
            }
        }

        return false;
    }

    private static boolean overrideFoundForContainsKeywords(final String originalTweet) {
        for (final String override : acceptedContainsKeywordsOverrides) {
            if (originalTweet.toLowerCase().contains(override)) {
                // was error - confirmed OK - moving down
                logger.debug("Found override= " + override + "; in tweet= \n" + originalTweet);
                return true;
            }
        }

        return false;
    }

    /**
     * - <b>local</b> <br/>
     */
    static boolean isRejectedByBannedRegexExpressions(final String text) {
        for (final String bannedRegExMaybe : bannedRegExesMaybe) {
            if (text.matches(bannedRegExMaybe)) {
                logger.error("new - Rejecting by regular expression (maybe)=  " + bannedRegExMaybe + "; text= \n" + text);
                return true;
            }
        }
        for (final String bannedRegEx : bannedRegExes) {
            if (text.matches(bannedRegEx)) {
                return true;
            }
        }

        return false;
    }

    // tweet - break

    /**
     * - returns null if there the text doesn't contain a single link
     */
    public static Pair<String, String> breakByUrl(final String originalTweet) {
        final Set<String> extractedUrls = new LinkService().extractUrls(originalTweet);
        if (extractedUrls.size() != 1) {
            return null;
        }
        final String mainUrl = extractedUrls.iterator().next();
        final int indexOfMainUrl = originalTweet.indexOf(mainUrl);
        final String before = originalTweet.substring(0, indexOfMainUrl);
        final String after = originalTweet.substring(indexOfMainUrl + mainUrl.length());
        return new ImmutablePair<String, String>(before, after);
    }

    public static String extractLargerPart(final String originalTweet) {
        final Pair<String, String> beforeAndAfter = breakByUrl(originalTweet);
        if (beforeAndAfter == null) {
            return originalTweet;
        }
        if (beforeAndAfter.getLeft().length() > beforeAndAfter.getRight().length()) {
            return beforeAndAfter.getLeft();
        } else {
            return beforeAndAfter.getRight();
        }
    }

}

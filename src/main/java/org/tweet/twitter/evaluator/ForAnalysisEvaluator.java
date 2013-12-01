package org.tweet.twitter.evaluator;

import java.util.List;

import org.tweet.twitter.util.ErrorUtil;
import org.tweet.twitter.util.TwitterUtil.ForAnalysis;

public class ForAnalysisEvaluator extends AbstractEvaluator {

    public ForAnalysisEvaluator() {
        super();
    }

    // API

    @Override
    public final boolean isRejectedByBannedRegexExpressions(final String tweet) {
        configureForRegexExpression(ForAnalysis.acceptedRegExes, ForAnalysis.bannedRegExesMaybe, ForAnalysis.bannedRegExes, "analysis-generic", ErrorUtil.bannedRegExesMaybeErrors);
        return super.isRejectedByBannedRegexExpressions(tweet);
    }

    @Override
    public final boolean isRejectedByContainsKeywordMaybe(final List<String> tweetTokens, final String tweet) {
        configureForContainsKeyword(ForAnalysis.bannedContainsKeywordsMaybe, ForAnalysis.acceptedContainsKeywordsOverrides, ErrorUtil.bannedContainsMaybeErrorsForAnalysis);
        return super.isRejectedByContainsKeywordMaybe(tweetTokens, tweet);
    }

    @Override
    public final boolean isTweetBanned(final String originalTweet) {
        configure(ForAnalysis.bannedExpressionsMaybe, ForAnalysis.bannedExpressions, ForAnalysis.bannedContainsKeywords, ForAnalysis.bannedStartsWithExprs);
        return super.isTweetBanned(originalTweet);
    }

}

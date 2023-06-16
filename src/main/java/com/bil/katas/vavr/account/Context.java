package com.bil.katas.vavr.account;

class Context {

    public final User user;
    public String accountId;
    public String twitterToken;
    public String tweetUrl;

    public Context(User user) {
        this.user = user;
    }

    public Context(User user, String accountId) {
        this.user = user;
        this.accountId = accountId;
    }

    public Context(User user, String accountId, String twitterToken) {
        this.user = user;
        this.accountId = accountId;
        this.twitterToken = twitterToken;
    }

    public Context(User user, String accountId, String twitterToken, String tweetUrl) {
        this.user = user;
        this.accountId = accountId;
        this.twitterToken = twitterToken;
        this.tweetUrl = tweetUrl;
    }

    public Context withAccountId(String accountId) {
        return new Context(user, accountId);
    }

    public Context withToken(String twitterToken) {
        return new Context(user, accountId, twitterToken);
    }

    public Context withTweetUrl(String tweetUrl) {
        return new Context(user, accountId, twitterToken, tweetUrl);

    }
}

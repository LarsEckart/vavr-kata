package com.bil.katas.vavr.account;

import io.vavr.control.Try;

import java.util.UUID;

public class AccountService {

    private final UserService userService;
    private final TwitterService twitterService;
    private final BusinessLogger businessLogger;

    public AccountService(UserService userService, TwitterService twitterService, BusinessLogger businessLogger) {
        this.userService = userService;
        this.twitterService = twitterService;
        this.businessLogger = businessLogger;
    }

    public String register(UUID id) {
        return Try.of(() -> findUser(id))
                .map(this::register)
                .map(this::authenticate)
                .map(this::tweet)
                .onSuccess(c -> saveAndLog(id, c))
                .map(c -> c.tweetUrl)
                .onFailure(ex -> logFailure(id, ex))
                .getOrElse(() -> null);
    }

    private Context findUser(UUID id) {
        return new Context(this.userService.findById(id));
    }

    private void logFailure(UUID id, Throwable ex) {
        this.businessLogger.logFailureRegister(id, ex);
    }

    private void saveAndLog(UUID id, Context c) {
        this.userService.updateTwitterAccountId(id, c.accountId);
        businessLogger.logSuccessRegister(id);
    }

    private Context register(Context c) {
        return c.withAccountId(this.twitterService.register(c.user.getEmail(), c.user.getName()));
    }

    private Context tweet(Context c) {
        return c.withTweetUrl(twitterService.tweet(c.twitterToken, "Hello I am " + c.user.getName()));
    }

    public Context authenticate(Context c) {
        return c.withToken(this.twitterService.authenticate(c.user.getEmail(), c.user.getPassword()));
    }
}

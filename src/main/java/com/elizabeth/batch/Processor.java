package com.elizabeth.batch;

import com.elizabeth.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import java.math.BigDecimal;

public class Processor implements ItemProcessor<User, User> {

    private static final Logger log = LoggerFactory.getLogger(Processor.class);

    public User process(User user) throws Exception {
        final Long userId = user.getUserId();
        final String firstName = user.getFirstName().toUpperCase();
        final BigDecimal accountBalance = user.getAccountBalance();

        final User transformedUser = new User(userId, firstName, accountBalance);
        log.info("Converting (" + user + ") into (" + transformedUser +")");

        return transformedUser;
    }
}

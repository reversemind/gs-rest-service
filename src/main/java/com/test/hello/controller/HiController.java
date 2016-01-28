package com.test.hello.controller;

import com.test.hello.Constants;
import com.test.hello.domain.Hi;
import com.test.hello.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.VndErrors;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping(value = Constants.API, produces = MediaType.APPLICATION_JSON_VALUE)
public class HiController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @Autowired
    AccountRepository accountRepository;

    @RequestMapping("/hi")
//    public Hi hi(Principal principal, @RequestParam(value="name", defaultValue="World") String name) {
        public Hi hi(@AuthenticationPrincipal User principal, @RequestParam(value="name", defaultValue="World") String name) {
        String userId = principal.getUsername();

        System.out.println("principal:" + principal);

        System.out.println("userId: " + userId);
        return new Hi(counter.incrementAndGet(),
                String.format(template, name));
    }

    private void validateUser(String userId) {
        this.accountRepository.findByUsername(userId).orElseThrow(
                () -> new UserNotFoundException(userId));
    }


    @SuppressWarnings("serial")
    public class UserNotFoundException extends RuntimeException {

        public UserNotFoundException(String userId) {
            super("could not find user '" + userId + "'.");
        }
    }

}
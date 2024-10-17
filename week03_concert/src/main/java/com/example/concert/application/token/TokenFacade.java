package com.example.concert.application.token;

import com.example.concert.domain.token.Token;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class TokenFacade {

    private final TokenService tokenService;

    public Token issue(Long userId) {
        return this.tokenService.issue(userId);
    }

    public Token check(Long userId, String tokenString) {
        Token token = this.tokenService.check(tokenString);
        token.validateUser(userId);
        return token;
    }
}

package by.it_academy.calorie_diary.services;

import by.it_academy.calorie_diary.entity.User;
import by.it_academy.calorie_diary.entity.UserStatus;
import by.it_academy.calorie_diary.entity.VerificationToken;
import by.it_academy.calorie_diary.repository.IVerificationTokenRepository;
import by.it_academy.calorie_diary.services.api.IVerificationTokenService;
import by.it_academy.calorie_diary.services.exception.VerificationUserException;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.security.crypto.keygen.BytesKeyGenerator;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class VerificationTokenService implements IVerificationTokenService {
    private static final BytesKeyGenerator DEFAULT_TOKEN_GENERATOR = KeyGenerators.secureRandom(15);
    private static final Charset US_ASCII = StandardCharsets.US_ASCII;
    private final IVerificationTokenRepository verificationTokenRepository;
    private static final String TOKEN_NOT_FOUND_EXCEPTION = "Specified token is not found";
    private static final String TOKEN_IS_EXPIRED_EXCEPTION = "Token is expired";
    private static final String USER_ACTIVE_EXCEPTION = "User is already active";

    public VerificationTokenService(IVerificationTokenRepository verificationTokenRepository) {
        this.verificationTokenRepository = verificationTokenRepository;
    }

    @Override
    public VerificationToken saveToken(User user) {
        VerificationToken verificationToken = generateToken(user);
        return verificationTokenRepository.save(verificationToken);
    }

    @Override
    public VerificationToken findByToken(String token) {
        return verificationTokenRepository.findByToken(token).orElseThrow(() ->
                new EntityNotFoundException(TOKEN_NOT_FOUND_EXCEPTION));
    }

    @Override
    public boolean validateVerificationToken(String token) {
        VerificationToken verificationToken = findByToken(token);
        validateTokenByExpirationDate(verificationToken);
        validateTokenByUserStatus(verificationToken);
        return true;
    }

    @Override
    public void removeToken(String token) {
        verificationTokenRepository.deleteByToken(token);
    }

    private void validateTokenByExpirationDate(VerificationToken verificationToken) {
        if (verificationToken.getExpirationDate().isBefore(LocalDateTime.now())) {
            throw new VerificationUserException(TOKEN_IS_EXPIRED_EXCEPTION);
        }
    }

    private void validateTokenByUserStatus(VerificationToken verificationToken) {
        User user = verificationToken.getUser();
        if (user.getStatus().equals(UserStatus.ACTIVATED)) {
            throw new VerificationUserException(USER_ACTIVE_EXCEPTION);
        }
    }

    private VerificationToken generateToken(User user) {
        String tokenValue = new String(Base64.encodeBase64URLSafe(DEFAULT_TOKEN_GENERATOR.generateKey()), US_ASCII);
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime accessExpiration = now.plusDays(5);
        return VerificationToken.builder()
                .id(UUID.randomUUID())
                .token(tokenValue)
                .user(user)
                .expirationDate(accessExpiration)
                .build();
    }
}

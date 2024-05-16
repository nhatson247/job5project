package com.fpt.job5project.service.impl;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fpt.job5project.dto.AuthenticationDTO;
import com.fpt.job5project.dto.IntrospectDTO;
import com.fpt.job5project.dto.LogoutDTO;
import com.fpt.job5project.dto.RefreshDTO;
import com.fpt.job5project.entity.InvalidatedToken;
import com.fpt.job5project.entity.User;
import com.fpt.job5project.exception.AppException;
import com.fpt.job5project.exception.ErrorCode;
import com.fpt.job5project.repository.EmployerRepository;
import com.fpt.job5project.repository.InvalidatedTokenResponsitory;
import com.fpt.job5project.repository.UserRepository;
import com.fpt.job5project.service.IAuthenticationService;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

import lombok.RequiredArgsConstructor;
import lombok.experimental.NonFinal;

@Service
@RequiredArgsConstructor

public class AuthenticationServiceImpl implements IAuthenticationService {

        @Autowired
        private UserRepository userRepository;

        @Autowired
        private EmployerRepository employerRepository;

        @Autowired

        private InvalidatedTokenResponsitory invalidatedTokenResponsitory;

        @NonFinal
        @Value("${com.nimbusds.jwt.signerKey}")
        protected String singerKey;

        // TO DO: Kiểm tra token
        public IntrospectDTO introspect(IntrospectDTO request)
                        throws JOSEException, ParseException {

                var token = request.getToken();
                boolean isValid = true;

                // Trả kết quả
                // try {
                // verifyToken(token, false);

                // } catch (Exception e) {
                // isValid = false;
                // }

                verifyToken(token, false);

                return IntrospectDTO.builder()
                                .valid(isValid)
                                .build();
        }

        // TO DO: Đăng nhập
        public AuthenticationDTO authenticate(AuthenticationDTO request) {

                PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(5);
                var user = userRepository.findByUserName(request.getUserName())
                                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

                boolean authenticated = passwordEncoder.matches(request.getPassword(),
                                user.getPassword());

                if (!authenticated)
                        throw new AppException(ErrorCode.USER_INVALID);

                // Employer employer = employerRepository.findByUser(user);
                // if (employer != null && !employer.isApproved()) {
                // throw new AppException(ErrorCode.EMPLOYER_NOT_APPROVED);
                // }

                var token = generateToken(user);

                return AuthenticationDTO.builder()
                                .token(token)
                                .authenticated(true)
                                .build();
        }

        // TO DO: Đăng xuất
        public void logout(LogoutDTO request) throws JOSEException, ParseException {
                // lấy token đã kiểm tra ở verifyToken
                var signToken = verifyToken(request.getToken(), true);

                // tạo ra 1 biến để lưu trữ
                String jit = signToken.getJWTClaimsSet().getJWTID();
                Date expiryTime = signToken.getJWTClaimsSet().getExpirationTime();

                InvalidatedToken invalidatedToken = InvalidatedToken.builder()
                                .id(jit)
                                .expiryTime(expiryTime)
                                .build();

                invalidatedTokenResponsitory.save(invalidatedToken);
        }

        // TO DO: Resert token
        public AuthenticationDTO refreshToken(RefreshDTO request) throws ParseException, JOSEException {
                var signedJWT = verifyToken(request.getToken(), true);

                var jit = signedJWT.getJWTClaimsSet().getJWTID();
                var expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();

                InvalidatedToken invalidatedToken = InvalidatedToken.builder().id(jit).expiryTime(expiryTime).build();

                invalidatedTokenResponsitory.save(invalidatedToken);

                var username = signedJWT.getJWTClaimsSet().getSubject();

                var user = userRepository.findByUserName(username)
                                .orElseThrow(() -> new AppException(ErrorCode.UNAUTHENTICATED));

                var token = generateToken(user);

                return AuthenticationDTO.builder()
                                .token(token)
                                .authenticated(true)
                                .build();
        }

        // TO DO: Đăng ký token
        public String generateToken(User user) {
                // Tạo token với thuật toán HS512
                JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

                // Body
                JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                                .subject(user.getUserName())
                                .issuer("son.com")
                                .issueTime(new Date())
                                .expirationTime(new Date(

                                                Instant.now().plus(4, ChronoUnit.HOURS).toEpochMilli()))
                                .jwtID(UUID.randomUUID().toString())
                                .claim("scope", user.getRole())
                                .build();

                Payload payload = new Payload(jwtClaimsSet.toJSONObject());

                JWSObject jwsObject = new JWSObject(header, payload);

                try {
                        jwsObject.sign(new MACSigner(singerKey.getBytes()));
                        return jwsObject.serialize();
                } catch (JOSEException e) {
                        throw new RuntimeException(e);
                }
        }

        private SignedJWT verifyToken(String token, boolean isRefresh) throws JOSEException, ParseException {
                JWSVerifier verifier = new MACVerifier(singerKey.getBytes());

                SignedJWT signedJWT = SignedJWT.parse(token);

                Date expiryTime = (isRefresh)
                                ? new Date(signedJWT.getJWTClaimsSet().getIssueTime()
                                                .toInstant().plus(4, ChronoUnit.HOURS).toEpochMilli())
                                : signedJWT.getJWTClaimsSet().getExpirationTime();

                var verified = signedJWT.verify(verifier);

                if (!(verified && expiryTime.after(new Date()))) {
                        throw new AuthenticationException("Token expired") {
                        };
                }

                if (invalidatedTokenResponsitory.existsById(signedJWT.getJWTClaimsSet().getJWTID()))
                        throw new AppException(ErrorCode.UNAUTHENTICATED);

                return signedJWT;
        }

}

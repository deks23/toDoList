package pl.damian.training.toDoList.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.damian.training.toDoList.exceptions.PasswordInvalidException;
import pl.damian.training.toDoList.model.User;
import pl.damian.training.toDoList.repository.RoleRepository;
import pl.damian.training.toDoList.repository.UserRepository;
import pl.damian.training.toDoList.model.Role;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import io.jsonwebtoken.*;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    String SECRET = "PASSWORD";
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Set <Role> roles = new HashSet<>();
        roles.add(roleRepository.findById(Long.valueOf(1)).get());
        user.setRoles(roles);
       /* user.setRoles(new HashSet<>(roleRepository.findAll()));*/
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Role findRoleById(int id){
        return roleRepository.findById(Long.valueOf(id)).get();
    }

    @Override
    public Role findRoleByName(String name) {
        return roleRepository.findByName(name).get();
    }

    @Override
    public String restLogin(String username, String password) throws PasswordInvalidException {
        return createJWT(userRepository.findByUsername(username).getId().toString(), username, password, 1000000000L);
    }

    private String createJWT(String id, String issuer, String password, long ttlMillis)  throws PasswordInvalidException{

        User user = userRepository.findByUsername(issuer);
        if(!bCryptPasswordEncoder.matches(password, user.getPassword()))
            throw new PasswordInvalidException();

        //The JWT signature algorithm we will be using to sign the token
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //We will sign our JWT with our ApiKey secret
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        //Let's set the JWT Claims
        JwtBuilder builder = Jwts.builder().setId(id)
                .setIssuedAt(now)
                .setIssuer(issuer)
                .signWith(signatureAlgorithm, signingKey);

        //if it has been specified, let's add the expiration
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }

        //Builds the JWT and serializes it to a compact, URL-safe string
        return builder.compact();
    }

    public Claims parseJWT(String jwt) {
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET))
                .parseClaimsJws(jwt).getBody();
        return claims;
    }


}
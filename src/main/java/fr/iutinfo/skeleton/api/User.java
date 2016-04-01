package fr.iutinfo.skeleton.api;

import com.google.common.base.Charsets;
import com.google.common.hash.Hasher;
import com.google.common.hash.Hashing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.Principal;
import java.security.SecureRandom;

public class User implements Principal {
    final static Logger logger = LoggerFactory.getLogger(User.class);

    private String name;
    private int id = 0;
    private String email;
    private Role role;
    private String password;
    private String passwdHash;
    private String salt;

    private static User anonymous = new User(-1, "Anonymous", Role.CUISINIER);

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public User(int id, String name, Role role) {
        this.id = id;
        this.name = name;
        this.role = role;
    }

    public User() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setPassword(String password) {
        passwdHash = buildHash(password, getSalt());
        this.password = password;
    }

    public String getPassword () {
        return this.password;
    }

    private String buildHash(String password, String s) {
        Hasher hasher = Hashing.md5().newHasher();
        hasher.putString(password + s, Charsets.UTF_8);
        return hasher.hash().toString();
    }

    public boolean isGoodPassword(String password) {
        if (isAnonymous()) {
            return false;
        }
        String hash = buildHash(password, getSalt());
        return hash.equals(getPasswdHash());
    }

    public String getPasswdHash() {
        return passwdHash;
    }

    public void setPasswdHash(String passwdHash) {
        this.passwdHash = passwdHash;
    }

    @Override
    public boolean equals(Object arg) {
        if (getClass() != arg.getClass())
            return false;
        User user = (User) arg;
        return name.equals(user.name)  && email.equals(user.email) && passwdHash.equals(user.getPasswdHash()) && salt.equals((user.getSalt()));
    }

    @Override
    public String toString() {
        return id + ": "+ name + " <" + email + ">";
    }
    
    public String getSalt() {
        if (salt == null) {
            salt = generateSalt();
        }
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    private String generateSalt() {
        SecureRandom random = new SecureRandom();
        Hasher hasher = Hashing.md5().newHasher();
        hasher.putLong(random.nextLong());
        return hasher.hash().toString();
    }

    public void resetPasswordHash() {
        if (password != null && ! password.isEmpty()) {
            setPassword(getPassword());
        }
    }

    public boolean isInUserGroup(){
        return ! (id == anonymous.getId());
    }

    public static User getAnonymousUser() {
        return anonymous ;
    }

    public boolean isAnonymous() {
        return this.getId() == getAnonymousUser().getId();
    }
}

package mordyasov;

import java.util.*;

public class UserSber {

    private String username;
    private String email;
    private byte[] passwordHash;

    public UserSber(String username, String email) {
        this.username = username;
        this.email = email;
        this.passwordHash = username.getBytes();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserSber userSber = (UserSber) o;
        return Objects.equals(username, userSber.username) && Objects.equals(email, userSber.email) && Arrays.equals(passwordHash, userSber.passwordHash);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(username, email);
        result = 31 * result + Arrays.hashCode(passwordHash);
        return result;
    }

    @Override
    public String toString() {
        return username + ":" + email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public byte[] getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(byte[] passwordHash) {
        this.passwordHash = passwordHash;
    }
}

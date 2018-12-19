package pt.ipleiria.estg.dei.amsi.fixbyte.modelo;

public class User {

    private long id;
    private String username;
    private String password_hash;
    private String email;

    public User(long id, String username, String password_hash, String email) {
        this.id = id;
        this.username = username;
        this.password_hash = password_hash;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword_hash() {
        return password_hash;
    }

    public void setPassword_hash(String password_hash) {
        this.password_hash = password_hash;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

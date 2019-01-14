package pt.ipleiria.estg.dei.amsi.fixbyte.modelo;

public class User {

    private long id;
    private String username;
    private String email;
    private String token;
    private String userNomeProprio;
    private String userApelido;
    private String userMorada;
    private String userDataNasc;

    public User(long id, String username, String email, String token, String userNomeProprio, String userApelido, String userMorada, String userDataNasc) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.token = token;
        this.userNomeProprio = userNomeProprio;
        this.userApelido = userApelido;
        this.userMorada = userMorada;
        this.userDataNasc = userDataNasc;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserNomeProprio() {
        return userNomeProprio;
    }

    public void setUserNomeProprio(String userNomeProprio) {
        this.userNomeProprio = userNomeProprio;
    }

    public String getUserApelido() {
        return userApelido;
    }

    public void setUserApelido(String userApelido) {
        this.userApelido = userApelido;
    }

    public String getUserMorada() {
        return userMorada;
    }

    public void setUserMorada(String userMorada) {
        this.userMorada = userMorada;
    }

    public String getUserDataNasc() {
        return userDataNasc;
    }

    public void setUserDataNasc(String userDataNasc) {
        this.userDataNasc = userDataNasc;
    }
}

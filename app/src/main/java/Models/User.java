package Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by jcapasix on 20/06/18.
 */

public class User {

    @SerializedName("_id")
    private String id;

    @SerializedName("username")
    private String username;

    @SerializedName("token")
    private String token;


    public User(String id, String username, String token) {
            this.id = id;
        this.username = username;
        this.token = token;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

package Request;

import com.google.gson.annotations.SerializedName;

import Models.Cultivo;

/**
 * Created by jcapasix on 25/06/18.
 */

public class RequestRegister {
    @SerializedName("username")
    private String username;

    @SerializedName("password")
    private String password;

    @SerializedName("admin")
    private Boolean admin;


    public RequestRegister(String username, String password, Boolean admin) {
        this.username = username;
        this.password = password;
        this.admin = admin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

}

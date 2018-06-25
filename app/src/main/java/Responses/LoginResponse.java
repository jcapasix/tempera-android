package Responses;

import com.google.gson.annotations.SerializedName;

import Models.User;

/**
 * Created by jcapasix on 20/06/18.
 */

public class LoginResponse {

    @SerializedName("success")
    private Boolean success;

    @SerializedName("user")
    private User user;

    public LoginResponse(User user) {
        this.user = user;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

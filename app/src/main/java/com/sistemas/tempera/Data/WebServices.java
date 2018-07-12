package com.sistemas.tempera.Data;

import com.sistemas.tempera.Models.Login;
import com.sistemas.tempera.Request.RequestCultivo;
import com.sistemas.tempera.Request.RequestCultivoEntity;
import com.sistemas.tempera.Request.RequestRegister;
import com.sistemas.tempera.Responses.BasicResponse;
import com.sistemas.tempera.Responses.CultivoResponse;
import com.sistemas.tempera.Responses.CultivosResponse;
import com.sistemas.tempera.Responses.LoginResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;


public interface WebServices {

    @Headers("Content-Type: application/json")
    @POST(Router.URL_LOGIN)
    Call<LoginResponse> login(@Body Login data);

    @POST(Router.URL_REGISTER)
    Call<BasicResponse> register(@Body RequestRegister data);

    @POST(Router.URL_CULTIVOS)
    Call<CultivosResponse> cultivos();

    @POST(Router.URL_CREATE_CULTIVO)
    Call<BasicResponse>createCultivo(@Body RequestCultivoEntity request);

    @POST(Router.URL_UPDATE_CULTIVO)
    Call<BasicResponse>updateCultivo(@Body RequestCultivo request);

    @FormUrlEncoded
    @POST(Router.URL_DELETE_CULTIVO)
    Call<BasicResponse>deleteCultivo(@Field("id") String id);

    @FormUrlEncoded
    @POST(Router.URL_ACTIVE_CULTIVO)
    Call<BasicResponse>activeCultivo(@Field("id") String id);

    @FormUrlEncoded
    @POST(Router.URL_GET_CULTIVO_ACTIVE)
    Call<CultivoResponse> getActiveCultivo();

}

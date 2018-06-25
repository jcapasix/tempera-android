package Data;

import Models.Cultivo;
import Models.Login;
import Request.RequestCultivo;
import Request.RequestCultivoEntity;
import Responses.BasicResponse;
import Responses.CultivoResponse;
import Responses.LoginResponse;
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

    @POST(Router.URL_CULTIVOS)
    Call<CultivoResponse> cultivos();

    @POST(Router.URL_CREATE_CULTIVO)
    Call<BasicResponse>createCultivo(@Body RequestCultivoEntity request);

    @POST(Router.URL_UPDATE_CULTIVO)
    Call<BasicResponse>updateCultivo(@Body RequestCultivo request);

    @FormUrlEncoded
    @POST(Router.URL_DELETE_CULTIVO)
    Call<BasicResponse>deleteCultivo(@Field("id") String id);

}

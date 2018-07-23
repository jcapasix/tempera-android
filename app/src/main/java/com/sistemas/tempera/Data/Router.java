package com.sistemas.tempera.Data;

/**
 * Created by jcapasix on 20/06/18.
 */

public class Router {

    //public static final String ROOT_URL = "http://localhost:3000/";

    public static final String API_NAME = "api/";
    public static final String MODULE = "auth/";

    public static final String URL_LOGIN = API_NAME + MODULE + "login/";
    public static final String URL_REGISTER = API_NAME + MODULE + "signup/";

    public static final String URL_CULTIVOS = API_NAME + "cultivos/";

    public static final String URL_UPDATE_CULTIVO = API_NAME + "cultivos/update/";
    public static final String URL_DELETE_CULTIVO = API_NAME + "cultivos/delete/";
    public static final String URL_ACTIVE_CULTIVO = API_NAME + "cultivos/active/";
    public static final String URL_CREATE_CULTIVO = API_NAME + "cultivo/";

    public static final String URL_GET_CULTIVO_ACTIVE = API_NAME + "cultivo/active/";

    public static final String URL_REPORTES = API_NAME + "reportes/";

    public static final String URL_TEMP = API_NAME + "temp/";

}

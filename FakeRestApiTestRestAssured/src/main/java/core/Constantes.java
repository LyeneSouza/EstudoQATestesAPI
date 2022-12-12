package core;

import io.restassured.http.ContentType;

public interface Constantes {

    String APP_BASE_URL = "https://fakerestapi.azurewebsites.net";
    Integer APP_PORT = 443;
    String APP_BASE_PATH = "/api/v1";

    ContentType APP_CONTENT_TYPE = ContentType.JSON;

    Long MAX_TIMEOUT = 5000L;
}

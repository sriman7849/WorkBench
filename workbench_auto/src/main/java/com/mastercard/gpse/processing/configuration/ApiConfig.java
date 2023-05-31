package com.mastercard.gpse.processing.configuration;

import com.mastercard.developer.oauth.OAuth;
import com.mastercard.developer.utils.AuthenticationUtils;
import com.mastercard.gpse.processing.fileoperations.TestProperties;
import com.mastercard.gpse.processing.constants.ApiConstants;
import com.mastercard.gpse.processing.utils.DateUtility;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Cookies;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;
import java.util.HashMap;
import java.util.Map;

public class ApiConfig {
    private final static Logger logger = (Logger) LogManager.getLogger(ApiConfig.class);
    public final static Map<String,Object> apiContext = new HashMap<String,Object>();
    public static final RequestSpecBuilder builder = new RequestSpecBuilder();
    public static String baseUrl;

    public static String getAuthHeaderForPost(String body,String resourceUri) {
        PrivateKey signingKey = null;
        try {
            signingKey = AuthenticationUtils.loadSigningKey(System.getProperty("user.dir") +ApiConstants.PKCS12_KEY_FILE_PATH, ApiConstants.SIGNING_KEY_ALIAS, ApiConstants.SIGNING_KEY_PASSWORD);
        }
        catch (Exception e){
            logger.error("Issue in Generating Private Key:" +e.getMessage());
        }
        URI uri = URI.create(getBaseUrl() + resourceUri);
        apiContext.put("URI",uri);
        String authHeader = OAuth.getAuthorizationHeader(uri, "POST", body, StandardCharsets.UTF_8, ApiConstants.CONSUMER_KEY, signingKey);
        return authHeader;
    }

    public static String getAuthHeaderForGet(String resourceUri) {
        PrivateKey signingKey = null;
        try {
            signingKey = AuthenticationUtils.loadSigningKey(System.getProperty("user.dir") +ApiConstants.PKCS12_KEY_FILE_PATH, ApiConstants.SIGNING_KEY_ALIAS, ApiConstants.SIGNING_KEY_PASSWORD);
        }
        catch (Exception e){
            logger.error("Issue in Generating Private Key:" +e.getMessage());
        }
        URI uri = URI.create(getBaseUrl() + resourceUri);
        apiContext.put("URI",uri);
        String authHeader = OAuth.getAuthorizationHeader(uri, "GET", "", StandardCharsets.UTF_8, ApiConstants.CONSUMER_KEY, signingKey);
        return authHeader;
    }

    public static String getBaseUrl(){
        String baseUrl = TestProperties.getInstance().getApiProperty("base.uri");
        return baseUrl;
    }

    public Response postRequest(String requestUrl, String body, Map<String, Object> headers,
                                Cookies... cookie) {
        builder.setBody(body);
        builder.setContentType(ContentType.JSON);
        logger.info("POST-RequestURL  = " + requestUrl);
        logger.info("POST-RequestBody  = " + body);
        logger.info("POST-Headers  = " + headers);

        RequestSpecification requestSpec = builder.build();
        Response response = cookie.length > 0 && cookie[0] != null
                ? RestAssured.given().headers(headers).cookies(cookie[0]).spec(requestSpec).when()
                .relaxedHTTPSValidation().log().all().post(requestUrl)
                : RestAssured.given().headers(headers).spec(requestSpec).when().relaxedHTTPSValidation().log().all()
                .post(requestUrl);
        //logger.info(response.asString());
        return response;
    }

    /**
     * Delete request.
     *
     * @param requestUrl   the request url
     * @param acceptFormat the accept format
     * @param headers      the headers
     * @param cookie       the cookie
     * @return the response
     */
    public Response deleteRequest(String requestUrl, ContentType acceptFormat, Map<String, Object> headers,
                                  Cookies... cookie) {
        logger.info("DELETE-RequestURL  = " + requestUrl);
        logger.info("DELETE-Accept  = " + acceptFormat);
        Response response = cookie.length > 0 && cookie[0] != null
                ? RestAssured.given().headers(headers).cookies(cookie[0]).when().relaxedHTTPSValidation().log().all()
                .delete(requestUrl)
                : RestAssured.given().headers(headers).when().relaxedHTTPSValidation().log().all().delete(requestUrl);
        return response;
    }

    /**
     * Gets the request.
     *
     * @param requestUrl   the request url
     * @param acceptFormat the accept format
     * @param headers      the headers
     * @param cookie       the cookie
     * @return the request
     */
    public Response getRequest(String requestUrl, ContentType acceptFormat, Map<String, Object> headers,
                               Cookies... cookie) {
        logger.info("GET-RequestURL  = " + requestUrl);
        logger.info("GET-Accept  = " + acceptFormat);
        Response response = cookie.length > 0 && cookie[0] != null
                ? RestAssured.given().headers(headers).cookies(cookie[0]).when().relaxedHTTPSValidation()
                .get(requestUrl)
                : RestAssured.given().headers(headers).when().relaxedHTTPSValidation().get(requestUrl);

        return response;
    }

    /**
     * Put request with body.
     *
     * @param requestUrl the request url
     * @param body       the body
     * @param headers    the headers
     * @param cookie     the cookie
     * @return the response
     */
    public Response putRequest(String requestUrl, String body, Map<String, Object> headers, Cookies... cookie) {
        builder.setBody(body);
        builder.setContentType(ContentType.JSON);
        logger.info("PUT-RequestURL  = " + requestUrl);
        logger.info("PUT-RequestBody  = " + body);
        logger.info("PUT-Headers  = " + headers);

        RequestSpecification requestSpec = builder.build();

        Response response = cookie.length > 0 && cookie[0] != null
                ? RestAssured.given().headers(headers).cookies(cookie[0]).spec(requestSpec).when()
                .relaxedHTTPSValidation().log().all().put(requestUrl)
                : RestAssured.given().headers(headers).spec(requestSpec).when().relaxedHTTPSValidation().log().all()
                .put(requestUrl);

        return response;
    }

    /**
     * This method is used to execute query & return string output
     *
     * @param json - response value
     * @param path    - query to execute
     * @return result of executed query in string format
     */
    public static String executeQuery(String json, String path) {
        String output = null;
        try {
            JsonPath jsonPath = new JsonPath(json);
            output = jsonPath.getString(path);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return output;
    }
}

package com.mastercard.gpse.processing.utils;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class JsonUtils {
    private static final Logger logger = LogManager.getLogger(JsonUtils.class);

    /**
     * Parse the json file into JSONString
     *
     * @param jsonFilePath - path of the given json file
     * @return - parsed JSONString
     */
    public static String parseJsonFileToJSONString(String jsonFilePath) {
        try {
            String jsonString = new JSONParser().parse(FileUtility.getFileReader(jsonFilePath)).toString();
            return jsonString;
        } catch (Exception e) {
            logger.info("Failed to parse the file into JSONString. File path: " + jsonFilePath + ". \r\n" + Arrays.toString(e.getStackTrace()));
            return null;
        }
    }

    /**
     * Parse the JSON file into JSONObject
     *
     * @param jsonFilePath - path of the json file to be parsed
     * @return - parsed JSONObject
     */
    public static JSONObject parseJsonFileToJSONObject(String jsonFilePath) {
        try {
            Object obj = new JSONParser().parse(FileUtility.getFileReader(jsonFilePath));
            return (JSONObject) obj;
        } catch (Exception e) {
            logger.info("Failed to parse the file into JSONObject. File path: " + jsonFilePath + ". \r\n" + Arrays.toString(e.getStackTrace()));
            return null;
        }
    }

    /**
     * Parse the JSON file into JSONArray
     *
     * @param jsonFilePath - path of the json file to be parsed
     * @return - parsed JSONArray
     */
    public static JSONArray parseJsonFileToJSONArray(String jsonFilePath) {
        try {
            Object obj = new JSONParser().parse(FileUtility.getFileReader(jsonFilePath));
            return (JSONArray) obj;
        } catch (Exception e) {
            logger.info("Failed to parse the file into JSONArray. File path: " + jsonFilePath + ". \r\n" + Arrays.toString(e.getStackTrace()));
        }
        return null;
    }

    /**
     * Parse the api response body into JSONObject
     *
     * @param response - response to be parsed into JSONObject
     * @return - parsed JSONObject
     */
    public static JSONObject parseResponseToJsonObject(Response response) {
        try {
            JSONObject json = (JSONObject) new JSONParser().parse(response.asString());
            return json;
        } catch (ParseException e) {
            logger.info("Failed to parse the rest assured Response into JSONObject. \r\n" + Arrays.toString(e.getStackTrace()));
            return null;
        }
    }

    /**
     * Parse the api response body into JSONArray
     *
     * @param response - response to be parsed
     * @return - parsed JSONArray
     */
    public static JSONArray parseResponseToJsonArray(Response response) {
        try {
            JSONArray json = (JSONArray) new JSONParser().parse(response.asString());
            return json;
        } catch (ParseException e) {
            logger.info("Failed to parse the rest assured Response into JSONArray. \r\n" + Arrays.toString(e.getStackTrace()));
            return null;
        }
    }

    /**
     * Parse the json string into JSONObject
     *
     * @param jsonString - json string to be parsed
     * @return parsed json object
     */
    public static JSONObject parseJSONStringToJSONObject(String jsonString) {
        try {
            JSONObject json = (JSONObject) new JSONParser().parse(jsonString);
            return json;
        } catch (ParseException e) {
            logger.info("Failed to parse the rest assured Response into JSONObject. \r\n" + Arrays.toString(e.getStackTrace()));
            return null;
        }
    }

    /**
     * Gets the nested JSONObject from the given JSONObject
     *
     * @param jSONObject - Outer JSONObject
     * @param path - path of the inner (nested) JSONObject
     * @return inner JSONObject
     */
    public static JSONObject getJSONObject(JSONObject jSONObject, String path){
        return (JSONObject) jSONObject.get(path);
    }

    /**
     * Gets the nested JSONArray from the given JSONObject
     *
     * @param jSONObject - Outer JSONObject
     * @param path - path of the inner (nested) JSONObject
     * @return inner JSONArray
     */
    public static JSONArray getJSONArray(JSONObject jSONObject, String path){
        return (JSONArray) jSONObject.get(path);
    }

    /**
     * Get the JSONObject from given index from given JSONArray
     *
     * @param jsonArray - given JSONArray
     * @param index - index of the required JSONObject
     * @return JSONObject from given index
     */
    public static JSONObject getJSONObject(JSONArray jsonArray, int index){
        return (JSONObject) jsonArray.get(index);
    }

    /**
     * Parse the json file into JSONObject and read the value of the given key
     *
     * @param jsonFilePath - path of the json file
     * @param key - json key
     * @return - value of the given json key
     */
    public static String getValueFromJsonFile(String jsonFilePath, String key) {
        try {
            Object obj = new JSONParser().parse(FileUtility.getFileReader(jsonFilePath));
            JSONObject jsonObject = (JSONObject) obj;
            return jsonObject.get(key).toString();
        } catch (Exception e) {
            logger.info("Failed to get the value of given key '" + key + "' from the parsed JSONObject from file: '" + jsonFilePath + "'. \r\n" + Arrays.toString(e.getStackTrace()));
            return null;
        }
    }

    /**
     * Get value of the given json path from the given JSONString
     *
     * @param json - JSONString
     * @param path - json path
     * @return - value of the json path
     */
    public static <T> T getValueFromJSONString(String json, String path) {
        try {
            JsonPath jsonPath = new JsonPath(json);
            T output = (T) jsonPath.getString(path);
            return output;
        } catch (NullPointerException e) {
            logger.info("Failed to get the value of the given json path '" + path + "' from the given JSONString. \r\n" + Arrays.toString(e.getStackTrace()));
            return null;
        }
    }

    /**
     * Extract the value of the given json path from the given rest assured response
     *
     * @param response - response value
     * @param path - query to execute
     * @return result of executed path in string format
     */
    public static String getValueFromResponse(Response response, String path) {
        try {
            String output = response.then().contentType(ContentType.JSON).extract().path(path).toString();
            return output;
        } catch (NullPointerException e) {
            logger.info("Failed to get the value of the given json path '" + path + "' from the given rest assured response. \r\n" + Arrays.toString(e.getStackTrace()));
            return null;
        }
    }

    /**
     * This method is used to execute query & return string output
     *
     * @param json - response value
     * @param path    - query to execute
     * @return result of executed query in string format
     */
    public static String getValueFromResponse(String json, String path) {
        String output = null;
        try {
            JsonPath jsonPath = new JsonPath(json);
            output = jsonPath.getString(path);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return output;
    }

    /**
     * This method is a helper function for method: getValueFromJsonObject(String
     * response, String key)<br>
     *
     * It needs to be recursively called to get the value from the nested
     * JSONObject.
     *
     * @param jsonObject - json object
     * @param key - json key
     * @return - value of the json key
     */
    public static String getValueFromJSONObject(org.json.JSONObject jsonObject, String key) {
        String value = null;
        boolean exists = jsonObject.has(key);
        Iterator<?> keys;
        String nextKeys;
        if (!exists) {
            keys = jsonObject.keys();
            while (keys.hasNext()) {
                nextKeys = (String) keys.next();
                try {
                    if (jsonObject.get(nextKeys) instanceof JSONObject) {
                        if (exists == false) {
                            getValueFromJSONObject(jsonObject.getJSONObject(nextKeys), key);
                        }
                    } else if (jsonObject.get(nextKeys) instanceof JSONArray) {
                        org.json.JSONArray jsonarray = jsonObject.getJSONArray(nextKeys);
                        for (int i = 0; i < jsonarray.length(); i++) {
                            String jsonarrayString = jsonarray.get(i).toString();
                            org.json.JSONObject innerJSOn = new org.json.JSONObject(jsonarrayString);
                            if (exists == false) {
                                getValueFromJSONObject(innerJSOn, key);
                            }
                        }
                    }
                } catch (Exception e) {
                }
            }
        } else {
            value = String.valueOf(jsonObject.get(key));
        }
        return value;
    }


    /**
     * Get list of all possible values for a given key
     *
     * @param jsonArray - JSONArray to be searched with given key
     * @param key - json key
     * @param <T>
     * @return - list of all possible values
     */
    public static <T> List<T> getListFromJSONArray(JSONArray jsonArray, T key) {
        List<T> keyValues = new ArrayList<T>();
        Iterator<JSONObject> i = jsonArray.iterator();
        while (i.hasNext()) {
            T value;
            JSONObject innerObj = (JSONObject) i.next();
            if (innerObj.get(key).getClass().getName().contains("Boolean")) {
                value = (T) innerObj.get(key);
            } else {
                value = (T) innerObj.get(key);
            }
            keyValues.add(value);
        }
        return keyValues;
    }

    /**
     * Replace the value of the given key in the given JSONString and return the updated JSONString
     *
     * @param inputJSONString  - input json string
     * @param key   - key for which value has to be replaced
     * @param value - value to be updated
     * @return  - outputJSONString
     */
    @SuppressWarnings("unchecked")
    public static String replaceValueInJSONString(String inputJSONString, String key, String value) {
        try {
            JSONObject jsonObject = (JSONObject) new JSONParser().parse(inputJSONString);
            jsonObject.put(key, value);
            String outputJSONString = jsonObject.toString();
            return outputJSONString;
        } catch (Exception e) {
            logger.info("Failed to replace the value of the given key '" + key + "' from the given JSONString. \r\n" + Arrays.toString(e.getStackTrace()));
        }
        return null;
    }

    /**
     * Append the given value of the given key to the existing value in the given JSONString and return the updated JSONString
     *
     * @param inputJSONString  - input json string
     * @param key   - key for which value has to be replaced
     * @param value - value to be appended
     * @return  - outputJSONString
     */
    @SuppressWarnings("unchecked")
    public static String appendValueInJSONString(String inputJSONString, String key, String value) {
        try {
            JSONObject jsonObject = (JSONObject) new JSONParser().parse(inputJSONString);
            jsonObject.put(key, jsonObject.get(key) + value);
            String outputJSONString = jsonObject.toString();
            return outputJSONString;
        } catch (Exception e) {
            logger.info("Failed to append the value of the given key '" + key + "' from the given JSONString. \r\n" + Arrays.toString(e.getStackTrace()));
            return null;
        }
    }

    /**
     * Replace the value of the given key in the given JSONObject and return the updated JSONObject
     *
     * @param jsonObject - given json object
     * @param key - given json key
     * @param value - value to be updated
     * @param <T> - to make the method generic
     * @return - updated json object
     */
    @SuppressWarnings("unchecked")
    public static <T> JSONObject replaceValueInJSONObject(JSONObject jsonObject, String key, T value) {
        try {
            jsonObject.put(key, value);
            return jsonObject;
        } catch (Exception e) {
            logger.info("Failed to replace the value of the given key '" + key + "' from the given JSONObject. \r\n" + Arrays.toString(e.getStackTrace()));
            return null;
        }
    }

    /**
     * Append the given value of the given key to the existing value in the given JSONObject and return the updated JSONObject
     *
     * @param jsonObject - given json object
     * @param key - given json key
     * @param value - value to be appended to existing value
     * @return - updated json object
     */
    @SuppressWarnings("unchecked")
    public static JSONObject appendValueInJSONObject(JSONObject jsonObject, String key, String value) {
        try {
            jsonObject.put(key, jsonObject.get(key) + value);
            return jsonObject;
        } catch (Exception e) {
            logger.info("Failed to append the value of the given key '" + key + "' from the given JSONObject. \r\n" + Arrays.toString(e.getStackTrace()));
            return null;
        }
    }

    /**
     * Parse the JSON file into JSONObject.
     * Replace the value of the given key in the given JSONObject and return the updated JSONObject
     *
     * @param filePath - path of the given json file
     * @param key - given key
     * @param value - new value
     * @param <T> - to make the method generic
     * @return - updated json object
     */
    public static <T> JSONObject replaceValueInJsonFile(String filePath, String key, T value) {
        try {
            JSONObject jsonObject = parseJsonFileToJSONObject(filePath);
            if (value instanceof String ) {
                jsonObject.put(key, (String)value);
            }
            return jsonObject;
        } catch (Exception e) {
            logger.info("Failed to replace the value of the given key '" + key + "' from the parsed JSONObject in the given file. \r\n" + Arrays.toString(e.getStackTrace()));
            return null;
        }
    }

    /**
     * Parse the JSON file into JSONObject.
     * Append the given value of the given key to the existing value in the parsed JSONObject and return the updated JSONObject
     *
     * @param filePath - path of the given json file
     * @param key - given key
     * @param value - new value
     * @return - updated json object
     */
    @SuppressWarnings("unchecked")
    public static JSONObject appendValueInJsonFile(String filePath, String key, String value) {
        try {
            JSONObject jsonObject = parseJsonFileToJSONObject(filePath);
            jsonObject.put(key, jsonObject.get(key) + value);
            return jsonObject;
        } catch (Exception e) {
            logger.info("Failed to append the value of the given key '" + key + "' from the parsed JSONObject in the given file. \r\n" + Arrays.toString(e.getStackTrace()));
            return null;
        }
    }
}

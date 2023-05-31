package com.mastercard.gpse.processing.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.mastercard.gpse.processing.constants.Constants;
import com.mastercard.gpse.processing.fileoperations.TestProperties;
import com.rallydev.rest.RallyRestApi;
import com.rallydev.rest.request.CreateRequest;
import com.rallydev.rest.request.QueryRequest;
import com.rallydev.rest.response.CreateResponse;
import com.rallydev.rest.response.QueryResponse;
import com.rallydev.rest.util.Fetch;
import com.rallydev.rest.util.QueryFilter;
import com.rallydev.rest.util.Ref;
import org.apache.commons.codec.binary.Base64;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URI;
import java.net.URISyntaxException;

public class RallyUtils {
    private static final Logger logger = (Logger) LogManager.getLogger(RallyUtils.class);

    public RallyRestApi restApi;
    public String testCaseResultRef = "";

    public void getRallyInstance() {


        try {
            URI rallyURI = new URI(TestProperties.getInstance().getEnvProperty("rally.url"));
            String apiKey = TestProperties.getInstance().getEnvProperty("rally.api.key");
            restApi = new RallyRestApi(rallyURI, apiKey);

        } catch (URISyntaxException e) {
            logger.error("Issue with Rest API Instance Creation: " + e.getMessage(), e);

        } catch (Exception e) {
            logger.error("Issue with Rest API Instance Creation: " + e.getMessage(), e);

        }

    }

    public void updateTestResult(String verdict, String testID) {
        getRallyInstance();
        logger.info("API Web Service Version: " + restApi.getWsapiVersion());
        String testerRallyID = TestProperties.getInstance().getEnvProperty("rally.user");
        String rallyProject = TestProperties.getInstance().getEnvProperty("rally.project");


        JsonObject createTestCaseResult = new JsonObject();
        createTestCaseResult.addProperty("Build", Constants.BUILD);
        createTestCaseResult.addProperty("Date", DateUtility.getExecutionDate());
        createTestCaseResult.addProperty("Tester", getUserRef(testerRallyID));
        createTestCaseResult.addProperty("TestCase", getTestCaseRef(testID));
        createTestCaseResult.addProperty("Project", rallyProject);
        createTestCaseResult.addProperty("Verdict", verdict);
        createTestCaseResult.addProperty("Notes", Constants.RALLY_NOTES);

        //createTestCaseResult.addProperty("Attachments", "C:\\Users\\e066045\\Documents\\Automation\\workbench_auto\\target\\screenshots\\ClientSearchTest_validateClientDetailsByBasicSearch_1641555661245.png");
        // Unable to create testSet - Getting Error Message - Not authorzed to create Test Set
        //String testSet = "12345";
        //createTestCaseResult.addProperty("TestSet",getTestSetRef());

        CreateRequest testCaseResultCreateRequest = new CreateRequest("testcaseresult", createTestCaseResult);


        try {
            CreateResponse testCaseResultCreateResponse = restApi.create(testCaseResultCreateRequest);
            testCaseResultRef = Ref.getRelativeRef(testCaseResultCreateResponse.getObject().get("_ref").getAsString());

            if (testCaseResultCreateResponse.wasSuccessful()) {

                logger.info("Test Results Updated Successfully");

                if(Constants.UPLOAD_SCREENSHOTS.equalsIgnoreCase("YES")) {
                    getTestCaseAttachment(testID);
                }
                else{
                    logger.info("Screen Shots not uploaded as Flag Set to NO");
                }



            } else {
                String[] createErrors = testCaseResultCreateResponse.getErrors();
                logErrors(createErrors);
            }
        }

        catch (FileNotFoundException fileException)
        {
            fileException.printStackTrace();
            logger.error("Exception in Attaching Test Result screenshots to ALM: " + fileException.getMessage(), fileException);
        }

        catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception in Updating Test Result to ALM: " + e.getMessage(), e);

        }

    }

    public void logErrors(String[] errors) {
        logger.error("Error occurred: ");
        for (int j = 0; j < errors.length; j++) {
            logger.error(errors[j]);
        }
    }

    public String getTestCaseRef(String testCaseID) {

        QueryRequest testCaseRequest = new QueryRequest("TestCase");
        testCaseRequest.setFetch(new Fetch("FormattedID", "Name"));
        testCaseRequest.setQueryFilter(new QueryFilter("FormattedID", "=", testCaseID));
        try {
            QueryResponse testCaseQueryResponse = restApi.query(testCaseRequest);

            //JsonObject testCaseJsonObject = testCaseQueryResponse.getResults().get(0).getAsJsonObject();
            //System.out.println("Count:" + testCaseQueryResponse.getTotalResultCount());
            //System.out.println("TestObj:" + testCaseJsonObject);
            if (testCaseQueryResponse.wasSuccessful()) {
                return testCaseQueryResponse.getResults().get(0).getAsJsonObject().get("_ref").getAsString();
            } else {
                String[] createErrors = testCaseQueryResponse.getErrors();
                logErrors(createErrors);
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception in Getting TCs Reference: " + e.getMessage(), e);

            return null;
        }
    }

    public void releaseApiResources() {
        try {
            restApi.close();

        } catch (Exception e) {

            logger.error("Exception in Releasing Api Resources: " + e.getMessage(), e);
        }
    }

    public String getUserRef(String rallyUserName) {

        QueryRequest userRequest = new QueryRequest("User");
        userRequest.setFetch(new Fetch("UserName", "Subscription", "DisplayName"));
        userRequest.setQueryFilter(new QueryFilter("UserName", "=", rallyUserName));
        try {
            QueryResponse userQueryResponse = restApi.query(userRequest);

            if (userQueryResponse.wasSuccessful()) {
                JsonArray userQueryResults = userQueryResponse.getResults();
                JsonObject userQueryObject = userQueryResults.get(0).getAsJsonObject();
                String usersRef = userQueryObject.get("_ref").getAsString();
                //System.out.println("User Ref:"+userRef);
                //System.out.println("UserObj:"+userQueryObject);
                return usersRef;
            } else {
                String[] createErrors = userQueryResponse.getErrors();
                logErrors(createErrors);
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception in Getting User Reference: " + e.getMessage(), e);
            return null;
        }
    }


    public String getTestSetRef() throws IOException {
        String testSetRef;
        JsonObject createTestSetReq = new JsonObject();
        createTestSetReq.addProperty("DefectStatus", "NONE");
        createTestSetReq.addProperty("TaskStatus", "NONE");
        createTestSetReq.addProperty("TestCaseStatus", "NONE");
        CreateRequest createRequest = new CreateRequest("TestSet", createTestSetReq);
        try {
            CreateResponse getResponse = restApi.create(createRequest);

            if (getResponse.wasSuccessful()) {
                testSetRef = getResponse.getObject().get("_ref").getAsString();

                return testSetRef;
            } else {
                String[] createErrors = getResponse.getErrors();
                logger.error("Error occurred: ");
                for (int j = 0; j < createErrors.length; j++) {
                    logger.error(createErrors[j]);
                }

                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception Error:", e.getMessage());
            return null;
        }

    }

    //Do Not Delete the method. Its for future Reference and for troubleshooting
    /*public void projectProperties(){
        GetRequest getRequest = new GetRequest("/testcase/621736903071");
        GetResponse getResponse = restApi.get(getRequest);
        System.out.println("Response1:"+getResponse.wasSuccessful());
        System.out.println("Response1:"+getResponse.getObject().toString());
        String testCaseRef = getResponse.getObject().get("_ref").getAsString();
        System.out.println("TestCaseRef GetRequest:"+testCaseRef);
        System.out.println("Project:"+getResponse.getObject().get("Project"));
        JsonObject project = getResponse.getObject().get("Project").getAsJsonObject();
        String projectRef = project.get("_ref").getAsString();
        System.out.println("Project Ref:"+projectRef);
    }*/

    public void getTestCaseAttachment(String testCaseID) throws FileNotFoundException {

        String fullImageFile;
        String imageFile;

        String imageFilePath = TestProperties.getInstance().getEnvProperty("wb.screenshots.dir.path");
        String screenShotLocation = Constants.USER_DIR + "\\" + imageFilePath + testCaseID;
        File directory = new File(screenShotLocation);

        // Check the number of Files available to attach and then attach each of them

        int fileCount=directory. list(). length;
        String[] filesList;
        filesList = directory.list();


        for (int currentFileCount = 0; currentFileCount < fileCount; currentFileCount++) {

            imageFile = filesList[currentFileCount];
            fullImageFile = screenShotLocation +"\\"+ imageFile;
            createAttachment(fullImageFile);

        }

    }

    public void createAttachment(String filePath) throws FileNotFoundException {

        RandomAccessFile myImageFileHandle = new RandomAccessFile(filePath, "r");
        String imageBase64String;
        long attachmentSize;

        try {
            // Get and check length
            long longLength = myImageFileHandle.length();
            long maxLength = 5000000;
            if (longLength >= maxLength) throw new IOException("File size >= 5 MB Upper limit for Rally.");
            int fileLength = (int) longLength;

            // Read file and return data
            byte[] fileBytes = new byte[fileLength];
            myImageFileHandle.readFully(fileBytes);
            imageBase64String = Base64.encodeBase64String(fileBytes);
            attachmentSize = fileLength;


            JsonObject myAttachmentContent = new JsonObject();
            myAttachmentContent.addProperty("Content", imageBase64String);

            CreateRequest attachmentContentCreateRequest1 = new CreateRequest("AttachmentContent", myAttachmentContent);
            CreateResponse attachmentContentResponse1 = restApi.create(attachmentContentCreateRequest1);
            String myAttachmentContentRef1 = attachmentContentResponse1.getObject().get("_ref").getAsString();
            //logger.info("Attachment Content created: " + myAttachmentContentRef1);

            // Now create the Attachment itself

            JsonObject myAttachment = new JsonObject();
            myAttachment.addProperty("TestCaseResult", testCaseResultRef);
            myAttachment.addProperty("Content", myAttachmentContentRef1);
            myAttachment.addProperty("Name", "AttachmentFromREST.jpg");
            myAttachment.addProperty("Description", "Attachment From REST");
            myAttachment.addProperty("ContentType", "image/jpg");
            myAttachment.addProperty("Size", attachmentSize);
            // myAttachment.addProperty("User", "Abhay");

            CreateRequest attachmentCreateRequest = new CreateRequest("Attachment", myAttachment);
            CreateResponse attachmentResponse = restApi.create(attachmentCreateRequest);

            if (attachmentResponse.wasSuccessful()) {
                logger.info("Screenshots attached successfully");
            } else {
                String[] attachmentContentErrors;
                attachmentContentErrors = attachmentResponse.getErrors();
                logger.error("Error occurred creating Attachment: ");
                for (int i = 0; i < attachmentContentErrors.length; i++) {
                    logger.error(attachmentContentErrors[i]);
                }
            }
        } catch (Exception e) {
            logger.error("Exception occurred while attempting to create Content and/or Attachment: ");
            e.printStackTrace();
        }


    }

}






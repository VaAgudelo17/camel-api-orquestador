package co.com.agudelo.microservice.resolveEnigmaApi.api;

import co.com.agudelo.microservice.resolveEnigmaApi.model.ErrorDetail;
import co.com.agudelo.microservice.resolveEnigmaApi.model.GetEnigmaStepResponse;
import co.com.agudelo.microservice.resolveEnigmaApi.model.JsonApiBodyRequest;
import co.com.agudelo.microservice.resolveEnigmaApi.model.JsonApiBodyResponseErrors;
import co.com.agudelo.microservice.resolveEnigmaApi.model.JsonApiBodyResponseSuccess;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-03-12T19:41:29.935-05:00[America/Bogota]")
@Controller
public class GetStepApiController implements GetStepApi {

	private static final Logger log = LoggerFactory.getLogger(GetStepApiController.class);
    private final ObjectMapper objectMapper;
    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public GetStepApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<?> getStep(@ApiParam(value = "request body get enigma step", required = true) @Valid @RequestBody JsonApiBodyRequest body) {
    	if(!isStepValid(body)) {
    		return new ResponseEntity<>(createResponseErrors(body), HttpStatus.BAD_REQUEST);
    	}
    	return new ResponseEntity<>(createResponseSuccess(body), HttpStatus.OK);
    }
    
    private boolean isStepValid(JsonApiBodyRequest body) {
    	return body.getData().get(0).getStep().equalsIgnoreCase("3");
    }
    
    
    @GetMapping("/getStepThree")
    public ResponseEntity<String> getThree() {
    	return new ResponseEntity<>("Step 3: Close the door", HttpStatus.OK);
    }
    
    private List<JsonApiBodyResponseErrors> createResponseErrors(JsonApiBodyRequest body) {
    	ErrorDetail errorDetail = new ErrorDetail();
    	errorDetail.setCode("003");
    	errorDetail.setDetail("Step: " + body.getData().get(0).getStep() + " not supported - Expected: 3");
    	errorDetail.setId(body.getData().get(0).getHeader().getId());
    	errorDetail.setSource("/getStep");
    	errorDetail.setStatus("400");
    	errorDetail.setTitle("Step not supported");
    	
    	JsonApiBodyResponseErrors responseError = new JsonApiBodyResponseErrors();
    	responseError.addErrorsItem(errorDetail);
    	
    	List<JsonApiBodyResponseErrors> responseErrorsList = new ArrayList<JsonApiBodyResponseErrors>(); 
    	responseErrorsList.add(responseError);
    	
    	return responseErrorsList;
    }
    
    private List<JsonApiBodyResponseSuccess> createResponseSuccess(JsonApiBodyRequest body) {
        GetEnigmaStepResponse responseEnigma = new GetEnigmaStepResponse();    
        responseEnigma.setHeader(body.getData().get(0).getHeader());
        responseEnigma.setStep(body.getData().get(0).getStep());
        responseEnigma.setStepDescription("Close the door");
        
        JsonApiBodyResponseSuccess responseSuccess = new JsonApiBodyResponseSuccess();
        responseSuccess.addDataItem(responseEnigma);
        
        List<JsonApiBodyResponseSuccess> responseSuccessList = new ArrayList<JsonApiBodyResponseSuccess>();  
        responseSuccessList.add(responseSuccess);
        
        return responseSuccessList;
    }
	

}
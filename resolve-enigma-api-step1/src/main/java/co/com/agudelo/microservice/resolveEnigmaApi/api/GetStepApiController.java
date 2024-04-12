package co.com.agudelo.microservice.resolveEnigmaApi.api;

import java.util.List;

import co.com.agudelo.microservice.resolveEnigmaApi.model.ErrorDetail;
import co.com.agudelo.microservice.resolveEnigmaApi.model.GetEnigmaRequest;
import co.com.agudelo.microservice.resolveEnigmaApi.model.GetEnigmaStepResponse;
import co.com.agudelo.microservice.resolveEnigmaApi.model.JsonApiBodyRequest;
import co.com.agudelo.microservice.resolveEnigmaApi.model.JsonApiBodyResponseErrors;
import co.com.agudelo.microservice.resolveEnigmaApi.model.JsonApiBodyResponseSuccess;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;

import org.apache.camel.EndpointInject;
import org.apache.camel.FluentProducerTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.Map;
import java.util.stream.Collectors;
import java.util.ArrayList;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-03-03T16:34:14.965-05:00[America/Bogota]")


@Controller
public class GetStepApiController implements GetStepApi {

	private static final Logger log = LoggerFactory.getLogger(GetStepApiController.class);
    private final ObjectMapper objectMapper;
    private final HttpServletRequest request;
    
    
    @EndpointInject(uri="direct:get-step-one")
    private FluentProducerTemplate producerTemplateResolveEnigma;
    
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
    	return body.getData().get(0).getStep().equalsIgnoreCase("1");
    }
    
    
    @GetMapping("/getStepOne")
    public ResponseEntity<String> getOne() {
    	return new ResponseEntity<>("Step 1: Open the refrigerator.", HttpStatus.OK);
    }
    
    private List<JsonApiBodyResponseErrors> createResponseErrors(JsonApiBodyRequest body) {
    	ErrorDetail errorDetail = new ErrorDetail();
    	errorDetail.setCode("001");
    	errorDetail.setDetail("Step: " + body.getData().get(0).getStep() + " not supported - Expected: 1");
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
        responseEnigma.setStepDescription("Open the refrigerator.");
        
        JsonApiBodyResponseSuccess responseSuccess = new JsonApiBodyResponseSuccess();
        responseSuccess.addDataItem(responseEnigma);
        
        List<JsonApiBodyResponseSuccess> responseSuccessList = new ArrayList<JsonApiBodyResponseSuccess>();  
        responseSuccessList.add(responseSuccess);
        
        return responseSuccessList;
    }

}

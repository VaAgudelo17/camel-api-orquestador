package co.com.agudelo.microservice.resolveEnigmaApi.api;

import io.swagger.annotations.ApiParam;

import org.apache.camel.EndpointInject;
import org.apache.camel.FluentProducerTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.RequestBody;

import co.com.agudelo.microservice.resolveEnigmaApi.api.GetStepApiController;
import co.com.agudelo.microservice.resolveEnigmaApi.model.JsonApiBodyResponseSuccess;
import co.com.agudelo.microservice.resolveEnigmaApi.model.ErrorDetail;
import co.com.agudelo.microservice.resolveEnigmaApi.model.JsonApiBodyRequest;
import co.com.agudelo.microservice.resolveEnigmaApi.model.JsonApiBodyResponseErrors;

import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
public class GetStepApiController implements GetStepApi {
	
    @EndpointInject(uri="direct:resolve-enigma")
    private FluentProducerTemplate producerTemplateResolveEnigma;
	
    private static final Logger log = LoggerFactory.getLogger(GetStepApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;
    
    private Object response;
    

    @org.springframework.beans.factory.annotation.Autowired
    public GetStepApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

 
    public ResponseEntity<?> getStep(@ApiParam(value = "request body get enigma step", required = true) @Valid @RequestBody JsonApiBodyRequest body) {
    	if(!isEnigmaValid(body)) {
    		return new ResponseEntity<>(createResponseErrors(body), HttpStatus.BAD_REQUEST);
    	}
        response = producerTemplateResolveEnigma.withBody(body).request();
    	return new ResponseEntity<JsonApiBodyResponseSuccess>((JsonApiBodyResponseSuccess)response, HttpStatus.OK);
    }
    
    private boolean isEnigmaValid(JsonApiBodyRequest body) {
    	return body.getData().get(0).getEnigma().equalsIgnoreCase("How to put a giraffe into a refrigerator?");
    }
    
    private List<JsonApiBodyResponseErrors> createResponseErrors(JsonApiBodyRequest body) {
    	ErrorDetail errorDetail = new ErrorDetail();
    	errorDetail.setCode("001");
    	errorDetail.setDetail("Enigma not supported - Expected: How to put a giraffe into a refrigerator?");
    	errorDetail.setId(body.getData().get(0).getHeader().getId());
    	errorDetail.setSource("/resolveSteps");
    	errorDetail.setStatus("400");
    	errorDetail.setTitle("Step not supported");
    	
    	JsonApiBodyResponseErrors responseError = new JsonApiBodyResponseErrors();
    	responseError.addErrorsItem(errorDetail);
    	
    	List<JsonApiBodyResponseErrors> responseErrorsList = new ArrayList<JsonApiBodyResponseErrors>(); 
    	responseErrorsList.add(responseError);
    	
    	return responseErrorsList;
    }
    
    
}
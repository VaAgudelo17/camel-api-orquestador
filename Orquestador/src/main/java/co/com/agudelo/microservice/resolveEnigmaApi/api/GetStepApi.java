package co.com.agudelo.microservice.resolveEnigmaApi.api;

import io.swagger.annotations.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import co.com.agudelo.microservice.resolveEnigmaApi.model.JsonApiBodyRequest;
import co.com.agudelo.microservice.resolveEnigmaApi.model.JsonApiBodyResponseErrors;
import co.com.agudelo.microservice.resolveEnigmaApi.model.JsonApiBodyResponseSuccess;

import javax.validation.Valid;


@Api(value = "resolveSteps", description = "the getSteps API")
public interface GetStepApi {
	@ApiOperation(value = "Get  answer enigma API", nickname = "getStepsPost", notes = "Get  answer enigma API", response = JsonApiBodyResponseSuccess.class, tags={ "resolve-enigma-api-controller", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Inquery step success", response = JsonApiBodyResponseSuccess.class),
        @ApiResponse(code = 424, message = "Invalid Input. please put correct request", response = JsonApiBodyResponseErrors.class) })
    @RequestMapping(value = "/resolveSteps",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<?> getStep(@ApiParam(value = "body" ,required=true )  @Valid @RequestBody JsonApiBodyRequest body);
}
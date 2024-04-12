package co.com.agudelo.microservice.resolveEnigmaApi.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;


/**
 * JsonApiBodyResponseSuccess
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-02-27T20:56:42.389-05:00[America/Bogota]")
public class JsonApiBodyResponseSuccess   {
	@JsonProperty("data")
	  @Valid
	  private List<Object> data = new ArrayList<Object>();

	  public JsonApiBodyResponseSuccess data(List<Object> data) {
	    this.data = data;
	    return this;
	  }

	  public JsonApiBodyResponseSuccess addDataItem( Object response) {
	    this.data.add(response);
	    return this;
	  }

	  /**
	   * Get data
	   * @return data
	  **/
	  @ApiModelProperty(required = true, value = "")
	  @NotNull
	  @Valid
	  public List<Object> getData() {
	    return data;
	  }

	  public void setData( List<Object> data) {
	    this.data = data;
	  }


	  @Override
	  public boolean equals(java.lang.Object o) {
	    if (this == o) {
	      return true;
	    }
	    if (o == null || getClass() != o.getClass()) {
	      return false;
	    }
	    JsonApiBodyResponseSuccess jsonApiBodyResponseSuccess = (JsonApiBodyResponseSuccess) o;
	    return Objects.equals(this.data, jsonApiBodyResponseSuccess.data);
	  }

	  @Override
	  public int hashCode() {
	    return Objects.hash(data);
	  }

	  @Override
	  public String toString() {
	    StringBuilder sb = new StringBuilder();
	    sb.append("class JsonApiBodyResponseSuccess {\n");
	    
	    sb.append("    data: ").append(toIndentedString(data)).append("\n");
	    sb.append("}");
	    return sb.toString();
	  }

	  /**
	   * Convert the given object to string with each line indented by 4 spaces
	   * (except the first line).
	   */
	  private String toIndentedString(java.lang.Object o) {
	    if (o == null) {
	      return "null";
	    }
	    return o.toString().replace("\n", "\n    ");
	  }
}
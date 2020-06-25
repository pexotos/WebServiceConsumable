package br.com.projeto.portfolio.portfolio.generic.response;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

public class GenericResponse<T> extends ModelResponse {

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE, include = JsonTypeInfo.As.WRAPPER_OBJECT, property = "@class")
    private T responseData;

    public GenericResponse() {
        super(0, "");
    }



    public GenericResponse(int returnCode, String returnMessage, T responseData) {
        super(returnCode, returnMessage);
        this.responseData = responseData;
    }

    public T getResponseData() {
        return responseData;
    }

    public void setResponseData(T responseData) {
        this.responseData = responseData;
    }

}

package com.slava.theapp.data.remote.responses;


public class ServerResponse<T> extends BaseResponse {

    private T response;



    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "ServerResponse{" +
                ", response=" + response +
                '}';
    }
}

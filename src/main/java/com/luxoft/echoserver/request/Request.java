package com.luxoft.echoserver.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Request {
    String uri;
    HttpMethod method;
}
package com.corecompete.i2o.utility;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class ErrorResponse implements Serializable {

    private static final long serialVersionUID = 3246606787634298873L;

    private String code;
    private String message;
    private String logref;
}

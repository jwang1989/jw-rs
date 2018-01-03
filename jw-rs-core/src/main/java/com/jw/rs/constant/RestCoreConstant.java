package com.jw.rs.constant;

/**
 * Rest core constants.
 *
 * @author JWang
 * @since Jan 02, 2018
 */
public enum RestCoreConstant {

    RESTEASY_SCAN("resteasy.scan"),
    RESTEASY_SCAN_VALUE("true"),
    RESTEASY_SERVLET_MAPPING_PREFIX("resteasy.servlet.mapping.prefix"),
    RESTEASY_NAME("resteasy-servlet"),
    RESTEASY_APPLICATION("javax.ws.rs.Application"),
    RESTEASY_APPLICATION_VALUE("com.jw.rs.application.ResteasyApplication");

    private String value;

    private RestCoreConstant(String value) {
        this.value = value;
    }

    /**
     * Gets constant value.
     *
     * @return value Constant string value
     */
    public String getValue() {
        return value;
    }

}

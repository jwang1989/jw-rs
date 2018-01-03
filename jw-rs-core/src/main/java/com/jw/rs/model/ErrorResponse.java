package com.jw.rs.model;

import java.io.Serializable;

/**
 * REST general error response which is used to handle the status and reason.
 *
 * @author JWang
 * @since Jan 02, 2018
 */
public class ErrorResponse implements Serializable {

    private static final long serialVersionUID = -3887127503888276670L;

    private Integer status;

    private String reason;

    /**
     * Constructs ErrorResponse.
     *
     * @param status http status
     * @param reason error reason
     */
    public ErrorResponse(Integer status, String reason) {
        this.status = status;
        this.reason = reason;
    }

    /**
     * Gets http status.
     *
     * @return status http status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * Sets http status.
     *
     * @param status http status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * Gets error reason.
     *
     * @return reason error reason
     */
    public String getReason() {
        return reason;
    }

    /**
     * Sets error reason.
     *
     * @param reason error reason
     */
    public void setReason(String reason) {
        this.reason = reason;
    }

    /*
     * (non-Javadoc)
     * @see Object#toString()
     */
    @Override
    public String toString() {
        return "ErrorResponse{" + "status=" + status + ", reason='" + reason + '\'' + '}';
    }

}

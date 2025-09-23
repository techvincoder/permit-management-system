// Create this in a new package, e.g., com.tpms.exceptions
package com.tpms.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1877230628785312316L;

	public ResourceNotFoundException(String message) {
        super(message);
    }
}
package com.tpms.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT) // This will return a 409 Conflict status
public class ResourceAlreadyExistsException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 6743761976718865299L;

	public ResourceAlreadyExistsException(String message) {
        super(message);
    }
}
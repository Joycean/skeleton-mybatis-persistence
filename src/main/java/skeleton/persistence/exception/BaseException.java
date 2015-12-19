package skeleton.persistence.exception;

import java.util.Map;

public abstract class BaseException extends RuntimeException {
    Map<String, String> errors;
    
    public BaseException(String message) {
        super(message);
    }
    
    public BaseException(String message, Map<String, String> errors) {
    	super(message);
        this.errors = errors;
    }

	public Map<String, String> getErrors() {
		return errors;
	}

	public void setErrors(Map<String, String> errors) {
		this.errors = errors;
	}
    
    
}

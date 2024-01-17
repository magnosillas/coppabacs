package br.edu.ufape.lmts.sementes.service.exception;

public class ObjectNotFoundException extends RuntimeException {

	public ObjectNotFoundException(String msg) {
		super(msg);
	}

	public ObjectNotFoundException(String msg, Throwable cause) {
		super(msg, cause);
	}
}

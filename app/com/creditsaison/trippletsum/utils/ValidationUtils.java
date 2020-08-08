package com.creditsaison.trippletsum.utils;

import java.util.Collection;

import javax.validation.ValidationException;

public class ValidationUtils {

	public static <T> void notNull(final T object, final String message) {
		if(object == null) {
			throw new ValidationException(message);
		}
	}

	public static <T> void notEmpty(final Collection<T> array, final String message) {
		if(array == null || array.size() < 3) {
			throw new ValidationException(message);
		}

	}
}

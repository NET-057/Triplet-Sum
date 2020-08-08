package com.creditsaison.parser;

import java.lang.reflect.Type;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonParserUtils {

	private static final Gson gson = getDateTimeHandled();

	private static Gson getDateTimeHandled() {
		return getDefaultGsonBuilder().
				create();
	}

	public static GsonBuilder getDefaultGsonBuilder() {
		return new GsonBuilder();
	}

	public static <T>T fromJson(String jsonStr, Class<T> classz) {
		if (jsonStr == null || jsonStr == "") {
			return null;
		}
		return gson.fromJson(jsonStr, classz);
	}



	public static <T>T fromJson(JsonNode jsonNode, Class<T> classz) {
		if (jsonNode ==null) {
			return null;
		}
		String jsonStr = jsonNode.toString();
		return fromJson(jsonStr, classz);
	}
	
	public static String toJson(Object obj) {
		if (obj == null) {
			return null;
		}
		return gson.toJson(obj);
	}


	public static<T> String toJson(Object obj, Class<T> classz) {
		return gson.toJson(obj, classz);
	}


	public static Object fromJson(String body, Type typeOfClass) {
		Object fromJson = gson.fromJson(body, typeOfClass);
		return fromJson;
	}

	public static Object fromJson(JsonNode body, Type typeOfClass) {
		Object fromJson = gson.fromJson(body.toString(), typeOfClass);
		return fromJson;
	}

}

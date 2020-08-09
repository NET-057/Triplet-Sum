package com.creditsaison.trippletsum.utils;

import java.util.List;
import java.util.stream.Collectors;

public class TrippletSumUtils {

	public static String getString(List<Integer> array) {
		if(array != null && array.size() > 0) {
			return array.stream().map(p1 -> String.valueOf(p1)).collect(Collectors.joining(","));
		}
		return null;
	}
}

package com.example.util;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class IterableUtil {

	public static <T> List<T> toList(Iterable<T> itr){
		if(itr==null) return new ArrayList<T>();
		return StreamSupport
				.stream(itr.spliterator(), false)
				.collect(Collectors.toList()) ;
	}
	
}

package model;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class TwitterEncoder {
	public static String encode(String s) throws UnsupportedEncodingException {
		String re = URLEncoder.encode(s, "ISO-8859-1");

		return re;
	}
}

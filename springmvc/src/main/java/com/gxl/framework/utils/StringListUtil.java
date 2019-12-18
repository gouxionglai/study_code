package com.gxl.framework.utils;

import java.util.ArrayList;
import java.util.List;

/***
 * string to list util
 *
 */
public class StringListUtil {

	/***
	 * 字符串转化为List集合，
	 * 
	 * @param stringlist
	 *            必须为 逗号间隔字符串
	 * @return
	 */
	public static List<String> stringToList(String stringlist) {

		List<String> list = new ArrayList<String>();

		if (!stringlist.equals("")) {
			String[] arrt = stringlist.split(",");

			for (int i = 0; i < arrt.length; i++) {
				if (!arrt[i].trim().equals("")) {
					list.add(arrt[i]);
				}
			}

		} else {
			list = null;
		}
		return list;

	}

}

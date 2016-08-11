package controller;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

import manager.Config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class InternLoadPropertFiles {
	private static final Logger log = LogManager
			.getLogger(InternLoadPropertFiles.class.getName());

	public static String getLangAbbrev(String i18ResourcePath, String className) {
		Set<String> set = new TreeSet<>();
		// String path = System.getProperty("user.dir") + File.separator
		// + i18ResourcePath;
		ClassLoader classLoader = InternLoadPropertFiles.class.getClassLoader();
		String path = classLoader.getResource(i18ResourcePath).getPath();

		String name = null;
		String res = "";
		StringTokenizer tk = null;
		if (path != null) {
			File[] files = new File(path).listFiles();

			if (files != null) {
				for (File file : files) {
					name = file.getName();
					if (name.endsWith(Config.PROPERTIES)) {
						tk = new StringTokenizer(name, "_.");
						if (tk.hasMoreTokens()) {
							if (tk.nextToken().equalsIgnoreCase(className)) {
								if (tk.hasMoreTokens()) {
									set.add(tk.nextToken().toUpperCase());
								}
							}
						}
					}
				}
			}
			res = String.join(",", set);
		}
		return res;
	}

	public static void main(String[] args) {
		System.out.println("Present Project Directory : "
				+ System.getProperty("user.dir"));
		// String str = getLangAbbrev(Config.i18RESOURCEPATH,
		// Config.MAINMENUBUNDLE);
		// System.out.println(str + "\n" + Message.class.getSimpleName());
		DateFormat format1 = new SimpleDateFormat("dd.MM.yyyy");

		Locale.setDefault(new Locale("en"));
		System.out.println(Long.parseLong(null));
		try {
			System.out.println(format1.parse("11.10.2016"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}

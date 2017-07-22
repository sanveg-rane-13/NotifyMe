package com.app.notifyme.services;

/*
 * 
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*")
@Service("renderPageService")
public class RenderPageServiceImpl implements RenderPageService {
	
	private final static String path = "C:/Users/ADMIN/wissen-project/NotifyMe/src/main/resources/xpath.js";

	private static final String JS;
	/*
	 * reads the script file at start-up and stores it within the variable JS
	 */
	static {
		BufferedReader br = null;
		try {
			br = new BufferedReader(
					new FileReader(new File(path)));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			StringBuilder sb = new StringBuilder();
			String line = null;
			try {
				line = br.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			while (line != null) {
				sb.append(line);
				sb.append("\n");
				try {
					line = br.readLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			JS = sb.toString();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/*
	 * 
	 * gets the url and connects to that url. Reads the entire html document and
	 * adds a script to the head of the html to make the document respond to user
	 * clicks on required fields like productname and price and returns the html as
	 * a string to the client-side
	 */
	@Override
	public String renderPage(String url) {
		// TODO Auto-generated method stub
		url = url.trim();
		URL targetUrl = null;
		try {
			targetUrl = new URL(url);
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			// e1.printStackTrace();
			return "could not connect to " + url;
		}
		URLConnection connection = null;
		try {
			connection = targetUrl.openConnection();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			// System.out.println("could not connect to "+url);
			return "could not connect to " + url;
		}
		// connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows
		// NT 6.1; zh-CN; rv:1.9.2.8) Gecko/20100722 Firefox/3.6.8");

		String line;
		String response = "";
		long totalBytes = 0;

		StringBuilder builder = new StringBuilder();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			return "unable to read from " + url;
		}
		try {
			while ((line = reader.readLine()) != null) {
				builder.append(line);
				totalBytes += line.getBytes("UTF-8").length;
				// System.out.println("Total bytes read :: " + totalBytes);
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			return "could not read from " + url;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return "could not read from " + url;
		}

		int start_index = builder.indexOf("</head>");
		response = builder.substring(0, start_index) + "<script>" + JS + "</script>" + builder.substring(start_index);
		// System.out.println(response);

		return response;

	}

}

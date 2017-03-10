import java.net.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.io.*;

public class sendScore {
	public static void sendScore(String name, int score, int coins) throws IOException{
		URL url;
		try {
			url = new URL("http://155.42.99.13/testPHP/getScore.php");
			String data = "name="+name+"&score="+score+"&coins="+coins;
			byte[] postDataBytes = data.getBytes("UTF-8");
		    HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		    conn.setRequestMethod("POST");
		    conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		    conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
		    conn.setDoOutput(true);
		    conn.getOutputStream().write(postDataBytes);

		    Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

		    for (int c; (c = in.read()) >= 0;){
		    	System.out.print((char)c);
		    }
		} catch (MalformedURLException e) {
			System.out.println("The score was not sent");
			e.printStackTrace();
		}
	    
	}

}
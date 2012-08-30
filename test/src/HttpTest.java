import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpTest {
	String urlString;

	public static void main(String[] args) throws Exception {
		// HttpTest client = new HttpTest("http://localhost:8080/antony/x.xml");
		String u = "http://hpwapv.dskcc.net/approval/service/document.asmx/DocListForMyService?userID=06534&title=&pageNumber=&pageSize=";
		HttpTest client = new HttpTest(u);
		//
		client.run();
	}

	public HttpTest(String urlString) {
		this.urlString = urlString;
	}

	public void run() throws Exception {
		URL url = new URL(urlString);
		HttpURLConnection urlConnection = (HttpURLConnection) url
				.openConnection();
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				urlConnection.getInputStream()));
		String line;
		String x = "";
		while ((line = reader.readLine()) != null) {
			// System.out.println(line);
			// System.out.println(line);
//			x = x + new String(line.getBytes("utf-8"), "euc-kr");
			x = x + line;
		}
		// System.out.println(x.substring(74, x.length()-9));
		System.out.println("아아남어ㅏㅁ너이ㅏㅁ너이ㅏㅁ ");
		// String domStr = x.substring(0, x.length() - 9);
		System.out.println(x);
		String replaceAll1 = x.replaceAll("&lt;", "<");
		String replaceAll2 = replaceAll1.replaceAll("&gt;", ">");

		System.out.println("\n\n\n" + replaceAll2);
	}
	
}
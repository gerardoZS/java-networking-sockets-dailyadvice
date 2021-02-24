import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class DailyAdviceClient {
	private final String serverHost;
	private final int serverPort;

	public DailyAdviceClient(String serverHost, int serverPort) {
		this.serverHost = serverHost;
		this.serverPort = serverPort;
	}

	public void getAdvice() {
		System.out.println("Establishing connection with the server... [Host: " + serverHost +
						" | Port: " + serverPort);
		try (Socket socket = new Socket(serverHost, serverPort)) {
			System.out.println("Connection established! " + socket.getLocalSocketAddress());

			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String advice = reader.readLine();
			System.out.println("Advice received: " + advice);
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			System.out.println("Connection closed.");
		}
	}

	public static void main(String args[]) {
		String serverHost = args[0];
		int serverPort = Integer.parseInt(args[1]);
		DailyAdviceClient client = new DailyAdviceClient(serverHost, serverPort);
		client.getAdvice();
	}

}

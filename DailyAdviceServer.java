import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class DailyAdviceServer {
	private final int port;

	private final String[] adviceList = {"Take smaller bites.",
			"Go for the tight jeans. No they do NOT make you look fat.",
			"One word: inappropriate.",
			"Just for today, be honest.",
			"Tell your boss what you *really* think.",
			"You might want to rethink that haircut."};

	public DailyAdviceServer(int port) {
		this.port = port;
	}

	public void start() {
		try(ServerSocket serverSocket = new ServerSocket(port)) {
			System.out.println("Server initialization successful at port: " + port);

			while(true) {
				System.out.println("Waiting for a client connection...");
				Socket socket = serverSocket.accept();
				System.out.println("Client connection established! " + socket.getRemoteSocketAddress());

				PrintWriter writer = new PrintWriter(socket.getOutputStream());
				String advice = getAdvice();
				writer.println(advice);
				System.out.println("Advice sent: " + advice);
				writer.close();
				System.out.println("Client connection closed.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			System.out.println("Server shutdown!");
		}	
	}

	private String getAdvice() {
		int random = (int) (Math.random() * adviceList.length);
		return adviceList[random];
	}

	public static void main(String[] args) {
		int port = Integer.parseInt(args[0]);
		DailyAdviceServer server = new DailyAdviceServer(port);
		server.start();
	}
}

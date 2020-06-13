import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;


/**
 * This class holds any utility methods needed for P2P networking communication.
 */
public class P2PUtil {


    /**
     * Allows a one time socket call to a server, gets reply, and then closes connection.
     *
     * @param sIP
     * @param iPort
     * @param sMessage
     * @return
     */
    public static String connectForOneMessage(String sIP, int iPort, String sMessage) {
        try (Socket socket = new Socket()) {

            socket.connect(new InetSocketAddress(sIP, iPort), 5000);

            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);
            // Send the message
            writer.println(sMessage);
            writer.flush();
            // Get return message
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader((input)));

            String toReturn = reader.readLine();
            return toReturn;

        } catch (Exception ex) {
            System.out.println(String.format("[client]: Client exception: %s", ex.getMessage()));
            return null;
        }
    }
}


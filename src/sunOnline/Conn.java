package sunOnline;

import java.util.Iterator;

public class Conn extends Server {
	public static void findThread(){
		Iterator<Connection> iter = connections.iterator();
		while(iter.hasNext()) {
			Connection conn = iter.next();
			if (conn.getName().equals("2")){
				conn.out.println("It works!");
				iter.remove();
				break;
			}
		}
	}
}

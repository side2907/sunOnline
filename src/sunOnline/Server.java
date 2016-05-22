package sunOnline;

import simple.Simple;
import admin.Admin;
import admin.User;
import sunOnline.Const;

import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
	
	private static final String nC = "(Server): ";
	protected static List<Connection> connections = Collections.synchronizedList(new ArrayList<Connection>());
	Simple S = new Simple();
	Admin admin = new Admin();
	User user = new User();
	private ServerSocket server;
	public int numClient = 0;
	
	public Server() {
		S.Out(nC+"Класс Server активирован.");
		S.Out(nC+"Сокет настроен.");
		S.Out(nC+"Порт"+Const.Port+".");
		String login = "localhost";
		String pass = "290****852";
		if(admin.getUser(login, pass, numClient)==true) S.Out(admin.getBaseNC()+"База данных активна.");
		else {
			S.EOut(nC+"База данных некативна! ПРИМИТЕ МЕРЫ! ERROR S.32.A.00.01.m");
			System.exit(-1);
		}
		S.Out(nC+"Метод ожидания подключения акивен.");
		
		try {
			server = new ServerSocket(Const.Port);
			while (true) {
				Socket socket = server.accept();
				Connection con = new Connection(socket);
				connections.add(con);
				con.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
	}
		
		private void closeAll() {
			try {
				server.close();
				Iterator<Connection> iter = connections.iterator();
				while(iter.hasNext()) ((Connection) iter.next()).close();
			} catch (Exception e) {
				S.EOut("Потоки не были закрыты!");
			}
		}

		class Connection extends Thread {
			private BufferedReader in;
			PrintWriter out;
			private Socket socket;
			public Connection(Socket socket) {
				this.socket = socket;
		
				try {
					in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					out = new PrintWriter(socket.getOutputStream(), true);
		
				} catch (IOException e) {
					e.printStackTrace();
					close();
				}
			}
			@Override
			public void run() {//поток пользователя
				numClient++;
				setName(Integer.toString(numClient));
				String login = null;
				boolean getUser = false;
				try {
					String nameClient;//Проверка логин-пароль
					int hashNum = numClient;
					
					
					out.println("Логин:");
					login = in.readLine();
					out.println("Пароль:");
					String pass = in.readLine();
					getUser = admin.getUser(login, pass, numClient);
					
					
				if (getUser==false){
					out.println("false");
				}else{
					nameClient = login;
					if(Const.diag==true) nameClient+=" "+hashNum;
					if(login.equals("Admin")) nameClient+=" id:"+hashNum+" "+socket;
					S.Out(nC+"Новое подключение. Пользователь: "+nameClient);
					out.println("Подключение прошло успешно! Ваше имя: "+ nameClient);
					out.println("Список всех команд: Наберите '--help' без кавычек.");
					String str = "";
					while (true) {
						str = in.readLine();
						if(str.equals("#down")&&!login.equals("Admin")) {
							admin.setOffline(login);
							S.EOut(nC+"Пользователь "+nameClient+" вышел из сети.");
							break;
						}
						if(login.equals("Admin")){
							if (str.equals("#down")||str.equals("#Sdown")){
								String str2="#down";
								toAll(str2);
								admin.setOfflineAll();
								S.AOut(nC,str+" >> Удаленное (экстренное) отключение пользователей прошло успешно.");
									if (str.equals("#Sdown")) {
										S.AOut(nC,str+" >> Удаленное (экстренное) выключение сервера прошло успешно.");
										System.exit(0);
									}
							}else if(str.equals("test")){
								Conn.findThread();
							}else {
							
										String newStr = admin.command(str,nameClient);
										boolean newBool = admin.commandType(str);
										if (newBool==false)out.println(newStr);
										else toAll(newStr);
							}
						}else{
							String newStr = user.command(str);
							boolean newBool = user.commandType(str);
							if (newBool==false)out.println(newStr);
							else toAll(nameClient+": "+newStr);
							
						}
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
				
			} finally {
				if (getUser!=false) admin.setOffline(login);
				close();
			}
		}
			
		public void close() {//Разрываем соединение
			try {
				in.close();
				out.close();
				socket.close();
			} catch (Exception e) {
				S.EOut("Потоки не были закрыты!");
			}
		}
	}
		
		public void toAll(String a){//отправка сообщения всем пользователям
			a=S.MaxLength(a);
			Iterator<Connection> iter = connections.iterator();
			while(iter.hasNext()) ((Connection) iter.next()).out.println(a);
			iter.remove();
		}
		
		/*public void findThread(){
			Iterator<Connection> iter = connections.iterator();
			while(iter.hasNext()) {
				Connection conn = iter.next();
				if (conn.getName().equals("2")){
					conn.out.println("It works!");
					iter.remove();
					break;
				}
			}
		}*/
		
		
	
}

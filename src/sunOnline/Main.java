package sunOnline;



import simple.Simple;
import sunOnline.Server;

public class Main {

	private static final String nC = "(_Main_): ";

	public static void main(String[] args){
		Simple S = new Simple();
		S.Out(Const.version);
		S.Out(nC+"Запуск сервера...");
		new Server(); //запуск сервера
	}

}

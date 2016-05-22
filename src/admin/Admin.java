package admin;
import simple.Simple;
import admin.Base;

public class Admin{
	Simple S = new Simple();
	Base baseAccess = new Base();
	public static final String nC = "(_Admin): ";
		
	public String command(String a,String c){
		String b="";
		if (a.equals("--help")){
			b=S.MaxLength("<----------------------------------------------------------> |                          HELP                          | <---------------------------------------------------------->");
			b+=XML.Help("ahelp");
			b+="<---------------------------------------------------------->";
		}else if (a.indexOf("#>")==0){
			b=a.replaceAll("\\#|\\>","");
			b=("SYSTEM: "+b);
		}else if(a.equals("#AddS")){
			baseAccess.setOffline("localhost");
			b="Подключение дополнительного сервера возможно.";
		}else if(a.equals("#AddA")){
			baseAccess.setOffline("Admin");
			b="Подключение дополнительного администратора возможно.";
		}else if(a.equals("#Online")){
			b="В сети: "+baseAccess.getOnline();
		} 
		
		else{
			b="Неверная команда: "+c+" Наберите --help для справки.";
			c="Неверная команда: "+c;
		}
		a=c+" << "+a;
		S.AOut(nC,a);		
		return b;
	}
	
	public boolean commandType(String a){
		boolean rt = false;
		if (a.indexOf("#>")==0) rt = true;	
		else rt = false;
		return rt;	
	}

	public boolean getUser(String login, String pass,int numClient){
		return baseAccess.getUser(login, pass, numClient);	
	}
	
	public String getBaseNC(){
		return Base.nC;
	}
	
	public void setOfflineAll(){
		baseAccess.setOfflineAll();	
	}
	
	public void setOffline(String login){
		baseAccess.setOffline(login);
	}
		
}

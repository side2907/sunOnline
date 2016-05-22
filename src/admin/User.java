package admin;
import simple.Simple;

public class User {
	Simple S = new Simple();
	public static final String nC = "(_User_): ";
	
	public String command(String a){
		String b="";
		if (a.equals("--help")){
			b=S.MaxLength("<----------------------------------------------------------> |                          HELP                          | <---------------------------------------------------------->");
			b+=XML.Help("uhelp");
			b+="<---------------------------------------------------------->";
		}else if (a.indexOf("#>")==0){
			b=a.replaceAll("\\#|\\>","");
			b=("СOOБЩЕНИЕ: " + b);
		}else b=a;		
		return b;
	}
	
	public boolean commandType(String a){
		boolean rt = true;
		if (a.equals("--help")) rt = false;
		return rt;	
	}

}

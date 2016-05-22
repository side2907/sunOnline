package admin;
import simple.Simple;

public class Base{
	Simple S = new Simple();
	String[][] userList=new String[4][4];
	String red = "red1";
	public static final String nC = "(_Base_): ";
	public Base(){
		userList[0][0]="localhost";
		userList[0][1]="290****852";
		userList[1][0]="User";
		userList[1][1]="1";
		userList[2][0]="Moder";
		userList[2][1]="2";
		userList[3][0]="Admin";
		userList[3][1]="3";
		setOfflineAll();
	}

	public boolean getUser(String a,String b, int id){ //Проверка логин-пароля
		int i;
		boolean c=false;
		for(i=0;i<userList.length;i++){
			if(userList[i][0].equals(a)){
				if(userList[i][1].equals(b)&&userList[i][2].equals("false")){
					c=true;
					userList[i][2]="true";
					S.Out(nC+"Пользователь "+userList[i][0]+" успешно идентифицирован. Запрет на вторичный вход добавлен.");
					break;
				}
				else break;
			}
		}
		if (c==true){
			userList[i][3]=Integer.toString(id);
			return c;
		}
		else return c;
	}

	public void setOffline(String a){ //Установка разрешения на вход пользователя в базе
		for(int i=0;i<userList.length;i++){
			if(userList[i][0].equals(a)){
				userList[i][2]="false";
				S.Out(nC+"Запрет на вход пользователя "+userList[i][0]+" успешно отменен.");
				break;
			}
		}
	}
		
	public void setOfflineAll(){ //Установка разрешения на вход всем пользователям в базе
		for(int i=0;i<userList.length;i++){
			userList[i][2]="false";
		}
	}
		
	public String getOnline(){ //Отображение всех пользователей в сети
		String a = "";
		for(int i=0;i<userList.length;i++){
			if(userList[i][2].equals("true")){
				a+=userList[i][0]+" ";
			}
		}
		return a;
	}
		
}

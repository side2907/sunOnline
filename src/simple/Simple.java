package simple;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Simple {

	public String tDate(){//Получаем текущее время и дату
		Date d = new Date();
		SimpleDateFormat tDate = new SimpleDateFormat("ddMMyy@hh:mm:ss ");
		return tDate.format(d);
	}

	public void Out(String a){//Вывод строки с переходом
		System.out.println(tDate()+a);
	}

	public void EOut(String a){//Вывод строки с ошибкой и переходом
		System.err.println(tDate()+a);
	}

	public void AOut(String a,String b){//Вывод строки команды администратора с переходом
		System.out.println(tDate()+a+b);
	}

	public void OutL(String a){//Вывод строки без даты и перехода
		System.out.print(a);
	}
	
	public String MaxLength(String a){//Сокращает строки по 60 знаков
		int max=60;
		String b="";	
		int i=(a.length()/max);
		if (a.length()%max!=0) i++;		
		for (int j=0;j<i;j++){
			if (j==i-1){
				b+=a.substring(j*max, a.length());
			}else{
				b+=a.substring(j*max, (j*max)+max)+"\n";
			}
		}
		return b;
	}

}

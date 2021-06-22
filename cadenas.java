package semana1;

import org.openqa.selenium.By;

public class cadenas {

	public static void main(String[] args) throws InterruptedException {
		String mensaje="HOLA MUNDO";
		cadenas puente= new cadenas();
		System.out.println(mensaje);
		System.out.println(mensaje.length());
		String sSubCadena = mensaje.substring(1);
		System.out.println(sSubCadena);
		String subCadena=mensaje.substring(0, 4);
		System.out.println(subCadena);
		puente.ingresar(subCadena);
		System.out.println(subCadena);
		
	}
	public static void ingresar(String subCadena) {
		
			subCadena="Mundo Hola";
			String nuevaCadena=subCadena.substring(0, 4);
		
	}

}

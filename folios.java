package semana1;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
public class folios {
    public static void main(String ars[]){
        try {
            String ruta = "C:\\Users\\Mario\\Desktop\\Metlife\\Ola2\\LayoutQA\\LSP\\NC\\LSP_2020100822531001.txt";
            String contenido = null;
            File file = new File(ruta);
            // Si el archivo no existe es creado
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            int b=0;
            int c=1002400;
            for(int a=2001011;a<=2002011;a++) {	
            	/*
        		//////NC 
            	
            	contenido="COMP|HP|"+c+"|112.68|0.00|USD|22.0000|112.68|E|NO IDENTIFICADO|99|\r\n" + 
            			"FIND|H|"+a+"\r\n" + 
            			"RCPTR|PUAM691112ST3|MARIO ALBERTO PUENTE AUN|||G03|MEXICANA|FISICA\r\n" + 
            			"CNPT|84131601|SEGUROS DE VIDA Prima|111.08|111.08|0.00|\r\n" + 
            			"TRSLD|111.08|002|EXENTO|||\r\n" + 
            			"CNPT|84131601|SEGUROS DE VIDA Tasa Financ. Pago Fracc.|1.60|1.60|0.00|\r\n" + 
            			"TRSLD|1.60|002|EXENTO|||\r\n" + 
            			"VARIA1|LSP|1|FORMA1||774/77409a/Tradicional/Servicios/Impresion|12:58:20|IN|||\r\n" + 
            			"VARIA2|8230522|TEMPORAL 10 EDUCA|12/12|16|MAY|2020|16|JUN|2020|U.S.DOLLAR\r\n" + 
            			"VARIA3|15|JUN|2020|6|Alejandro Gonza|212|774|NO-DEDUCIBLE|10662|U.S.DOLL\r\n" + 
            			"VARIA4|JUL|20|TEMPORAL 10 EDUCALIFE|10662\r\n" + 
            			"VARIA5||3.68|||0.00|0.00|I.V.A.:|0.00|Total a Pagar:|(CIENTO DOCE DLLS. 68/100)|\r\n" + 
            			"VARIA6||2992140616|TRAD0000000082305229||BBVA BANCOMER CIE 628492|BANAMEX 870 56655-3|HSBC RAP 7202|BANORTE CONVENIO 53971|MEXICO, D.F.|ROSALES ORNELAS MARIA EMMA\r\n" + 
            			"VARIA7||0|||1 DE 1|20140616|E3177633|\r\n" + 
            			"VARIA8|3.50%|0.90%|000010662|100.00%|3.68||000.00%|0.00|000000000|0000000000\r\n" + 
            			"VARIA9|0.00|0.00|ATARDECER 7041 SENDERO LAS MORAS|TLAJOMULCO DE ZUNIGA JALISCO 45645|2020|6|16|0.00|MENSUAL|00\r\n";
            			
            	*/
            	/////Facturas
            	contenido="COMP|H|"+a+"|112.68|0.00|USD|22.0000|112.68|I|NO IDENTIFICADO|99|\r\n" + 
            			"RCPTR|PUAM691112ST3|MARIO ALBERTO PUENTE AUN|||G03|MEXICANA|FISICA\r\n" + 
            			"CNPT|84131601|SEGUROS DE VIDA Prima|111.08|111.08|0.00|\r\n" + 
            			"TRSLD|111.08|002|EXENTO|||\r\n" + 
            			"CNPT|84131601|SEGUROS DE VIDA Tasa Financ. Pago Fracc.|1.60|1.60|0.00|\r\n" + 
            			"TRSLD|1.60|002|EXENTO|||\r\n" + 
            			"VARIA1|LSP|1|FORMA1||774/77409a/Tradicional/Servicios/Impresion|12:58:20|IN|||\r\n" + 
            			"VARIA2|8230522|TEMPORAL 10 EDUCA|12/12|16|MAY|2020|16|JUN|2020|U.S.DOLLAR\r\n" + 
            			"VARIA3|15|JUN|2020|6|Alejandro Gonza|212|774|NO-DEDUCIBLE|10662|U.S.DOLL\r\n" + 
            			"VARIA4|JUL|20|TEMPORAL 10 EDUCALIFE|10662\r\n" + 
            			"VARIA5||3.68|||0.00|0.00|I.V.A.:|0.00|Total a Pagar:|(CIENTO DOCE DLLS. 68/100)|\r\n" + 
            			"VARIA6||2992140616|TRAD0000000082305229||BBVA BANCOMER CIE 628492|BANAMEX 870 56655-3|HSBC RAP 7202|BANORTE CONVENIO 53971|MEXICO, D.F.|ROSALES ORNELAS MARIA EMMA\r\n" + 
            			"VARIA7||0|||1 DE 1|20140616|E3177633|\r\n" + 
            			"VARIA8|3.50%|0.90%|000010662|100.00%|3.68||000.00%|0.00|000000000|0000000000\r\n" + 
            			"VARIA9|0.00|0.00|ATARDECER 7041 SENDERO LAS MORAS|TLAJOMULCO DE ZUNIGA JALISCO 45645|2020|6|16|0.00|MENSUAL|00\r\n";
            	/*
            /////PAgos
            	contenido="COMP|HP|"+c+"|112.68|0.00|USD|22.0000|112.68|P|TARJETA DE DEBITO|28|\r\n" + 
            			"RCPTR|PUAM691112ST3|MARIO ALBERTO PUENTE AUN|||G03|MEXICANA|FISICA\r\n" + 
            			"CNPT|84131601|SEGUROS DE VIDA Prima|100.47|100.47|0.00|\r\n" + 
            			"TRSLD|100.47|002|EXENTO|||\r\n" + 
            			"CNPT|84131601|SEGUROS DE VIDA Tasa Financ. Pago Fracc.|12.21|12.21|0.00|\r\n" + 
            			"TRSLD|12.21|002|EXENTO|||\r\n" + 
            			"VARIA1|LSP|3|NO APLICA|NO APLICA|NO APLICA|161838|ET|||\r\n" + 
            			"VARIA2|8332672|PERFECTLIFE 10|07/12|08|JUL|2020|08|AGO|2020|PESOS\r\n" + 
            			"VARIA3|07|AGO|2020|1|CESAR HERRERA N|112|780||15976|PESOS\r\n" + 
            			"VARIA4|SEP|20|PERFECTLIFE 10                                2015|15976\r\n" + 
            			"VARIA5||728.02|||0.00|0.00|I.V.A.:|0.00|Total a Pagar:|(CIENTO Y DOCE DOLARES 68/100 DLS.)|\r\n" + 
            			"VARIA6||2020006022|TRAD0000000083326729||BBVA BANCOMER CIE 628492|BANAMEX 870 56655-3|HSBC RAP 7202|BANORTE CONVENIO 53971|MEXICO, D.F.|LLAMAS PALOMAR MARIA JOSE\r\n" + 
            			"VARIA7|0011|0|||1 DE 1|20200108|AQ965809|\r\n" + 
            			"VARIA8|35.00%|8.00%|000015976|100.00%|728.02||000.00%|0.00|000000000|0000000000\r\n" + 
            			"VARIA9|0.00|0.00|C DE LA QUEBRADA 4634 FRACC LOMAS DE SAN ISIDRO CULI|CULIACAN SINALOA 80014|2020|8|8|0.00|MENSUAL|05\r\n" + 
            			"PAGO|2020-09-01T00:00:00|28|MXN||112.68|||||||\r\n" + 
            			"PDRL||H|"+a+"|USD|22.0000||||112.68|\r\n";
            	*/
        		bw.write(contenido);
        		b++;
        		c++;
            }
           contenido="CONT|  " + b ;
           bw.write(contenido);
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
package modelo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Paciente {

	public Paciente() {
		// TODO Auto-generated constructor stub
	}
	
	public String calcularEdad(Date fechaNacimiento) {
        Date d = new Date();
        SimpleDateFormat sdfDia = new SimpleDateFormat("dd");
        SimpleDateFormat sdfMes = new SimpleDateFormat("MM");
        SimpleDateFormat sdfA�o = new SimpleDateFormat("yyyy");
        int a�o = Integer.parseInt(sdfA�o.format(d)) - Integer.parseInt(sdfA�o.format(fechaNacimiento));
        int mes = Integer.parseInt(sdfMes.format(d)) - Integer.parseInt(sdfMes.format(fechaNacimiento));
        int dia = Integer.parseInt(sdfDia.format(d)) - Integer.parseInt(sdfDia.format(fechaNacimiento));

        if (mes < 0) {
            a�o = a�o - 1;
            mes = 12 + mes;
        }

        if (dia < 0) {
            mes = mes - 1;

            switch (Integer.parseInt(sdfMes.format(d))) {
                case 2:
                    int ano = Integer.parseInt(sdfA�o.format(d));
                    if ((ano % 4 == 0) && ((ano % 100 != 0) || (ano % 400 == 0))) {
                        dia = 29 + dia;
                    } else {
                        dia = 28 + dia;
                    }
                    break;
                case 4:
                case 6:
                case 9:
                case 10:
                case 11:
                    dia = 30 + dia;
                    break;
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 12:
                    dia = 31 + dia;
                    break;
            }
        }
        if (mes < 0) {
            a�o = a�o - 1;
            mes = 12 + mes;
        }
        String edad = a�o + " a�os con " + mes + " meses y " + dia + " d�as";
        return edad;
    }
     
	
}

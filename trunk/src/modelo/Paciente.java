package modelo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Paciente {

	private long dni;
	private String tipoDoc;
	private String sexo;
	private String nombre;
	private String apellido;
	private long celular;
	private long telefono;
	private Date fechaNaciemiento;
	private String email;
	private ObraSocial obraSocial;
	private float nroAfiliado;
		

	public Paciente(long dni, String tipoDoc, String sexo, String nombre,
			String apellido, long celular, long telefono,
			Date fechaNaciemiento, String email, ObraSocial obraSocial,
			float nroAfiliado) {
		super();
		this.dni = dni;
		this.tipoDoc = tipoDoc;
		this.sexo = sexo;
		this.nombre = nombre;
		this.apellido = apellido;
		this.celular = celular;
		this.telefono = telefono;
		this.fechaNaciemiento = fechaNaciemiento;
		this.email = email;
		this.obraSocial = obraSocial;
		this.nroAfiliado = nroAfiliado;
	}

	public Paciente(){};
	
	public String calcularEdad(Date fechaNacimiento) {
        Date d = new Date();
        SimpleDateFormat sdfDia = new SimpleDateFormat("dd");
        SimpleDateFormat sdfMes = new SimpleDateFormat("MM");
        SimpleDateFormat sdfAnio = new SimpleDateFormat("yyyy");
        int anio = Integer.parseInt(sdfAnio.format(d)) - Integer.parseInt(sdfAnio.format(fechaNacimiento));
        int mes = Integer.parseInt(sdfMes.format(d)) - Integer.parseInt(sdfMes.format(fechaNacimiento));
        int dia = Integer.parseInt(sdfDia.format(d)) - Integer.parseInt(sdfDia.format(fechaNacimiento));

        if (mes < 0) {
            anio = anio - 1;
            mes = 12 + mes;
        }

        if (dia < 0) {
            mes = mes - 1;

            switch (Integer.parseInt(sdfMes.format(d))) {
                case 2:
                    int ano = Integer.parseInt(sdfAnio.format(d));
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
            anio = anio - 1;
            mes = 12 + mes;
        }
        String edad = anio + " años con " + mes + " meses y " + dia + " dias";
        return edad;
    }


	public long getDni() {
		return dni;
	}


	public String getTipoDoc() {
		return tipoDoc;
	}

	public String getSexo() {
		return sexo;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public long getCelular() {
		return celular;
	}

	public long getTelefono() {
		return telefono;
	}

	public Date getFechaNaciemiento() {
		return fechaNaciemiento;
	}

	public String getEmail() {
		return email;
	}

	public ObraSocial getObraSocial() {
		return obraSocial;
	}

	public float getNroAfiliado() {
		return nroAfiliado;
	}

	public void setDni(long dni) {
		this.dni = dni;
	}

	public void setTipoDoc(String tipoDoc) {
		this.tipoDoc = tipoDoc;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public void setCelular(long celular) {
		this.celular = celular;
	}

	public void setTelefono(long telefono) {
		this.telefono = telefono;
	}

	public void setFechaNaciemiento(Date fechaNaciemiento) {
		this.fechaNaciemiento = fechaNaciemiento;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setObraSocial(ObraSocial obraSocial) {
		this.obraSocial = obraSocial;
	}

	public void setNroAfiliado(float nroAfiliado) {
		this.nroAfiliado = nroAfiliado;
	}

	
	
}

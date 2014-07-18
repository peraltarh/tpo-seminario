package modelo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import controlador.Sistema;

public class Paciente {

	private String nroDoc;
	private String tipoDoc;
	private String sexo;
	private String nombre;
	private String apellido;
	private long celular;
	private long telefono;
	private Date fechaNaciemiento;
	private String email;
	private ArrayList<ObraSocial> obrasSociales;
	private ArrayList<Integer> nroAfiliadoOSs;
		

	public Paciente(String nroDoc, String tipoDoc, String sexo, String nombre,
			String apellido, long celular, long telefono,
			Date fechaNaciemiento, String email) {
		
		super();
		this.nroDoc = nroDoc;
		this.tipoDoc = tipoDoc;
		this.sexo = sexo;
		this.nombre = nombre;
		this.apellido = apellido;
		this.celular = celular;
		this.telefono = telefono;
		this.fechaNaciemiento = fechaNaciemiento;
		this.email = email;
		this.obrasSociales = new ArrayList<ObraSocial>();
		this.nroAfiliadoOSs = new ArrayList<Integer>();
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
        String edad = anio + " a�os con " + mes + " meses y " + dia + " dias";
        return edad;
    }


	public String getDni() {
		return nroDoc;
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

	public ArrayList<ObraSocial> getObrasSociales() {
		return obrasSociales;
	}

	public ArrayList<Integer> getNroAfiliado() {
		return nroAfiliadoOSs;
	}

	public void setDni(String dni) {
		this.nroDoc = dni;
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

	public void addObraSocial(ObraSocial obraSocial,int nroAfiliado) {
		if(obrasSociales==null)obrasSociales=new ArrayList<ObraSocial>();
		this.obrasSociales.add(obraSocial);
		if(nroAfiliadoOSs==null)nroAfiliadoOSs=new ArrayList<Integer>();
		this.nroAfiliadoOSs.add(nroAfiliado);
	}

	public void addObraSocial(String obraSocial, String nroAfiliado) {
		if(obrasSociales==null)obrasSociales=new ArrayList<ObraSocial>();
		this.obrasSociales.add(Sistema.getInstancia().buscarObrasocial(obraSocial));
		if(nroAfiliadoOSs==null)nroAfiliadoOSs=new ArrayList<Integer>();
		this.nroAfiliadoOSs.add(Integer.valueOf(nroAfiliado));
		
	}

	public boolean sosPaciente(String nroDoc, String tipo) {
		if(this.nroDoc.equals(nroDoc)&&this.tipoDoc.equals(tipo))return true;
		return false;
	}


	
	
}

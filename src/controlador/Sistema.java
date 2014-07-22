package controlador;


import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


import java.util.GregorianCalendar;

import DTO.HistoriaClinicaDTO;
import DTO.PacienteDTO;
import DTO.UsuarioDTO;


import persistencia.AdministradorPersistenciaAuditoria;
import persistencia.AdministradorPersistenciaHCE;
import persistencia.AdministradorPersistenciaNomenclador;
import persistencia.AdministradorPersistenciaPaciente;
import persistencia.AdministradorPersistenciaPracticaAmbulatoria;
import persistencia.AdministradorPersistenciaPracticaQuirurgica;
import persistencia.AdministradorPersistenciaPrestacion;
import persistencia.AdministradorPersistenciaUsuario;
import persistencia.AdministradorPresistenciaObrasSociales;
import persistencia.PoolConnection;



import modelo.*;


public class Sistema{
	
	
	
	private static Sistema instancia;
	private ArrayList<Usuario> usuarios;
	private ArrayList<String> permisos;
	private ArrayList<HistoriaClinica> historiasClinicas;
	private ArrayList<ObraSocial> obrasSociales;
	private ArrayList<Prestacion> prestaciones;
	private ArrayList<Nomenclador> nomencladores;
	private ArrayList<Paciente> pacientes;
	
	
	private UsuarioDTO usuarioActual;
	
	//OK
	private Sistema() {
		usuarios = new ArrayList<Usuario>();
		permisos = new ArrayList<String>();
		historiasClinicas = new ArrayList<HistoriaClinica>();
		pacientes = new ArrayList<Paciente>();
	}
	
	

	//OK
	public static Sistema getInstancia(){
		if(instancia == null)
			instancia = new Sistema();
		return instancia;
	}
	
	//OK
	public java.sql.Date fechaActual(){
		
		Calendar calendar = Calendar.getInstance();
		
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		java.sql.Date f = new java.sql.Date(calendar.getTime().getTime());
		return f;
				
	}
	
	//OK
	public Date fechaActualMasUnDia(){
		
		Calendar calendar = Calendar.getInstance();
		
		calendar.add(Calendar.DATE,1);//Le suma una dia
		
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}
	
	//OK
	public boolean validarContrasenaActual(int dni , String psw){
		
		Usuario u = buscarUsuario(dni) ;
				
		if (u!=null){
			if(psw.equals(u.getPassword())){
				return true;
			}else
				return false;
		}else
			return false;
	}

	//OK
	public boolean validarLogin(String userName, String password){
		
		String pswEnc = Encriptacion.Encriptar(password);
		
		Usuario u = this.buscarUsuario(userName, pswEnc) ;
		
		if (u!=null){
			
			if(pswEnc.equals(u.getPassword())){
				usuarioActual = u.getView();
				return true;
			}
			else
				return false;
		}
		return false;
	}
	
	//OK
	public boolean cambiarContrasena(int dni, String psw){
		
		psw = Encriptacion.Encriptar(psw);
		
		Usuario u = buscarUsuario(dni) ;
			
			if (u!=null){
				u.setPassword(psw);
				u.updateContrasena();
				return true;
			}else
				return false;
	}
	
	//OK
	public boolean borrarUsuario(int dni){
		Usuario u = buscarUsuario(dni) ;
		
			if (u!=null){
				u.setBorrado(true);
				u.updateBorrado(dni);
				return true;
			}else
				return false;
	}
	
	//OK
	public boolean probarConexion(){
		
		Connection con = PoolConnection.getPoolConnection().getConnection();
		if (con == null){
			return false;
		}else
		return true;
	}
	
	//OK
	public Usuario buscarUsuario(String userName, String password){
		
		Usuario u = null ;
		
		if (!usuarios.isEmpty()){
		for (Usuario usuarioTemp : this.usuarios) {
			if (usuarioTemp.getUserName().equals(userName)){
				u = usuarioTemp;
				break;
			}
		}
		}
		if (u == null){
			
		u = new Usuario().buscarUsuario(userName);
		this.usuarios.add(u);
		
		}
		
		if (u!=null){
			return u;
		}else
		return null;
	}
	
	//OK
	public Usuario buscarUsuario(int dni){
		
		Usuario u = null ;
		
		if (!usuarios.isEmpty()){
		for (Usuario usuarioTemp : this.usuarios) {
			if (usuarioTemp.getDni() == dni)
			{
				u = usuarioTemp;
				break;
			}
		}
		}
		
		if (u == null){
		u = new Usuario().buscarUsuario(dni);
		this.usuarios.add(u);
		}
		
		return u;
	}
	
	
	//OK
	@SuppressWarnings("unchecked")
	public ArrayList<String> getAllPermisos(){
			
			ArrayList<String> vecPermisoDTO = new ArrayList<String>();
			
			if(permisos.size() == 0){
				
			}

			if (!permisos.isEmpty()) {return (ArrayList<String>)permisos.clone();}
			else{permisos = AdministradorPersistenciaUsuario.getInstancia().getEspecialidades();	}

			return vecPermisoDTO;
		}
		
	
	public ArrayList<UsuarioDTO> getUsuarios (){
		ArrayList<UsuarioDTO> vecUsuarioDTO = new ArrayList<UsuarioDTO>();
		
		if(usuarios.size() <= 1  ){//si tengo uno es el logueado
			usuarios = new Usuario().buscarAll();	
		}
		
		for(int i=0;i<usuarios.size();i++){
			vecUsuarioDTO.add(usuarios.get(i).getView());	
		}
			
		return vecUsuarioDTO;
		
	}
	
	
	public UsuarioDTO getUsuario(int dni){
		Usuario u = buscarUsuario(dni);
		if (u==null){
			return null;
		}else{
			usuarioActual  = u.getView();
			return usuarioActual;	
		}
		
	}
	
	
	public UsuarioDTO getUsuarioActual() {
		return usuarioActual;
	}

	
	public boolean altaUsuario(String nombre, String apellido, int dni, String matricula, String userName, String password, ArrayList<String> Permisos ){
		
		Usuario u = buscarUsuario(dni);
		ArrayList<String> vp = new ArrayList<String>();
		vp=Permisos;
		
		if(u == null){
			u = new Usuario(nombre, apellido, matricula, dni, userName, password);

			u.setEspecialidades(vp);
			u.savePermisos();
			usuarios.add(u);
			return true;
		}else{
			return false;
		}
	}
	
	
	public boolean modificarUsuario(String nombre, String apellido, int dni, String matricula, String userName, 
		String password, ArrayList<String> Permisos, boolean borrado){
		
		Usuario u = buscarUsuario(dni);
		ArrayList<String> vp = new ArrayList<String>();
		vp=Permisos;
		
		if(u != null){
			u.modificarUsuario(nombre, apellido, matricula, userName, password, borrado);
					
			u.setEspecialidades(vp);
			return true;
		}else{
			return false;
		}
	}
	
	
	public boolean validarPermiso (String permiso){
		ArrayList<String> vp = new ArrayList<String>();
		vp=usuarioActual.getEspecialidades();

		for(String string : vp)
			if (string.matches(".*"+permiso+".*"))
				return true;
		return false;
		
	}
	
	
	public boolean backup(){
		
		String path = "C:/";
		
		Connection con = PoolConnection.getPoolConnection().getConnection();
		

		if (con!=null){
			String dataBaseName =  PoolConnection.getPoolConnection().getDataBaseName();
			String comand ="BACKUP DATABASE " +  dataBaseName + " TO DISK = "; 
			
			
			 File miDir = new File (".");
			 try {
				  path = miDir.getCanonicalPath()+"/";
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			
			DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss"); 
			Date date = new Date(); 
			dateFormat.format(date); 
			
			String nameBackup = dataBaseName+ "_"+dateFormat.format(date)+".BAK"; 
					
			String dbackup =comand + "'"+ path +nameBackup+"'";
		
			Statement callBackupDbase = null;
			
			try {
				con.setAutoCommit(true);
				callBackupDbase = con.createStatement();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			if(callBackupDbase != null){
				try {
					callBackupDbase.execute(dbackup);
					return true;
				}catch (SQLException e) {
					e.printStackTrace();
					return false;
				}
			}else return false;
			
			
		}else
			return false;
		
		
	}

	
	
	public ArrayList<PacienteDTO> getPacientes() {

		ArrayList<PacienteDTO> pacientesDTO = new ArrayList<PacienteDTO>();
		

			this.pacientes = AdministradorPersistenciaPaciente.getInstancia().buscarAll();

		
		for (Paciente pacienteTemp : this.pacientes) {
			pacientesDTO.add(new PacienteDTO(pacienteTemp));
		}
		
		return pacientesDTO;
	}


	public PacienteDTO getPaciente(String nroDoc, String tipoDoc) {
		
		for (Paciente pacienteTemp : this.pacientes) {
			if(String.valueOf(pacienteTemp.getDni()).equals(nroDoc) && pacienteTemp.getTipoDoc().equals(tipoDoc)){
				return new PacienteDTO(pacienteTemp);
			}
		}
		
		Paciente pacienteTemp = AdministradorPersistenciaPaciente.getInstancia().buscarPaciente(nroDoc, tipoDoc);
		
		if(pacienteTemp == null) return null;
		
		this.pacientes.add(pacienteTemp);
		
		String auditar="Se busco HCE con dni\t"+nroDoc+"\ttipo documento\t"+tipoDoc;
		AdministradorPersistenciaAuditoria.getInstancia().auditar(Sistema.getInstancia().getUsuarioActual(),auditar);
		
		return new PacienteDTO(pacienteTemp);
		
		
	}

	public ArrayList<String> getPrestaciones() {
		
		ArrayList<String> prestacionesDesc=new ArrayList<String>();
		prestacionesDesc=AdministradorPersistenciaPrestacion.getInstancia().getDescripcionPrestaciones();
		return prestacionesDesc;
		
	}
	
	
	public void cargaInicial(){
		//Debe seguir este orden ya que Nomencaldor necesita Obras Sociales y Prestaciones
		cargarObrasSociales();
		cargarPrestaciones();
		cargarNomenclador();
		
	}
	
	
	public void cargarPrestaciones() {
		prestaciones = new ArrayList<Prestacion>();
		ArrayList<String> prestacionesDesc=new ArrayList<String>();
		prestacionesDesc=AdministradorPersistenciaPrestacion.getInstancia().getDescripcionPrestaciones();

		for (String string : prestacionesDesc) {
			prestaciones.add(new Prestacion(string));
		}
		
		
	}
	
	private void cargarObrasSociales() {
		obrasSociales = new ArrayList<ObraSocial>();
		ArrayList<String> obrasSocialesTemp = AdministradorPresistenciaObrasSociales.getInstancia().getObrasSociales();
		
		for (String string : obrasSocialesTemp) {
			obrasSociales.add(new ObraSocial(string));
		}

	}
	
	private void cargarNomenclador(){
		nomencladores = new ArrayList<Nomenclador>();
		nomencladores.addAll(AdministradorPersistenciaNomenclador.getInstancia().getAll());
	}

	public ObraSocial buscarObrasocial(String obraSocial) {
		for (ObraSocial obraSocialTemp : obrasSociales) {
			if(obraSocialTemp.getRazonSocial().equals(obraSocial)) return obraSocialTemp;
		}
		return null;
	}

	public Prestacion buscarPrestaion(String prestacion) {
		for (Prestacion prestacionTemp : prestaciones) {
			if(prestacionTemp.getDescripcion().equals(prestacion)) return prestacionTemp;
		}
		return null;
	}

	public ArrayList<String> getPracticasAmbulatoriasObraSocial(String obraSocial) {
		
		ArrayList<String> praticas = new ArrayList<String>();
		
		for (Nomenclador nomencladorTemp : nomencladores) {
			if(nomencladorTemp.getObraSocial().getRazonSocial().equals(obraSocial) && nomencladorTemp.getPrestacion().getDescripcion().contains("PA -")){
				praticas.add(nomencladorTemp.getPrestacion().getDescripcion());
			}
		}

		return praticas;
	}
	
	public ArrayList<String> getPracticasQuirurjicasObraSocial(String obraSocial) {
		
		ArrayList<String> praticas = new ArrayList<String>();
		
		for (Nomenclador nomencladorTemp : nomencladores) {
			if(nomencladorTemp.getObraSocial().getRazonSocial().equals(obraSocial) && nomencladorTemp.getPrestacion().getDescripcion().contains("PQ -")){
				praticas.add(nomencladorTemp.getPrestacion().getDescripcion());
			}
		}

		return praticas;
	}



	@SuppressWarnings({ "unchecked", "rawtypes"})
	public boolean altaPracticaQuirurjica(String prestacion,
			UsuarioDTO usuarioActual, String ojo, String diagnostico,
			String monitoreo, String hsIni, String hsFin, String anestecia,
			Date dateString, String nroDoc, String tipoDoc) {

		
		HistoriaClinica hce = buscarHCE(nroDoc, tipoDoc);
		
		if (hce == null){
			return false;
		}else{
			
			PracticaQuirurgica pqTemp = new PracticaQuirurgica(prestacion, ojo, diagnostico, monitoreo, hsIni, hsFin, anestecia, usuarioActual.getApellido() + " " + usuarioActual.getNombre());
			

			//Date dTemp = GregorianCalendar.getInstance().getTime();
			
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String dateText= format.format(dateString);
			
			hce.addPractica(new itemHistoriaClinica(dateString, pqTemp));
				
			AdministradorPersistenciaPracticaQuirurgica.getInstancia().altaCirugia(
					prestacion,
					usuarioActual,
					ojo,
					diagnostico, 
					monitoreo,
					hsIni, hsFin, 
					anestecia,
					dateText, nroDoc,
					tipoDoc);
			
			PacienteDTO pacienteDTOAct = getPaciente(nroDoc, tipoDoc);
			
			String auditar="Se creo una Practica Quirurgica y se asocio al Paciente \t"+pacienteDTOAct.getNombre()+"\t"+pacienteDTOAct.getApellido();
			AdministradorPersistenciaAuditoria.getInstancia().auditar(Sistema.getInstancia().getUsuarioActual(),auditar);
			
			return true;
			
		}
			
		
		
		
	

	}



	public Paciente buscarPaciente(String tipoDoc, String nroDoc) {
		for (Paciente pacienteTemp : this.pacientes) {
			if(String.valueOf(pacienteTemp.getDni()).equals(nroDoc) && pacienteTemp.getTipoDoc().equals(tipoDoc)){
				return pacienteTemp;
			}
		}
		
		Paciente pacienteTemp = AdministradorPersistenciaPaciente.getInstancia().buscarPaciente(nroDoc, tipoDoc);
		
		this.pacientes.add(pacienteTemp);
		
		return pacienteTemp;
	}

	public HistoriaClinicaDTO getHCE (String nroDoc, String tipoDoc) {
		
		HistoriaClinica hceTemp = buscarHCE ( nroDoc,  tipoDoc);

		return new HistoriaClinicaDTO(hceTemp);
	}
	
	
	public HistoriaClinica buscarHCE (String nroDoc, String tipoDoc) {
//		for (HistoriaClinica historiaClinica : historiasClinicas) {
//			if(historiaClinica.tenesPaciente(nroDoc,tipoDoc)){
//				return historiaClinica;
//			}
//		}
		
		HistoriaClinica hceTemp = AdministradorPersistenciaHCE.getInstancia().buscarHistoriaClinica(tipoDoc, nroDoc);
		this.historiasClinicas.add(hceTemp);
		return hceTemp;
	}




	public boolean altaPracticaAmbulatoria(String prestacion,
			UsuarioDTO usuarioActual2, String ojo, String diagnostico,
			Date dateString, String nroDoc, String tipoDoc)
	{
			
		
			HistoriaClinica hce = buscarHCE(nroDoc, tipoDoc);
			
			if (hce==null){
				return false;
			}else{
			
				PracticaAmbulatoria paTemp = new PracticaAmbulatoria(prestacion, ojo, diagnostico, usuarioActual.getApellido() + " " + usuarioActual.getNombre());
				
				//Date dTemp = GregorianCalendar.getInstance().getTime();
				
				DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				String dateText= format.format(dateString);
							
				hce.addPractica(new itemHistoriaClinica(dateString, paTemp));

				AdministradorPersistenciaPracticaAmbulatoria.getInstancia().altaAmbulatoria(prestacion,usuarioActual2,ojo,diagnostico,dateText,nroDoc,tipoDoc);
				PacienteDTO pacienteDTOAct = getPaciente(nroDoc, tipoDoc);
				String auditar="Se creo una Practica Ambulatoria y se asocio al Paciente \t"+pacienteDTOAct.getNombre()+"\t"+pacienteDTOAct.getApellido();
				AdministradorPersistenciaAuditoria.getInstancia().auditar(usuarioActual2,auditar);
				return true;
			}
			
	}



	/*public void Auditar(UsuarioDTO usuarioDTO, String auditar) {
		AdministradorPersistenciaAuditoria.getInstancia().auditar(usuarioDTO,auditar);
		
	}*/
	

}
	

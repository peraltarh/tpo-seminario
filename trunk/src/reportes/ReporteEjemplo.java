package reportes;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import persistencia.PoolConnection;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;

public class ReporteEjemplo {

	private File file;

	public ReporteEjemplo() throws JRException {
		
		Connection con = PoolConnection.getPoolConnection().getConnection();
		
		if (con!= null){
			JasperReport reporte = (JasperReport) JRLoader.loadObject("usuarios.jasper");
			String tempName = "tempPdf";
			try {
				file = File.createTempFile(tempName, ".pdf", new File("C:/"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
						
			JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, null, con);
			JRExporter exporter = new JRPdfExporter();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRExporterParameter.OUTPUT_FILE,file);
			exporter.exportReport();
	      
	      try {
			     Desktop.getDesktop().open(file);
			}catch (IOException ex) {
			     ex.printStackTrace();
			}
			
		}
		
		file.deleteOnExit(); //elimina el archivo cuando termina el programa
		PoolConnection.getPoolConnection().realeaseConnection(con);
	
	}

}

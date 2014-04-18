package reportes;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import persistencia.PoolConnection;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;

public class reporte {

	public reporte() throws JRException {
		
		Connection con = PoolConnection.getPoolConnection().getConnection();
		
		if (con!= null){
			JasperReport reporte = (JasperReport) JRLoader.loadObject("Template_reporte.jasper");
			
			JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, null, con);

			JRExporter exporter = new JRPdfExporter();
			
			File file = new File ("Reportes/reportePDF.pdf");
			
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRExporterParameter.OUTPUT_FILE,file);
			
			
	      // JasperExportManager.exportReportToPdfFile( jasperPrint, "c:/reporte.pdf");
			
	      exporter.exportReport();
	      
	      try {
			     //File path = new File ("reportePDF.pdf");
			     Desktop.getDesktop().open(file);
			}catch (IOException ex) {
			     ex.printStackTrace();
			}

			
			
		}
		
				
		PoolConnection.getPoolConnection().realeaseConnection(con);
	
	}

}

package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.Nomenclador;

public class AdministradorPersistenciaNomenclador {

	static AdministradorPersistenciaNomenclador pool = null;

	private AdministradorPersistenciaNomenclador() {
	};

	public static AdministradorPersistenciaNomenclador getInstancia() {
		if (pool == null)
			pool = new AdministradorPersistenciaNomenclador();
		return pool;
	}

	public ArrayList<Nomenclador> getAll() {

		ArrayList<Nomenclador> nomencladoresTemp = new ArrayList<Nomenclador>();

		Connection con = PoolConnection.getPoolConnection().getConnection();

		try {
			String senten = "SELECT descripcion,precio,razonSocial FROM Prestacion,(SELECT idPrestacion,precio,razonSocial FROM Nomenclador, ObraSocial WHERE Nomenclador.idObraSocial =  ObraSocial.idObraSocial) AS nomencTemp WHERE nomencTemp.idPrestacion = Prestacion.idPrestacion;";
			PreparedStatement ps = con.prepareStatement(senten);
			ResultSet result = ps.executeQuery();

			while (result.next()) {

				
				Nomenclador nomencladorTemp = new Nomenclador(result.getString("razonSocial"), result.getString("descripcion"),
						result.getString("precio"));
				
				nomencladoresTemp.add(nomencladorTemp);

			}

			PoolConnection.getPoolConnection().realeaseConnection(con);
		} catch (SQLException e) {
			System.out.println("Mensaje Error Carga Nomenclador: "
					+ e.getMessage());
			e.printStackTrace();
			PoolConnection.getPoolConnection().realeaseConnection(con);
		}

		return nomencladoresTemp;

	}

}

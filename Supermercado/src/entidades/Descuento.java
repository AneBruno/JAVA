package entidades;

import java.sql.Date;

public class Descuento {

	private int idDcto;
	private Date fechaDctoFin, fechDctoInicio;
	private Double porcDcto;
	
	public int getIdDcto() {
		return idDcto;
	}
	public void setIdDcto(int idDcto) {
		this.idDcto = idDcto;
	}
	public Date getFechaDctoFin() {
		return fechaDctoFin;
	}
	public void setFechaDctoFin(Date fechaDctoFin) {
		this.fechaDctoFin = fechaDctoFin;
	}
	public Date getFechDctoInicio() {
		return fechDctoInicio;
	}
	public void setFechDctoInicio(Date fechDctoInicio) {
		this.fechDctoInicio = fechDctoInicio;
	}
	public Double getPorcDcto() {
		return porcDcto;
	}
	public void setPorcDcto(Double porcDcto) {
		this.porcDcto = porcDcto;
	}
	
	
}

package entidades;

import java.sql.Date;

public class Precio {

	private int id_proveedor, id_producto;
	private float precioUnitario;
	private Date fecha;
	
	public float getPrecioUnitario() {
		return precioUnitario;
	}
	public void setPrecioUnitario(float precioUnitario) {
		this.precioUnitario = precioUnitario;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public int getId_proveedor() {
		return id_proveedor;
	}
	public void setId_proveedor(int id_proveedor) {
		this.id_proveedor = id_proveedor;
	}
	public int getId_producto() {
		return id_producto;
	}
	public void setId_producto(int id_producto) {
		this.id_producto = id_producto;
	}
	
	
}

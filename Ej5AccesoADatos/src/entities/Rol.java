package entities;

public class Rol {
	private int id;
	private String descripcion;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		if(id==1) {
			this.setDescripcion("admin");
		}else {
			this.setDescripcion("user");
		}
		this.id = id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	@Override
	public String toString() {
		return "Rol [id=" + id + ", descripcion=" + descripcion + "]";
	}

}
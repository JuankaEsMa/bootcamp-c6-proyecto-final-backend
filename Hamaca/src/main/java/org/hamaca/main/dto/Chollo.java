package org.hamaca.main.dto;

import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity
public class Chollo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Id")
	private int id;
	@Column(name="Titulo")
	private String titulo;
	@Column(name="Imagen")
	private String imagen;
	@Column(name="Precio_Persona")
	private double precioPersona;
	@Column(name="Cantidad_Personas")
	private int cantidadPersonas;
	@Column(name="Descripcion")
	private String descripcion;
	@Column(name="Is_Deleted")
	private boolean isDeleted;
	@Column(name="Fecha_Caducidad")
	private Date fechaCaducidad;
	@JoinColumn(name="Id_Localidad")
	private Localidad localidad;
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
        name = "Pertenece",
        joinColumns = { @JoinColumn(name = "Id_Chollo") },
        inverseJoinColumns = { @JoinColumn(name = "Id_Tematica")}
    )
    @JsonIgnoreProperties("Id_Chollo")
	private List<Tematica> tematicas;
	@JoinColumn(name="Id_Empleado")
    private Empleado empleado;
	
	public Chollo() {
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	public double getPrecioPersona() {
		return precioPersona;
	}
	public void setPrecioPersona(double precioPersona) {
		this.precioPersona = precioPersona;
	}
	public int getCantidadPersonas() {
		return cantidadPersonas;
	}
	public void setCantidadPersonas(int cantidadPersonas) {
		this.cantidadPersonas = cantidadPersonas;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Date getFechaCaducidad() {
		return fechaCaducidad;
	}
	public void setFechaCaducidad(Date fechaCaducidad) {
		this.fechaCaducidad = fechaCaducidad;
	}
	public Localidad getLocalidad() {
		return localidad;
	}
	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}
	public List<Tematica> getTematicas() {
		return tematicas;
	}
	public void setTematicas(List<Tematica> tematicas) {
		this.tematicas = tematicas;
	}
	public Empleado getEmpleado() {
		return empleado;
	}
	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}
	public boolean isDeleted() {
		return isDeleted;
	}
	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
}

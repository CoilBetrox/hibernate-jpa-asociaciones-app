package org.rdtroya.java.jdbc.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)     //si postgres cambiar a SEQUENCE
    private Long id;
    private String nombre;
    private String apellido;

    @Column(name = "forma_pago")
    private String formaPago;

    @Embedded
    private Auditoria audit = new Auditoria();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    //@JoinColumn(name="id_cliente")
    //tabla intermedia personalizada desacoplando las tablas
    //
    @JoinTable(name = "tbl_clientes_direcciones", joinColumns = @JoinColumn(name = "id_cliente") //nombre personalizado, join configurado con la clave foranea principal para que id_cliente se pueda repetir
            , inverseJoinColumns = @JoinColumn(name = "id_direccion") //de la relacion opuesta
            , uniqueConstraints = @UniqueConstraint(columnNames = {"id_direccion"})) //indice unico entre {} por ser arreglo
    private List<Direccion> direcciones;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "cliente")
    private List<Factura> facturas;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "cliente") //mappedBy no puede estar junta a JoinColumn
    private ClienteDetalle clienteDetalle;


    public Cliente() {
        facturas = new ArrayList<>();
        direcciones = new ArrayList<>();
    }

    public Cliente(String nombre, String apellido) {
        this();
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Cliente(Long id, String nombre, String apellido, String formaPago) {
        this();
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.formaPago = formaPago;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    public List<Direccion> getDirecciones() {
        return direcciones;
    }

    public void setDirecciones(List<Direccion> direcciones) {
        this.direcciones = direcciones;
    }

    public List<Factura> getFacturas() {
        return facturas;
    }

    public void setFacturas(List<Factura> facturas) {
        this.facturas = facturas;
    }

    public Auditoria getAudit() {
        return audit;
    }

    public void setAudit(Auditoria audit) {
        this.audit = audit;
    }

    public ClienteDetalle getClienteDetalle() {
        return clienteDetalle;
    }

    public void setClienteDetalle(ClienteDetalle clienteDetalle) {
        this.clienteDetalle = clienteDetalle;
    }

    public Cliente addFactura(Factura factura){
        this.facturas.add(factura);
        factura.setCliente(this);
        return this;
    }

    public void removeFactura(Factura factura3) {
        this.facturas.remove(factura3);
        factura3.setCliente(null);
    }

    public void addClienteDetalle(ClienteDetalle detalle){
        this.clienteDetalle = detalle;
        detalle.setCliente(this);
    }

    public void removeClienteDetalle(){
        clienteDetalle.setCliente(null);
        this.clienteDetalle = null;
    }

    @Override
    public String toString() {
        LocalDateTime creado= this.audit != null? audit.getCreadoEn():null;
        LocalDateTime editado = this.audit != null? audit.getEditadoEn():null;
        return  "{" +"id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", formaPago='" + formaPago + '\'' +
                ", creadoEn=" + creado + '\'' +
                ", editadoEn=" + editado+
                ", direcciones=" + direcciones +
                ", facturas=" + facturas +
                ", clienteDetalle=" + clienteDetalle +

                '}';
    }


}

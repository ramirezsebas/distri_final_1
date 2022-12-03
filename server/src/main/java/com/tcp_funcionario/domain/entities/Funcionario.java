package com.tcp_funcionario.domain.entities;

public class Funcionario {
    String cedula;
    String nombre;
    String apellido;
    double salario;
    int edad;

    public Funcionario(String cedula, String nombre, String apellido, double salario, int edad) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.salario = salario;
        this.edad = edad;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
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

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    @Override
    public String toString() {
        return "Funcionario [apellido=" + apellido + ", cedula=" + cedula + ", edad=" + edad + ", nombre=" + nombre
                + ", salario=" + salario + "]";
    }

}

package com.tcp_funcionario.data.mappers;

import com.tcp_funcionario.utils.JsonUtil;

public class BaseDto {
    int tipoOperacion;
    String cedula;
    String nombre;
    String apellido;
    double salario;
    int edad;

    public BaseDto(int tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }

    public BaseDto(int tipoOperacion, String cedula, String nombre, String apellido, double salario, int edad) {
        this.tipoOperacion = tipoOperacion;
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.salario = salario;
        this.edad = edad;
    }

    public int getTipoOperacion() {
        return tipoOperacion;
    }

    public void setTipoOperacion(int tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
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

    public static BaseDto fromJson(String json) {
        return JsonUtil.fromJson(json, BaseDto.class);
    }

    public String toJson() {
        return JsonUtil.toJson(this);
    }

}

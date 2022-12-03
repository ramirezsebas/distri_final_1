package com.tcp_funcionario.data.mappers;

import com.tcp_funcionario.utils.JsonUtil;

public class BaseResponse<T> {

    Integer tipoOperacion;
    String mensaje;
    T data;

    public BaseResponse() {
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Integer getTipoOperacion() {
        return tipoOperacion;
    }

    public void setTipoOperacion(Integer tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }

    public T getDato() {
        return data;
    }

    public void setDato(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "BaseResponse{" + ", mensaje=" + mensaje + ", data=" + data + '}';
    }

    public String toJson() {
        return JsonUtil.toJson(this);
    }

    public static BaseResponse fromJson(String json) {
        return JsonUtil.fromJson(json, BaseResponse.class);

    }
}

package com.tcp_funcionario.utils;

import java.net.InetAddress;

public class UIConsole {
    public void insertOperation() {
        System.out.println("1. Listar funcionarios");
        System.out.println("2. Registrar funcionario");

        System.out.println();
    }

    public void insertNis() {
        System.out.print("Ingrese su NIS: ");
        System.out.println();
    }

    public void insertConsumo() {
        System.out.println("Ingrese su consumo: ");
    }

    public void sendInfo(InetAddress IPAddress, int puertoServidor, String protocolo) {
        System.out.println("Enviando datos al servidor = " + IPAddress + ":" + puertoServidor + " via "
                + protocolo.toUpperCase() + "...");

    }

    public void sendData(InetAddress IPAddress, int puertoServidor, String protocolo, String respuesta) {
        System.out.println("Respuesta del servidor = " + IPAddress + ":" + puertoServidor + " via "
                + protocolo.toUpperCase() + "... " + JsonUtil.printJson(respuesta));

    }

    public void waitInfo(InetAddress IPAddress, int puertoServidor, String protocolo) {
        System.out.println("Esperando respuesta del servidor = " + IPAddress + ":" + puertoServidor + " via "
                + protocolo.toUpperCase() + "...");

    }

    public void noResponse(InetAddress IPAddress, int puertoServidor, String protocolo) {
        System.out.println("No se recibió respuesta del servidor = " + IPAddress + ":" + puertoServidor + " via "
                + protocolo.toUpperCase() + "...");

    }

    public void connecting(InetAddress IPAddress, int puertoServidor, String protocolo) {
        System.out.println("Intentando conectar a = " + IPAddress + ":" + puertoServidor + " via "
                + protocolo.toUpperCase() + "...");
    }

}

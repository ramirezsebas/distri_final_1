package com.tcp_funcionario;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import com.tcp_funcionario.data.mappers.BaseDto;
import com.tcp_funcionario.utils.UIConsole;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {

        String direccionServidor = "127.0.0.1";

        int puertoServidor = 9876;

        System.out.println("Iniciando cliente TCP " + "...");

        iniciarClienteTCP(direccionServidor, puertoServidor);

    }

    private static void iniciarClienteTCP(String direccionServidor, int puertoServidor) {
        UIConsole uiConsole = new UIConsole();

        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        try {
            Socket mySocket = new Socket(direccionServidor, puertoServidor);

            InetAddress IPAddress = InetAddress.getByName(direccionServidor);

            uiConsole.connecting(IPAddress, puertoServidor, "TCP");

            PrintWriter out = new PrintWriter(mySocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(mySocket.getInputStream()));

            uiConsole.insertOperation();

            int tipoOperacion = Integer.parseInt(inFromUser.readLine());

            switch (tipoOperacion) {
                case 1:
                    System.out.println("Listar funcionarios");
                    BaseDto baseDto1 = new BaseDto(1);
                    String json1 = baseDto1.toJson();

                    uiConsole.sendInfo(IPAddress, puertoServidor, "TCP");

                    out.println(json1);

                    uiConsole.waitInfo(IPAddress, puertoServidor, "TCP");

                    String respuesta = in.readLine();

                    if (respuesta != null) {
                        uiConsole.sendData(IPAddress, puertoServidor, "TCP", respuesta);
                    } else {
                        uiConsole.noResponse(IPAddress, puertoServidor, "TCP");
                    }

                    break;
                case 2:
                    System.out.println("Registrar funcionario");
                    System.out.println("Ingrese su cedula: ");
                    String cedula = inFromUser.readLine();
                    System.out.println("Ingrese su nombre: ");
                    String nombre = inFromUser.readLine();
                    System.out.println("Ingrese su apellido: ");
                    String apellido = inFromUser.readLine();
                    System.out.println("Ingrese su salario: ");
                    double salario = Double.parseDouble(inFromUser.readLine());
                    System.out.println("Ingrese su edad: ");
                    int edad = Integer.parseInt(inFromUser.readLine());

                    BaseDto baseDto2 = new BaseDto(
                            2,
                            cedula,
                            nombre,
                            apellido,
                            salario,
                            edad);

                    String json2 = baseDto2.toJson();

                    uiConsole.sendInfo(IPAddress, puertoServidor, "TCP");

                    out.println(json2);

                    uiConsole.waitInfo(IPAddress, puertoServidor, "TCP");

                    String respuesta2 = in.readLine();

                    uiConsole.sendData(IPAddress, puertoServidor, "TCP", respuesta2);

                    break;

                default:
                    System.out.println("Operacion no valida");
                    break;
            }

            out.close();

            in.close();

            mySocket.close();

        } catch (UnknownHostException e) {
            System.err.println("Host desconocido");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Error de I/O en la conexion al host");
            System.exit(1);
        }
    }
}

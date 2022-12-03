package com.tcp_funcionario;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.tcp_funcionario.data.mappers.BaseDto;
import com.tcp_funcionario.data.repositories.MockFuncionarioRepository;
import com.tcp_funcionario.domain.entities.Funcionario;
import com.tcp_funcionario.domain.repositories.FuncionarioRepository;
import com.tcp_funcionario.utils.JsonUtil;

/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) {

        Map<String, Funcionario> bdLocal = App.initDB();
        FuncionarioRepository suministroRepository = new MockFuncionarioRepository(bdLocal);
        int puertoServidor = 9876;

        App.iniciarServidorTCP(puertoServidor, suministroRepository);

    }

    private static void iniciarServidorTCP(int puertoServidor, FuncionarioRepository suministroRepository) {
        Socket clientSocket = null;
        int tiempo_procesamiento_miliseg = 2000;
        int tipoOperacion;

        PrintWriter out = null;
        BufferedReader in = null;
        try {
            ServerSocket serverSocket = new ServerSocket(puertoServidor);
            System.out.println("Servidor TCP iniciado en el puerto " + puertoServidor);

            while (true) {
                System.out.println("Esperando conexiones...");
                clientSocket = serverSocket.accept();

                System.out
                        .println("Conexion establecida: "
                                + clientSocket.getInetAddress().getHostAddress() + ":" + clientSocket.getPort());

                out = new PrintWriter(clientSocket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                String request = in.readLine();

                System.out.println("Recibido: " + request);

                BaseDto baseDto = JsonUtil.fromJson(request, BaseDto.class);

                tipoOperacion = baseDto.getTipoOperacion();

                System.out.println("Tipo de operacion: " + tipoOperacion);

                switch (tipoOperacion) {

                    case 1:
                        List<Funcionario> funcionarios = suministroRepository.listarFuncionarios();

                        // COnvertir a JSON
                        List<String> funcionariosJson = new ArrayList<>();
                        for (Funcionario funcionario : funcionarios) {
                            funcionariosJson.add(JsonUtil.toJson(funcionario));
                        }

                        // Enviar respuesta
                        System.out.println("Enviando respuesta...");
                        out.println(funcionariosJson);

                        break;

                    case 2:
                        System.out.println("Registrar Funcionario");

                        Funcionario nuevFuncionario = JsonUtil.fromJson(baseDto.toJson(), Funcionario.class);
                        Funcionario funcionarioRegistrado = suministroRepository.registrarFuncionario(
                                nuevFuncionario);

                        // Enviar respuesta
                        System.out.println("Enviando respuesta...");

                        if (funcionarioRegistrado != null) {
                            out.println("Funcionario registrado correctamente");
                        } else {
                            out.println("Error al registrar el funcionario");
                        }

                        break;

                    default:
                        System.out.println("Operacion no valida");
                        break;

                }

                TimeUnit.MILLISECONDS.sleep(tiempo_procesamiento_miliseg);

                clientSocket.close();

                System.out.println("Conexion cerrada: " + clientSocket.getInetAddress().getHostAddress() + ":"
                        + clientSocket.getPort());

            }
        } catch (IOException e) {
            System.out.println("Error de E/S");
            e.printStackTrace();
        } catch (InterruptedException e) {
            System.out.println("Error de E/S");
            e.printStackTrace();
        }

    }

    private static Map<String, Funcionario> initDB() {
        Map<String, Funcionario> bdLocal = new HashMap<>();
        bdLocal.put("1", new Funcionario("1", "Juan", " Pereria", 1234.56, 24));
        bdLocal.put("2", new Funcionario("2", "Juan", " Perez", 1234.5, 24));
        bdLocal.put("3", new Funcionario("3", "Maria", " Ramirez", 10000, 24));
        bdLocal.put("4", new Funcionario("4", "Maria", " Ramirez", 10000, 24));
        bdLocal.put("5", new Funcionario("5", "Mario", " Gomez", 1234.5, 24));
        bdLocal.put("6", new Funcionario("6", "Juan", " Torres Brizuela", 12671, 24));
        bdLocal.put("7", new Funcionario("7", "Mariano", " Lopez", 1234.5, 24));
        bdLocal.put("8", new Funcionario("8", "Juan", " Torres", 1234.5, 24));
        bdLocal.put("9", new Funcionario("9", "Juan", " Torres", 1234.5, 24));
        bdLocal.put("10", new Funcionario("10", "Juan", " Torres", 1234.5, 24));
        bdLocal.put("11", new Funcionario("11", "Juan", " Torres", 1234.5, 24));
        bdLocal.put("12", new Funcionario("12", "Juan", " Torres", 1234.5, 24));
        bdLocal.put("13", new Funcionario("13", "Juan", " Torres", 1234.5, 24));
        bdLocal.put("14", new Funcionario("14", "Juan", " Torres", 1234.5, 24));
        bdLocal.put("15", new Funcionario("15", "Juan", " Torres", 1234.5, 24));

        return bdLocal;
    }
}

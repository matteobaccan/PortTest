/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.baccan.porttest;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Objects;

/**
 *
 * @author Matteo
 */
public class PortConnector {

    private final String remoteIp;
    private final int remotePort;
    private InputStream inputStream;
    private OutputStream outputStream;

    public PortConnector(final String ip, final int port) {
        remoteIp = ip;
        remotePort = port;
    }

    private Socket socket;

    public void connect(final int connectTimeout, final int readTimeout) throws IOException {
        SocketAddress socketAddress = new InetSocketAddress(remoteIp, remotePort);
        socket = new Socket();
        socket.connect(socketAddress, connectTimeout * 1000);
        socket.setSoTimeout(readTimeout * 1000);
        inputStream = socket.getInputStream();
        outputStream = socket.getOutputStream();
    }

    public OutputStream getOutputStream() {
        return outputStream;
    }

    public InputStream getIntputStream() {
        return inputStream;
    }

    public void close() throws IOException {
        if (Objects.nonNull(socket)) {
            socket.close();
        }
    }

    public void send(final String send) throws IOException {
        if (Objects.nonNull(socket)) {
            socket.getOutputStream().write(send.getBytes());
        }
    }

}

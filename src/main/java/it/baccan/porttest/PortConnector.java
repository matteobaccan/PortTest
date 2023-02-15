/* 
 * This file is part of the PortTest distribution (https://github.com/matteobaccan/PortTest).
 * Copyright (c) 2021 Matteo Baccan
 * 
 * This program is free software: you can redistribute it and/or modify  
 * it under the terms of the GNU General Public License as published by  
 * the Free Software Foundation, version 3.
 *
 * This program is distributed in the hope that it will be useful, but 
 * WITHOUT ANY WARRANTY; without even the implied warranty of 
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU 
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License 
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package it.baccan.porttest;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Objects;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

/**
 *
 * @author Matteo Baccan
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

    public void connect(final int connectTimeout, final int readTimeout, final boolean ssl) throws IOException {
        SocketAddress socketAddress = new InetSocketAddress(remoteIp, remotePort);
        if( ssl ){            
            SSLSocketFactory factory = (SSLSocketFactory)SSLSocketFactory.getDefault();
            socket = factory.createSocket();
        } else {
            socket = new Socket();
        }
        
        socket.connect(socketAddress, connectTimeout * 1000);
        socket.setSoTimeout(readTimeout * 1000);
        inputStream = socket.getInputStream();
        outputStream = socket.getOutputStream();

        if( ssl ){            
            ((SSLSocket)socket).startHandshake();
        }
    }

    public OutputStream getOutputStream() {
        return outputStream;
    }

    public InputStream getInputStream() {
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

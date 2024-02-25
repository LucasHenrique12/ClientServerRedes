package clienteServidor;
import java.net.*;
import java.io.*;

public class clienteUDP {
    public static void main(String args[]) {
        // Endereço IP e porta do servidor
        String enderecoServidor = "000.000.0.1";
        int portaServidor = 4000;

        try {
            // Cria um socket UDP
            DatagramSocket socketCliente = new DatagramSocket();

            // Cria um buffer para enviar dados ao servidor
            byte[] bufferEnvio = new byte[1024];
            String mensagem = "Olá, servidor!";
            bufferEnvio = mensagem.getBytes();

            // Cria um pacote UDP para enviar os dados ao servidor
            DatagramPacket pacoteEnvio = new DatagramPacket(bufferEnvio, bufferEnvio.length, InetAddress.getByName(enderecoServidor), portaServidor);

            // Envia o pacote ao servidor
            socketCliente.send(pacoteEnvio);

            // Cria um buffer para receber dados do servidor
            byte[] bufferRecebimento = new byte[1024];
            DatagramPacket pacoteRecebimento = new DatagramPacket(bufferRecebimento, bufferRecebimento.length);

            // Recebe o pacote do servidor
            socketCliente.receive(pacoteRecebimento);
            String mensagemRecebida = new String(pacoteRecebimento.getData());
            System.out.println("Está foi a mensagem  recebida do servidor: " + mensagemRecebida);

            // Fecha o socket
            socketCliente.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
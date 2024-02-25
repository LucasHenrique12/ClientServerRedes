package clienteServidor;
import java.net.*;
import java.io.*;
public class servidorUdplocal {
    public static void main(String args[]) {
        // Porta em que o servidor irá receber os pacotes (localhost)
        int portaServidor = 9876;

        try {
            // Cria um socket UDP na porta especificada
            DatagramSocket socketServidor = new DatagramSocket(portaServidor, InetAddress.getByName("localhost"));

            // Cria um buffer para receber os dados do cliente
            byte[] bufferRecebimento = new byte[1024];
            DatagramPacket pacoteRecebimento = new DatagramPacket(bufferRecebimento, bufferRecebimento.length);

            // Recebe o pacote do cliente
            socketServidor.receive(pacoteRecebimento);
            String mensagemRecebida = new String(pacoteRecebimento.getData());
            System.out.println("Mensagem recebida do cliente: " + mensagemRecebida);

            // Cria um buffer para enviar dados ao cliente
            byte[] bufferEnvio = new byte[1024];
            String mensagem = "Olá, cliente!";
            bufferEnvio = mensagem.getBytes();

            // Cria um pacote UDP para enviar os dados ao cliente
            DatagramPacket pacoteEnvio = new DatagramPacket(bufferEnvio, bufferEnvio.length, pacoteRecebimento.getAddress(), pacoteRecebimento.getPort());

            // Envia o pacote ao cliente
            socketServidor.send(pacoteEnvio);

            // Fecha o socket
            socketServidor.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

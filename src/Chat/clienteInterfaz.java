/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import Chat.ClienteChat;
import Chat.ServidorChat;
import static Chat.ServidorChat.selecPuerto;

/**
 *
 * @author Gonzalo
 */
public class clienteInterfaz extends javax.swing.JFrame {

    private static DataInputStream entradaServ = null;
    private static DataOutputStream salidaServ = null;
    private Socket clienteSocket = null;
    private static String usuario;
    private static String conecta;
    static boolean conectado = true;
    private static String mensaje2 = "";

    /**
     * Creates new form clienteInterfaz
     */
    public clienteInterfaz() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

        private void initComponents(){
            
            
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        cuerpoChat = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        cuerpoMensaje = new javax.swing.JTextArea();
        btnEnviar = new javax.swing.JButton();
        
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        cuerpoChat.setEditable(false);
        cuerpoChat.setBackground(new java.awt.Color(241, 241, 241));
        cuerpoChat.setColumns(20);
        cuerpoChat.setRows(5);
        jScrollPane1.setViewportView(cuerpoChat);

        cuerpoMensaje.setBackground(new java.awt.Color(241, 241, 241));
        cuerpoMensaje.setColumns(20);
        cuerpoMensaje.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        cuerpoMensaje.setRows(5);
        jScrollPane2.setViewportView(cuerpoMensaje);

        btnEnviar.setText("Enviar");
        btnEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 572, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(btnEnviar)
                        .addGap(0, 34, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(btnEnviar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviarActionPerformed
        String mensaje = cuerpoMensaje.getText();
        if (!mensaje.equals("/bye")) {

            try {
                salidaServ.writeUTF(mensaje);
                salidaServ.flush();
            } catch (IOException ex) {
                Logger.getLogger(clienteInterfaz.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            conectado = false;

        }

    }//GEN-LAST:event_btnEnviarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
 ServidorChat serv = new ServidorChat();
        try {
            System.out.println("Creando socket cliente");
            Socket clienteSocket = new Socket();
            System.out.println("Estableciendo la conexion");

            String ip = JOptionPane.showInputDialog("Indique IP donde alojar su servidor");
            int port = selecPuerto();

            InetSocketAddress addr = new InetSocketAddress(ip, port);
            clienteSocket.connect(addr);

            String usuario = JOptionPane.showInputDialog("Indique nombre de usuario");
            entradaServ = new DataInputStream(clienteSocket.getInputStream());
            salidaServ = new DataOutputStream(clienteSocket.getOutputStream());

            salidaServ.writeUTF(usuario);
            salidaServ.flush();

            //inicializamos interfaz
            clienteInterfaz chat = new clienteInterfaz();
            chat.setVisible(true);
            chat.setDefaultCloseOperation(3);
            if (!chat.isEnabled()) {
                System.out.println("Cerrando el socket cliente");
                clienteSocket.close();
            }

            String conecta = usuario + " se ha conectado.";
            salidaServ.writeUTF(conecta);
            salidaServ.flush();

            while (conectado) {
                mensaje2 = entradaServ.readUTF();
                cuerpoChat.append(mensaje2 + "\n");
            }

            if (!conectado) {
                try {
                    //Mensaje de desconexión
                    conecta = usuario + " se ha desconectado.";
                    salidaServ.writeUTF(conecta);
                    salidaServ.flush();

                    clienteSocket.close();
                    entradaServ.close();
                    salidaServ.close();
                } catch (IOException ex) {
                    Logger.getLogger(clienteInterfaz.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(ClienteChat.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
package co.edu.uniquindio.envios.utils;

import java.util.Properties;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.*;


@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class EnvioEmail {

    //atributos
    private String destinatario, asunto, mensaje;

    //método
    private Session crearSesion() {

        // Se definen las credenciales de la cuenta de correo
        final String username = "SU_EMAIL";
        final String password = "SU_CLAVE_EMAIL";

        // Se configuran las propiedades de la conexión
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // Se crea un objeto de tipo Authenticator
        Authenticator authenticator = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        };

        // Se crea la sesión
        return Session.getInstance(props, authenticator);
    }

    public void enviarNotificacion() {

        Session session = crearSesion();

        try {
            // Se crea un objeto de tipo Message
            Message message = new MimeMessage(session);

            // Se configura el remitente
            message.setFrom(new InternetAddress( "no-reply@email.com" ));

            // Se configura el destinatario
            message.setRecipients( Message.RecipientType.TO, InternetAddress.parse(destinatario));

            // Se configura el asunto del mensaje
            message.setSubject( asunto );

            // Se configura el mensaje a enviar
            message.setText( mensaje );

            // Se envía el mensaje
            Transport.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}

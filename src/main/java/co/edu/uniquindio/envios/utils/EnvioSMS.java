package co.edu.uniquindio.envios.utils;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.*;


@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class EnvioSMS {

    private String mensaje, numero;


    public void crearConexion() {
        Twilio.init(
                "SU_SID",
                "SU_TOKEN"
        );
    }

    public void enviarNotificacion() {
        crearConexion();
        Message.creator(
                        new PhoneNumber(numero),
                        new PhoneNumber("SU_NUMERO_TWILIO"),
                        mensaje)
                .create();
    }

}

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.ClassNotFoundException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.eclipse.paho.client.mqttv3.*;


public class Server {

    public static void main(String[] args) throws Exception {
        final String serverUrl ="tcp://192.168.2.103:1883";
        final String clientId    = "my_mqtt_java_client";
        final String TEMP_TOPIC = "ids-example-010/temp";
        final MqttConnectOptions options = new MqttConnectOptions();

        final MqttClient client = new MqttClient(serverUrl,clientId,null);
        client.connect(options);
        client.subscribe(TEMP_TOPIC);

        client.setCallback(new MqttCallback() {
            @Override
            public void connectionLost(Throwable cause) {
                cause.printStackTrace();
                System.out.println("Connection Lost");
            }

            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                System.out.println("Received message: "+message.toString());
                  }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {
            }
        });

        Executors.newSingleThreadScheduledExecutor().scheduleWithFixedDelay(new Runnable() {
            public void run () {
                try {
                    int temp = (int) (Math.random() * 10 + 10);

                    System.out.println("Sending temperature measurement (" + temp + "º) ...");
                    client.publish(TEMP_TOPIC, new MqttMessage(("211," + temp).getBytes()));
                } catch (MqttException e) {
                    e.printStackTrace();
                }
            }
        }, 1, 7, TimeUnit.SECONDS);
    }

}

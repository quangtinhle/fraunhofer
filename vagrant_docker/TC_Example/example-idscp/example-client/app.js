/*
	Example of a very simple MQTT consumer app.

	This app subscribes to events at a public MQTT broker and makes them 
	available by a REST API. For demonstration purposes, it continuously 
	publishes random values under the subscribed MQTT topic.

	(C) Fraunhofer AISEC, 2017
*/

// Start MQTT client
const mqtt = require('mqtt');
const client = mqtt.connect('mqtt://mqtt-broker');
//const client = mqtt.connect('tcp://192.168.1.20:1883');

const faker = require('faker');
//console.log(faker.fake("{{name.lastName}},{{name.firstName}}, {{name.suffix}}"));

const TEMP_TOPIC = 'ids-example-010/temp';
const DATA_TOPIC = 'ids-example-010/data';

// React on MQTT connection
client.on('connect', () => {
    console.log("Connected to MQTT broker")
    client.subscribe(TEMP_TOPIC)
    client.subscribe(DATA_TOPIC)
})

// React on received MQTT messages
client.on('message', (topic, message) => {
  switch (topic) {
    case TEMP_TOPIC:
      console.log('Temp %s', message);
      break;
    case DATA_TOPIC:
      console.log('Data %s', message);
      break;
    default:
      console.log('No handler for topic %s', topic);
  }
})


// simulate sensor data by publishing random temperature data to MQTT broker
setInterval(() => {
    client.publish(TEMP_TOPIC,  String(Math.random() * (35 - 10) + 10));
    var id = faker.random.number();
    var name = faker.name.findName();
    var email = faker.internet.email();
    //var card = faker.helpers.createCard();

  data = {
    Id: id,
    Name:name,
    Email:email
    }
    client.publish(DATA_TOPIC, JSON.stringify(data));
}, 1000)
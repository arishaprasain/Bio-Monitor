#include <WiFi.h>
#include <MAX30100_PulseOximeter.h>
#include <WebServer.h>
#include <Wire.h>

// WiFi credentials
const char *ssid = "yourAP";         // WiFi Access Point SSID
const char *password = "yourPassword"; // WiFi Password (must be at least 8 characters)

WebServer server(80);                // WebServer on port 80
PulseOximeter pox;                   // Pulse oximeter instance

float BPM = 0;                       // Variable for heart rate (BPM)
float SpO2 = 0;                      // Variable for SpO2
uint32_t tsLastReport = 0;           // Time for reporting
#define REPORTING_PERIOD_MS 500      // 500ms for reporting to serial

// Serve JSON response at /sensor endpoint
void handleSensorData() {
  // Generate the most recent sensor values
  String jsonResponse = "{\"BPM\":" + String((int)BPM) + ", \"SpO2\":" + String((int)SpO2) + "}";
  server.send(200, "application/json", jsonResponse);  // Send JSON data
}

void setup() {
  Serial.begin(115200);  // Initialize serial communication for debug
  
  // Setup Wi-Fi access point
  WiFi.softAP(ssid, password);
  IPAddress myIP = WiFi.softAPIP();
  Serial.print("AP IP address: ");
  Serial.println(myIP);  // Print the IP address

  // Configure the web server to respond with sensor data
  server.on("/sensor", handleSensorData);  // On hitting /sensor, run handleSensorData()
  server.begin();
  Serial.println("Server started");

  // Initialize pulse oximeter
  if (!pox.begin()) {
    Serial.println("FAILED to initialize MAX30100 sensor!");
    while (1);  // Stop here if initialization fails
  } else {
    Serial.println("MAX30100 initialized successfully!");
    pox.setIRLedCurrent(MAX30100_LED_CURR_7_6MA);  // Set LED current
  }
}

void loop() {
  // Update sensor readings regularly
  pox.update();
  BPM = pox.getHeartRate();
  SpO2 = pox.getSpO2();

  // Print sensor data to the serial monitor every 500ms
  if (millis() - tsLastReport > REPORTING_PERIOD_MS) {
    Serial.print("BPM: ");
    Serial.println(BPM);
    Serial.print("SpO2: ");
    Serial.println(SpO2);
    Serial.println("***************************");
    tsLastReport = millis();
  }

  // Continuously handle incoming client requests
  server.handleClient();
}

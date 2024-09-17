# Bio Monitor

## Overview

The **Bio Monitor** is an innovative health monitoring system utilizing an ESP32 microcontroller as an access point to create a web server. The system integrates with an Android application to fetch real-time health data via Wi-Fi, providing users with live insights into their pulse oximeter and heartbeat readings.

This project leverages the Internet of Things (IoT) approach to reduce costs compared to traditional hospital-grade health monitors. It is designed for future scalability, with plans to integrate additional sensors, such as an ECG, to enhance its capabilities.

## Features

- **ESP32 Web Server**: The ESP32 operates as an access point and hosts a web server to serve health data.
- **Android App Integration**: The Android application uses the Wi-Fi module to perform GET requests and retrieve live health data.
- **Real-time Monitoring**: Displays live data from the pulse oximeter and heartbeat sensors.
- **Line Charts**: Visual representation of data through line charts for easy tracking of health metrics.
- **Cost-effective**: Provides an affordable alternative to expensive analog health monitors used in hospitals.
- **Future Scalability**: Designed to integrate additional features, including ECG monitoring.

## Sensors Used

- **Pulse Oximeter**: Measures blood oxygen levels.
- **Heartbeat Sensor**: Monitors heart rate.


## Libraries Used

### Android App

- **Jetpack Compose**: For building the UI with modern and efficient composables.
- **Retrofit**: For making network requests and handling API interactions.
- **MPAndroidChart**: For displaying line charts and other chart types.

### ESP32

- **WiFi**:  For Wi-Fi connectivity and network management.
- **MAX30100_PulseOximeter**:  For interfacing with the pulse oximeter and heartbeat sensor.
- **WebServer**:  For hosting the web server on the ESP32.
- **Wire**:  For I2C communication with the sensors.

## Installation

### Android App Setup

1. **Clone the Repository**:
   ```sh
   https://github.com/arishaprasain/Bio-Monitor.git

### Configure Wi-Fi Module

1. **Set Up Wi-Fi Connection**:
   - Configure the wifi to connect to the ESP32's Wi-Fi network.



### Run the App

1. **Build and Run**:
   - Build the project in Android Studio.
   - Run the app on your Android device to start monitoring health data.

## Usage

### View Live Data

1. **Monitor Health Data**:
   - View live data from the pulse oximeter and heartbeat sensors as displayed in the app.

### Analyze Data

1. **Track Metrics**:
   - Use the provided line charts within the app to track and analyze your health metrics over time.

## Benefits

- **Cost Reduction**:
   - Utilizes IoT technology to provide a cost-effective solution for health monitoring.

- **Enhanced Accessibility**:
   - Offers an accessible and affordable alternative to high-cost hospital monitors.

- **Future-proof**:
   - Designed to be scalable with future integration of additional sensors like ECG.

## Future Enhancements

- **ECG Integration**:
   - Planned integration of ECG functionality for more comprehensive health monitoring.







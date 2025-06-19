# PortTest: TCP & SSL Port Tester

PortTest is a user-friendly Java application designed to help you test TCP communication on socket ports. Whether you need to troubleshoot a connection issue, verify server availability, or inspect data exchange, PortTest provides a simple interface for sending messages over plain text or SSL-encrypted connections.

[![CodeQL](https://github.com/matteobaccan/PortTest/actions/workflows/codeql-analysis.yml/badge.svg)](https://github.com/matteobaccan/PortTest/actions/workflows/codeql-analysis.yml)
[![Codacy Security Scan](https://github.com/matteobaccan/PortTest/actions/workflows/codacy.yml/badge.svg)](https://github.com/matteobaccan/PortTest/actions/workflows/codacy.yml)

## Features

*   **Test TCP Connections:** Easily check if a TCP/IP port is open and responsive.
*   **SSL Support:** Test connections over secure SSL/TLS channels.
*   **Plain Text Communication:** Send and receive messages in plain text for non-encrypted protocols.
*   **Predefined Port List:** Comes with a configurable list of common ports and protocols (e.g., HTTP, HTTPS, SMTP) defined in `port.yaml`.
*   **Customizable Commands:** Send predefined or custom commands to the server.
*   **User-Friendly GUI:** An intuitive graphical interface for easy operation.
*   **Cross-Platform:** Being a Java application, PortTest can run on any system with Java installed.

## Getting Started

### Prerequisites

*   Java Runtime Environment (JRE) 8 or higher.

### Installation

1.  **Download:** Download the latest `PortTest.jar` file from the [GitHub Releases page](https://github.com/matteobaccan/PortTest/releases).
2.  **Run:** Open your terminal or command prompt, navigate to the directory where you downloaded the JAR file, and run the application using the following command:

    ```bash
    java -jar PortTest.jar
    ```
    This will launch the PortTest GUI.

## Usage

PortTest provides a graphical user interface (GUI) to test TCP connections.

![PortTest GUI](./porttest.png)

1.  **Enter Host and Port:**
    *   In the "Host" field, enter the hostname or IP address of the server you want to connect to.
    *   In the "Port" field, enter the port number.
    *   Alternatively, you can select a predefined service from the "Port List" dropdown, which will populate the Host, Port, and potentially SSL status and commands.

    ![Port List](./portlist.png)

2.  **Choose Connection Type:**
    *   Select the "SSL" checkbox if the server requires a secure connection (e.g., for HTTPS, SMTPS). Leave it unchecked for plain text connections.

3.  **Connect:**
    *   Click the "Connect" button to establish a connection to the server.
    *   Connection status and any received messages will be displayed in the text area below.

4.  **Send Commands:**
    *   Once connected, you can type commands into the input field at the bottom and click "Send" or press Enter.
    *   If you selected a service from the "Port List" that has predefined commands, you can choose them from the "Commands" dropdown.

    ![Commands](./commands.png)

5.  **View Communication:**
    *   All messages sent to and received from the server will be logged in the main text area. This allows you to inspect the communication.

6.  **Disconnect:**
    *   Click the "Disconnect" button to close the connection.

## Configuration (`port.yaml`)

PortTest uses a `port.yaml` file to store a predefined list of services, ports, and commands. This file is located in `src/main/resources/port.yaml` within the source code, and is bundled with the application.

Here's an overview of the `port.yaml` structure:

```yaml
name: "Port definitions"
releaseDate: 2022-02-17 # Last update date of this configuration file
portDetails:
  - port: 80 # The port number
    protocol: "HTTP" # The common protocol name for this port
    ssl: false # Whether SSL is typically used for this protocol on this port
    commands: # A list of common commands for this protocol
      - cmd: "GET / HTTP/1.0"
      - cmd: "HEAD / HTTP/1.0"
      - cmd: "DELETE / HTTP/1.0"
  - port: 443
    protocol: "HTTPS"
    ssl: true
    commands:
      - cmd: "GET / HTTP/1.0"
      - cmd: "HEAD / HTTP/1.0"
  - port: 23
    protocol: "TELNET"
    # ssl is implicitly false if not specified
    # commands is implicitly empty if not specified
  - port: 25
    protocol: "SMTP"
  - port: 110
    protocol: "POP3"
  # ... and so on for other predefined ports
```

**Fields:**

*   `name`: A descriptive name for the configuration.
*   `releaseDate`: The date when this configuration was last updated.
*   `portDetails`: A list of port definition objects.
    *   `port`: The default TCP port number for the service.
    *   `protocol`: A human-readable name for the protocol (e.g., "HTTP", "FTP").
    *   `ssl`: (Optional) A boolean (`true` or `false`) indicating if SSL is typically enabled for this service on this port. Defaults to `false` if omitted.
    *   `commands`: (Optional) A list of common commands that can be sent to the server for this protocol.
        *   `cmd`: The actual command string.

This file allows users to quickly select common services from the GUI, pre-filling connection details and providing a list of relevant commands. You can modify this file if you build PortTest from source to customize the predefined list.

## Contributing

Contributions are welcome! If you have suggestions for improvements, bug fixes, or new features, please feel free to:

1.  **Open an Issue:** Discuss the change you wish to make via the [GitHub Issues page](https://github.com/matteobaccan/PortTest/issues).
2.  **Fork the Repository:** Create your own fork of the project.
3.  **Create a Branch:** Make your changes in a dedicated branch.
4.  **Commit Your Changes:** Follow good commit message practices.
5.  **Open a Pull Request:** Submit a pull request for review.

Please ensure your code adheres to the existing style and that any new features are appropriately documented.

## License

PortTest is open-source software licensed under the [MIT License](LICENSE). See the `LICENSE` file for more details.

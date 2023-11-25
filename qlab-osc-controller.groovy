/*

QLab OSC Controller

This Hubitat Elevation driver allows sending a text string via UDP to a device on the local network.
The goal is to control Figure 53's QLab using plaintext OSC commands, but this driver could be used
for any network device that is looking for commands via plaintext UDP.

Set up a Button Controller or Rule Machine rule to trigger the sendMsg command with your message
(e.g., "/go"). Then you can use any device compatible with Hubitat (perhaps a button like the Sonoff
SNZB-01) to send commands. Devices created with this driver can be found under the "Actuator"
capability.

*/

metadata {
	definition(name: "QLab OSC Controller", namespace: "swiss6th", author: "Andrew Hall", importUrl: "https://github.com/swiss6th/hubitat-qlab-controller/raw/main/qlab-osc-controller.groovy") {
		capability "Actuator"
		capability "Telnet"
	}
	preferences {
		section("Target Device") {
			input "qlabAddress", "text", title: "<strong>Address of QLab</strong>:", required: true, multiple: false, displayDuringSetup: true
			input "qlabPort", "number", title: "<strong>Port of QLab</strong>:", required: true, multiple: false, displayDuringSetup: true, range: "0..65353", defaultValue: 53535
		}
	}
}

def updated() {
	state.clear()
	log.info("Preferences saved")
}

def installed() {
	log.info("Installed")
	updated()
}

def sendMsg(String message) {
	String destination = "${qlabAddress}:${qlabPort}"
	def udpToSend = new hubitat.device.HubAction(
		message,
		hubitat.device.Protocol.LAN,
		[
			type: hubitat.device.HubAction.Type.LAN_TYPE_UDPCLIENT, 
			destinationAddress: destination,
			ignoreResponse: true
		]
	)
	sendHubCommand(udpToSend)

	String logMessage = "Sending ${message} to ${destination}"
	log.info(logMessage)
	sendEvent(name: sendMsg, value: message, descriptionText: logMessage, isStateChange: true, type: "digital")
}

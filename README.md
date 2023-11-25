# QLab OSC Controller

This [Hubitat Elevation](https://hubitat.com) driver allows sending a text string via UDP to a device on the local network. The goal is to control Figure 53's [QLab](https://qlab.app) using [plaintext OSC commands](https://qlab.app/docs/v5/scripting/osc-dictionary-v5#getting-started), but this driver could be used for any network device that is looking for commands via plaintext UDP.

Set up a [Button Controller](https://docs2.hubitat.com/apps/button-controllers/button-controller-5-1) or [Rule Machine](https://docs2.hubitat.com/apps/rule-machine) rule to trigger the `sendMsg` command with your message (e.g., `/go`). Then you can use any device compatible with Hubitat (perhaps a button like the [Sonoff SNZB-01](https://sonoff.tech/product/gateway-amd-sensors/snzb-01/)) to send commands. Devices created with this driver can be found under the `Actuator` capability.

This is a purpose-built driver for my own workflow, but I will entertain pull requests. Feel free to fork.

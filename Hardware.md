# RME Fireface UCX

Enter class-compliant mode:

> Connect the UCX to power and switch it on. Turn the encoder
knob in Channel mode until SU appears in the display. Press the knob once to activate Level mode.
Turn the knob until CC is shown in the display, then press and hold the encoder until the display
shows on (pressing the knob again will make the display show oF, indicating CC mode is inactive).
The unit will reboot, all LEDs will light up, after which it will be in Class Compliant mode.

The unit may be in 'C8' mode, which means DAW channels 1+2 are copied to outputs 7+8 as well. To restore 1:1 routing,
select SU again, now find 'CA' mode, keep encoder pressed.

__N.B.__ Phantom power settings cannot be accessed on the device, but must be copied via TotalMix on macOS or Windows to the device first.
Six (?) setups can be recalled within the SU sub-menu.

# Scarlett 18i20

There can be corrupt alsastate / USB when accidentally disconnecting the device without properly shutting down Jack (I think).

As a result, the analog outputs are broken (output DC or nothing at all).

The only reliable work-around is this:

- turn off the Scarlett
- connect it to the Mac Mini, and boot it up under OS X
- check the Scarlett mix control; possibly restore correct settings
- test (in OS X) that playback works from SuperCollider
- on the Linux machine: `sudo rm /var/lib/alsa/asound.state`; reboot; remove it again, reboot;
- put the Scarlett back onto the Linux machine

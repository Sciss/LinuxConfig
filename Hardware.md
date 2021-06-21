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

## new solution

It seems much easier now to fix this: open `alsamixer` and find the last item `Scarlett 18i20 USB-Sync Clock Source`.
This will report as `Internal` but it's corrupt. Switch it up to `S/PDIF` and `ADAT`, and back to `Internal`. Now the
device should be properly set up.

## old solution

__OLD:__ The only reliable work-around is this:

- turn off the Scarlett
- connect it to the Mac Mini, and boot it up under OS X
- check the Scarlett mix control; possibly restore correct settings
- test (in OS X) that playback works from SuperCollider
- on the Linux machine: `sudo rm /var/lib/alsa/asound.state`; reboot; remove it again, reboot;
- put the Scarlett back onto the Linux machine

# LogiLink UA0099 (Cmedia 6206)

There might be problems with using the analog Line-In. Apparently the card can have the capture feature of this input turned off, although there is no apparent switch in the ALSA Mixer.
This can be verfied: Find out the card number using `aplay -l` -- let's assume it's `card 1`. Then `amixer -c1` gives the state of all controls. Check that `PCM Capture Source` is set to `Line`
(this can be set in `alsamixer`). Additionally, check the status of the corresponding control:

```
$ amixer -c1 sget Line
Simple mixer control 'Line',0
  Capabilities: pvolume cvolume pswitch pswitch-joined cswitch cswitch-joined
  Playback channels: Front Left - Front Right
  Capture channels: Front Left - Front Right
  Limits: Playback 0 - 8065 Capture 0 - 6928
  Front Left: Playback 6209 [77%] [0.25dB] [off] Capture 0 [0%] [-16.00dB] [off]   // oh noes!
  Front Right: Playback 6209 [77%] [0.25dB] [off] Capture 0 [0%] [-16.00dB] [off]
```

To enable the capture mode here, and set volume, use `amixer -c1 set Line cap` and (for example) `amixer -c1 set Line 6dB-`.

# Brennenstuhl DT IP44 Zeitschaltuhr

- plug in
- press small 'R' button with paperclip to turn on and erase all programs
- keep 'OK' pressed for 5 seconds to enter set-clock mode
- set day-of-week with Up/Dn, confirm with Ok, and repeat for hours and minutes
- press 'Mode' repeatedly until 'PROG 1 ON' starts blinking, press 'OK' to define PROG 1 ON
- i.e. select week days and time; 'PROG 1 OFF' begins to blink. press 'OK' to define PROG 1 OFF
- continue for further programs


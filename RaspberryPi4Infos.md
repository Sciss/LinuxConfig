Assuming Raspbian Buster.

## Enabling SSH on a fresh Buster image

Mount the boot partition on a desktop computer. Go into its root directory and write `touch ssh`.

To find the Pi on the net, `ssh pi@raspberrypi.local` should be enough. You can also use `nmap`:

    sudo nmap -sn 192.168.0.0/24

Where the IP depends on the desktop computer's eth0 IP.

## Firmware

Check current firmware version

    sudo /opt/vc/bin/vcgencmd version

Update to latest firmware

    sudo rpi-update

(last thing I saw was 4.19.88)

## VNC

For it to work without HDMI connected, in addition to `/boot/config.txt`:

    hdmi_group=2
    hdmi_mode=23 
    
    framebuffer_width=1280
    framebuffer_height=768

it seems we also need to select from `sudo raspi-config`, advanced, a screen resolution option that is not default, e.g. 1280 x 768.

To use the standard Debian VNC viewer, we need to disable VNC encryption.

First, generate an encrypted password

    vncpasswd -print

Then

    sudo vi /root/.vnc/config.d/vncserver-x11

And replace contents by

    Authentication=VncAuth
    Encryption=AlwaysOff
    Password=<what-vncpasswd-gave-us>

## Monitoring temperature

- GPU: `vcgencmd measure_temp`
- CPU: `cat /sys/class/thermal/thermal_zone0/temp`

Assuming Raspbian Buster.

## Enabling SSH on a fresh Buster image

Mount the boot partition on a desktop computer. Go into its root (`/`) directory of the `boot` (first) partition, and write `touch ssh`.

To find the Pi on the net, `ssh pi@raspberrypi.local` should be enough. You can also use `nmap`:

    sudo nmap -sn 192.168.0.0/24

Where the IP depends on the desktop computer's eth0 IP.

__In general, it should be sufficient to just `ssh pi@raspberrypi.local`__

## Static IP

Run `sudo vi /etc/dhcpcd.conf` and add lines

    interface eth0
    static ip_address=192.168.0.111/24

where 111 should be replaced.

When connecting to a wifi network that unfortunately uses the same IP space, one can override the DNS through

    sudo vi /etc/resolv.conf

And change, for example, to `8.8.8.8`

## Firmware

Check current firmware version

    sudo /opt/vc/bin/vcgencmd version

Update to latest firmware

    sudo rpi-update

(last thing I saw was 4.19.88)

## VNC

__Note:__ changing `config.txt` is no longer needed; just set explicit resolution in `raspi-config`.

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

## Forget Wifi Passwords

`sudo vi /etc/wpa_supplicant/wpa_supplicant.conf` and delete `network=` entries.

## Wiring-Pi

Wiring-Pi is broken on the Pi 4. The pull up/down resistors cannot be configured.
See [pi4j.com/1.3/install.html](https://pi4j.com/1.3/install.html#WiringPi_Native_Library) -- one needs to replace the installed versions
with an unofficial one:

    sudo apt remove wiringpi -y
    sudo apt install git-core gcc make
    cd ~/Documents/devel/
    git clone https://github.com/WiringPi/WiringPi --branch master --single-branch wiringpi
    cd wiringpi
    sudo ./build


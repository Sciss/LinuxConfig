Note: Some of this stuff is old and probably outdated!

## Installation and Setup

- https://www.youtube.com/watch?v=Th_3AvK-EbM
- screen resolution: https://www.raspberrypi.org/documentation/configuration/config-txt.md
- change keyboard layout to US: `/etc/default/keyboard` and `XKBLAYOUT="us"` instead of `gb`.
- configure read-only SD: https://hallard.me/raspberry-pi-read-only/
- auto-start: create a `foo.desktop` entry in `~/.config/autostart/`: https://raspberrypi.stackexchange.com/questions/8805/auto-login-into-lxde-and-auto-start-video-player-omxplayer#43350 ;
  another place might be `/etc/xdg/lxsession/LXDE-pi/autostart`
- free space: `sudo apt-get purge wolfram-engine`
- force screen resolution: in `/boot/config.txt` enable `hdmi_group=2` and `hdmi_mode=23` (1280 x 768); mode list: https://www.raspberrypi.org/documentation/configuration/config-txt.md
- backup and restore SD card: https://thepihut.com/blogs/raspberry-pi-tutorials/17789160-backing-up-and-restoring-your-raspberry-pis-sd-card ; backup with progress: `sudo apt-get install pv`; `sudo dd if=/dev/mmcblk0 | pv | dd of=~/disk.img` ; (use `df -h` to list of devices)
- shrink sd card image: http://www.aoakley.com/articles/2015-10-09-resizing-sd-images.php -- now you can use `raspbian-shrink` from [this repository](https://github.com/aoakley/cotswoldjam).
- downgrade firmware when WiringPi complains "Unable to determine hardware version. I see: Hardware   : BCM2835": `sudo rpi-update 52241088c1da59a359110d39c1875cda56496764`

## Getting a USB sound card to work:

- http://www.instructables.com/id/Use-USB-Sound-Card-in-Raspberry-Pi/
- https://raspberrypi.stackexchange.com/questions/43320/raspberry-pi-3-audio-input
- https://www.youtube.com/watch?v=p-Za8yzDXpU
- https://www.youtube.com/watch?v=GQDQ_Z-NmHQ

## Disable on-board sound card

- in `/boot/config.txt` change `dtparam=audio=on` to `dtparam=audio=off`.

## Camera Module:

- https://www.youtube.com/watch?v=ojJhLQBiv0I
- https://www.raspberrypi.org/blog/whats-that-blue-thing-doing-here/
- capture through OpenCV: http://stefanshacks.blogspot.co.at/2015/05/build-opencv-with-java-bindings-on_6.html
- OpenCV 3 / Jessie: http://www.pyimagesearch.com/2016/04/18/install-guide-raspberry-pi-3-raspbian-jessie-opencv-3/ | and for Java bindings instead of Python: http://stefanshacks.blogspot.co.at/2015/05/build-opencv-with-java-bindings-on_6.html | make sure to install `ant` and log out and in again after changing `/etc/environment` for `JAVA_HOME`.
- http://www.linux-projects.org/documentation/uv4l-raspicam/

## Waveshare 4" HDMI Display

See [repository](https://github.com/waveshare/LCD-show)

In `/boot/config.txt`:

    hdmi_group=2
    hdmi_mode=87
    hdmi_cvt 480 800 60 6 0 0 0
    dtoverlay=ads7846,cs=1,penirq=25,penirq_pull=2,speed=50000,keep_vref_on=0,swapxy=0,pmax=255,xohms=150,xmin=200,xmax=3900,ymin=200,ymax=3900
    display_rotate=3

## Motors and Stuff:

- https://www.youtube.com/watch?v=dMUeshpBusI

## Sensors:

- Gyro and Accel: http://ozzmaker.com/guide-to-interfacing-a-gyro-and-accelerometer-with-a-raspberry-pi/
- GPS: https://www.youtube.com/watch?v=4Wy9wjZ0bTg
- integrating accel: https://electronics.stackexchange.com/questions/4983/how-to-determine-position-from-gyroscope-and-accelerometer-input
- on iOS: https://gis.stackexchange.com/questions/29720/how-to-record-tracks-finer-than-10-30cm-resolution-possibly-using-an-iphone
- http://blog.bitify.co.uk/2013/11/interfacing-raspberry-pi-and-mpu-6050.html
- key matrix: https://www.youtube.com/watch?v=yYnX5QodqQ4
- getting around having no ADC: http://abyz.co.uk/rpi/pigpio/ex_LDR.html
- ADC: http://www.raspberrypi-spy.co.uk/2013/10/analogue-sensors-on-the-raspberry-pi-using-an-mcp3008/

## Powering from Battery:

- http://www.makeuseof.com/tag/pi-go-x-ways-powering-raspberry-pi-portable-projects/
- headless mode: http://www.circuitbasics.com/raspberry-pi-basics-setup-without-monitor-keyboard-headless-mode/
- saving energy: http://www.jeffgeerling.com/blogs/jeff-geerling/raspberry-pi-zero-conserve-energy
- turn off wifi: https://raspberrypi.stackexchange.com/questions/43720/ - `wpa_cli terminate`
- turn off bluetooth: https://www.raspberrypi.org/forums/viewtopic.php?t=154112&p=1008745

## Network:

- https://www.raspberrypi.org/documentation/remote-access/ssh/
- static IP: http://www.modmypi.com/blog/how-to-give-your-raspberry-pi-a-static-ip-address-update ; in particular
  `sudo vi /etc/dhcpcd.conf` and add lines `interface eth0` and `static ip_address=192.168.0.111/24` where `111` should be replaced.
- ssh server might need to be enabled through `sudo raspi-config` (advanced settings; newer versions: interfacing). With defaults, you need `ssh pi@10.0.0.1`. Default password is `raspberry`
- find MAC address: `cat /sys/class/net/eth0/address`
- copy ssh key so we can log in without entering password: `ssh-copy-id pi@<ip-address>`
- find pis on the net: `nmap -sn 192.168.0.0/24` (finds IPs on this subnet mask)

## Misc:

- emulator: http://embedonix.com/articles/linux/emulating-raspberry-pi-on-linux/
- IntelliJ IDEA: https://pavelfatin.com/install-intellij-idea-on-raspberry-pi/
- Disabling screen blanking: https://raspberrypi.stackexchange.com/questions/1384/how-do-i-disable-suspend-mode (`xset s off` and `xset -dpms`)
- checking CPU temperature: `vcgencmd measure_temp`

## Video:

- http://raspberrypi.stackexchange.com/questions/14818/pause-omxplayer-after-set-time
- https://github.com/popcornmix/omxplayer/issues/436

## Troubleshooting:

- VNC: "Cannot currently show the desktop". There is good chance that the SD card is full. Check with `df -h` and if 100% full, delete some files.
  Also make sure that the root file system has been expanded if using a compact image. (raspi-config: advanced options)

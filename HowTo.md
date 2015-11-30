# PDF

## Convert a grayscale PDF to a black-and-white (1-bit) PDF

    convert -density 300 -threshold 50% input.pdf output.pdf

## Join PDFs

    pdftk in1.pdf in2.pdf cat output out1.pdf

# Videos

## Drop audio track from movie

Use the `-an` option for `avconv`.

## Make video from image sequence with offset and num-frames

    avconv -r <fps> -ss <hh:mm:ss> -vframes <num-frames> -i in_%d.png out.mp4

Where `avconv` doesn't have the `-start_number` option in older versions.

## Concat two videos

    avconv -i concat:file1.h264\|file2.h264 -c copy -f mp4 out.mp4

Note: doesn't work with input mp4 format.

# Vim

##  Toggle auto-indenting for code paste 

Before pasting: `:set paste`. Afterwards go back to normal mode: `:set nopaste`

# Network

Restore a sane network configuration:

    sudo vi /etc/network/interfaces

This:

```shell
# This file describes the network interfaces available on your system
# and how to activate them. For more information, see interfaces(5).

# The loopback network interface
auto lo
iface lo inet loopback

# ethernet
allow-hotplug eth0
iface eth0 inet dhcp

# wlan
allow-hotplug wlan0
```

# Gnome

Kill and restart tracker:

    tracker-control -r

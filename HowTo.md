# PDF

## Convert a grayscale PDF to a black-and-white (1-bit) PDF

    convert -density 300 -threshold 50% input.pdf output.pdf

## Join PDFs

    pdftk in1.pdf in2.pdf cat output out1.pdf

# Videos

## Drop audio track from movie

Use the `-an` option for `avconv`.

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


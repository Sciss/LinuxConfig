# PDF

## Convert a grayscale PDF to a black-and-white (1-bit) PDF

    convert -density 300 -threshold 50% input.pdf output.pdf

## Join PDFs

    pdftk in1.pdf in2.pdf cat output out1.pdf

## Convert TIFF to PDF

The package `libtiff-tools` must be installed. Then

    tiff2pdf -o out.pdf in.tif

## Extract pages from PDF

    pdftk A=in.pdf cat A<first-page>-<last-page> output out.pdf

# Videos

## Drop audio track from movie

Use the `-an` option for `avconv`.

## Make video from image sequence with offset and num-frames

    avconv -r <fps> -ss <hh:mm:ss> -vframes <num-frames> -i in_%d.png out.mp4

Where `avconv` doesn't have the `-start_number` option in older versions.

## Concat two videos

    avconv -i concat:file1.h264\|file2.h264 -c copy -f mp4 out.mp4

Note: doesn't work with input mp4 format.

## Trim a video

    avconv -i input.mp4 -ss <start-time> -t <duration> -codec copy out.mp4

Where times are `HH:MM:SS` or fractional seconds.

## Split a large file into multiple

    split -n <number-of-files> file.mp4

The output chunks will be named `xaa`, `xab`, `xac`, ... To join them again:

    cat <chunk-1> <chunk-2> ... >file.mp4

## Author a DVD-Video

This converts a HD input video (e.g. h.264) into a DVD-Video. I didn't have
sound in this example, therefore the sound codec is a bit crappy, in particular
to convert first to mp3 then to ac3 doesn't make sense, so one would have to
improvise these steps eventually.

The following debian packages are needed:

 - `libav-tools` - for `avconv`
 - `mjpegtools` - for `mplex`
 - `dvdauthor`
 - `growisofs`

First convert to MPEG (see [here](https://superuser.com/questions/835871/how-to-make-an-mpeg2-video-file-with-the-highest-quality-possible-using-ffmpeg)):

    avconv -i in.mp4 -shortest -c:v mpeg2video -qscale:v 2 -target pal-dvd -c:a libmp3lame "out.mpg"

Then demux:

    avconv -i out.mpg -vcodec copy out.m2v
    avconv -i out.mpg -vcodec copy out.ac3

Then remux (see [here](https://superuser.com/questions/465666/how-to-author-a-looping-dvd)):

    mplex -f 8 -o out_remux.mpg out.m2v out.ac3

Then configure `dvdauthor` by creating a file `out.xml` with the following content:

```xml
<dvdauthor>
    <vmgm />
    <titleset>
        <titles>
            <pgc>
                <vob file="out_remux.mpg" />
                <post>
                    jump title 1;
                </post>
            </pgc>
        </titles>
    </titleset>
</dvdauthor>
```

This ensures the video is automatically looped. Create the `VIDEO_TS` folder:

    mkdir out-DVD
    export VIDEO_FORMAT=PAL
    dvdauthor -o out-DVD -x out.xml

Then burn the DVD:

    growisofs -v -Z /dev/dvd -dvd-video -V out out-DVD

## Record a Screen Cast

- [Vokoscreen](http://hackerspace.kinja.com/screen-recording-in-linux-1686055808)

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

# Iceweasel

## Uninstall Flash

https://wiki.debian.org/FlashPlayer#Uninstall :

    sudo update-flashplugin-nonfree --uninstall
    sudo apt-get remove flashplugin-nonfree

# Web Sites

## Define a preview picture used when linking from Facebook

In the `<head>` section:

    <link rel="image_src" href="my-image.jpg" /><!--formatted-->

# Misc Shell

## Create a Zip file that preserves symlinks

    zip --symlinks -r foo.zip foo/

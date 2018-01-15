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

## Shrink PDF size

    gs -sDEVICE=pdfwrite -dCompatibilityLevel=1.4 -dPDFSETTINGS=/screen -dNOPAUSE -dBATCH  -dQUIET -sOutputFile=output.pdf input.pdf

Where instead of `/screen` (smallest), one can also use `/ebook` (medium) or `/printer` (large).

## Rotate PDF

Clockwise:

    pdftk in.pdf cat 1east output out.pdf # new pdftk

## Single to Double Page

Use preview (eog) and print to file.
Problem with this approach is that it might not always respect page margins.

Other approach:

- extract individual pages: `pdftk A=in.pdf cat A1 output out1.pdf`, `pdftk A=in.pdf cat A2 output out2.pdf`, etc.
- join double pages: `pdfjam out1.pdf out2.pdf out3.pdf ... --nup 2x1 --landscape --outfile out.pdf`

## Multiple Pages on One with Precise Size

For instance, input pages are 106mm wide. Using `--nup 2x2 --landscape`, pdfjam will enlarge them.
One can add the correct scaling factors as (106.0/(297.0/2)) = 0.7143

    pdfjam p1.pdf p2.pdf p3.pdf p4.pdf --nup 2x2 --landscape --scale 0.7142857142857143 --outfile out.pdf

# Sound System

## Route pulseaudio through jack

If not yet installed:

    sudo apt install pulseaudio-module-jack

Then devices can be created ad-hoc:

    pactl load-module module-jack-sink channels=2
    pactl load-module module-jack-source channels=2

Where 'sink' is an audio _output_ device for applications. So to route audio output from an application via jack to another application, we need to select the 'Jack sink' device.

## Set sound card volume

E.g. if USB device is second card:

    amixer -c 1 set Speaker 6DB-

Device index (`-c`) is identical with list shown in `alsamixer`.

# Videos

## Drop audio track from movie

Use the `-an` option for `avconv`.

## Make video from image sequence with offset and num-frames

    avconv -r <fps> -ss <hh:mm:ss> -vframes <num-frames> -i in_%d.png out.mp4

Where `avconv` doesn't have the `-start_number` option in older versions.

## Make image sequence from video

    avconv -i input.mp4 -f image2 output-%d.png

For a lossy format such as JPG, use `-q:v 1` to get the best possible quality.

## Concat two videos

    avconv -i concat:file1.h264\|file2.h264 -c copy -f mp4 out.mp4

Note: doesn't work with input mp4 format.

## Trim a video

    avconv -i input.mp4 -ss <start-time> -t <duration> -codec copy out.mp4

Where times are `HH:MM:SS` or fractional seconds.

## Make Animated GIF

    ffmpeg -i frame-%d.png -f image2pipe -vcodec ppm - | convert -delay 5 - gif:- | convert -layers Optimize - output.gif

## Apply fade-in/out while converting

    avconv -i input.mp4 -vf fade=type=in:start_frame=0:nb_frames=25,fade=type=out:start_frame=975:nb_frames=25 output.mp4

## Apply Gamma correction

This needs ffmpeg, avconv does not have the corresponding filter!

    ffmpeg -i input.mp4 -vf eq=gamma=0.5 output.mp4

## Watermark a video

Use this video-filter:

    -vf "movie=watermark.png [watermark]; [in][watermark] overlay=10:10 [out]" 

Where `10:10` is the image offset from the top-left corner. To compose multiple filters, move `[out]` to the end:

    -vf "movie=watermark.png [watermark]; [in][watermark] overlay=10:10, scale=960:540 [out]" 

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

- Use the built in Gnome screencast recording facility: `Ctrl-Shift-Alt-R`. Make sure to
  reserve enough time: `gsettings set org.gnome.settings-daemon.plugins.media-keys max-screencast-length 1200`
  (limits recording to 20 minutes)
- [Vokoscreen](http://hackerspace.kinja.com/screen-recording-in-linux-1686055808)

# Vim

## Toggle auto-indenting for code paste 

Before pasting: `:set paste`. Afterwards go back to normal mode: `:set nopaste`

# Texmaker

## Change editor's spell checking language

Aliases to `/usr/share/myspell` are missing. Dictionaries are found in
`/usr/share/hunspell`. To switch, use menu Options > Configure Texmaker > Editor.

# Network

## Restore a sane network configuration

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

## Disable IPv6

When firewall causes IPv6 to hang:

    sudo sysctl -w net.ipv6.conf.all.disable_ipv6=1

# Git

## Resolve Merge Conflict

In order to force git to take either version of a conflict file:

- To use the file of branch that gets merged in: `git checkout --theirs the-file-name`
- To keep the file of the current branch like it was before merge: `git checkout --ours the-file-name`

See also: http://gitready.com/advanced/2009/02/25/keep-either-file-in-merge-conflicts.html

## Display Remote URL

    git config --get remote.origin.url

# Apt

List all installed packages with versions:

    dpkg-query -l

# Gnome

## Kill and restart tracker

    tracker-control -r

## Type special characters

- http://fsymbols.com/keyboard/linux/compose/
- e.g. for 'pilcrow' paragraph sign: <kbd>Compose</kbd> (<kbd>Right-Alt</kbd>) + <kbd>p</kbd> + <kbd>!</kbd>
- e.g. for 'section' paragraph sign: <kbd>Compose</kbd> (<kbd>Right-Alt</kbd>) + <kbd>o</kbd> + <kbd>s</kbd>
- n-dash: <kbd>Compose</kbd> <kbd>-</kbd> <kbd>-</kbd> <kbd>.</kbd> (hyphen-hyphen-period)
- m-dash: <kbd>Compose</kbd> <kbd>-</kbd> <kbd>-</kbd> <kbd>-</kbd> (hyphen-hyphen-hyphen)

# Debian

## Recover password

 - interrupt GRUB by pressing any key
 - enter edit command by pressing 'e'
 - find the first `setparams` sections
 - within that, find the `linux /vmlinuz...` line and
   append ` init=/bin/bash`
 - press F10 to boot
 - at the bash prompt enter `mount -rw -o remount /`
 - enter `passwd <username>` and give new password
 - reboot (how? don't know; you can switch off/on hard)

## Watch SysLog

    watch cat /var/log/syslog

# Iceweasel

## Uninstall Flash

https://wiki.debian.org/FlashPlayer#Uninstall :

    sudo update-flashplugin-nonfree --uninstall
    sudo apt-get remove flashplugin-nonfree

# LibreOffice

## Fully fill table cell background

See [here](https://ask.libreoffice.org/en/question/15307/using-styles-for-background-cell-color-in-table/): 
Right click on cell, select "Table...", go to "Background" tab and set color "For Cell".

# Web Sites

## Define a preview picture used when linking from Facebook

In the `<head>` section:

    <link rel="image_src" href="my-image.jpg" /><!--formatted-->

# Misc Shell

## Create a Zip file that preserves symlinks

    zip --symlinks -r foo.zip foo/

## Add to $PATH

Use `~/.profile` not `~/.bash_profile`. See [here](https://unix.stackexchange.com/questions/26047/how-to-correctly-add-a-path-to-path)

## Allow shutdown and reboot without entering password

- https://makandracards.com/tushar/5181-how-to-shutdown-without-sudo-password-ubuntu-debian

## Check differences between two directories

Ignoring owner, group, permissions; ignoring newer files in upstream (`u`):

    rsync -rltDuv --dry-run --itemize-changes --exclude=.git downstream-dir upstream-dir >changes.txt

Link: [Understanding the output of rsync --itemize-changes](http://andreafrancia.blogspot.co.at/2010/03/as-you-may-know-rsyncs-delete-options.html)


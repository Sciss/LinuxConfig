#!/bin/sh

if [ "x$1" = "x" -o "x$2" = "x" ]; then
    echo Usage: `basename "$0"` "<input.pdf>" "<output.pdf>" >&2
    exit 1
fi

gs -sDEVICE=pswrite -dNOCACHE -sOutputFile=- -q -dbatch \
   -dNOPAUSE -dQUIET "$1" -c quit | ps2pdf - "$2"

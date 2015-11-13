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

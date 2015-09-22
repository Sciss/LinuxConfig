set number          " Show line numbers.
set mouse=a         " Enable the use of the mouse.
filetype plugin indent on
filetype on
set autoindent " Copy indent from the row above
set si " Smart indent

set wildmenu

set nocompatible "Not vi compativle (Vim is king)

""""""""""""""""""""""""""""""""""
" Syntax and indent
"""""""""""""""""""""""""""""""""""
syntax on " Turn on syntax highligthing
set showmatch  "Show matching bracets when text indicator is over them

set hls " highlight search
set lbr " linebreak

" Use 2 space instead of tab during format
set expandtab
set shiftwidth=2
set softtabstop=2

" let g:solarized_termcolors=256
" set background=dark
" colorscheme solarized

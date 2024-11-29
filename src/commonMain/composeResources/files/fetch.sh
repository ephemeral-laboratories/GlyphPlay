#!/usr/bin/env bash

/usr/bin/wget \
 --mirror \
 --no-parent --no-host-directories --cut-dirs=3 \
 --no-check-certificate \
 --directory-prefix ucd/ \
 https://www.unicode.org/Public/16.0.0/ucd/

( cd ucd && /usr/bin/unzip Unihan.zip )

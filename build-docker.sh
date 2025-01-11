#!/bin/sh

cp -r ../fastText/fasttext .
cp -r ../fastText/lid.176.bin .

docker build -t languagetool:latest .

rm fasttext
rm lid.176.bin

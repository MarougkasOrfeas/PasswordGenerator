#!/bin/bash

SRC=src/main/java
OUT=out
JAR=dist/password-generator.jar
MAIN_CLASS=gr.marou.password_generator.Main

# Create necessary directories
mkdir -p "$OUT"
mkdir -p dist

# Compile all Java files
echo "Compiling sources..."
find "$SRC" -name "*.java" > sources.txt
javac -d "$OUT" @sources.txt

# Create the JAR file
echo "Packaging JAR..."
jar cfe "$JAR" "$MAIN_CLASS" -C "$OUT" .

echo "JAR created at $JAR"

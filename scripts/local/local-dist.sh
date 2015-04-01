#!/usr/bin/env bash
#Executing all scripts for localhost

echo "Packing..."
sh scripts/package.sh

echo "Installing..."
sh scripts/install.sh

echo "Running..."
sh scripts/start-server.sh

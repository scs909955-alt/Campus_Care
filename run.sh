#!/usr/bin/env bash
# ==============================================================================
# CampusCare Build & Execution Script
# ==============================================================================

set -e

echo "========================================="
echo "       COMPILING CAMPUSCARE...           "
echo "========================================="

# Compile all modules
javac Main.java person/*.java management/*.java utility/*.java

echo "Compilation successful!"
echo ""
echo "========================================="
echo "       STARTING CAMPUSCARE...            "
echo "========================================="

# Execute Main
java Main

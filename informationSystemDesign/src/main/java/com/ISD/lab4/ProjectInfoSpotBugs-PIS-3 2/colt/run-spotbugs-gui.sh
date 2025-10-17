#!/bin/bash
# SpotBugs GUI launcher for IntelliJ IDEA
cd "$(dirname "$0")"
./spotbugs-4.8.6/bin/spotbugs -gui lib/colt.jar


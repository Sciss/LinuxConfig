#!/bin/bash
ps -x -o pid,cmd | grep nailgun | cut -f 1 -d ' ' | xargs kill

#!/bin/sh
sqlite3 song_db "select * from song limit 5;" > /dev/null

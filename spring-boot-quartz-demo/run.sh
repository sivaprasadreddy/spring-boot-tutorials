#!/bin/bash

vmargs="-Duser.timezone=GMT"
nohup java $vmargs -jar target/demo-0.0.1-SNAPSHOT.jar -Xms1G -Xmx3G > nohup.out &
#!/bin/sh

first=true
while IFS='' read -r line || [[ -n "$line" ]]; do
  if [ "$first" == "false" -a "$line" != "" ]
  then
    set -- "$line" 
    IFS=","; declare -a Array=($*) 
    json="{\"observed_time\": \"${Array[0]}\" , \"received_time\": \"${Array[1]}\" , \"value\": ${Array[2]} , \"quality_code\": ${Array[3]} , \"sensor_id\": ${Array[4]}}"
    echo "$json" > observation.json
    curl -i -XPUT -H "Content-Type: application/json" -H "Accept: application/json" --data @observation.json http://localhost:8080/observation
  fi
  first=false
done < "observations.csv"

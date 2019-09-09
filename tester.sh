#!/bin/bash
# Bash Script to do some light-weight integration tests for API validation
INPUT="./test_data.csv"
RED='\033[0;31m'
GREEN='\033[0;32m'
{
  read # Ignore header column
  while IFS= read -r line
    do
      # Doing some url encoding for the '+' in the ISO format
      read start end expected < <(echo "$line" | awk -F',' '{gsub(/\+/,"%2B")}; {print $1,$2,$3}')
      output=$(curl -s "localhost:8080/rates?start=$start&end=$end")

      if [[ "$expected" == "$output" ]]; then
        echo -e "${GREEN}PASS"
      else
        echo -e "${RED}FAIL! Expected: $expected != Actual: $output"
      fi
    done
} < "$INPUT"


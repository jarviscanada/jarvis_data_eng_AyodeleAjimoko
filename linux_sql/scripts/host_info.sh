#host_info.sh

# Setup and validate arguments
psql_host=$1
psql_port=$2
db_name=$3
psql_user=$4
psql_password=$5

# Check # of args
if [ "$#" -ne 5 ]; then
    echo "Illegal number of parameters"
    exit 1
fi

# Save machine statistics in MB and current machine hostname to variables
hostname=$(hostname -f)
lscpu_out=`lscpu`
cpu_number=$(echo "$lscpu_out"  | egrep "^CPU\(s\):" | awk '{print $2}' | xargs)

# hardware info
cpu_architecture=$(echo "$lscpu_out"  | egrep "^Architecture:" | awk '{print $2}' | xargs)

cpu_model=$(echo "$lscpu_out"  | egrep "^Model name:" | awk '{print $3}' | xargs)

cpu_mhz=$(echo "$lscpu_out"  | egrep "^CPU MHz:" | awk '{print $3}' | xargs)

l2_cache=$(echo "$lscpu_out"  | egrep "^L2 cache:" | awk '{print $3}' | xargs)

total_mem=$(vmstat --unit M | tail -1 | awk '{print $4}')

# Current time in `2019-11-26 14:40:19` UTC format
timestamp=$(date +"%Y-%m-%d %H:%M:%S")


# Subquery to find matching id in host_info table

st_id=$(psql -d db_name -U psql_user -c "SELECT id FROM host_info WHERE hostname='$hostname';" -t)

l2_cache_digits=$(echo "$l2_cache" | grep -o '[0-9]*' | tr -d '\n')

#PSQL command: Inserts hardware data into host_info table
insert_stmt="INSERT INTO host_info(hostname, cpu_number, cpu_architecture, cpu_model, cpu_mhz, l2_cache, timestamp, total_mem) VALUES('$hostname','$cpu_number', '$cpu_architecture', '$cpu_model', '$cpu_mhz', "$l2_cache_digits", '$timestamp', $total_mem);"


#env var for psql cmd
export PGPASSWORD=$psql_password 
#Insert date into the database
psql -h $psql_host -p $psql_port -d $db_name -U $psql_user -c "$insert_stmt"
exit $?


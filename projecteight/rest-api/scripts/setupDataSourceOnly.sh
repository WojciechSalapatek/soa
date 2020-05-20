CURRENT=$(pwd)
BATCH=$CURRENT/datasource.cli

echo "Please enter your wildfly home directory"
read -r JBOSS_PATH

echo "Please enter your postgresql driver name"
read -r DRIVER_NAME

echo "Saving batch script to $BATCH"

echo "connect 127.0.0.1

batch

data-source add --jndi-name=java:/PostgresDS --name=PostgresPool --connection-url=jdbc:postgresql://localhost:5432/soa?serverTimezone=UTC --driver-name=$DRIVER_NAME --user-name=postgres --password=postgres

run-batch" > "$BATCH"

echo "Make sure server is running"
read -p "Press enter to continue"
echo "Running batch script with jboss.cli"
"$JBOSS_PATH"/bin/jboss-cli.sh --file="$BATCH"

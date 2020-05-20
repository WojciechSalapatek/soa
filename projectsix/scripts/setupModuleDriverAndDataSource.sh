CURRENT=$(pwd)
DRIVER=$CURRENT/postgresql-42.2.12.jar
BATCH=$CURRENT/datasource.cli

echo "Using driver: $DRIVER"

echo "Please enter your wildfly home directory"
read -r JBOSS_PATH

echo "Saving batch script to $BATCH"

echo "connect 127.0.0.1

batch

module add --name=postgres --resources=$DRIVER --dependencies=javax.api,javax.transaction.api

/subsystem=datasources/jdbc-driver=postgres:add(driver-name=postgres,driver-module-name=org.postgres)

data-source add --jndi-name=java:/PostgresDS --name=PostgresPool --connection-url=jdbc:postgresql://localhost:5432/soa?serverTimezone=UTC --driver-name=postgres --user-name=postgres --password=postgres

run-batch" > "$BATCH"

echo "Make sure server is running"
read -p "Press enter to continue"
echo "Running batch script with jboss.cli"
"$JBOSS_PATH"/bin/jboss-cli.sh --file="$BATCH"

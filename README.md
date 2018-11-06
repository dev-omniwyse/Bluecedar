# Bluecedar Analizer API

Required Java 8
# Environment variables required:
Elastic search engine details:

# ES host url
export ES_HOST
# ES http ports. (,) saparated if multiple ports. Example: 9200,9201,9201
export ES_HTTP_PORTS

# ES XPAK datails. If XPAK installed on ES then ES_XPAK_INSTALED should be true and username and password are mandatory. If not ES_XPAK_INSTALED be false and username and password are not required
export ES_XPAK_INSTALED
export ES_USERNAME
export ES_PASSWORD

# Tomcat port number. Bydefault 8080
export SERVER_PORT


Environmental variables required to operate
# Application variables
export BC_ORGANIZATION_DATA_SERVICE_PORT="8080"
export SERVLET_CONTEXT_PATH='/v1/data'
export SERVICE_URL="http://localhost:$BC_ORGANIZATION_DATA_SERVICE_PORT/v1/data/actuator/health"
export EXPECTED_STATUS=200
export JAVA_OPTS='-Xmx1024m' # General java options
export CATALINA_OPTS='-Xmx1024m' # Tomcat specific options

# BC Service project
export DEFAULT_DB=bc_organization
export ENCRYPTION_KEY=soemfknadsfioandkn
export POSTGRESQL_DB_HOST="localhost:5550"
export POSTGRESQL_DB_USERNAME="bc_user"
export POSTGRESQL_DB_PASSWORD="bc_password"

# Logback Config (required only to send logs to Datadog service)
export BRANCH_NAME="dev"
export LOGSTASH_HOST="localhost"
export LOGSTASH_PORT="5001"

# Datadog variables
export DATADOG_AGENT_PORT=8125
export DATADOG_AGENT_HOSTNAME="localhost"
Build the docker container

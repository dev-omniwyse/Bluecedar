# Bluecedar Analizer API

Required Java 8

## Environmental variables required to operate
```
# Application variables
# Tomcat port number. By default 8080
export SERVER_PORT
export BRANCH_NAME

# Elastic search engine variables
# ES host url
export ES_HOST

# ES http ports. (,) saparated if multiple ports. Example: 9200,9201,9201
export ES_HTTP_PORTS

# ES XPAK datails. If XPAK installed on ES then ES_XPAK_INSTALED should be true and username and password are mandatory. If not ES_XPAK_INSTALED should be false and username and password are not required.

export ES_XPAK_INSTALED
export ES_USERNAME
export ES_PASSWORD
```


# ConfigThinger
Example Spring Cloud Config Server

## Running with Scissors

You can run with a simple gradle command:

```shell
./gradlew bootRun
```

By default, this server pulls configs from 

https://github.com/evanepio/example-scc-repo

These configurations are meant to be used by the Quoter microservice:

https://github.com/evanepio/Quoter

> If you want to use encrypted property values, you must set `encrypt.key` to something secure, and preferably provide it to this server via an environment variable. This key shuld be provided to client Sprng Boot apps and specified in the `bootstrap.properties` file, or preferably and environment variable.

## Some Endpoint Queries

To pull configs out of this service you can try:

```shell
curl --request GET \
  --url http://localhost:8888/quoter/default/main \
  --header 'Authorization: Basic cm9vdDpzM2NyM3Q=' 
```

If you need it as a properties file:

```shell
curl --request GET \
  --url http://localhost:8888/main/quoter-default.properties \
  --header 'Authorization: Basic cm9vdDpzM2NyM3Q=' 
```

Or to retrieve it as a YAML file:

```shell
curl --request GET \
  --url http://localhost:8888/main/quoter-default.yaml \
  --header 'Authorization: Basic cm9vdDpzM2NyM3Q=' 
```

It should not care if the property in the config repo is a "properties" file or a "yaml" file. Change `main` in the URL to the branch you want (but considering I likely only have a `main` branch, leave it be).

And to get any file from Spring Cloud Config server, we can use:

```shell
curl --request GET \
  --url http://localhost:8888/quoter/default/main/misc/secret.txt \
  --header 'Authorization: Basic cm9vdDpzM2NyM3Q=' 
```

For the above, the application name (quoter) and the profile (default) does not matter. We just need the branch (main) and the path of the file in the repo (misc/secret.txt) to be correct.

## Encrypted Properties

If you want to encrypt value, you can send it to the encrypt endpoint.

```shell
curl --request POST \
  --url http://localhost:8888/encrypt/ \
  --header 'Authorization: Basic cm9vdDpzM2NyM3Q=' \
  --header 'Content-Type: text/plain' \
  --data 'This is something'
```

Take the return of this service, and save it as a value like this:

```yaml
credentials:
  user: 'someuser'
  password: '{cipher}83fe45945be44db2923f9e6397e0fe395bd4f0c35cc5a37d5cea6d5d3ba8d68c008f1fec83aef16d1cc52e06832f8393'
```

To manually decrypt:

```shell
curl --request POST \
  --url http://localhost:8888/decrypt/ \
  --header 'Authorization: Basic cm9vdDpzM2NyM3Q=' \
  --header 'Content-Type: text/plain' \
  --data 83fe45945be44db2923f9e6397e0fe395bd4f0c35cc5a37d5cea6d5d3ba8d68c008f1fec83aef16d1cc52e06832f8393
```

> Only properties can have encrypted values. The various files will be returned as is.
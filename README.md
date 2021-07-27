# ConfigThinger
Example Spring Cloud Config Server

You can run with a simple gradle command:

```
./gradlew bootRun
```

By default, this server pulls configs from 

https://github.com/evanepio/example-scc-repo

These configurations are meant to be used by the Quoter microservice:

https://github.com/evanepio/Quoter

To pull configs out of this service you can try:

```
curl --request GET \
  --url http://localhost:8888/quoter/default/main \
  --header 'Authorization: Basic cm9vdDpzM2NyM3Q=' 
```

If you need it as a properties file:

```
curl --request GET \
  --url http://localhost:8888/main/quoter-default.properties \
  --header 'Authorization: Basic cm9vdDpzM2NyM3Q=' 
```

Or to retrieve it as a YAML file:

```
curl --request GET \
  --url http://localhost:8888/main/quoter-default.yaml \
  --header 'Authorization: Basic cm9vdDpzM2NyM3Q=' 
```

It should not care if the property in the config repo is a "properties" file or a "yaml" file. Change `main` in the URL to the branch you want (but considering I likely only have a `main` branch, leave it be).

# Rainy Hills
REST based J2EE 7 solution for Rainy Hills problem. 

## Deployment instructions
### Prerequisites
1. Maven 3 installed
2. Java 8 installed
3. Wildfly 13.0.0.Final installed

###Build the artifact and deploy to the Wildfly
```
mvn clean wildfly:deploy
```

## Testing instructions

1. Open Swagger (OpenAPI) url: ```http://<server>:<port>/RainyHills/```

_Example: http://localhost:8080/RainyHills/_
2. Select ```/api/rainy-hills/volume``` endpoint
3. Click on ```Try it out``` button
4. Define array in ```Request body``` field (JSON format)

_Example:_
``` 
{"array": [3, 2, 4, 1, 2]}
```
 5. Click on ```Execute``` button
 6. Result/Response will be populated into the ```Response body``` field.
 
 _Example:_
 ```
{
  "volume": 2
}
```
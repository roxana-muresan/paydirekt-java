# paydirekt Java Client

Java client implementation and samples for the [paydirekt REST API](https://www.paydirekt.de/haendler/merchant-api.html).

The source code demonstrates how to create valid requests for the paydirekt REST API using Java.


## Requirements
* Java 8 or later
* Java Cryptography Extension (JCE)
* Maven 3 or later

## Dependencies
The following dependencies are used:
* [SLF4J](http://www.slf4j.org/) as a logging facade. Just add a binding for your favorite logging framework.

## Recommended 3rd-Party Libraries
The following libraries are recommended and their use is demonstrated in the example code:
* [Apache HttpClient](https://hc.apache.org/httpcomponents-client-4.5.x/index.html) for http connections, which is highly
  recommended over Java's HttpUrlConnection.
* [Jackson 2](http://wiki.addfasterxml.com/JacksonRelease20) for JSON serialization and deserialization.

## Usage
Make sure that Java 8, the Java Cryptography Extension (JCE) and Maven are properly installed.
Then clone this repository and run the tests:

```
mvn clean test
```

By default, all requests run against the sandbox environment.
To run against the actual production endpoints, set the system property:

```
-Dproduction=true
```


## HMAC Signature
The HMAC signature (to be used in the `X-Auth-Code` header) can be created using the [`Hmac.java`](src/main/java/de/paydirekt/client/security/Hmac.java) class.

```
String randomNonce = Nonce.createRandomNonce();
String signature = Hmac.signature(requestId, now, apiKey, apiSecret, randomNonce)
```

API-Key and API-Secret for your shop are provided via the paydirekt merchant portal.
Be aware, that there are different credentials for sandbox and live mode.

Please refer to [`ObtainTokenIntegrationTest.java`](src/test/java/de/paydirekt/client/ObtainTokenIntegrationTest.java) for a full example how to build an http request with all header fields
and payload using the [Apache HttpClient](https://hc.apache.org/httpcomponents-client-4.5.x/index.html).

## Security Advice

Do never print sensitive information to log files. The following values should never be logged:
* API-Secret
* OAuth2 Access Token

## License
MIT License.
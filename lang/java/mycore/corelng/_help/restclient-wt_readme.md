# restclient-wattsight
https://api.wattsight.com

##  Consideration
* failed status
    * retries
* asynchronous calls


## Testing
Main test
`restclient-wt/src/../WattsightClientTest.java`

## Structure

####  Wattsight Client
##### Constructor
private Constructor
##### fields
* WattsightAuthenticationToken token
* ObjectMapper objectMapper
* clientId, clientSecretKey
##### Responsiblities:
* login   :v
	* getToken()
	* refreshToken()
* restCall()
	* wrapper to take care about refreshing token
* getters for specific Endpoints Client

## restclieent-common
### UriBuilder
* implements Builder pattern
* has static constructor,  called in `UriBuilder.url()`
*  UrilBuilder.build() returns a string

### RestRequest, RestReponse
@Data, @Builder lombok  beans

### RestUtils
Where the main work happens
_restCall(ObjectMapper, RestRequest,Authentication,Map<String,String>)_  is where an actuall rest call happens with  get, put, post delete  calls

_parseResponse(ObjectMapper, RestReponse,ClientResponse<?>, Class<T>)  maps responses to lombok @Data Entities


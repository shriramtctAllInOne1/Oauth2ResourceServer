- Run the code using 

		mvn spring-boot:run
		
- Open the PostMan and use below url to get token
 
      http://localhost:8087/foo
      
- Add token as  Bearer    

-------------------------------------------------------API-----------------------------------------

API URL :
http://localhost:8087/StockAnalyzer/FetchSymbolDataFromGdfAPI

Request : 

{
    "symbolName": "NIFTY",
   "expiryDate": [
        "27JUN19",
        "04JUL19",
        "11JUL19",
        "18JUL19",
         "25JUL19",
         "01AUG19",
         "08AUG19",
         "15AUG19",
         "22AUG19",
         "29AUG19"
         
      ],
    "peUp": 20,
    "peDown": 20,
    "ceUP": 20,
    "ceDown": 20,
    "stepSize": 50,
    "ltp": 11450
}

------------------------------------------------createWatchList---------------------------------------------

API : http://localhost:8087/StockAnalyzer/createWatchList

Request :
{
  "WatchList1": [
    {
      "symbolName": "NIFTY",
      "expiryDate": [
        "27JUN19",
        "04JUL19",
        "11JUL19",
        "18JUL19",
         "25JUL19",
         "01AUG19",
         "08AUG19",
         "15AUG19",
         "22AUG19",
         "29AUG19"
         
      ],
      "peUp": 20,
      "peDown": 20,
      "ceUP": 20,
      "ceDown": 20,
      "stepSize": 50,
      "ltp": 11450
    }
   
  ]
}
-----------------------------------Create user------------------------------------------

      
  {
  "username": "ramtest",
  "password": "ramtest",
  "role": "USER",
  "clientId": "web-client1",
  "clientSecret": "web-client-secret1",
  "secretRequired": "true",
  "resourceIds": "oauth",
  "scoped": true,
  "scope": ["read","write"],
  "authorizedGrantTypes": ["password",
  "refresh_token","client_credentials"],
  "registeredRedirectUri": "http://localhost:8082",
  "accessTokenValiditySeconds": "60",
  "refreshTokenValiditySeconds": "60",
  "autoApprove": false
}
  
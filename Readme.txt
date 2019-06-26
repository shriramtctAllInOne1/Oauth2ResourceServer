- Run the code using 

		mvn spring-boot:run
		
- Open the PostMan and use below url to get token
 
      http://localhost:8087/foo
      
- Add token as  Bearer    

-------------------------------------------------------API-----------------------------------------

API URL :
http://localhost:8087/StockAnalyzer/oaitm

Request : 

{
    "symbolName": "NIFTY",
   "expiryDate": [
    "13JUN19",
    "20JUN19",
    "27JUN19",
    "04JUL19"
  ],
    "peUp": 20,
    "peDown": 20,
    "ceUP": 20,
    "ceDown": 20,
    "stepSize": 50,
    "ltp": 11450
}


      
  
  
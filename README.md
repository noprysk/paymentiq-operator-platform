# PaymentIQ Integration API

For REST API is available Swagger representation:
    http://127.0.0.1:3000/api/swagger-ui.html
    
Logic rules:

    1. user has balance: 100 USD;
    
    2. authorize call: -60 UDS
        - check if user balance is not over credited. (user balance + (all user PENDING withdrawal transactios) - 60 < 0) 
             Not: create transaction with status PENDING;
             Yes: create transaction with status REJECTED and return error.
        - if amout is 60 USD (deposit) this is no checking for over credited, just create PENDING transaction.
    
    3. transfer call: with transaction Id (txId) and txAmount = -60;
        - it just set status of PENDING transaction to APPROVED and decrease user balance for 60 USD.    
        - if transaction Id (txId) and txAmount = -100 (different than in authorize call) then return error.   
              
    4. cancel call: set status of PENDING transaction to CANCELED.
   
REST API calls (all data storage just in memory):

    1. verifyuser POST: 
        http://127.0.0.1:3000/api/verifyuser
        with request body:
        {
            "sessionId": "<sessionId>", // can be any value, this is no any validation for sessionId, except presents  
            "userId": "demoUser"        // PASS: demoUser, FAIL: any other value or not present
        } 
        
        There are validations:
            for required fields (both fields are required);
            user exists.
        
    2. authorize POST
        http://127.0.0.1:3000/api/authorize
        with request body:
        {
            "userId": "demoUser"        // PASS: demoUser, FAIL: any other value or not present
            "txAmount": -60,
            "txAmountCy": "USD",        // PASS: if txAmountCy == user currency, FAIL: any other  
            "txId": "1",                 
            "txTypeId": 101,
            "txName": "NetellerDeposit",
            "provider":"Neteller"
        } 
        
        There are validations:
            for required fields (above list of required fields);
            user exists and correct currency;
            check user balance will not be over credited.
            
    3. transfer POST
        http://127.0.0.1:3000/api/transfer
        with request body:
        {
            "userId": "demoUser"                     // PASS: demoUser, FAIL: any other value or not present
            "authCode": "<id from authorize call>",  // is optional
            "txAmount": -60,
            "txAmountCy": "USD",                     // PASS: if txAmountCy == user currency, FAIL: any other
            "txPspAmount": "111",
            "txPspAmountCy": "USD",
            "fee": "0.50",
            "feeCy": "USD",
            "txId": "1",                            // PASS: if txId the same as was in authorize, FAIL: other not existed
            "txTypeId": 101,
            "txName": "NetellerDeposit",
            "provider":"Neteller",
            "txRefId": "100019999A26720"
        } 
                
        There are validations:
            for required fields (above list of required fields);
            user exists and correct currency;
            transaction exists and check data (amount) consistency.
            
    4. cancel POST 
        http://127.0.0.1:3000/api/cancel
        with request body:
        {
            "userId": "demoUser"                     // PASS: demoUser, FAIL: any other value or not present
            "authCode": "<id from authorize call>",  
            "txAmount": -60,
            "txCurrency": "USD",                     // PASS: if txAmountCy == user currency, FAIL: any other
            "txId": "1",                             // PASS: if txId the same as was in authorize, FAIL: other not existed   
            "txTypeId": 101,
            "txName": "NetellerDeposit",
            "provider":"Neteller"
        } 
                
        There are validations:
            for required fields (above list of required fields);
            user exists and correct currency;
            transaction exists and check data (amount) consistency.
    
    5. get transactions GET - just return all transactions
        http://127.0.0.1:3000/api/transactions

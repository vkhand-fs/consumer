# Instructions
1. Start the  Zookeeper  and make sure it is running at port : 2181.
   
   **zookeeper-server-start.bat C:\Vinay\kb\kafka_2.12-2.8.0\config\zookeeper.properties**
2. Start the Kafka server and make sure it is running at port : 9092
   
   **kafka-server-start.bat C:\Vinay\kb\kafka_2.12-2.8.0\config\server.properties**
3. Create the topic -  fixedincome.trades.data
   
   **kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic fixedincome.trades.data**

4. Start the 'Producer' Microservice and make sure it is running at port : 9081

5. Start the Consumer Microservice and make sure it is running at port : 9082

6. Launch PostMan and hit the POST url provided by 'Producer' MS. This should publish the trade deals into Kafka topic

**POST localhost:9081/fixed-income/trades**
    
    **Headers**
    Accept:application/json
    Content-Type:application/json

    **Body**
    {
                "timestamp": 1590566743,
                "symbol": "ABC",
                "price": 300,
                "size": 200,
                "status": "X"
    }

7. Launch PostMan and hit the GET url provided by 'Consumer' MS. This MS is subscribed to the topic and should get all the trade statistics.

**GET - localhost:9082/fixed-income/trades/stats**

    **Sample Output**
    {
        "maxTradeSizes": [
            {
                "symbol": "ABC",
                "size": 500
            },
            {
                "symbol": "XYZ",
                "size": 500
            }
        ],
        "avgTradePrices": [
            {
                "symbol": "ABC",
                "price": 350
            },
            {
                "symbol": "XYZ",
                "price": 266.6666666666667
            }
        ],
        "tradesCounts": [
            {
                "symbol": "ABC",
                "count": 2
            },
            {
                "symbol": "XYZ",
                "count": 3
            }
        ]
    }

**GET - localhost:9082/fixed-income/trades** 

This should return all the trades that are published into the topic

**Sample output**

{
    "trades": [
        {
            "timestamp": 1590566743,
            "symbol": "XYZ",
            "price": 300,
            "size": 300,
            "status": "X"
        },
        {
            "timestamp": 1590566743,
            "symbol": "XYZ",
            "price": 100,
            "size": 200,
            "status": "X"
        },
        {
            "timestamp": 1590566743,
            "symbol": "XYZ",
            "price": 400,
            "size": 500,
            "status": "X"
        },
        {
            "timestamp": 1590566743,
            "symbol": "ABC",
            "price": 400,
            "size": 500,
            "status": "X"
        },
        {
            "timestamp": 1590566743,
            "symbol": "ABC",
            "price": 300,
            "size": 200,
            "status": "X"
        }
    ]
}

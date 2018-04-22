Nautical Logger
===================
A Priority Message Reciever

*Author: Parag Srivastava*

*Date: 4/21/2018*

Running Tests
----------------
To run the tests, download the source code and run the [junit tests](https://github.com/psrivast/NauticalLogger/tree/master/src/test)

Alternatively, you can download the jar file and Build the artifact in your preffered IDE

Design
---------
### Log
This class is an object representation of a log that includes a priority and a timestamp. The purpose of this class is to allow for a log to hold more information and have an easy solution to add more fields. In the future it  can perhaps use some sort of unbounded list of attributes to allow for more information to be stored

### Logger
Main logging class that allows for logging of messages with priority. This interfaces with the LogsCache to update messages. Additional functionality to log messages without priority is there with special logging functions

### LogBuffer
This holds a thread-safe mutable sequence of characters through a StringBuffer. This allows for ongoing addition to a log statement through the __append__ or __appendNewLine__ methods. Once the client has completed adding to the log messages, the __done__ method is called to add the log message to the cache

### LogReader
This provides an interface to retrieve log messages in the order that is prioritized by
1. Priority (3 = HIGH, 2 = MED, 1 = LOW)
2. Most Recent
This also provides the option to recieve the message formatted with all the additional items in the __Log__ class. __dumpAllLogs__ puts a report of all the logs in a txt file that can be stored.

### LogCache
Singleton that holds all of the messages. The underlying data structure is a *ConcurrentHashmap* that ensures that multiple loggers can modify the store of messages without any race conditions. There is teh standard interface to get and put messages, but also the functionality to recieve a formatted dump of all files and to clear the logs safely.

Example Workflows
--------------------
### Using a single logger
To log single messages, call Logger's log method with a priority and a message
```java
Logger logger = new Logger();
logger.log(1, msg1);
logger.log(2, msg2);
```

### Not specifying priority
There are functions available to allow logging to occur without specifying priority everytime
```java
logger.logHighPriority(msg3);
logger.logMedPriority(msg4);
```

### Using a log buffer
If the user wants to create a message over multiple lines, they can simply use a __LogBuffer__
```java
LogBuffer lb = new LogBuffer(1);
lb.append("abc");
lb.append("def";
lb.appendNewLine("ghijkl");
lb.close();
```

### Retrieve Logs
To retrieve logs, use the __LogReader__ class. This returns the logs in prioritiezed order.
```java
LogReader logReader = new LogReader();
logReader.get(); // msg3
logReader.get(); // msg4
logReader.get(); // msg2
logReader.get(); // abcdef(\n)ghijkl
logReader.get(); // msg1
logReader.get(); // message indicating empty logs
```

### Dumping formatted logs into a txt file
With logs, we would like to be able to save them for future reference along with additional information. To save the logs with timestamp and priority, simply use the __dumpAllLogs__ method
```java
logReader.dumpAllLogs();
```

Making Memory Unbounded
---------------------------
The LogsCache makes this easy to do. The client will need to specify the capacity which will be configurable in a separate file. When the log cache has reached capacity, the __clear__ function will make it easy to handle with a few courses of action. This is the workflow I would choose:
1. Dump all the logs into a txt file. The file name should include a timestamp, so that the name is unique. This can be done with the __dumpAllLogs__ method.
2. Clear the cache entirely. This can be done with the __clear__ method.
3. Return a code to the client indicating the logCache has been reset

I chose to descripe and implement the necessary functionality for this instead of fully implementing it as doing so would have been too intrusive to the basic design.

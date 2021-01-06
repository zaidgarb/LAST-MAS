# LAST-MAS
MultiAgents Systems 2020-2021 URV- ZAID GARB

##Task

The main purpose of this task is to create a fuzzy agent-based decision support system (FA-DSS), which allows make decisions about inputs using fuzzy inference mechanisms built into specific agents.
The system performs two main actions: preparation of a fuzzy agent-based system and processing of user requests in order. Initialization is performed in accordance with the requirements sent by the user. Once the agent is ready to work, the user can use it to evaluate various records. One of the main goals is to create an open system and a scalable system that could be used in different circumstances and taking into account different strategies and topologies.
In this task inference mechanism activates the fuzzy rules contained in its knowledge base, the premises of which are under fuzzy entrances, and then aggregates all the conclusions of these activated rules. Finally, the inference mechanism turns a fuzzy solution into an output area.
This task is performed in 4 different classes, namely – Driver, FuzzyAgent, ManagerAgent and UserAgent.

##Implementation:

Class Driver is used as start point , to launch ManagerAgent and UserAgent with passing created instance of ManagerAgent.
ManagerAgent class had Boolean flag of initializing and list for FuzzyAgent storing. Also that class consist 3 methods:
•	evaluate – get String path as parameter, read that file and interpret each line by translateData method
•	translateData – get domain and input Strings as parameters. Input String parse like double numbers, and interpret by existing Fuzzy agents. Result of interpreting  aggregated, using appropriate method from agents
•	initialize – get String path as parameter, read properties from file and  construct defined amount of agents

UserAgent class contain link to ManagerAgent class and has method runCycle, which in cycle accept commands:
•	I – read initialization file
•	D – read evaluation file
•	E – exit program
FuzzyAgent contain defined list of possible configuration, and fields to storing data. Also FuzzyAgent contain all calculation logic. In constructor method this class initialize fuzzy logic with all parameters. Also, class contain getter to aggregation String and “evaluate” method, which read selected numbers array and return output.

##Work cycle:

Driver class create and run ManagerAgent and UserAgent. In repeated cycle UserAgent accept data from user in 3 possible commands:
•	initializing with init file – system read file, initializing some counts of FuzzyAgent objects with requested parameters
•	evaluating data file – system read file and pass data through FuzzyAgent to obtain data
•	exiting – system exit

##Testing
Program was tested in manual mode. Input commands was entered manually. In result, all incorrect command was ignored, and all entered files was processed

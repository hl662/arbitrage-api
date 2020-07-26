# Arbitrage-api
A Java api using the Bellman-Ford algorithm to find arbitrage opportunities on the foreign exchange market (FOREX).

## Description
I was reading about graph algorithms in Thomas H. Cormen's *Algorithms Unlocked* and came by the Bellman-Ford Algorithm and some of its applications. I was self-learning Java at the time and decided to make my first personal project - arbitrage-api.

The goal of this project at the start was to learn Java syntax through application. Along the way I learned how to use IntelliJ IDEA, and create a Maven project.  

The program receives real-time JSON data from [api.exchangeratesapi.io][1], processes the data into a weighted directed graph and runs the algorithm.

**Note:** This project is not meant for real world use. It is extremely unlikely that this program would find an arbitrage opportunity when it is given real-world data, as I am sure there are people who get paid to make better, faster algorithms  than a student who did this in his free time.
## Requirements
* Java 11 or higher.
* Maven is installed on your computer.
* IntelliJ is preferred.
## Steps
### Installation
 Create an empty repository on your local machine, and enter the following commmand into your terminal: 
 ```bash
  git clone https://github.com/hl662/arbitrage-api.git 
```
Alternatively, you can donwload the zipped file of the repository and extract it into the empty repo.

### Build and Run
If you have IntelliJ IDEA, simply go to `src/main/java/BellmanFord.java` and run or debug main()!

[1]: https://exchangeratesapi.io/
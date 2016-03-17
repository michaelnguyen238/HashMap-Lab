# HashMap-Lab
Objective - Implement the Map ADT as a hash table with separate chaining.

In this lab, I was required to use my knowledge of Maps and Hash Tables to combine the two and create a HashMap. Additionally, I was to read my data from a CSV file containing the data to be operated on.

Challenges - Fortunately for me, I did not have any issues that were too time consuming to solve. The only problem I found was that I was getting an OutOfBounds exception when trying to insert a new node onto my HashMap. After thorough research I realized that .hashcode() can sometimes give negative values, which resulted in my program attempting to insert a new node at a negative index. A simple Math.abs() solved this for me.

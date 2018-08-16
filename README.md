**Project Sketch**<br>
Restaurant 6431 is now open. A restaurant requires careful coordination of resources, which are the tables in the restaurant, the cooks available to cook the order, and the machines available to cook the food (we assume that there is no contention on other possible resources, like servers to take order and serve the orders).

Your simulation must create one thread for each arriving diner, which will then compete for an available table. There will also be one thread for each cook, all of them active for the entire duration the restaurant is open. Tables and machines for cooking the food are resources whose use must be coordinated among the threads.

Your simulation should output when each diner was seated, which table they were seated in, which cook processed their order, when each of the machines was used for their orders, and when the food was brought to their table. Finally, you should state the time when the last diner leaves the restaurant. 

**My Run Result**<br>
The leaving time of last diner for data1.txt and data2.txt is 177 and 252~255 respectively.

**Step 1**<br>
Put your extra test files in the current directory, the same as where the sample test "data1.txt" locates.

**Step 2**<br>
`make rebuild`

**Step 3**<br>
`make run FILE=yourfilename`<br>
Correct example: <br>
`make run FILE=data1.txt`

**Note 1**<br>
For the step 3 command, no space is allowed proceeding or post to "=". <br>
For example, **make run FILE =data1.txt** and **make run FILE= data1.txt** are both illegal.

**Note 2** <br>
The initial excution of Step 3 may achieve an undesirable answer. Please repeat executing Step 3 one or two more times for a normal result.

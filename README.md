**Normal Run Result**

The leaving time of last diner for data1.txt and data2.txt is 177 and 252~255 respectively.

**Step 1**

Put your extra test files in the current directory, the same as where the sample test "data1.txt" locates.

**Step 2**

> make rebuild

**Step 3**

> make run FILE=yourfilename

Correct example: **make run FILE=data1.txt**

**Note1**

For the step 3 command, no space is allowed proceeding or post to "=".

For example, **make run FILE =data1.txt** and **make run FILE= data1.txt** are both illegal.

**Note 2**

The initial excution of Step 3 may achieve an undesirable answer. Please repeat executing Step 3 one or two more times for a normal result.



# IntercomSampleCode
The program is coded in Java for android using Android Studio 3.5.3.<br><br>
Project consists of 4 .java files (including 1 test file).<br><br>
Programs initialises with MainActivity.java file in which onCreate() method runs automatically as per android activity life cycle.<br><br>
MainActivity.java consists of main methods and method calls.<br><br>
1) MainActivity.java first check for internet connection.<br>
If internet connection is available, it reads the file from URL by calling ReadFileAsyncTask.java class.<br><br>
2) ReadFileAsyncTask.java class extends asynchronous call using system defined methods (functions).<br>
doInBackGround() method in ReadFileAsyncTask.java class is called automatically to read and store data from file.<br>
onPostExecute() method automatically gets called after doInBackGround() method. <br>
In onPostExecute() method, the data is extracted. <br><br>
3) After extracting the data, distance is calculated using calculateDistance() method in MainActivity.java class. - The result is calculated in Kilometers.<br>
NOTE: ConstantValue is used to convert degrees to kilometers. Values are rounded off to 0 decimal place.<br><br>
4) Calculated Result is then checked. If result is less than 100km, IDs and names are then added in a list using Map class. <br><br>
5) These values are then added in TreeMap to sort based on user IDs in ascending order. <br>
Final sorted list is then printed.<br><br>
- CustomMethods.java class consists of generic functions, like conversion of string to double, round off double, etc.

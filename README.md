# Mobile-App-Development
Work done for the Mobile Device Application Course with Professor Guillermo Rivas. 

## Partial 1 
- Identify elements in Layout
- Toast
- Intent
- Shared Preferences 
- SQLite

## Identify elements in layout

For each activity, two files are created: a java file and a xml file. The xml is the one that containts the layout of the activity with a set of elements. How can the java file make use of those elements contained in the xml for it to make use of them?

For example, a Plain Text element is inserted in the xml file. The java file identifies it as a EditText object. Then, one should be able to find that element by its id for it to be assign into a variable. It is suggested for such step to be done within the `onCreate()` function for such elements to be assigned on initialization of activity.

```java
private EditText varName;
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        varName = findViewById(R.id.idOnXML);
}
```
After that, one can now make use of such element. Let's say the code will set a certain text to such element when a function is called. 

```java
public void myFunction(View v){
    varName.setText("New text");
}
```

One can assign the previous function to a button by selecting it on the `onClick()` attribute of the button in the XML file. It is important both to make the function public and for it to receive a View for this to be identified by the XML. s

## Toast

Toast is used to send a message to the user after performing an action. This way the user can know if such action was performed succesfully or if an error ocurred. 

```java
Toast.makeText(this, "Message to the user",Toast.LENGTH_SHORT).show();
```

## Intent

Intent can be used to start another activity. In this case, it was used to change between views. So, in this case if we wanted to go from Activity A to Activity B, you use:

```java
Intent intent = new Intent(this, ActivityName.class);
startActivity(intent);
```

It is possible to send values as a variable from activity A to Activity B, before starting the activity, with the following function of Intent.

```java
intent.putExtra("varName",value);
```

You can also send values from the Activity B to Activity A.

First, one should start the new activity, but expecting a certain result.

```java
startActivityForResult(intent, REQUEST_CODE);
```

Then, from Activity B, you send the value using *putExtra()* as before. Then, finish the activity after setting the result that the previous function called for.

```java
intent..putExtra("varName",value);
setResult(Activity.RESULT_OK, intent);
finish();
```

After activity B finished, activity A reacts to the result received from B. For example, if Activity B sent a string value and we want it to be set as the value of a TextView. Then one should use the following function in Activity A:

```java
protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        tvName.setText(data.getStringExtra("varName"));
}
```

## Shared Preferences

Shared preferences can be used to store data within the application.

```java
private static final String FILE = "filename";
private SharedPreferences prefs;
```
With this one can used different functions of the object. For example, one can store a value by using an Editor.

```java
public void save(){
    SharedPreferences.Editor editor = prefs.edit();
    editor.putString("varStored","value");
    editor.commit();
}
```

One can use a getter to obtain the value stored in prefs. If no value is stored, then the getter has a parameter for the default value. Let's load the value stored in prefs into a TextView.

```java
public void load(){
    prefs = getSharedPreferences(FILE,MODE_PRIVATE);
    varName.setText(prefs.getString("varStored","default"));
}
```

As usual, if a value can be saved one should be able to delete it as well. One way to do it is by removing just one field (variable). The object Editor is needed for this as well.

```java
public void deleteVar(){
    SharedPreferences.Editor editor = prefs.edit();
    editor.remove(varStored);
    editor.commit();
}
```

Then, one can also delete all of the variables stored in the shared preferences.

```java
public void deleteAll(){
    SharedPreferences.Editor editor = prefs.edit();
    editor.clear();
    editor.commit();
}
```

## SQLite

A database can be used to store values as well. In this case, with SQLite one can make use of a DB. 

First one should create a DBHelper java file that will take care of creating an object that extends from `SQLiteOpenHelper`. Within the DBHelper, one should create the tables of the database depending on the aplication. One can do it within the overrided `onCreate()` for the database to be initializated with atleast one table. Fun fact, primary keys are automatically generated when a value is inserted in the table. 

```java
@Override
public void onCreate(SQLiteDatabase db) {
    db.execSQL("CREATE TABLE TABLE_NAME(
        FIELD_ID INTEGER PRIMARY KEY, 
        FIELD_ONE TEXT, 
        FIELD_N DATATYPE)"
    );
}
```

Other function to implement and override is the `onUpgrade` function in case a new version of the databased is released.

```java
@Override
public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("DROP TABLE IF EXISTS TABLE_NAME", {"TABLE_NAME"});
}
```
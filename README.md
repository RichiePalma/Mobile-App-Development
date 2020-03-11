# Mobile-App-Development
Work done for the Mobile Device Application Course with Professor Guillermo Rivas. 

## Partial 1 
- Identify elements in Layout
- Toast
- Intent
- Shared Preferences 
- SQLite

## Identify elements in layout

For each activity, two files are created: a java file and a xml file. The xml is the one that contains the layout of the activity with a set of elements. How can the java file make use of those elements contained in the xml for it to make use of them?

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

Toast is used to send a message to the user after performing an action. This way the user can know if such action was performed successfully or if an error ocurred. 

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

First one should create a DBHelper java file that will take care of creating an object that extends from `SQLiteOpenHelper`. Within the DBHelper, one should create the tables of the database depending on the application. One can do it within the overrode `onCreate()` for the database to be initialized with at least one table. Fun fact, primary keys are automatically generated when a value is inserted in the table. 

```java
@Override
public void onCreate(SQLiteDatabase db) {
    db.execSQL("CREATE TABLE TABLE_NAME(
        FIELD_ID INTEGER PRIMARY KEY, 
        FIELD_ONE TEXT, 
        FIELD_N DATATYPE)"
    );
    //DATATYPE could be any valid for SQLite
    //For the examples, FIELD_N will be TEXT
}
```

Other function to implement and override is the `onUpgrade` function in case a new version of the database is released.

```java
@Override
public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("DROP TABLE IF EXISTS TABLE_NAME", {"TABLE_NAME"});
}
```
Most importantly,do not forget about the DBHelper constructor.

```java
public DBHobbies(Context context){
    super(context,DB_FILE,null,1);
}
```
As same as prefs, SQLite implements functions that allow the developer to save, delete and find values. When creating a new `SQLiteDatabase` it is important to identify the permissions needed. In other words, if it is a readable database or a writeable one. 

In case of storing values in the database, it needs writeable permissions, of course. Then by making use of `ContentValues` is how one gathers the table column and value to be inserted into the database.

```java
public void save(String value, String value2){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(FIELD_ONE,value);
        values.put(FIELD_N,value2);
        db.insert(TABLE_NAME,null,values);
}
```

Same permissions apply for the process of deleting a value from the table. The `SQLiteDatabase` function `delete()` takes three parameters: Table name, delete condition and an array of arguments. This function returns an integer value that represents the number of rows deleted from the database that contained the value given. 

You can make use of a bind variable by using the question mark symbol for the delete condition to identify the argument value during runtime. 

```java
public int delete(String value){
    SQLiteDatabase db = getWritableDatabase();
    return db.delete(TABLE_NAME,FIELD_ONE + " = ?",{value});
}
```

This delete only deletes all rows that match with the value. But it is possible to delete the whole database by changing the parameters of the `delete()` function.

```java
public int deleteAll(){
    SQLiteDatabase db = getWritableDatabase();
    return db.delete(TABLE_NAME,null,null);
}
```

Finally, searching a value in the database is essential. In this case, a `Cursor` is needed to explore the database in search for the value requested. This object moves to the first row if there is one. Then it will do what the developer tells it to do while there is a next row. The cursor has getter methods that will allow it to obtain the value of a specified column

```java
public String find(String value){
    SQLiteDatabase db = getReadableDatabase();
    String filter = FIELD_ONE + " = ?";
    String[] args = {value};
    Cursor c = db.query(TABLE_NAME, null,filter,args,null,null,null);

    String result = "";
    if(c.moveToFirst()){
        do{
            // Do something
            result = c.getString(2)
        }while(c.moveToNext());
    }
    return result
}
```

Now that the DBHelper is completely set up, one can use it in an activity by creating such object along with `Properties` stored in an xml file. Such file error should be handled properly. 

```java
private DBHelper db;
private Properties properties;
private static final String PROPERTIES_FILE = "properties.xml";

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_menu);
    db = new DBHobbies(this);
    properties = new Properties();

    File file = new File(getFilesDir(),PROPERTIES_FILE);

    if (file.exists()) {
        Toast.makeText(this, "FILE EXISTS", Toast.LENGTH_SHORT).show();
        try{
            FileInputStream fis = openFileInput(PROPERTIES_FILE);
            properties.loadFromXML(fis);
            fis.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }else{
        Toast.makeText(this,"FILE DOESN'T EXIST",Toast.LENGTH_SHORT).show();
        saveProperties();
    }
}

private void saveProperties(){
    try{
        FileOutputStream fos = openFileOutput(PROPERTIES_FILE, Context.MODE_PRIVATE);
        properties.storeToXML(fos,null);
        fos.close();
    }catch (Exception e){
        e.printStackTrace();
    }
}
```

And now we can use the functions of DBHelper within the Java file of the activity.

```java
public void store(View v){
    db.save("value");
}

public void remove(View v){
    db.delete("value");
}

public String search(View v){
    return db.find("value");
}
```
# Meteor Android-DDP Boilerplate Example


This is Meteor Android-DDP Boilerplate, uses the [Android-DDP](https://github.com/delight-im/Android-DDP/) library.


Connect your native Android apps, written in Java, to apps built with the [Meteor](https://www.meteor.com/) framework and build real-time features.

And this boilerplate shows how to manage it/





## Installation

 * Add this library to your project
   * Declare the Gradle repository in your root `build.gradle`

     ```gradle
     allprojects {
         repositories {
             maven { url "https://jitpack.io" }
         }
     }
     ```

   * Declare the Gradle dependency in your app module's `build.gradle`

     ```gradle
     dependencies {
         compile 'com.github.delight-im:Android-DDP:v2.1.0'
     }
     ```

 * Add the Internet permission to your app's `AndroidManifest.xml`:

    `<uses-permission android:name="android.permission.INTERNET" />`

## Configure

 * Creating a new instance of the DDP client

   ```
   public class App extends Application {

    //This is your url set ws if not secured yet
  
    String url = "wss://yourURL.com/websocket";
    //Replace with your URL
    

    @Override
    public void onCreate() {
        super.onCreate();


        //we create a Singleton of Meteor instance and use it throughout the app life cycle
        MeteorSingleton.createInstance(this, url);


        //Enable when Debugging and better remove it when deploying
        Meteor.setLoggingEnabled(true);
    }
	}
   ```
Implement MeteorCallBack on Activtiy  see Full Implementation here [Example](https://github.com/inovio/meteor-native-android-boilerplate/blob/master/MeteorAndroid/app/src/main/java/meteor/android/todo/TodoListActivity.java)
```
public class YourActivity extends AppCompatActivity implements MeteorCallback
{

@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        	//set Call back to this activity
        MeteorSingleton.getInstance().setCallback(this);
        
        //Subscribe to a Publication
          MeteorSingleton.getInstance().subscribe("publicLists");
        
        }

//UnSet callback
    @Override
    protected void onDestroy() {
        MeteorSingleton.getInstance().unsetCallback(this);
        super.onDestroy();
    }

//MeteorCallback
    @Override
    public void onConnect(boolean b) {

    }

    @Override
    public void onDisconnect() {

    }

    @Override
    public void onException(Exception e) {

    }

    @Override
    public void onDataAdded(String s, String s1, String s2) {
    
    //here you can make Java Object class, you may use GSON or Jackson

    }

    @Override
    public void onDataChanged(String s, String s1, String s2, String s3) {

    }

    @Override
    public void onDataRemoved(String s, String s1) {

    }
}

```
and you can login like this 
```
 MeteorSingleton.getInstance().loginWithEmail(email, password, new ResultListener() {
            
            @Override
            public void onSuccess(String result) {

                Log.d("mLogin", "Successfully logged in");


            }

            @Override
            public void onError(String error, String reason, String details) 		{
              

            }
        });

```
* And Register
```
 MeteorSingleton.getInstance().registerAndLogin(name, email, password, new ResultListener() {
            @Override
            public void onSuccess(String s) {
               
            }

            @Override
            public void onError(String s, String s1, String s2) {
            }
        });


```
* Unregistering a callback

```
     
     //UnSet callback
    @Override
    protected void onDestroy() {
        MeteorSingleton.getInstance().unsetCallback(this);
        super.onDestroy();
    }

     
```
  

 * Inserting data into a collection

   ```
   Map<String, Object> values = new HashMap<String, Object>();
   values.put("_id", "my-id");
   values.put("some-key", "some-value");

   mMeteor.insert("my-collection", values);
   // or
   // mMeteor.insert("my-collection", values, new ResultListener() { });
   ```

 * Updating data in a collection

   ```
   Map<String, Object> query = new HashMap<String, Object>();
   query.put("_id", "my-id");

   Map<String, Object> values = new HashMap<String, Object>();
   values.put("some-key", "some-value");

   mMeteor.update("my-collection", query, values);
   // or
   // mMeteor.update("my-collection", query, values, options);
   // or
   // mMeteor.update("my-collection", query, values, options, new ResultListener() { });
   ```

 * Deleting data from a collection

   ```
   mMeteor.remove("my-collection", "my-id");
   // or
   // mMeteor.remove("my-collection", "my-id", new ResultListener() { });
   ```

 * Subscribing to data from the server

   ```
   String subscriptionId = mMeteor.subscribe("my-subscription");
   // or
   // String subscriptionId = mMeteor.subscribe("my-subscription", new Object[] { arg1, arg2 });
   // or
   // String subscriptionId = mMeteor.subscribe("my-subscription", new Object[] { arg1, arg2 }, new SubscribeListener() { });
   ```

 * Unsubscribing from a previously established subscription

   ```
   mMeteor.unsubscribe(subscriptionId);
   // or
   // mMeteor.unsubscribe(subscriptionId, new UnsubscribeListener() { });
   ```

 * Calling a custom method defined on the server

   ```
   mMeteor.call("myMethod");
   // or
   // mMeteor.call("myMethod", new Object[] { arg1, arg2 });
   // or
   // mMeteor.call("myMethod", new ResultListener() { });
   // or
   // mMeteor.call("myMethod", new Object[] { arg1, arg2 }, new ResultListener() { });
   ```

 * Disconnect from the server

   `mMeteor.disconnect()`

 * Creating a new account (requires `accounts-password` package)

   ```
   mMeteor.registerAndLogin("john", "john.doe@example.com", "password", new ResultListener() { });
   // or
   // mMeteor.registerAndLogin("john", "john.doe@example.com", "password", profile, new ResultListener() { });
   ```

 * Signing in with an existing username (requires `accounts-password` package)

   `mMeteor.loginWithUsername("john", "password", new ResultListener() { });`

 * Signing in with an existing email address (requires `accounts-password` package)

   `mMeteor.loginWithEmail("john.doe@example.com", "password", new ResultListener() { });`

 * Check if the client is currently logged in (requires `accounts-password` package)

   `mMeteor.isLoggedIn()`

 * Get the client's user ID (if currently logged in) (requires `accounts-password` package)

   `mMeteor.getUserId()`

 * Logging out (requires `accounts-password` package)

   ```
   mMeteor.logout();
   // or
   // mMeteor.logout(new ResultListener() { });
   ```

 * Checking whether the client is connected

   `mMeteor.isConnected()`

 * Manually attempt to re-connect (if necessary)

   `mMeteor.reconnect()`
   

* Handleing dynamic data insertion , change etc, when an activity is subcribed to a publish then data keeps coming and changes happens , we need to handle it properly for that purpose we may use [EventBus](https://github.com/greenrobot/EventBus) see here Full Implementation of [Event Bus Example](https://github.com/inovio/meteor-native-android-boilerplate/blob/master/MeteorAndroid/app/src/main/java/meteor/android/todo/TodoListActivity.java)


* To create Java object class you may want create your own or use any automation system like [JsonSchema2Pojo](jsonschema2pojo.org) or [JsonExport](https://github.com/Ahmed-Ali/JSONExport)


	


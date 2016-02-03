package meteor.android.todo;

import android.app.Application;

import im.delight.android.ddp.Meteor;
import im.delight.android.ddp.MeteorSingleton;

/**
 * Created by Sadmansamee on 2/2/16.
 */
public class App extends Application {

    //This is your url set ws if not secured yet
    String url = "wss://meteor-ios-todos.meteor.com/websocket";

    @Override
    public void onCreate() {
        super.onCreate();


        //we create a Singleton of Meteor instance and use it throughout the app life cycle
        MeteorSingleton.createInstance(this, url);

        //Enable when Debugging and better remove it when deploying
        Meteor.setLoggingEnabled(true);
    }
}

package meteor.android.todo;

import android.app.Application;

import im.delight.android.ddp.Meteor;
import im.delight.android.ddp.MeteorSingleton;

/**
 * Created by Sadmansamee on 2/2/16.
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();


        MeteorSingleton.createInstance(this, "");

        //Enable when Debugging and better remove it when deploying
        Meteor.setLoggingEnabled(true);
    }
}

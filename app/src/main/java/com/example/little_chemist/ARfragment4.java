package com.example.little_chemist;
import com.google.ar.core.Config;
import com.google.ar.core.Session;
import com.google.ar.sceneform.ux.ArFragment;

public class ARfragment4 extends ArFragment{
    @Override
    protected Config getSessionConfiguration(Session session) {
        Config config = new Config(session);
        config.setUpdateMode(Config.UpdateMode.LATEST_CAMERA_IMAGE);
        LabLesson3 activity = (LabLesson3) getActivity();
        //activity.loadDB(session, config);
        this.getArSceneView().setupSession(session);

        return config;
    }
}

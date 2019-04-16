/*===============================================================================
Copyright (c) 2018 PTC Inc. All Rights Reserved.

Copyright (c) 2012-2015 Qualcomm Connected Experiences, Inc. All Rights Reserved.

Vuforia is a trademark of PTC Inc., registered in the United States and other 
countries.
===============================================================================*/


package com.vuforia.engine.CoreSamples.ui.ActivityList;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.vuforia.engine.CoreSamples.R;


/**
 * This class will display the Vuforia Engine features list and start the selected activity
  */

public class ActivityLauncher extends ListActivity
{
    
    private final String[] mActivities = { "View in AR" };
    
    
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
            R.layout.activities_list_text_view, mActivities);
        
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        setContentView(R.layout.activities_list);
        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id)
    {
        if (position == 0) {
            String mClassToLaunch;
            String mClassToLaunchPackage;

            mClassToLaunchPackage = getPackageName();
            mClassToLaunch = mClassToLaunchPackage + ".app.ImageTargets.ImageTargets";

            Intent i = new Intent();
            i.setClassName(mClassToLaunchPackage, mClassToLaunch);
            startActivity(i);
        } else {
            Log.e("ActivityLauncher", "Invalid activity");

        }
    }
}

package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
Button button;
CheckBox able ,vis;
TextView getTextView;
ListView listView;
BluetoothAdapter bluetoothAdapter;
Set<BluetoothDevice> pared;


/*
TextView textView;
IntentFilter scanintentFilter=new IntentFilter(BluetoothAdapter.ACTION_SCAN_MODE_CHANGED);
BroadcastReceiver scanmodereceiver=new BroadcastReceiver() {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action=intent.getAction();
        if(action.equals(BluetoothAdapter.ACTION_SCAN_MODE_CHANGED))
        {
            int modevalue = intent.getIntExtra(BluetoothAdapter.EXTRA_SCAN_MODE,BluetoothAdapter.ERROR);
            if (modevalue==BluetoothAdapter.SCAN_MODE_CONNECTABLE){
                textView.setText("the driver is not discover");

            }else if (modevalue==BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE)
            {
                textView.setText("Driver is in discoverable mode");
            }else if (modevalue==BluetoothAdapter.SCAN_MODE_NONE)
            {
                textView.setText("the driver is not discover ///////////////////");

            }
            else {
                textView.setText("erooor");
            }
        }

    }
};




 */


    public String getlocalbluetoothname(){
        if (bluetoothAdapter==null){
            bluetoothAdapter=BluetoothAdapter.getDefaultAdapter();
        }
        String name=bluetoothAdapter.getName();
        if (name==null){
            name=bluetoothAdapter.getAddress();
        }
        return name;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  setContentView(R.layout.activity_main);

          setContentView(R.layout.sss);
getTextView=findViewById(R.id.namebt);
listView=findViewById(R.id.list);
able=findViewById(R.id.enable);
        vis=findViewById(R.id.vusuble);

getTextView.setText(getLocalClassName());
        bluetoothAdapter=BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapter==null){
            Toast.makeText(getApplicationContext(),"null",Toast.LENGTH_LONG).show();
            finish();
        }
        if (bluetoothAdapter.isEnabled()){
            able.setChecked(true);
        }
able.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (!isChecked){
            bluetoothAdapter.disable();
            Toast.makeText(getApplicationContext(),"turned of",Toast.LENGTH_LONG).show();


        }else {
            Intent intent=new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(intent,0);
            Toast.makeText(getApplicationContext(),"turned on",Toast.LENGTH_LONG).show();




        }

        vis.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    Intent get=new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
                    startActivityForResult(get,0);
                    Toast.makeText(getApplicationContext(),"visible for 2 min",Toast.LENGTH_LONG).show();



                }
            }
        });
        getTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pared=bluetoothAdapter.getBondedDevices();
                ArrayList list=new ArrayList();
                for (BluetoothDevice bt:pared){
                    list.add(bt.getName());

                }
                Toast.makeText(getApplicationContext(),"showed devises",Toast.LENGTH_LONG).show();
                ArrayAdapter adapter=new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1,list);
                listView.setAdapter(adapter);



            }
        });





    }
});






//        textView=findViewById(R.id.text);
  //      button=findViewById(R.id.btn);

    /*  button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent discoverableIntent=new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
                discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION,8);
                startActivity(discoverableIntent);


            }
        });
        registerReceiver(scanmodereceiver,scanintentFilter);

    }

     */
}
}